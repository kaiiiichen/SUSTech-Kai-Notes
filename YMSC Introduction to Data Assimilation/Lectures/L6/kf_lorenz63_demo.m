function ekf_lorenz63_demo()
% EKF for Lorenz-63 with full and partial observation cases.
% Time: [0, 20], RK4 integration, Gaussian process/obs noises.
% Plots x, y, z for truth, observations, and EKF estimates.

%% ----------------------- Problem setup -----------------------
rng(0);                          % reproducibility
sigma = 10; rho = 28; beta = 8/3;% Lorenz-63 parameters
f = @(x) lorenz63_rhs(x, sigma, rho, beta);
Jf = @(x) lorenz63_jac(x, sigma, rho, beta);

T  = 20;
dt = 0.05;                       % integration step
N  = round(T/dt);
t  = (0:N)*dt;

% "True" initial state and initial EKF guess
x0_true = [1; 1; 1];
x0_ekf  = x0_true + [ -1.0; 1.0; 2.0 ];

% Discrete-time process noise covariance (per step)
q = 3;                         % process noise std per component
Qd = (q^2) * eye(3);

% Observation noise levels
r_full = 5.0;                    % std for full obs (x,y,z)
r_part = 5.0;                    % std for partial obs (x,z)

% Integrator for the truth (RK4) + process noise
rk4 = @(x) rk4_step(f, x, dt);

%% ----------------------- Simulate "truth" -----------------------
x_true = zeros(3, N+1);
x_true(:,1) = x0_true;
for k = 1:N
    % propagate deterministic dynamics
    x_det = rk4(x_true(:,k));
    % add process noise (discrete-time model uncertainty)
    x_true(:,k+1) = x_det + chol(Qd,'lower')*randn(3,1);
end

%% ----------------------- Case A: Full observation ----------------
H_full = eye(3);
R_full = (r_full^2) * eye(3);

% Generate observations
y_full = zeros(3, N+1);
for k = 1:N+1
    y_full(:,k) = H_full*x_true(:,k) + chol(R_full,'lower')*randn(3,1);
end

% Run EKF
[x_est_full, ~] = run_ekf(x0_ekf, f, Jf, rk4, H_full, Qd, R_full, y_full, dt);

% Plot
figure('Name','EKF on Lorenz-63 (Full Observation)','Color','w');
comp_names = {'x','y','z'};
for i = 1:3
    subplot(3,1,i); hold on; box on;
    plot(t, x_true(i,:), 'k-', 'LineWidth', 3);
    plot(t, y_full(i,:), 'o', 'MarkerSize', 8, 'MarkerFaceColor',[1.0 .6 .6], 'MarkerEdgeColor','none');
    plot(t, x_est_full(i,:), 'b:', 'LineWidth', 3);
    ylabel(comp_names{i});
    if i==1, title('Full observation (x,y,z)'); end
    if i==3, xlabel('time'); end
    legend({'True','Observed','EKF'}, 'Location','best');
    set(gca, 'fontsize', 30);
end

%% ----------------------- Case B: Partial observation (x,z) ------
% % Observe only x; y and z are unobserved.
H_part = [0 0 1];
R_part = r_part^2;

% Generate partial observations
y_part = zeros(1, N+1);
for k = 1:N+1
    y_part(:,k) = H_part*x_true(:,k) + chol(R_part,'lower')*randn;
end

% % Observe only x and z; y is unobserved.
% H_part = [0 1 0; 0 0 1];
% R_part = diag([r_part^2, r_part^2]);
% 
% % Generate partial observations
% y_part = zeros(2, N+1);
% for k = 1:N+1
%     y_part(:,k) = H_part*x_true(:,k) + chol(R_part,'lower')*randn(2,1);
% end

% Run EKF with partial observations
[x_est_part, ~] = run_ekf(x0_ekf, f, Jf, rk4, H_part, Qd, R_part, y_part, dt);

% For plotting: expand obs to 3 rows with NaNs for unobserved y
y_part_plot = nan(3, N+1);
%y_part_plot(1,:) = y_part(1,:);     % observed x
y_part_plot(3,:) = y_part(1,:);     % observed z % comment out for only
%observe x

% Plot
figure('Name','EKF on Lorenz-63 (Partial Observation: x and z)','Color','w');
for i = 1:3
    subplot(3,1,i); hold on; box on;
    plot(t, x_true(i,:), 'k-', 'LineWidth', 3);
    if i~=2
        plot(t, y_part_plot(i,:), 'o', 'MarkerSize', 6, 'MarkerFaceColor',[.6 .6 .6], 'MarkerEdgeColor','none');
        lgd = {'True','Observed','EKF'};
    else
        % y is unobserved
        lgd = {'True','EKF'};
    end
    plot(t, x_est_part(i,:), 'b:', 'LineWidth', 3);
    ylabel(comp_names{i});
    if i==1, title('Partial observation (x,z), y unobserved'); end
    if i==3, xlabel('time'); end
    legend(lgd, 'Location','best');
    set(gca, 'fontsize', 30);
end

end % main function

%% ======================= Helper functions =======================
function x_next = rk4_step(f, x, dt)
% One step of classical RK4 for x' = f(x)
k1 = f(x);
k2 = f(x + 0.5*dt*k1);
k3 = f(x + 0.5*dt*k2);
k4 = f(x + dt*k3);
x_next = x + (dt/6)*(k1 + 2*k2 + 2*k3 + k4);
end

function [x_est_hist, P_hist] = run_ekf(x0, f, Jf, rk4, H, Qd, R, y_all, dt)
% Extended Kalman Filter for a discrete-time system obtained
% by time-discretizing a continuous nonlinear model x' = f(x).
%
% State propagation: x_{k+1} = Phi_k x_k + w_k (approximated nonlinearly)
% We propagate the mean with RK4 and the covariance with linearization.
%
% Inputs:
%   x0     : initial state estimate (3x1)
%   f, Jf  : dynamics and Jacobian (continuous-time)
%   rk4    : function handle: x_{k+1} = rk4(x_k)
%   H, R   : observation matrix and covariance
%   Qd     : discrete process noise covariance (per step)
%   y_all  : observations at each step (m x (N+1)), includes k=0
%   dt     : time step
%
% Outputs:
%   x_est_hist : 3 x (N+1) state estimates
%   P_hist     : 3 x 3 x (N+1) covariances

[~, Np1] = size(y_all);
N = Np1 - 1;
n = length(x0);

x_est = x0;
P     = 5^2 * eye(n);      % fairly uncertain initial covariance

x_est_hist = zeros(n, N+1);
P_hist     = zeros(n, n, N+1);
x_est_hist(:,1) = x_est;
P_hist(:,:,1)   = P;

I = eye(n);

for k = 1:N
    % -------- Predict --------
    % Mean by nonlinear integration
    x_pred = rk4(x_est);

    % Covariance by linearization: Phi ≈ I + J(x_est)*dt
    F    = Jf(x_est);
    Phi  = I + dt*F;       % first-order discretization of the flow Jacobian
    Pprd = Phi*P*Phi' + Qd;

    % -------- Update --------
    yk = y_all(:,k+1);
    S  = H*Pprd*H' + R;
    K  = (Pprd*H')/S;
    innov = yk - H*x_pred;

    x_est = x_pred + K*innov; % update
    P     = (I - K*H)*Pprd; % posterior var

    % store
    x_est_hist(:,k+1) = x_est;
    P_hist(:,:,k+1)   = P;
end
end

function dx = lorenz63_rhs(x, sigma, rho, beta)
% Lorenz-63 ODE: x' = f(x)
% x = [x; y; z]
dx = [ sigma*(x(2) - x(1));
       x(1)*(rho - x(3)) - x(2);
       x(1)*x(2) - beta*x(3) ];
end

function J = lorenz63_jac(x, sigma, rho, beta)
% Jacobian of Lorenz-63 RHS at state x
% f = [ sigma(y - x);
%       x(rho - z) - y;
%       xy - beta z ]
J = [ -sigma,    sigma,      0;
      (rho-x(3)),-1,        -x(1);
       x(2),      x(1),    -beta ];
end

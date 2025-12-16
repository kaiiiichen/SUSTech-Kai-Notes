function ENTAKF
% LORENZ63_FILTER_COMPARISON
% Compare EKF, EnKF, ETKF, and EAKF on the Lorenz–63 model.
%
% Outputs:
%  - Printed Table-style comparison of RMSE and pattern correlation (global)
%  - Trajectory plots (x,y,z as subplots) for EnKF / ETKF / EAKF for K in K_list
%  - Componentwise RMSE and pattern correlation vs K (3 figures: x, y, z)
%
% K = [2,3,5,10]

rng(1);  % For reproducibility

%% Parameters
params.sigma = 10;
params.rho   = 28;
params.beta  = 8/3;

dt      = 0.01;    % integration time step for truth and filters
T_final = 40.0;    % final time
dt_obs  = 0.2;    % observation interval

% Observation error covariance: doubled compared to 2*I
R_o = 4*eye(3);    % observation noise variance = 4 for each component

% Ensemble inflation
r_infl_enkf  = 0.04;
r_infl_sqrt  = 0.04;  % for ETKF & EAKF

% Spin-up: number of initial observation times to skip in skill metrics
spinup_obs = 50;

% Ensemble sizes to compare
K_list = [2, 3, 5, 10];

%% Generate truth and observations
u0_true = [1.5089; -1.5313; 25.4609];

[t, truth] = integrate_lorenz63(u0_true, dt, T_final, params);

% Observation indices (uniform)
stepObs    = round(dt_obs / dt);
obs_idx    = 1:stepObs:length(t);
n_obs      = numel(obs_idx);
t_obs      = t(obs_idx);
truth_obs  = truth(:, obs_idx);

% Generate noisy observations: v = u + epsilon, epsilon ~ N(0, R_o)
L_Ro   = chol(R_o, 'lower');
noise  = L_Ro * randn(3, n_obs);
y_obs  = truth_obs + noise;

%% Run EKF once (does not depend on K)
u0_ekf = truth_obs(:,1) + 0.5*randn(3,1);
R0_ekf = eye(3);  % initial covariance
[u_ekf] = run_EKF(u0_ekf, R0_ekf, y_obs, dt, dt_obs, params, R_o);

%% Containers for global results (Table-style)
methodNames = {};
K_values    = [];
RMSE_values = [];
PC_values   = [];

% EKF global skill
[rmse_ekf, pc_ekf] = compute_skill(truth_obs, u_ekf, spinup_obs);
methodNames{end+1} = 'EKF';
K_values(end+1)    = 0;           % indicate "no ensemble size"
RMSE_values(end+1) = rmse_ekf;
PC_values(end+1)   = pc_ekf;

%% Run ensemble methods for each K and store per-component skill
plotData = struct();  % for trajectories
skill    = struct();  % for per-component RMSE/PCC

for KK = K_list
    % Initial ensemble around the first observed truth
    X0 = truth_obs(:,1) + 1.0*randn(3,KK);
    
    % ----- EnKF -----
    [mean_enkf] = run_EnKF(X0, y_obs, dt, dt_obs, params, R_o, r_infl_enkf);
    [rmse_enkf, pc_enkf] = compute_skill(truth_obs, mean_enkf, spinup_obs);
    [rmse_vec_enkf, pc_vec_enkf] = compute_skill_components(truth_obs, mean_enkf, spinup_obs);
    methodNames{end+1} = 'EnKF';
    K_values(end+1)    = KK;
    RMSE_values(end+1) = rmse_enkf;
    PC_values(end+1)   = pc_enkf;
    skill(KK).EnKF.rmse = rmse_vec_enkf;
    skill(KK).EnKF.pc   = pc_vec_enkf;
    
    % ----- ETKF -----
    [mean_etkf] = run_ETKF(X0, y_obs, dt, dt_obs, params, R_o, r_infl_sqrt);
    [rmse_etkf, pc_etkf] = compute_skill(truth_obs, mean_etkf, spinup_obs);
    [rmse_vec_etkf, pc_vec_etkf] = compute_skill_components(truth_obs, mean_etkf, spinup_obs);
    methodNames{end+1} = 'ETKF';
    K_values(end+1)    = KK;
    RMSE_values(end+1) = rmse_etkf;
    PC_values(end+1)   = pc_etkf;
    skill(KK).ETKF.rmse = rmse_vec_etkf;
    skill(KK).ETKF.pc   = pc_vec_etkf;
    
    % ----- EAKF -----
    [mean_eakf] = run_EAKF(X0, y_obs, dt, dt_obs, params, R_o, r_infl_sqrt);
    [rmse_eakf, pc_eakf] = compute_skill(truth_obs, mean_eakf, spinup_obs);
    [rmse_vec_eakf, pc_vec_eakf] = compute_skill_components(truth_obs, mean_eakf, spinup_obs);
    methodNames{end+1} = 'EAKF';
    K_values(end+1)    = KK;
    RMSE_values(end+1) = rmse_eakf;
    PC_values(end+1)   = pc_eakf;
    skill(KK).EAKF.rmse = rmse_vec_eakf;
    skill(KK).EAKF.pc   = pc_vec_eakf;
    
    % Store means for trajectory plotting
    plotData(KK).mean_enkf = mean_enkf;
    plotData(KK).mean_etkf = mean_etkf;
    plotData(KK).mean_eakf = mean_eakf;
end

%% Printed comparison table (global RMSE/PCC)
fprintf('\n=============================================\n');
fprintf('   Method      K        RMSE        PCorr\n');
fprintf('=============================================\n');
for i = 1:numel(methodNames)
    fprintf('%-8s   %3d   %10.4f   %10.4f\n', ...
        methodNames{i}, K_values(i), RMSE_values(i), PC_values(i));
end
fprintf('=============================================\n\n');

%% Trajectory plots: x, y, z as subplots for each K
for KK = K_list
    figure;
    
    % x-component
    subplot(3,1,1); hold on;
    plot(t_obs, truth_obs(1,:), 'k-', 'LineWidth', 1.5);
    plot(t_obs, plotData(KK).mean_enkf(1,:), 'r--', 'LineWidth', 1.2);
    plot(t_obs, plotData(KK).mean_etkf(1,:), 'b-.', 'LineWidth', 1.2);
    plot(t_obs, plotData(KK).mean_eakf(1,:), 'g:', 'LineWidth', 1.2);
    ylabel('x(t)');
    title(sprintf('Trajectories for K = %d', KK));
    legend('Truth','EnKF','ETKF','EAKF','Location','Best');
    grid on;
    
    % y-component
    subplot(3,1,2); hold on;
    plot(t_obs, truth_obs(2,:), 'k-', 'LineWidth', 1.5);
    plot(t_obs, plotData(KK).mean_enkf(2,:), 'r--', 'LineWidth', 1.2);
    plot(t_obs, plotData(KK).mean_etkf(2,:), 'b-.', 'LineWidth', 1.2);
    plot(t_obs, plotData(KK).mean_eakf(2,:), 'g:', 'LineWidth', 1.2);
    ylabel('y(t)');
    grid on;
    
    % z-component
    subplot(3,1,3); hold on;
    plot(t_obs, truth_obs(3,:), 'k-', 'LineWidth', 1.5);
    plot(t_obs, plotData(KK).mean_enkf(3,:), 'r--', 'LineWidth', 1.2);
    plot(t_obs, plotData(KK).mean_etkf(3,:), 'b-.', 'LineWidth', 1.2);
    plot(t_obs, plotData(KK).mean_eakf(3,:), 'g:', 'LineWidth', 1.2);
    xlabel('time');
    ylabel('z(t)');
    grid on;
end

%% Componentwise RMSE and PCC vs K (3 figures)
methods_ens = {'EnKF','ETKF','EAKF'};
colors      = {'r','b','g'};
comp_names  = {'x','y','z'};

for comp = 1:3
    figure;
    
    % RMSE vs K for this component
    subplot(2,1,1); hold on;
    for m = 1:numel(methods_ens)
        method = methods_ens{m};
        rmse_byK = zeros(size(K_list));
        for iK = 1:numel(K_list)
            KK = K_list(iK);
            rmse_byK(iK) = skill(KK).(method).rmse(comp);
        end
        plot(K_list, rmse_byK, '-o', 'LineWidth', 1.5, 'Color', colors{m});
    end
    xlabel('Ensemble size K');
    ylabel(sprintf('RMSE(%s)', comp_names{comp}));
    title(sprintf('RMSE vs K for %s-component', comp_names{comp}));
    legend(methods_ens, 'Location', 'Best');
    grid on;
    
    % PCC vs K for this component
    subplot(2,1,2); hold on;
    for m = 1:numel(methods_ens)
        method = methods_ens{m};
        pc_byK = zeros(size(K_list));
        for iK = 1:numel(K_list)
            KK = K_list(iK);
            pc_byK(iK) = skill(KK).(method).pc(comp);
        end
        plot(K_list, pc_byK, '-o', 'LineWidth', 1.5, 'Color', colors{m});
    end
    xlabel('Ensemble size K');
    ylabel(sprintf('PCC(%s)', comp_names{comp}));
    title(sprintf('Pattern correlation vs K for %s-component', comp_names{comp}));
    legend(methods_ens, 'Location', 'Best');
    grid on;
end

end % main function


%% ===== Helper functions ===============================================

function [t, U] = integrate_lorenz63(u0, dt, T_final, params)
% Integrate Lorenz–63 with RK4
n_steps = round(T_final / dt);
t = (0:n_steps)*dt;
U = zeros(3, n_steps+1);
U(:,1) = u0;
u = u0;
for n = 1:n_steps
    u = lorenz63_step(u, dt, params);
    U(:,n+1) = u;
end
end


function u_next = lorenz63_step(u, dt, params)
% One RK4 step for Lorenz–63
k1 = lorenz63_rhs(u, params);
k2 = lorenz63_rhs(u + 0.5*dt*k1, params);
k3 = lorenz63_rhs(u + 0.5*dt*k2, params);
k4 = lorenz63_rhs(u + dt*k3, params);
u_next = u + (dt/6)*(k1 + 2*k2 + 2*k3 + k4);
end


function f = lorenz63_rhs(u, params)
x = u(1); y = u(2); z = u(3);
sigma = params.sigma;
rho   = params.rho;
beta  = params.beta;
f = [sigma*(y - x);
     x*(rho - z) - y;
     x*y - beta*z];
end


function J = lorenz63_jacobian(u, params)
% Jacobian of Lorenz–63 rhs
x = u(1); y = u(2); z = u(3);
sigma = params.sigma;
rho   = params.rho;
beta  = params.beta;
J = [ -sigma,   sigma,   0;
      rho - z,  -1,     -x;
      y,        x,      -beta ];
end


%% ================= EKF ================================================

function [u_ana] = run_EKF(u0, R0, y_obs, dt, dt_obs, params, R_o)
% Extended Kalman Filter with simple tangent approximation Fm

n_obs = size(y_obs,2);
u_ana = zeros(3, n_obs);
u_prev = u0;
R_prev = R0;

% initial analysis at first obs time: just use u0
u_ana(:,1) = u_prev;

n_sub = round(dt_obs/dt);
I3 = eye(3);

for m = 1:n_obs-1
    % Forecast mean and tangent map F ≈ product of (I + dt J)
    [u_fore, Fm] = ekf_forecast(u_prev, dt, n_sub, params);
    
    % Forecast covariance (no model noise)
    R_fore = Fm * R_prev * Fm.';
    
    % Analysis step with linear observation operator G = I
    G = I3;
    K = R_fore * G.' / (G * R_fore * G.' + R_o);
    
    y = y_obs(:,m+1);
    u_post = u_fore + K * (y - G*u_fore);
    R_post = (I3 - K*G) * R_fore;
    
    u_ana(:,m+1) = u_post;
    u_prev = u_post;
    R_prev = R_post;
end
end


function [u_fore, Fm] = ekf_forecast(u_a, dt, n_sub, params)
% Forecast mean and tangent map Fm via Euler linearization
N = numel(u_a);
Fm = eye(N);
u  = u_a;
for n = 1:n_sub
    J = lorenz63_jacobian(u, params);
    F_step = eye(N) + dt * J;  % tangent of Euler map
    Fm = F_step * Fm;
    u = lorenz63_step(u, dt, params);
end
u_fore = u;
end


%% ================= EnKF ===============================================

function [mean_ana] = run_EnKF(X0, y_obs, dt, dt_obs, params, R_o, r_infl)
% Stochastic EnKF with perturbed observations

[~, K] = size(X0);
n_obs  = size(y_obs,2);
mean_ana = zeros(3, n_obs);

X = X0;
mean_ana(:,1) = mean(X,2);

n_sub = round(dt_obs/dt);
L_Ro  = chol(R_o, 'lower');

for m = 1:n_obs-1
    % Forecast each ensemble member
    X_fore = forecast_ensemble(X, dt, n_sub, params);
    
    % Analysis step
    [X, u_bar] = enkf_analysis(X_fore, y_obs(:,m+1), R_o, L_Ro, r_infl);
    mean_ana(:,m+1) = u_bar;
end
end


function X_fore = forecast_ensemble(X_a, dt, n_sub, params)
[N,K] = size(X_a);
X_fore = zeros(N,K);
for k = 1:K
    u = X_a(:,k);
    for n = 1:n_sub
        u = lorenz63_step(u, dt, params);
    end
    X_fore(:,k) = u;
end
end


function [X_a, u_bar_a] = enkf_analysis(X_f, y_obs, R_o, L_Ro, r_infl)
% Stochastic EnKF analysis with inflation
[N,K] = size(X_f);

u_bar_f = mean(X_f,2);
A = X_f - u_bar_f;

% multiplicative inflation
if r_infl > 0
    A = sqrt(1 + r_infl) * A;
    X_f = u_bar_f + A;
end

% Observation operator H = I
Y_f = X_f;
v_bar_f = mean(Y_f,2);
V = Y_f - v_bar_f;

% Sample covariances
C = (A * V.') / (K - 1);       % cross-covariance (3x3)
S = (V * V.') / (K - 1) + R_o; % obs covariance (3x3)

Kmat = C / S;   % Kalman gain

% Perturbed observations
eps_obs = L_Ro * randn(N,K);
Y_obs_pert = y_obs + eps_obs;

% Update each ensemble member
X_a = zeros(N,K);
for k = 1:K
    X_a(:,k) = X_f(:,k) + Kmat * (Y_obs_pert(:,k) - Y_f(:,k));
end
u_bar_a = mean(X_a,2);
end


%% ================= ETKF ===============================================

function [mean_ana] = run_ETKF(X0, y_obs, dt, dt_obs, params, R_o, r_infl)
[~, K] = size(X0);
n_obs  = size(y_obs,2);
mean_ana = zeros(3, n_obs);

X = X0;
mean_ana(:,1) = mean(X,2);

n_sub = round(dt_obs/dt);

for m = 1:n_obs-1
    % Forecast
    X_fore = forecast_ensemble(X, dt, n_sub, params);
    
    % Analysis
    [X, u_bar] = etkf_analysis(X_fore, y_obs(:,m+1), R_o, r_infl);
    mean_ana(:,m+1) = u_bar;
end
end


function [X_a, u_bar_a] = etkf_analysis(X_f, y_obs, R_o, r_infl)
% Deterministic ETKF (ensemble transform Kalman filter)
[N,K] = size(X_f);

u_bar_f = mean(X_f,2);
A_f     = X_f - u_bar_f;

% Inflation
if r_infl > 0
    A_f = sqrt(1 + r_infl) * A_f;
end

% Observation operator H = I
Y_f     = X_f;
v_bar_f = mean(Y_f,2);
d       = y_obs - v_bar_f;  % innovation of mean

% R^{-1/2} via Cholesky
L = chol(R_o, 'lower');
R_m12 = inv(L);  % R^{-1/2}

% S = R^{-1/2} H A_f / sqrt(K-1)
S = (R_m12 * A_f) / sqrt(K-1);   % (3 x K)

% Matrix in ensemble space
M = eye(K) + S.' * S;            % (K x K)

% Eigen-decomposition for transform T = (I + S^T S)^{-1/2}
[E,D] = eig(M);
Ddiag = diag(D);
T = E * diag(1 ./ sqrt(Ddiag)) * E.';

% Mean update weights in ensemble space:
w = M \ (S.' * (R_m12 * d));   % (K x 1)

u_bar_a = u_bar_f + A_f * (w / sqrt(K-1));

% Anomaly update
A_a = A_f * T * sqrt(K-1);

X_a = u_bar_a + A_a;
end


%% ================= EAKF ===============================================

function [mean_ana] = run_EAKF(X0, y_obs, dt, dt_obs, params, R_o, r_infl)
[~, K] = size(X0);
n_obs  = size(y_obs,2);
mean_ana = zeros(3, n_obs);

X = X0;
mean_ana(:,1) = mean(X,2);

n_sub = round(dt_obs/dt);

for m = 1:n_obs-1
    % Forecast
    X_fore = forecast_ensemble(X, dt, n_sub, params);
    
    % Analysis via sequential 1D EAKF updates
    [X, u_bar] = eakf_analysis(X_fore, y_obs(:,m+1), R_o, r_infl);
    mean_ana(:,m+1) = u_bar;
end
end


function [X_a, u_bar_a] = eakf_analysis(X_f, y_obs, R_o, r_infl)
% Anderson-style EAKF with sequential 1D observation updates
[N,K] = size(X_f);
X = X_f;

% Observation noise variance (assume R_o = r_o * I)
r_o = R_o(1,1);

% Optional inflation before analysis
u_bar = mean(X,2);
A = X - u_bar;
if r_infl > 0
    A = sqrt(1 + r_infl) * A;
    X = u_bar + A;
end

for i = 1:N  % process each observed component sequentially (H = I)
    u_bar = mean(X,2);
    A = X - u_bar;
    
    yi_f   = X(i,:);                 % 1 x K
    ybar_f = mean(yi_f);
    dy     = yi_f - ybar_f;
    Pf     = (dy * dy.') / (K - 1);  % scalar variance
    
    if Pf < 1e-12
        continue;
    end
    
    % State-observation covariance
    cov_xy = (A * dy.') / (K - 1); % N x 1
    K_vec  = cov_xy / (Pf + r_o);  % N x 1 (Kalman gain vector)
    
    % Posterior mean in obs space
    yi_obs = y_obs(i);
    ybar_a = ybar_f + Pf/(Pf + r_o) * (yi_obs - ybar_f);
    
    % Anomaly scaling for 1D EnSRF
    alpha = sqrt(r_o / (Pf + r_o));  % shrink anomalies
    
    y_a = ybar_a + alpha * dy;       % updated obs ensemble
    
    % Update state ensemble
    X = X + K_vec * (y_a - yi_f);    % N x K add rank-1 update
end

u_bar_a = mean(X,2);
X_a = X;
end


%% ================= Skill metrics =====================================

function [rmse, pcorr] = compute_skill(truth_obs, est_obs, spinup)
% Global RMSE and pattern correlation (all components flattened)
if nargin < 3
    spinup = 0;
end

[~, n_obs] = size(truth_obs);
idx = (spinup+1):n_obs;
T = truth_obs(:, idx);
E = est_obs(:, idx);

T_flat = T(:);
E_flat = E(:);

T_flat = T_flat - mean(T_flat);
E_flat = E_flat - mean(E_flat);

rmse = sqrt(mean((E_flat - T_flat).^2));
pcorr = (T_flat' * E_flat) / (sqrt(T_flat' * T_flat) * sqrt(E_flat' * E_flat));
end


function [rmse_vec, pcorr_vec] = compute_skill_components(truth_obs, est_obs, spinup)
% Componentwise RMSE and pattern correlation for x, y, z separately
if nargin < 3
    spinup = 0;
end

[~, n_obs] = size(truth_obs);
idx = (spinup+1):n_obs;

rmse_vec   = zeros(3,1);
pcorr_vec  = zeros(3,1);

for comp = 1:3
    T = truth_obs(comp, idx);
    E = est_obs(comp, idx);
    
    Tz = T - mean(T);
    Ez = E - mean(E);
    
    rmse_vec(comp)  = sqrt(mean((Ez - Tz).^2));
    num             = (Tz * Ez.');
    den             = sqrt((Tz*Tz.')*(Ez*Ez.'));
    if den > 0
        pcorr_vec(comp) = num / den;
    else
        pcorr_vec(comp) = NaN;
    end
end
end

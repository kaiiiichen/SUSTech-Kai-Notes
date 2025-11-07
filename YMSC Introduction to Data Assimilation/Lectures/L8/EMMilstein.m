%=============================================================
% Euler–Maruyama vs Milstein: strong/weak convergence demo
% SDE: dX_t = a*X_t dt + b*X_t dW_t   (Geometric Brownian Motion)
% if B(X(t), t) is independent of X, additive noise; otherwise it's
% multiplicative noise
% Exact: X_T = X0 * exp( (a - 0.5*b^2)T + b*W_T )
%
% We compare strong error E|X_num(T)-X_exact(T)|
% and weak error |E[phi(X_num(T))] - E[phi(X_exact(T))]|
% for phi(x)=x (first moment).
%=============================================================
clear; close all; clc; rng(7);

% Problem setup
a   = 2.0;              % drift coefficient
b   = 0.1;              % diffusion coefficient
X0  = 1.0;              % initial condition
T   = 1.0;              % final time

% Grid refinements (choose N so that T/dt is integer)
Ns  = [25, 50, 100, 200, 400, 800, 1600];
dts = T ./ Ns;

M   = 20000;             % ensemble size (increase for smoother curves)

% Storage for errors
err_strong_EM  = zeros(numel(Ns),1);
err_strong_Mil = zeros(numel(Ns),1);
err_weak_EM    = zeros(numel(Ns),1);
err_weak_Mil   = zeros(numel(Ns),1);

% Loop over step sizes
for k = 1:numel(Ns)
    N  = Ns(k);
    dt = dts(k);

    % Initialize
    Xem  = X0*ones(M,1);     % Euler–Maruyama
    Xml  = X0*ones(M,1);     % Milstein
    Wsum = zeros(M,1);       % accumulate Brownian motion: W_T = sum dW

    % Time stepping with SHARED dW for fair strong-error comparison
    for n = 1:N
        dW   = sqrt(dt) * randn(M,1);
        Wsum = Wsum + dW;

        % Euler–Maruyama:  X_{n+1} = X_n + a X_n dt + b X_n dW
        Xem  = Xem + a*Xem*dt + b*Xem.*dW; % 

        % Milstein (scalar noise): X_{n+1} = X_n + a X_n dt + b X_n dW
        %                           + 0.5 b^2 X_n ( (dW)^2 - dt )
        Xml  = Xml + a*Xml*dt + b*Xml.*dW + 0.5*(b^2)*Xml.*(dW.^2 - dt);
    end

    % Exact solution using same W_T (pathwise coupling)
    Xexact = X0 * exp( (a - 0.5*b^2)*T + b*Wsum );

    % Strong error (mean absolute error at T)
    err_strong_EM(k)  = mean( abs(Xem  - Xexact) );
    err_strong_Mil(k) = mean( abs(Xml  - Xexact) );

    % Weak error for phi(x)=x (first moment)
    phi_num_EM  = mean(Xem);
    phi_num_Mil = mean(Xml);
    phi_ex      = mean(Xexact);
    err_weak_EM(k)    = abs( phi_num_EM  - phi_ex );
    err_weak_Mil(k)   = abs( phi_num_Mil - phi_ex );
end

%===========================
% Plot: strong/weak errors
%===========================
figure('Color','w','Position',[80 80 1150 470]);

% Strong error
subplot(1,2,1);
loglog(dts, err_strong_EM,  'o-', 'LineWidth',1.5, 'MarkerSize',6); hold on;
loglog(dts, err_strong_Mil, 's-', 'LineWidth',1.5, 'MarkerSize',6);

% Reference slopes: dt^{1/2} and dt^{1}
C1 = err_strong_EM(1) / sqrt(dts(1));
C2 = err_strong_Mil(1) / dts(1);
loglog(dts, C1*sqrt(dts), 'k--', 'LineWidth',1.2);
loglog(dts, C2*dts,       'k-.', 'LineWidth',1.2);

grid on; box on;
xlabel('\Delta t'); ylabel('Strong error  E|X_{num}(T)-X_{exact}(T)|');
title('Strong Convergence');
legend('Euler–Maruyama (theory: 1/2)', 'Milstein (theory: 1)', ...
       'ref: \Delta t^{1/2}', 'ref: \Delta t^{1}', 'Location','southeast');

% Weak error
subplot(1,2,2);
loglog(dts, err_weak_EM,  'o-', 'LineWidth',1.5, 'MarkerSize',6); hold on;
loglog(dts, err_weak_Mil, 's-', 'LineWidth',1.5, 'MarkerSize',6);

% Reference slope: dt^{1} (weak order 1 for both here)
Cw = err_weak_EM(1) / dts(1);
loglog(dts, Cw*dts, 'k--', 'LineWidth',1.2);

grid on; box on;
xlabel('\Delta t'); ylabel('| E[\phi(X_{num}(T))] - E[\phi(X_{exact}(T))] |, \ \phi(x)=x');
title('Weak Convergence');
legend('Euler–Maruyama (theory: 1)', 'Milstein (theory: 1)', ...
       'ref: \Delta t^{1}', 'Location','southeast');

%===========================
% Optional: single-path demo
%===========================
% Show how EM vs Milstein track one sample path at the same dW
N_demo  = 200; dt_demo = T/N_demo;
Xem     = X0; Xml = X0; Wsum = 0; Xex = X0;
tgrid   = linspace(0,T,N_demo+1);
path_em = zeros(1,N_demo+1); path_ml = path_em; path_ex = path_em;
path_em(1) = X0; path_ml(1) = X0; path_ex(1) = X0;

for n = 1:N_demo
    dW    = sqrt(dt_demo)*randn;
    Wsum  = Wsum + dW;
    % EM
    Xem   = Xem + a*Xem*dt_demo + b*Xem*dW;
    % Milstein
    Xml   = Xml + a*Xml*dt_demo + b*Xml*dW + 0.5*(b^2)*Xml*((dW^2) - dt_demo);
    % Exact along same Brownian path at tn
    tcur  = n*dt_demo;
    Xex   = X0*exp((a - 0.5*b^2)*tcur + b*Wsum);

    path_em(n+1) = Xem;
    path_ml(n+1) = Xml;
    path_ex(n+1) = Xex;
end

figure('Color','w','Position',[120 120 900 420]);
plot(tgrid, path_ex, 'k-', 'LineWidth',1.8); hold on;
plot(tgrid, path_em, 'b--', 'LineWidth',1.4);
plot(tgrid, path_ml, 'r-.', 'LineWidth',1.4);
grid on; box on;
xlabel('t'); ylabel('X(t)');
title('One Sample Path: Exact vs Euler–Maruyama vs Milstein');
legend('Exact','Euler–Maruyama','Milstein','Location','best');

%===========================
% Console summary
%===========================
fprintf('=== Summary (T=%.2f, M=%d) ===\n', T, M);
fprintf('dt          StrongErr(EM)   StrongErr(Mil)   WeakErr(EM)     WeakErr(Mil)\n');
for k = 1:numel(Ns)
    fprintf('%-10.5f  %12.4e  %14.4e  %14.4e  %14.4e\n', ...
        dts(k), err_strong_EM(k), err_strong_Mil(k), err_weak_EM(k), err_weak_Mil(k));
end

% Notes:
% * Strong rates: EM ~ O(dt^{1/2}), Milstein ~ O(dt^1).
% * Weak rate here: both ~ O(dt^1) for phi(x)=x.
% * We coupled both schemes with the SAME dW increments to make
%   the strong-error comparison fair (pathwise).

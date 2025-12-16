function ParticleFilter
% LORENZ63_PF_RHF
% Basic bootstrap particle filter (PF) and Rank Histogram Particle Filter (RHF)
% for the Lorenz–63 system, with trajectories and RMSE / PCC comparison.
%
% Model:
%   Truth: deterministic Lorenz–63
%   Filters: Lorenz–63 + small additive model noise
%   Observations: full (x,y,z) with Gaussian noise N(0, R_o)
%
% This is for Lecture 12 particle-filter demos.

rng(3);  % For reproducibility

%% Parameters
params.sigma = 10;
params.rho   = 28;
params.beta  = 8/3;

dt      = 0.01;    % integration time step for truth and filters
T_final = 40.0;    % final time
dt_obs  = 0.2;    % observation interval

% Observation noise covariance (R_o = r_o * I)
r_o = 4.0;
R_o = r_o * eye(3);

% Model noise strength (applied in particle forecast)
q_model = 2.0;   % standard deviation of model noise per sqrt(dt)

% Number of particles (same for PF and RHF)
K = 100;

% Spin-up (number of initial observation times to ignore in skill)
spinup_obs = 50;

%% Generate truth and observations
u0_true = [1.5089; -1.5313; 25.4609];

[t, truth] = integrate_lorenz63(u0_true, dt, T_final, params);

% Observation indices
stepObs    = round(dt_obs / dt);
obs_idx    = 1:stepObs:length(t);
n_obs      = numel(obs_idx);
t_obs      = t(obs_idx);
truth_obs  = truth(:, obs_idx);

% Generate noisy observations: v = u + epsilon, epsilon ~ N(0, R_o)
L_Ro   = chol(R_o, 'lower');
noise  = L_Ro * randn(3, n_obs);
y_obs  = truth_obs + noise;

%% Initial ensembles for PF and RHF (same initial ensemble)
X0 = truth_obs(:,1) + 1.0 * randn(3, K);  % initial prior at first obs time

% Containers for posterior means
mean_pf  = zeros(3, n_obs);
mean_rhf = zeros(3, n_obs);

% Initialization (no assimilation at first obs)
X_pf  = X0;
X_rhf = X0;
mean_pf(:,1)  = mean(X_pf, 2);
mean_rhf(:,1) = mean(X_rhf, 2);

n_sub = round(dt_obs / dt);  % number of model steps between observations

%% Main filtering loop over observation times
for m = 1:n_obs-1
    % ===== Forecast step (PF) =====
    X_pf_fore = forecast_ensemble_pf(X_pf, dt, n_sub, params, q_model);
    
    % ===== Analysis step (PF) - bootstrap particle filter =====
    X_pf = pf_analysis_bootstrap(X_pf_fore, y_obs(:,m+1), r_o);
    mean_pf(:,m+1) = mean(X_pf, 2);
    
    % ===== Forecast step (RHF) =====
    X_rhf_fore = forecast_ensemble_pf(X_rhf, dt, n_sub, params, q_model);
    
    % ===== Analysis step (RHF) - rank histogram particle filter (1D marginals) =====
    X_rhf = rhf_analysis(X_rhf_fore, y_obs(:,m+1), r_o);
    mean_rhf(:,m+1) = mean(X_rhf, 2);
end

%% Compute skill: global and component-wise
[rmse_pf,  pcc_pf]  = compute_skill(truth_obs, mean_pf,  spinup_obs);
[rmse_rhf, pcc_rhf] = compute_skill(truth_obs, mean_rhf, spinup_obs);

[rmse_pf_comp,  pcc_pf_comp]  = compute_skill_components(truth_obs, mean_pf,  spinup_obs);
[rmse_rhf_comp, pcc_rhf_comp] = compute_skill_components(truth_obs, mean_rhf, spinup_obs);

fprintf('\n=== Global skill (all components, after spin-up) ===\n');
fprintf('PF :  RMSE = %8.4f,   PCC = %8.4f\n', rmse_pf,  pcc_pf);
fprintf('RHF:  RMSE = %8.4f,   PCC = %8.4f\n\n', rmse_rhf, pcc_rhf);

fprintf('=== Component-wise RMSE / PCC (x, y, z) ===\n');
comp_chars = ['x','y','z'];
for j = 1:3
    fprintf('Component %c: PF  RMSE=%6.3f PCC=%6.3f   |   RHF RMSE=%6.3f PCC=%6.3f\n', ...
        comp_chars(j), rmse_pf_comp(j), pcc_pf_comp(j), rmse_rhf_comp(j), pcc_rhf_comp(j));
end
fprintf('\n');

%% Plot trajectories (truth vs PF vs RHF), x,y,z as subplots
figure;
for j = 1:3
    subplot(3,1,j); hold on;
    plot(t_obs, truth_obs(j,:), 'k-', 'LineWidth', 1.5);
    plot(t_obs, mean_pf(j,:),  'r--', 'LineWidth', 1.2);
    plot(t_obs, mean_rhf(j,:), 'b-.', 'LineWidth', 1.2);
    if j == 1
        ylabel('x(t)');
        title(sprintf('Lorenz-63 trajectories: Truth vs PF vs RHF (K = %d)', K));
    elseif j == 2
        ylabel('y(t)');
    else
        ylabel('z(t)');
        xlabel('time');
    end
    legend('Truth','PF','RHF','Location','Best');
    grid on;
end

%% Plot RMSE and PCC per component as bar charts
comp_labels = {'x','y','z'};

% RMSE
figure;
for j = 1:3
    subplot(3,1,j);
    bar([rmse_pf_comp(j), rmse_rhf_comp(j)]);
    set(gca, 'XTick', 1:2, 'XTickLabel', {'PF','RHF'});
    ylabel(sprintf('RMSE(%s)', comp_labels{j}));
    if j == 1
        title('Component-wise RMSE: PF vs RHF');
    end
    grid on;
end

% PCC
figure;
for j = 1:3
    subplot(3,1,j);
    bar([pcc_pf_comp(j), pcc_rhf_comp(j)]);
    set(gca, 'XTick', 1:2, 'XTickLabel', {'PF','RHF'});
    ylabel(sprintf('PCC(%s)', comp_labels{j}));
    if j == 1
        title('Component-wise pattern correlation: PF vs RHF');
    end
    ylim([-0.1,1]);
    grid on;
end

end % main function



%% ======== Lorenz–63 model integration =================================

function [t, U] = integrate_lorenz63(u0, dt, T_final, params)
% Integrate deterministic Lorenz–63 with RK4
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



%% ======== Forecast with model noise (for PF and RHF) ===================

function X_fore = forecast_ensemble_pf(X_a, dt, n_sub, params, q_model)
% Forecast ensemble with Lorenz–63 + additive Gaussian model noise
[N,K] = size(X_a);
X_fore = zeros(N,K);
for k = 1:K
    u = X_a(:,k);
    for n = 1:n_sub
        u = lorenz63_step(u, dt, params);
        u = u + q_model * sqrt(dt) * randn(N,1);  % additive model noise
    end
    X_fore(:,k) = u;
end
end



%% ======== Bootstrap particle filter analysis ===========================

function X_a = pf_analysis_bootstrap(X_f, y_obs, r_o)
% Basic SIR (bootstrap) particle filter analysis for full observation H = I
[~,K] = size(X_f);
w = zeros(K,1);

% Compute importance weights (up to a constant factor)
for k = 1:K
    res = y_obs - X_f(:,k);     % innovation
    w(k) = exp(-0.5 * (res' * res) / r_o);
end
wsum = sum(w);
if wsum <= 0
    w(:) = 1/K;
else
    w = w / (wsum + eps);
end

% Systematic resampling
idx = systematic_resampling(w);

% Resampled ensemble, equal weights
X_a = X_f(:, idx);
end

function idx = systematic_resampling(w)
K = numel(w);
idx = zeros(1,K);
edges = cumsum(w(:)');
u0 = rand / K;
j = 1;
for k = 1:K
    u = u0 + (k-1)/K;
    while u > edges(j) && j < K
        j = j + 1;
    end
    idx(k) = j;
end
end



%% ======== Rank Histogram Particle Filter analysis ======================

function X_a = rhf_analysis(X_f, y_obs, r_o)
% Rank histogram particle filter (RHF-like) analysis
% Applied component-wise with full observation (H = I).
[N,K] = size(X_f);
X_a = zeros(N,K);

for j = 1:N
    x_prior = X_f(j,:);          % 1 x K
    y_j     = y_obs(j);
    x_new   = rhf_update_scalar(x_prior, y_j, r_o);
    X_a(j,:)= x_new;
end
end

function x_new = rhf_update_scalar(x_prior, y_obs, r_o)
% One-dimensional RHF-like update for a single coordinate.
% x_prior: 1 x K (prior samples)
% y_obs  : scalar observation
% r_o    : observation noise variance (H=1, v = u + noise)

x_prior = x_prior(:)';  % ensure row vector
K = numel(x_prior);

% Sort prior
[x_sorted, idx_sort] = sort(x_prior);

% Construct extended endpoints (simple linear extrapolation)
x_ext = zeros(1, K+2);
x_ext(2:K+1) = x_sorted;
x_ext(1)     = 2*x_sorted(1) - x_sorted(2);
x_ext(K+2)   = 2*x_sorted(K) - x_sorted(K-1);

% Likelihood (up to constant)
lik = @(u) exp(-0.5 * ((y_obs - u).^2) / r_o);

% Compute unnormalized posterior mass for each interval [x_ext(k), x_ext(k+1)]
m = zeros(1, K+1);
for k = 1:(K+1)
    uL = x_ext(k);
    uR = x_ext(k+1);
    width = uR - uL;
    if width <= 0
        m(k) = 0;
    else
        Lk = 0.5 * (lik(uL) + lik(uR));  % average likelihood in interval
        m(k) = max(Lk * width, 1e-16);   % approximate mass; include width
    end
end

msum = sum(m);
if msum <= 0
    % If numerically degenerate, just return prior
    x_new = x_prior;
    return;
end

% Normalize to get posterior mass per interval
M = m / msum;

% Densities in each interval (piecewise-uniform posterior)
dens = zeros(1, K+1);
for k = 1:(K+1)
    width = x_ext(k+1) - x_ext(k);
    if width <= 0
        dens(k) = 0;
    else
        dens(k) = M(k) / width;
    end
end

% Target posterior quantiles at probabilities i/(K+1), i=1..K
new_x_sorted = zeros(1,K);
cdf_prev = 0;
k = 1;

for i = 1:K
    target = i / (K+1);
    % Find interval containing this target
    while (k <= K+1) && (target > cdf_prev + M(k) - 1e-14)
        cdf_prev = cdf_prev + M(k);
        k = k + 1;
    end
    if k > K+1
        k = K+1;
    end
    width = x_ext(k+1) - x_ext(k);
    if dens(k) <= 0 || width <= 0
        % Degenerate interval: place particle at center
        x_here = 0.5 * (x_ext(k) + x_ext(k+1));
    else
        % Invert linear CDF within interval:
        % F(x) = cdf_prev + dens(k)*(x - x_ext(k))
        x_here = x_ext(k) + (target - cdf_prev) / dens(k);
        % Clamp to interval
        x_here = max(min(x_here, x_ext(k+1)), x_ext(k));
    end
    new_x_sorted(i) = x_here;
end

% Undo sorting to assign new positions to original particle labels
x_new = zeros(size(x_prior));
x_new(idx_sort) = new_x_sorted;
end



%% ======== Skill metrics ===============================================

function [rmse, pcc] = compute_skill(truth_obs, est_obs, spinup)
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
pcc  = (T_flat' * E_flat) / (sqrt(T_flat' * T_flat) * sqrt(E_flat' * E_flat));
end

function [rmse_vec, pcc_vec] = compute_skill_components(truth_obs, est_obs, spinup)
% Component-wise RMSE and pattern correlation for x, y, z separately
if nargin < 3
    spinup = 0;
end

[~, n_obs] = size(truth_obs);
idx = (spinup+1):n_obs;

rmse_vec  = zeros(3,1);
pcc_vec   = zeros(3,1);

for j = 1:3
    T = truth_obs(j, idx);
    E = est_obs(j, idx);
    Tz = T - mean(T);
    Ez = E - mean(E);
    rmse_vec(j) = sqrt(mean((Ez - Tz).^2));
    num = Tz * Ez';
    den = sqrt((Tz*Tz') * (Ez*Ez'));
    if den > 0
        pcc_vec(j) = num / den;
    else
        pcc_vec(j) = NaN;
    end
end
end

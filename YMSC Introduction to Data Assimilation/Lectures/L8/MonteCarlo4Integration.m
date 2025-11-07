% ===============================================================
% Monte Carlo estimation of I = ∫₀¹ x² dx = 1/3
% ===============================================================
% Methods:
% (1) Summation (direct Monte Carlo integration)
%     I ≈ mean(U.^2),  U ~ Unif(0,1)
% (2) Geometric / hit-or-miss:
%     I ≈ P(V <= U.^2), (U,V) ~ Unif([0,1]²)
%
% This code also produces:
%   (a) plots of convergence trajectories for both methods
%   (b) a log–log error plot showing N^{-1/2} reference rate
%
% ===============================================================

clear; clc; close all;
rng(1);                                  % reproducibility

I_exact = 1/3;
Ns = unique(round(logspace(2,6,30)));    % sample sizes (10^2 ... 10^6)
nK = numel(Ns);

% Preallocation
est_sum = zeros(nK,1);
est_geo = zeros(nK,1);
err_sum = zeros(nK,1);
err_geo = zeros(nK,1);

% ===============================================================
% Main Monte Carlo loop
% ===============================================================
for k = 1:nK
    N = Ns(k);

    % ---- Method (1): Summation ----
    U = rand(N,1);
    est_sum(k) = mean(U.^2);

    % ---- Method (2): Geometric / Hit-or-miss ----
    U = rand(N,1);
    V = rand(N,1);
    est_geo(k) = mean(V <= U.^2);

    % Errors
    err_sum(k) = abs(est_sum(k) - I_exact);
    err_geo(k) = abs(est_geo(k) - I_exact);
end

% ===============================================================
% Plot (1): Convergence of both estimators
% ===============================================================
figure('Color','w','Position',[80 80 900 400]);
subplot(1,2,1)
plot(Ns, est_sum, '-o', 'LineWidth',1.5, 'MarkerSize',5); hold on;
yline(I_exact,'k--','LineWidth',1.2);
xlabel('Number of samples N'); ylabel('Estimate of I');
title('Summation Monte Carlo');
grid on;

subplot(1,2,2)
plot(Ns, est_geo, '-s', 'LineWidth',1.5, 'MarkerSize',5); hold on;
yline(I_exact,'k--','LineWidth',1.2);
xlabel('Number of samples N'); ylabel('Estimate of I');
title('Geometric Monte Carlo (Hit-or-Miss)');
grid on;

% ===============================================================
% Plot (2): Error vs N (log–log)
% ===============================================================
figure('Color','w','Position',[100 100 900 500]);
loglog(Ns, err_sum, '-o', 'LineWidth',1.5, 'MarkerSize',5); hold on;
loglog(Ns, err_geo, '-s', 'LineWidth',1.5, 'MarkerSize',5);

% Reference slope N^{-1/2}
refC = max([err_sum(1), err_geo(1)]) * sqrt(Ns(1));
loglog(Ns, refC ./ sqrt(Ns), 'k--', 'LineWidth',1.5);

grid on; box on;
xlabel('Number of samples N');
ylabel('Absolute error  |I_N - 1/3|');
title('Monte Carlo Convergence Rate (Error vs N)');
legend('Summation MC','Geometric MC','Reference N^{-1/2}','Location','southwest');

% ===============================================================
% Summary output
% ===============================================================
fprintf('Exact integral: I = 1/3 = %.10f\n\n', I_exact);
fprintf('    N         est_sum         err_sum        est_geo         err_geo\n');
for k = 1:nK
    fprintf('%8d   %12.8f   %12.3e   %12.8f   %12.3e\n', ...
        Ns(k), est_sum(k), err_sum(k), est_geo(k), err_geo(k));
end

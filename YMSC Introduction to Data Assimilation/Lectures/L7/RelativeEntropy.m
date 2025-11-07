% Figure 2.3-style demo: RMSE and Corr can be similar while Relative Entropy differs
% This script creates two forecast cases with (nearly) identical RMSE and correlation
% against the same “truth”, but with very different *distributions* — evidenced by KL divergence.
%
% Case A: unbiased but *wrong variance* (too spread).
% Case B: *biased mean* but variance matched.
%
% References (Gaussian 1D KL):
%   D_KL(p||q) = 0.5 * ( ( (mu_p-mu_q)^2 / var_q ) + (var_p/var_q) - 1 - log(var_p/var_q) )

clear; close all; clc; rng(7);

%% 1) Generate a “truth” time series
N  = 4000;                  % sample length (large to stabilize statistics)
phi = 0.9;                  % AR(1) coefficient to introduce temporal structure
eta = randn(N,1);           % driving white noise
x   = zeros(N,1);           % truth
for k=2:N, x(k) = phi*x(k-1) + eta(k); end

% Standardize truth to mean 0, variance 1 (so "p = N(0,1)" in 1D)
x = (x - mean(x)) / std(x,1);  % population std (divide by N)

%% 2) Construct TWO forecasts with same target Corr and RMSE, but different (mu, var)
r0   = 0.90;              % target correlation Corr(x, y) ~ r0
R0   = 1.00;              % target RMSE ~ R0
e    = randn(N,1);        % independent noise ~ N(0,1)

% Helper: Given a and r0, choose b so that Corr(x, a*x + b*e + c) = r0 (independent x,e).
% Corr = a / sqrt(a^2 + b^2)  =>  b^2 = a^2 * (1 - r0^2)/r0^2 = a^2 * k
k    = (1 - r0^2)/(r0^2);

% ---------- Case A: unbiased (cA=0), pick 'aA' so that RMSE = R0 exactly ----------
% RMSE^2 = (a-1)^2 + b^2 + c^2 = (a-1)^2 + a^2*k + 0
% Solve (1+k)a^2 - 2a = R0^2 - 1  +  ???  (work directly): (a-1)^2 + k a^2 = R0^2
%   => a^2 - 2a + 1 + k a^2 = R0^2
%   => (1+k)a^2 - 2a + (1 - R0^2) = 0
Aq = (1+k); Bq = -2; Cq = (1 - R0^2);
aA = (2)/(1+k);  % positive root since Cq=0 when R0=1; reduces to aA = 2/(1+k)
bA = sqrt(aA^2 * k);
cA = 0;

yA = aA*x + bA*e + cA;

% ---------- Case B: biased (cB ~= 0), pick 'aB' arbitrarily then set bB by Corr and cB by RMSE ----------
aB = 0.90;                         % choose a smaller slope
bB = sqrt(aB^2 * k);               % enforce Corr = r0
% RMSE^2 = (a-1)^2 + b^2 + c^2  =>  c = +/- sqrt(R0^2 - (a-1)^2 - b^2)
RMSE2_needed = R0^2 - (aB-1)^2 - bB^2;
if RMSE2_needed <= 0
    error('Chosen (aB,bB) make target RMSE infeasible. Pick a different aB or r0/R0.');
end
cB = sqrt(RMSE2_needed);           % pick positive bias (you may flip sign to -cB)
yB = aB*x + bB*e + cB;

%% 3) Compute metrics (empirical)
RMSE  = @(u,v) sqrt(mean((u-v).^2));
Corr  = @(u,v) corr(u,v);  % Pearson

rmseA = RMSE(x,yA);
rmseB = RMSE(x,yB);
corrA = Corr(x,yA);
corrB = Corr(x,yB);

% 1D Gaussian KL (truth p=N(mu_p,var_p), forecast q=N(mu_q,var_q))
KL_1D = @(mu_p,var_p,mu_q,var_q) 0.5 * ( ((mu_p-mu_q).^2)./var_q + var_p./var_q - 1 - log(var_p./var_q) );

mu_x  = mean(x);    var_x = var(x,1);   % population variance
mu_A  = mean(yA);   var_A = var(yA,1);
mu_B  = mean(yB);   var_B = var(yB,1);

KL_A  = KL_1D(mu_x,var_x, mu_A,var_A);
KL_B  = KL_1D(mu_x,var_x, mu_B,var_B);

%% 4) Build the figure: time series, scatter, and histograms to visualize differences
T = 600;  % show first T points for time-series panels
edges = linspace(-6,6,60);

figure('Color','w','Position',[80 80 1200 800]);

% --- Panel (1,1): Time series (Case A)
subplot(2,3,1);
plot(1:T, x(1:T), 'LineWidth',1.0); hold on;
plot(1:T, yA(1:T), 'LineWidth',1.0);
grid on; box on;
title(sprintf('Case A: Time Series (first %d pts)', T));
legend('Truth x','Forecast y_A','Location','best');
xlabel('t'); ylabel('value');

% --- Panel (1,2): Time series (Case B)
subplot(2,3,2);
plot(1:T, x(1:T), 'LineWidth',1.0); hold on;
plot(1:T, yB(1:T), 'LineWidth',1.0);
grid on; box on;
title(sprintf('Case B: Time Series (first %d pts)', T));
legend('Truth x','Forecast y_B','Location','best');
xlabel('t'); ylabel('value');

% --- Panel (1,3): Summary metrics table-like text
subplot(2,3,3); axis off;
text(0.0,0.95,'SUMMARY METRICS','FontWeight','bold','FontSize',12);
text(0.0,0.85,sprintf('Target Corr = %.2f, Target RMSE = %.2f', r0, R0),'FontSize',11);

text(0.0,0.70,'Case A (unbiased, wrong variance):','FontWeight','bold','FontSize',11);
text(0.0,0.63,sprintf('Corr(x,y_A)  = %.3f', corrA),'FontSize',11);
text(0.0,0.56,sprintf('RMSE(x,y_A)  = %.3f', rmseA),'FontSize',11);
text(0.0,0.49,sprintf('KL(p||q_A)   = %.3f', KL_A),'FontSize',11);
text(0.0,0.42,sprintf('mean(y_A)=%.3f, var(y_A)=%.3f', mu_A, var_A),'FontSize',11);

text(0.0,0.27,'Case B (biased mean, matched variance):','FontWeight','bold','FontSize',11);
text(0.0,0.20,sprintf('Corr(x,y_B)  = %.3f', corrB),'FontSize',11);
text(0.0,0.13,sprintf('RMSE(x,y_B)  = %.3f', rmseB),'FontSize',11);
text(0.0,0.06,sprintf('KL(p||q_B)   = %.3f', KL_B),'FontSize',11);
text(0.0,-0.01,sprintf('mean(y_B)=%.3f, var(y_B)=%.3f', mu_B, var_B),'FontSize',11);

% --- Panel (2,1): Scatter (Case A)
subplot(2,3,4);
plot(x, yA, '.', 'MarkerSize',4); hold on; grid on; box on;
lsline; % least-squares line
title('Case A: Scatter (y_A vs x)');
xlabel('Truth x'); ylabel('Forecast y_A');
axis tight;

% --- Panel (2,2): Scatter (Case B)
subplot(2,3,5);
plot(x, yB, '.', 'MarkerSize',4); hold on; grid on; box on;
lsline;
title('Case B: Scatter (y_B vs x)');
xlabel('Truth x'); ylabel('Forecast y_B');
axis tight;

% --- Panel (2,3): Histograms / PDFs
subplot(2,3,6); hold on; grid on; box on;
% Empirical histograms (normalized)
histogram(x,   edges, 'Normalization','pdf', 'DisplayStyle','stairs', 'LineWidth',1.5);
histogram(yA,  edges, 'Normalization','pdf', 'DisplayStyle','stairs', 'LineWidth',1.5);
histogram(yB,  edges, 'Normalization','pdf', 'DisplayStyle','stairs', 'LineWidth',1.5);
% Overlay Gaussian fits for visual guidance
xx = linspace(min(edges), max(edges), 400);
plot(xx, normpdf(xx, mu_x, sqrt(var_x)),  'k-', 'LineWidth',1.2);   % truth N(0,1)
plot(xx, normpdf(xx, mu_A, sqrt(var_A)),  'r-', 'LineWidth',1.2);   % forecast A Gaussian
plot(xx, normpdf(xx, mu_B, sqrt(var_B)),  'b-', 'LineWidth',1.2);   % forecast B Gaussian
title('Empirical PDFs and Gaussian fits');
xlabel('value'); ylabel('pdf');
legend('x (hist)','y_A (hist)','y_B (hist)', ...
       'N(0,1)','N(\mu_A,\sigma_A^2)','N(\mu_B,\sigma_B^2)', ...
       'Location','best');

sgtitle({'RMSE and Corr can be similar,','but Relative Entropy (KL) reveals distributional differences'}, 'FontWeight','bold');

% --- Print final console summary
fprintf('--- Summary ---\n');
fprintf('Case A: corr=%.3f, rmse=%.3f, KL(p||q_A)=%.3f, mean=%.3f, var=%.3f\n', corrA, rmseA, KL_A, mu_A, var_A);
fprintf('Case B: corr=%.3f, rmse=%.3f, KL(p||q_B)=%.3f, mean=%.3f, var=%.3f\n', corrB, rmseB, KL_B, mu_B, var_B);

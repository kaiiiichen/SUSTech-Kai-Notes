function lorenz63_sensitivity_demo()
% Lorenz-63: compare original vs perturbed initial condition
% Plots x(t), y(t), z(t) in 3 subplots.
% Original trajectory (red), perturbed trajectory (blue).

%% Parameters
sigma = 10; rho = 28; beta = 8/3;  % standard Lorenz-63
T = 40;                             % time horizon
x0 = [5; 0; 25];                     % original initial condition
eps0 = 1e-6;                        % tiny perturbation
x0p = x0 + [eps0; 0; 0];            % perturbed initial condition

%% Integrate both trajectories on the same time grid
opts = odeset('RelTol',1e-10,'AbsTol',1e-12,'MaxStep',0.01);
[t,  X] = ode45(@(t,x) lorenz63_rhs(x, sigma, rho, beta), [0 T],  x0,  opts);
[tp, Y] = ode45(@(t,x) lorenz63_rhs(x, sigma, rho, beta), [0 T],  x0p, opts);

% Ensure same time grid by interpolating Y onto t if needed
if ~isequal(t, tp)
    Y = interp1(tp, Y, t, 'pchip');
end

%% Plot x, y, z in subplots (original = red; perturbed = blue)
vars = {'x','y','z'};
figure('Color','w','Name','Lorenz-63: Original (red) vs Perturbed (blue)');
for i = 1:3
    subplot(3,1,i); hold on; grid on; box on;
    plot(t, X(:,i), 'r-', 'LineWidth', 2);              % original (red)
    plot(t, Y(:,i), 'b:', 'LineWidth', 2);              % perturbed (blue)
    ylabel(vars{i});
    if i==1
        title(sprintf('Lorenz-63: original vs perturbed (\\epsilon = %.1e on x)', eps0));
    end
    if i==3, xlabel('time'); end
    legend({'Original','Perturbed'}, 'Location','best');
    set(gca,'fontsize',24)
end

end

%% -------- Lorenz-63 dynamics --------
function dx = lorenz63_rhs(x, sigma, rho, beta)
dx = [ sigma*(x(2) - x(1));
       x(1)*(rho - x(3)) - x(2);
       x(1)*x(2) - beta*x(3) ];
end

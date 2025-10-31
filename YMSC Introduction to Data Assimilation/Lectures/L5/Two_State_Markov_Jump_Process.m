% Simulation two-state Markov jump process
% The two states are named as 'st' and 'un'

rng(1); % fix random number seeds to reproduce results 
N = 250000; % total length of the simulation points
dt = 0.005; % numerical integration time step
X = zeros(1,N); % the simulated time series
u = zeros(1,N); % state variable 
e = zeros(1,N); % energy 
omega = 0.5; sigma = 0.2;

mu = 0.1; % transition probability from 'un' to 'st'
nu = 0.1; % transition probability from 'st' to 'un'
X_st = 2.27; % state value at the 'st' state
X_un = -0.04; % value at the 'un' state

X(1) = X_st; % initial value

% simulate the time series
for i = 2:N
    rd = rand; % generate a random number to determine if the transition occurs
    if X(i-1) == X_un % current: 'un' state
        if rd < mu * dt 
            X(i) = X_st; % transition to 'st' state
        else
            X(i) = X_un; % remain as 'un'
        end
    else % current: 'st' state
        if rd < nu * dt 
            X(i) = X_un; % transition to 'un' state
        else
            X(i) = X_st; % remain as 'st'
        end
    end
    
    gamma = X(i);
    u(i) = u(i-1) + ( (-gamma + 1i * omega) * u(i-1))*dt + sigma * sqrt(dt) * (randn + 1i*randn)/sqrt(2);
    e(i) = (real(u(i)))^2 + (imag(u(i)))^2;
end
%% plot the results
figure
subplot(4,1,1)
plot(dt:dt:N*dt, real(u), '-b', 'linewidth',2)
subplot(4,1,2)
plot(dt:dt:N*dt, imag(u), '-g', 'linewidth',2)
subplot(4,1,3)
plot(dt:dt:N*dt, X, 'b', 'linewidth',2)
subplot(4,1,4)
plot(dt:dt:N*dt, e, 'r', 'linewidth',2)
ylim([X_un-0.1,X_st+0.1])
xlim([0,N*dt])
xlabel('t')
ylabel('States')
box on
set(gca,'fontsize',12)
title('Simulated time series')


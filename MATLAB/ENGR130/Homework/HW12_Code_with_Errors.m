% ENGR 130 HW 12
% Orange chucking code with errors

clc; clear; close all

rng('shuffle');

num_orange = 150; % number of oranges

% velocity limits
a = 20;
b = 30;
c = 25;
d = 45;

% the calculations in the two lines below contain no errors - promise!
x_vel = a + (a+b)*rand([num_orange,1]); % m/s; constant horizontal velocity
y_vel = c + (c+d)*rand([num_orange,1]); % m/s; initial vertical velocity

made_it = 0; % initialize counter for number of oranges that hit the pile

figure()
for n = 1 : num_orange
    % call the user-defined function
    made_it = made_it + orange_flight(x_vel(n), y_vel(n));
end

hold off

fprintf('%i oranges will land in the compost pile.\n', made_it)

function [compost] = orange_flight(vy, vx)

% This function takes the initial velocity of an orange, calculates its
    % final x location, and plots the orange's flight path
% Format of Call: orange_flight(x_vel(n), y_vel(n))
% Input: Horizontal velocity & initial vertical velocity for the specific
    % orange
% Output: logical (1 if the orange landed in the compost pile, 0 if it
    % didn't)

% constants:
g = -9.81; % m/s^2
dt = 0.25; % m/s    
compost_dist = 500; % m; distance to the compost pile
m = 200; % g; mass of orange
xi = 0; % m; inital horizontal distance
yi = 50; % m; initial height
t = 0; % s; time vector
x = xi; % x position vector
y = yi; % y position vector

idx = 1; % counter
compost = 0; % counter for oranges that make it in

while y(idx) >= 0
    % while the orange is in flight 
    idx = idx + 1;
    t(idx) = t(idx-1) + dt;
    x(idx) = xi + vx*t(idx);
    y(idx) = yi + vy(1)*t(idx) + 0.5*g*t(idx)^2;
end

if x(idx) == compost_dist
    % if the orange makes it into the compost pile
    compost = compost + 1;

else
    % if the orange misses the compost pile
    plot(y,x, '*')
    title('Flights of Oranges that Missed the Compost Pile')
    xlabel('horizontal distance (m)')
    ylabel(height (m))

end

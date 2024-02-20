% Trevor Swan
% ENGR 130 Homework 12
% November 19, 2023

%% Problem 2
% ENGR 130 HW 12 - Orange chucking code with errors
clc; 
clear; 
close all

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

hold on %*** a hold on command is needed to compliment the hold off in the
            % and to have all plotted on the same graph
for n = 1 : num_orange
    % call the user-defined function
    made_it = made_it + orange_flight(x_vel(n), y_vel(n)); 
end

hold off

fprintf('%i oranges will land in the compost pile.\n', made_it)

%% Problem 3 - corrected code
clc;
clear;
close all;

% Input Trial Results
height = [10000 20000 30000];
arrow_rods = [262 512 762];

% send a plot label through the function
title_arrow = "Time Taken for an Arrow to Fall";

% Determine the data for the arrow experiment data
[arrow_time, arrow_terminal] = experiment_data(height, arrow_rods, title_arrow);
%*** 'title_arrow' should not be in quotes as the variable contains string
%    data itself

%% Functions
% Problem 2 Function
function [compost] = orange_flight(vx, vy) %*** function comments want the
% x_vel to be first, this aligns with the future code as well as function call

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

if x(idx) >= compost_dist %*** per prompt's instructions, an orange makes
    % makes it into the pile if it goes anywhere past the minimum distance
    % if the orange makes it into the compost pile
    compost = compost + 1;

else
    % if the orange misses the compost pile
    plot(x,y, '*') %*** matlab plots in the order: (x,y)
    title('Flights of Oranges that Missed the Compost Pile')
    xlabel('horizontal distance (m)')
    ylabel('height (m)') %*** strings for plot features must be in quotes

end
end %*** function was missing an end statement

% Problem 3 Functions - correct
function [real_time, terminal_velocity] = experiment_data(height, rods, str)
% This function determines the time vector for height and the items
% Terminal
% Format of call: experiment_data(height, rods)
% Inputs: 3 values
%       height - vector of heights the subject was dropped from 
%       rods - number of rods outputted based on height data
%       str - string variable for graph name
% Outputs: 2 values
%       time - calculated time vector for the heights
%       terminal_velocity - terminal sped obtained by the object

% Each item pumped through a hopper takes 0.4 seconds
real_time = 0.4*rods;

% plot the time vs height data
plot(height, real_time)
ylabel('Time (s)') %*** plot labels must be in quotes 
xlabel('Height (m)')
title(str)

% use the terminal velocity function to calculate the terminal velocity
terminal_velocity = terminal_v(height, real_time); %*** height was mispelled 
% and the function could not be called properly
end

function terminal = terminal_v(height, time)
% This function determines the terminal velocity of a data set
% Format of call: terminal_v(height, time)
% Inputs: 2 vectors
%       height - numerator of slope/velocity unit
%       time - denominator of slope/velocity unit
%  ex. height in meters, time in seconds -> v = m/s
% Output: 1 value
%       terminal - terminal velocity of the object in question

% check to see if the vectors are equal length
[rowx, colx] = size(height);
[rowy, coly] = size(time);
if (rowx ~= rowy) || (colx ~= coly) %*** must be an 'or' statement as the 
    % function should not run if either dimension is mismathced
    error('Please Use Vectors of equal Dimensions.')
end

% Determine the line of best fit for the data
linear_coef = polyfit(time, height, 1); %*** polyfit goes in the order (x, y)
terminal = linear_coef(1);
end
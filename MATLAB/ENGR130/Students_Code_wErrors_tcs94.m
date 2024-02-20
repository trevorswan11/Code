%% Problem 3 Code with Errors
clc;
clear;
close all;

% Input Trial Results
height = [10000 20000 30000];
arrow_rods = [262 512 762];

% assign a plot label to send through the function call
title_arrow = "Time Taken for an Arrow to Fall";

% Determine the data for the arrow experiment data
[arrow_time, arrow_terminal] = experiment_data(height, arrow_rods, 'title_arrow');

%% Problem 3 Functions with Error
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

% plot the height vs the actual time data
plot(real_time, height)
xlabel(Time (s))
ylabel('Height (m)')
title(str)

% use the terminal velocity function to calculate the terminal velocity
terminal_velocity = terminal_v(heihgt, real_time);
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
if (rowx ~= rowy) && (colx ~= coly)
    error('Please Use Vectors of equal Dimensions.')
end

% Determine the line of best fit for the data
linear_coef = polyfit(height, time, 1);
terminal = linear_coef(1);
end
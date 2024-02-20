% Trevor Swan
% ENGR 130 Homework 7
% October 8, 2023
%% Problem 1 - Trajectory
% housekeeping
clear;
clc;
close all;

% initial conditions and constants
v_x = 200; % horizontal velocity
y_o = 1000; % initial height

%***per the prompts instructions, the package should be tracked until it is
%   just under 185m
y_f = 185;  % final height

g = 9.8;

% putting initial values into vectors and loop
t = 0;
idx = 0;
x(1) = 0;
y(1) = y_o;

% calculate x and y coordinates as payload drops
% until parachute is deployed

%***with the calculation, the height may never equal 185 exactly, so the
%   code should stop when the y value is less than or equal to 0
%***y must be evaulated logically, and stop when a logical 0 is found
while (y >= y_f)
    idx = idx + 1;
    t = t + .1;
    x(idx) = v_x*t;
    y(idx) = y_o - .5*g*t^2;
end

% check horizontal distance and display appropriate message
%***all display messages should include units where applicable and full sentences
if x(idx) > 2800
    %***a varibale must be specified at the end of this fprintf statement
    fprintf('The distance of %.2f m is in Zone 3.\n',x(idx));
    %***elseif should be used instead of a seperate if statement
elseif x(idx) > 1400
    %***fprintf must be used to print variables
    fprintf('The distance of %.2f m is in Zone 2.\n', x(idx));
else
    %***fprintf must be used for the new line command to work
    fprintf('The chute will deploy in Zone 1.\n');
end

%% Problem 2 - Turbines
% Turbines R Us
% Community Center Project Power Analysis
% 9/25/23

clear;
clc;
close all;

% Read in power data
%***the load command should be used to load .mat files, and the variable
%   needed should be specified in the command itself
load('watts.mat','P');

% Calculate average and standard deviation.
avg = sum(P)/length(P);

%***sqrt was spelled 'sqrrt'
stdev = sqrt(sum((P-avg).^2)/length(P));

% Determine minimum power needed
%***The turbine must be able to produce power one std above the avg
powMin = avg + stdev;

% Print values to screen
%***%.2f should be used to accurately display floating point values. %s is
%   for strings. Also put \n at the end of statements
fprintf('Average: %.2f watts\n',avg);
fprintf('Standard Deviation: %.2f watts\n',stdev);

%***comma was missing betweeen string statement and variable output
fprintf('Minimum Power Requirement: %.2f watts\n',powMin);

% Create wind speed vector
wind = [5:1:13];

% Declare variables
rho = 1.225;
%***variable 'A' should be renamed 'A1' to align with future code
A1 = 10;
A2 = 70;
A3 = 150;

% Determine power of each wind turbine given the wind speeds
Pow1 = (1/6)*rho*A1.*(wind.^3);
Pow2 = (1/6)*rho*A2.*(wind.^3);
Pow3 = (1/6)*rho*A3.*(wind.^3);

% Plot power vs. speed
%***problem requests power vs. wind speed, so wind speed should be the 'x'
%   axis and power should be the 'y' axis
plot(wind,Pow1,'r',wind,Pow2,'b-*',wind,Pow3,'g--');

% Label plot
title('Power Generation for Three Wind Turbines');
xlabel('Wind Speed (m/s)');
ylabel('Power (watts)');

%*** quotations missing around legend information to be printed
legend('A = 10 m^2', 'A = 70 m^2', 'A = 150 m^2');

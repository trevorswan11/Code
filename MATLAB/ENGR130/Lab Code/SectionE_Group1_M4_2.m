% Jonathan Callejas, Madhav Narayan, Angel Gonzalez, Trevor Swan
% ENGR 130 Module 4.2 Report
% Section E
% October 30, 2023

%% Lab 1 - General Ball Code
clc;
clear;
close all;

% Assign constants based on given information
velocity = 0;
mass = 1;
spring = 5000;
damp = 2;
position = 1;
total_time = 10;
dt = 0.0001;

% Use the Function fallin_ball to calculate the ball's data
[velocity_vector, height_vector, time_vector] = fallin_ball(velocity, mass, spring, damp, ...
    position, total_time, dt);

% plot the height vs time for this simulation and label 
ballplot(time_vector, height_vector, total_time)
title('Position of a Dropped Ball Over Time')

% calculate the minimum height the ball reaches
min_height = min(height_vector);
fprintf('The ball reaches a minimum height of %.2f m',min_height)

%% Lab 1 - Ball Without Damping
clearvars -except min_height

% Assign constants based on given information
velocity = 0;
mass = 1;
spring = 5000;
damp = 0;
position = 1;
dt = 0.0001;
total_time = 10;

% Use the Function fallin_ball to calculate the ball's data
[velocity_vector, height_vector, time_vector] = fallin_ball(velocity, mass, spring, damp, ...
    position, total_time, dt);

% calculate the minimum height the ball reaches
ballplot(time_vector, height_vector, total_time)
title('Position of a Dropped Ball Over Time Without Dampening')

% Graph and animate the balls path 
figure()
comet(time_vector, height_vector, 0.01)
xlabel('Time, seconds')
ylabel('Vertcal Position, meters')
title('Position of a Dropped Ball Over Time Without Dampening')

% provide graph limits to make the graph more appealing
xlim([0, total_time])
ylim([min(height_vector) - .03, max(height_vector) + .03])

%% Lab 1 - Dampening 2, Spring 8000
clearvars -except min_height;

% Assign constants based on given information
velocity = 0;
mass = 1;
spring = 8000;
damp = 2;
position = 1;
dt = 0.0001;
total_time = 10;

% Use the Function fallin_ball to calculate the ball's data
[velocity_vector, height_vector, time_vector] = fallin_ball(velocity, mass, spring, damp, ...
    position, total_time, dt);

% plot the height vs time for this simulation and label 
ballplot(time_vector, height_vector, total_time)
title('Position of a Dropped Ball Over Time With Increased K (k=8000)')

%% Lab 1 - Dampening 4, Spring 5000
clearvars -except min_height;

% Assign constants based on given information
velocity = 0;
mass = 1;
spring = 5000;
damp = 4;
position = 1;
dt = 0.0001;
total_time = 10;

% Use the Function fallin_ball to calculate the ball's data
[velocity_vector, height_vector, time_vector] = fallin_ball(velocity, mass, spring, damp, ...
    position, total_time, dt);

% calculate the minimum height the ball reaches
ballplot(time_vector, height_vector, total_time)
title('Position of a Dropped Ball Over Time With Greater(c=4) Damping')

%% Lab 1 - Dampening 10, Spring 5000
clearvars -except min_height;

% Assign constants based on given information
velocity = 0;
mass = 1;
spring = 5000;
damp = 10;
position = 1;
dt = 0.0001;
total_time = 10;

% Use the Function fallin_ball to calculate the ball's data
[velocity_vector, height_vector, time_vector] = fallin_ball(velocity, mass, spring, damp, ...
    position, total_time, dt);

% calculate the minimum height the ball reaches
ballplot(time_vector, height_vector, total_time)
title('Position of a Dropped Ball Over Time With Dramatic (c=10) Damping')

%% Data Functions for the Ball

function [a, b, c] = fallin_ball(velocity, mass, spring, damp, position, total_time, dt)
% This function calculates the velocity, height, and time vectors for a
% ball. It also stores the total time travled
% Format of call: fallin_ball(velocity, mass, spring, damp, position, total_time, dt)
% Input: 7 variables
%       velocity - initial velocity of the dropped ball
%       mass - mass of the dropped ball
%       spring - spring constant of the ball
%       damp - damping constant of the ball
%       position - starting position of the ball
%       total_time - desired time of experiment
%       dt - change of time per data reading
% Output: 3 Variables
%       a - vector containing velocity data over time
%       b - vector containing height data over time
%       c - vector containing time data in incriments of dt up to total

idx = 1;
grav = -9.81;
accel = grav;
time = 0;

while time <= total_time
    velocity = velocity + accel*dt;
    velocity_vector(idx) = velocity;
    position = position + velocity*dt;
    height_vector(idx) = position;
    % alter the acceleration constant based on the ball being in free fall
    % or not (i.e. the ball is below 0m)
    if position >= 0
        accel = -9.81;
    else
        % this accel equation is given
        accel = (-mass * grav - spring * position - damp * velocity) / mass;
    end
    % store the time in a vector and increase indicies
    time_vector(idx) = time;
    time = time + dt;
    idx = idx + 1;
end

% store the vectors from the function in the output variables
a = velocity_vector;
b = height_vector;
c = time_vector;
end

function ballplot(time_vector, height_vector, total_time)
% This function plots and labels a graph given time and height vectors
% Format of call: ballplot(time_vector, height_vector, total_time)
% Input: 3 variables
%       time_vector - experiment's time data
%       height_vector - ball's height data in terms of time_vector
%       total_time - desired time of experiment
% Output: height vs. time plot for the simulation

% plot the height of the ball vs. time
figure()
plot(time_vector, height_vector, 'k-');
xlabel('Time, seconds')
ylabel('Vertcal Position, meters')

% provide graph limits to make the graph more appealing
xlim([0, total_time])
ylim([min(height_vector) - .03, max(height_vector) + .03])
end
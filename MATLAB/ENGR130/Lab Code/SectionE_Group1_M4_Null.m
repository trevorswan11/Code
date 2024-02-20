% Jonathan Callejas, Madhav Narayan, Angel Gonzalez, Trevor Swan
% ENGR 130 Module 4.1 Report
% Section E
% October , 2023
%% Lab 1 - General 
clc;
clear;
close all;

% Assign general consants
idx = 1;
grav = -9.81;
% initial acceleration is the acceleration due to gravity b/c of freefall
accel = grav;
time = 0;

% Assign constants based on given information
velocity = 0;
mass = 1;
spring = 5000;
damp = 2;
position = 1;
dt = 0.0001;

% determine the position and velocity of the all for 10 seconds
while time <= 10
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

% plot the height of the ball vs. time
figure()
plot(time_vector, height_vector,'k-');
xlabel('Time, seconds')
ylabel('Vertcal Position, meters')
title('Position of a Dropped Ball Over Time')

% provide graph limits to make the graph more appealing
xlim([0,time])
ylim([min(height_vector)-.03,max(height_vector)+.03])

% calculate the minimum height the ball reaches
min_height = min(height_vector);

%% Lab 1 - Ball Without Damping
clearvars -except min_height

% Assign general consants
idx = 1;
grav = -9.81;
% initial acceleration is the acceleration due to gravity b/c of freefall
accel = grav;
time = 0;

% Assign constants based on given information
velocity = 0;
mass = 1;
spring = 5000;
damp = 0;
position = 1;
dt = 0.0001;

% determine the position and velocity of the all for 10 seconds
while time <= 10
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

% plot the height of the ball vs. time
figure()
plot(time_vector, height_vector,'k-');
xlabel('Time, seconds')
ylabel('Vertcal Position, meters')
title('Position of a Dropped Ball Over Time Without Dampening')

% provide graph limits to make the graph more appealing
xlim([0,time])
ylim([min(height_vector)-.03,max(height_vector)+.03])

% Graph and animate the balls path 
figure()
comet(time_vector, height_vector, 0.01)
xlabel('Time, seconds')
ylabel('Vertcal Position, meters')
title('Position of a Dropped Ball Over Time Without Dampening')

% provide graph limits to make the graph more appealing
xlim([0,time])
ylim([min(height_vector)-.03,max(height_vector)+.03])

%% Lab 1 - Dampening 2, Spring 8000
clearvars -except min_height;

% Assign general consants
idx = 1;
grav = -9.81;
% initial acceleration is the acceleration due to gravity b/c of freefall
accel = grav;
time = 0;

% Assign constants based on given information
velocity = 0;
mass = 1;
spring = 8000;
damp = 2;
position = 1;
dt = 0.0001;

% determine the position and velocity of the all for 10 seconds
while time <= 10
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

% plot the height of the ball vs. time
figure()
plot(time_vector, height_vector,'k-');
xlabel('Time, seconds')
ylabel('Vertcal Position, meters')
title('Position of a Dropped Ball Over Time With Increased K')

% provide graph limits to make the graph more appealing
xlim([0,time])
ylim([min(height_vector)-.03,max(height_vector)+.03])

%% Lab 1 - Dampening 4, Spring 5000
clearvars -except min_height;

% Assign general consants
idx = 1;
grav = -9.81;
% initial acceleration is the acceleration due to gravity b/c of freefall
accel = grav;
time = 0;

% Assign constants based on given information
velocity = 0;
mass = 1;
spring = 5000;
damp = 4;
position = 1;
dt = 0.0001;

% determine the position and velocity of the all for 10 seconds
while time <= 10
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

% plot the height of the ball vs. time
figure()
plot(time_vector, height_vector,'k-');
xlabel('Time, seconds')
ylabel('Vertcal Position, meters')
title('Position of a Dropped Ball Over Time With Greater Damping')

% provide graph limits to make the graph more appealing
xlim([0,time])
ylim([min(height_vector)-.03,max(height_vector)+.03])

%% Lab 1 - Dampening 10, Spring 5000
clearvars -except min_height;

% Assign general consants
idx = 1;
grav = -9.81;
% initial acceleration is the acceleration due to gravity b/c of freefall
accel = grav;
time = 0;

% Assign constants based on given information
velocity = 0;
mass = 1;
spring = 5000;
damp = 10;
position = 1;
dt = 0.0001;

% determine the position and velocity of the all for 10 seconds
while time <= 10
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

% plot the height of the ball vs. time
figure()
plot(time_vector, height_vector,'k-');
xlabel('Time, seconds')
ylabel('Vertcal Position, meters')
title('Position of a Dropped Ball Over Time With Dramatic Damping')

% provide graph limits to make the graph more appealing
xlim([0,time])
ylim([min(height_vector)-.03,max(height_vector)+.03])



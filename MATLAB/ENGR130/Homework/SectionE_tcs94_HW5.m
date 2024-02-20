% Trevor Swan
% ENGR 130 Homework 5
% September 24, 2023
%% Problem 1 - MATLAB Grader
clear;
clc;
close all;

% Input data
%  a vector of x values
x = [0.124,  0.745,  1.391,  2.195,  2.789,  3.638,  4.121,  5.001, 5.671,  6.479,  6.916,  8.127,  8.533,  9.06, 10.186];
% a vector of y values
y = [0.078,  0.439,  0.768,  0.982,  0.984,  0.755,  0.520, -0.001, -0.521, -0.821, -0.933, -0.923, -0.807, -0.503,  0.116];

% Calculate the polynomial coefficients here
% Complete the line below, replacing ... with the appropriate code
p = polyfit(x,y,4); 

% Create a vector of 100 equally-spaced values between 0.124 and 10.186,
% replacing ... with the appropriate code
x_fit = linspace(0.124,10.186,100);

% Complete the line below, replacing ... with the appropriate code
% evaluate x_fit using the polynomial you generated earlier
y_fit = polyval(p,x_fit);

% Plot the fitted data and the original data on the same axes
plot(x_fit, y_fit, '-', x, y, 'o');
legend('Fitted data', 'Original data');
title('Practice with Data Fitting');
xlabel('x (units unknown)');
ylabel('y (units unknown)');
%% Problem 2 - Case Rocket Team
clear;
clc;
close all;

% assign necessary constants
initial_velocity = 140;
g = 9.8;
t = 0;
count = 1;
height_vector = 0;

% Calculate the rockets velocity from launch until land
while height_vector >= 0
    velocity_vector(count) = initial_velocity - g*t;
    height_vector(count) = initial_velocity*t - (1/2)*g*t^2;
    t = t + 0.075;
    count = count + 1;
end

% create a vector for time
time_vector = 0:0.075:t;

% plot height vs. time
figure(1)
plot(time_vector,height_vector,'r')
xlabel('Time, seconds')
ylabel('Height, meters')
title('Height of a Launched Rocket Over Time, Ignoring Drag')

% plot velocity vs. time
figure(2)
plot(time_vector,velocity_vector,'m')
xlabel('Time, seconds')
ylabel('Velocity, meters/second')
title('Velocity of a Launched Rocket Over Time, Ignoring Drag')
%% Problem 3 - Package Plotting
clear;
clc;
close all;

% x = vt
% y = y0 - 1/2gt^2

% assign constants
initial_velocity = 200;
initial_altitude = 1000;
parachute_height = 185;
g = 9.8;

% calculate total travel time and create a vector in incriments of 0.1s
total_time = sqrt((parachute_height - initial_altitude)/(-.5*g));
time_vector = 0:0.1:(total_time);

% calculate the x and y coordinates for all t 
x_coordinate = initial_velocity*time_vector;
y_coordinate = initial_altitude - .5*g*time_vector.^2;

% plot the package's position
hold on
plot(x_coordinate,y_coordinate, 'c', 'LineWidth',6)
xlabel('Horizontal position of package, meters')
ylabel('Vertical position of package, meters')
title('Position of a Package Dropped from a Plane')

% pick 10 points on the graph and label/plot them
x_points = zeros(1,10);
y_points = zeros(1,10);
    % find a time scalar s.t. 10 evenly spaced points are chosen
scalar = floor(total_time);
for point = 1:10
    x_points(point) = x_coordinate(scalar*point);
    y_points(point) = y_coordinate(scalar*point);
end
plot(x_points,y_points,'k*','LineWidth', 6.5)

% create a linear fit line
linear_fit = polyfit(x_points,y_points,1);
linear_equa = polyval(linear_fit,x_points);
plot(x_points,linear_equa,'m--','LineWidth',1.5)

% create a quadratic fit line
quadratic_fit = polyfit(x_points,y_points,2);
quadratic_equa = polyval(quadratic_fit,x_points);
plot(x_points,quadratic_equa,'r-','LineWidth',1.75)

% add a legend
legend('Vertical vs. Horizontal Postion','10 Equally Spaced Points', ...
    'Linear fit line','Quadratic fit line')
hold off
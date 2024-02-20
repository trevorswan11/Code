%% Quick Lecture Notes/Housekeeping
clear;
clc;
close all;
%% Lecture Practice Problem 1
clear;
clc;
close all;

figure (1)
force = 0:0.982:5.892; % Newtons
displacement = [0.001 0.0432 0.079 0.134 0.169 0.2012 0.2592]; % meters
plot(displacement,force,'ro')
xlabel('Displacement, meters')
ylabel('Force, newtons')
title('Spring Constant Experiment Plot')

figure (2)
fit_coeff = polyfit(displacement, force, 1);
fit_force = polyval(fit_coeff, displacement);
plot(displacement, force, 'ro',displacement, fit_force, 'g--')
xlabel('Displacement, meters')
ylabel('Force, newtons')
title('Spring Constant Experiment Plot')
%% Regressions
clear;
clc;
close all;

force = 0:0.982:5.892;
displacement = [0.001 0.0432 0.079 0.134 0.169 0.2012 0.2592]; 
plot(displacement,force,'ro')
xlabel('Displacement, meters')
ylabel('Force, newtons')
title('Spring Constant Experiment Plot')

% any smooth curve can be represented by a polynomial and can be graphed by matlab
reg_eq_coef1 = polyfit(displacement, force, 1);
    % polyfit(x_data,y_data,order)
reg_eq_coef1(1)
%% Curve fitting example
clear;
clc;
close all;

x = 1:7;
y = [3 23 25 15 9 5 9];
plot(x, y, 'r*')
reg_eq_coef2 = polyfit(x, y, 3);

% polyval(polynomial coef vector, x)
reg_eq2 = polyval(reg_eq_coef2,x);
hold on

curve_fit_x = 0:0.1:8;
curve_fit_y = polyval(reg_eq_coef2,curve_fit_x);
plot(curve_fit_x,curve_fit_y)
%% Parctice Problem 3
clear;
clc;
close all; 

% x = vt
% y = y0 - 1/2gt^2

v_int = 200;
y_int = 1000;
tot_time = sqrt((185 - 1000)/(-.5*9.8));

t = 0:0.1:tot_time;
x_coord = 200*t;
y_coord = 1000 - 0.5*9.8*t.^2;

plot(x_coord,y_coord,'r')
xlabel('Horizontal Position, meters')
ylabel('Vertical Position, meters')
title('Positon of a package being dropped')

x_points = zeros(1,10);
y_points = zeros(1,10);

for point = 1:10
    x_points(point) = x_coord(12*point);
    y_points(point) = y_coord(12*point);
end

hold on
plot(x_points,y_points,'mo')
reg = polyfit(x_points,y_points,2);
equa = polyval(reg, x_points);
plot(x_points,equa,'y--')

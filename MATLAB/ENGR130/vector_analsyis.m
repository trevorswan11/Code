%% Vector Field - Volterra System
clc;
clear;
close all;

x = -5:0.5:5;
y = x;

hold on
for val = 1:length(x)
    x_current = x(val);
    comp1x = 0.4*x_current - 0.4*y;
    comp2x = -0.1*y + 0.2*x_current*y;

    y_current = y(val);
    comp1y = 0.4*x - 0.4*y_current;
    comp2y = -0.1*y_current + 0.2*x*y_current;

    comp1 = abs(comp1y - comp1x);
    comp2 = abs(comp2y - comp2x);
    quiver(x, y, comp1, comp2)
end

%housekeeping
clear;
clc;
close all;

%given
g = 9.81;
t = 1;

%ask user for initial height value
y_0 = ('What is the initial height?');

%determine vertical position values
y_vals = y_O - 0.5*g*t^2;

%calculate position values until it reaches the ground
while(y ~= 0)
    y_vals = y_O - 0.5*g*t^2;
    t = t + 1;
end

%plot the data
plot(y_vals,t,"*")
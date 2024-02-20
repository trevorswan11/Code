%% housekeeping comands
clear;
clc;
close all;

%% This will plot a vectors datat against its index
y_data = 1:2:100;
plot(y_data)
%% plotting two data sets against each other
x_data = 1:0.1:5;
y_data = sin(x_data);

% proper terminology for decribing a plot is "vert-axis vs horiz-axis"
plot(x_data, y_data)

% vectors must be the same length because matlab is actually making a
% scatterplot instead of a cts line. Using enequal lengths yields an error
%% plot(x_data,y_data,'line specifications')
plot(x_data,y_data,'ro')
%% Lectrure Prep Practice 1
t = 0:0.2:20;
x = cos(2*pi*t);
plot(t,x,'m--')
%% Labeling plots
% xlabel('label for x-axis')
% ylabel('label for y-axis')
% title('title of my plot')
% labels should include units and titles should be descriptive
x_data = 1:4;
y_data = [1 2 5.5 4];
plot(x_data,y_data,'ro')
xlabel('Experiment Number')
ylabel('Height, feet')
title('Maximum Height of Ball')
%% Multiple figure windows
% the default for a plot is figure one, so making a second figure replaces
% the original figure one. Use the 'figure()' command to get around this
t = 0:0.1:10;
y1 = sin(t);
y2 = sin(t)*2;
figure(1)
plot(t,y1)
figure(2)
plot(t,y2)
%% Multiple plots on one figure using syntax
t = 0:0.1:10;
y1 = sin(t);
y2 = cos(t);
plot(t,y1,t,y2)
%% Multiple plots on one figure using hold
t = 0:0.1:10;
y1 = sin(t);
y2 = 0.5*sin(t);
y3 = 2*sin(t);

hold on
plot(t,y1)
plot(t,y2)
plot(t,y3)
hold off
%% Using a legend
t = 0:0.1:10;
y1 = sin(t);
y2 = 0.5*sin(t);
y3 = 2*sin(t);

hold on
plot(t,y1)
plot(t,y2)
plot(t,y3)
hold off
legend('Data set 1','Data set 2','Data set 3')
%% Lectrure Prep Practice 1.5
close all;

t = 0:0.2:20;
x = cos(2*pi*t);
v = sin(2*pi*t);
figure(1)
plot(t,x,'m--')
xlabel('time, seconds')
ylabel('vertical distance, meters')
title('Oscillation of a frictionless spring Plot 1')
figure(2)
plot(t,v,'c')
xlabel('time in seconds')
ylabel('velocity, meters/sec')
title('Oscillation of a frictionless spring Plot 2')
%% Lecture Prep Practice 2
t = 0:0.2:20;
x = cos(2*pi*t);
v = sin(2*pi*t);
hold on
plot(t,x,'m--')
plot(t,v,'c')
hold off
title('Oscillation of a frictionless spring')
legend('vertical distance, meters','velocity, meters/sec')

%% housekeeping comands
clear;
clc;
close all;

%% Files
% Notes from video 1
% great for sharing data
% .mat files dont need to be of the same data type
% load('my_file.mat') puts full data in
% load('my_file.mat','var1','var2') put only specific variables in
% workspace
% load command, double-clicking on file icon in matlab, or double clicking
% on variable in the workspace
%% saving .mat files
% save('new_file.mat') saves all workspace variables to the listed value
% save('my_file.mat','var1','var2') saves only specific variables
clear;
clc;
close all;

figure(1)
altitudes = [1000 2000 3000 5000 10000];
temperatures = [288 281 269 256 223];
sex = 4;
save('alt_temps1','altitudes','temperatures')
clear;
load('alt_temps1.mat')
plot(altitudes,temperatures)

figure(2)
alt_temp = [altitudes;temperatures];
save('alt_temps2','alt_temp')
alts = alt_temp(1,:);
temps = alt_temp(2,:);
plot(alts,temps)
%% exporting .csv files
% great for exporting to things liek excel
% writematrix(a, 'my_file.csv')
    % only workss for matrices, there are other commands for other data
    % types
% vec = readmatrix('my_file.csv')
    % imports my_file.csv into the variable vec
    % can also double click on .csv files
    % after downloading csv files, you must move them to the matlab folder

% save the .csv file
clear
c = 0:5:500;
writematrix(c, 'sample_data.csv')

% load the csv file
clear
d = readmatrix('sample_data.csv');

% if youre going to double click, make sure you switch to 'numeric array'
% instead of table
    % matlab will create a new variable after you hit import

clear;
clc;
close all;

x = readmatrix('xdata.csv');
y = readmatrix('ydata.csv');
plot(x,y)

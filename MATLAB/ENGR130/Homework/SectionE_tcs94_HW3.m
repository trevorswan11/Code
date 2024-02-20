% Trevor Swan
% ENGR 130 Homework 3
% September 12, 2023
%% Problem 2 - MATLAB Grader
clear;
clc;
close all;

% assign the gravitational constant
g = 9.8;

% create vector with lenthgs of pendula
length = [0.062 0.248 0.993];

% create vector with respective stock of pendula
in_stock = [200 45 126];

% calculate period of each pendula length
period1 = 2*pi*sqrt(length(1)/g);
period2 = 2*pi*sqrt(length(2)/g);
period3 = 2*pi*sqrt(length(3)/g);
T=[period1 period2 period3];

% create vector with toal wire length for each different wire in warehouse
tot_length = length.*in_stock;

% total length of wire in warehouse using basic mutiplication/addition
total_1 = tot_length(1) + tot_length(2) + tot_length(3);

% total lenght of wire in warehouse using built in function
total_2 = sum(tot_length);

% check to see if differently computed totals are equal
check = total_2-total_1;
%% Problem 3 - DeBrogile Wavelength
clear;
clc;
close all;

% assign planck's constant
h=6.626e-34;

% create a vector that stores mass data for the devices
mass_data_kg=[1.673e-27 1.673e-27 1.674e-27 1.674e-27 6.645e-27];

% create a vector that stores the speed data for the devices
speed_data_mpers=[2.7e4 4.3e5 1.5e4 3.6e5 8.6e5];

% create a vector that contains all wavelengths in ONE LINE OF CODE
deBro_wav_nm=(h./(mass_data_kg.*speed_data_mpers))*1e9;

% convert the speeds of all particles to km/s
speed_data_kmpers=speed_data_mpers./1e3;

% finds and prints the minimum and average wavelength
smallest=min(deBro_wav_nm);
average=mean(deBro_wav_nm);

fprintf(['The minimum possible deBrogile wavelength is %.4f nm.\n\nFor this set, the' ...
    ' average deBrogile wavelength is %.4f nm.\n\n'],smallest,average)

% ask the user which device they would like to find out about
device=input('What is the number of the device that you would like to know about? ');

% print the chosen device's information to the screen
fprintf(['\nHere are the details of device %i:\n   Mass of particle: %.2E kg\n' ...
    '   Speed of particle: %.2f km/s\n   deBrogile wavelength: %.4f nm\n'] ...
    ,device,mass_data_kg(device),speed_data_kmpers(device),deBro_wav_nm(device));
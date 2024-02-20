% Trevor Swan, Jennifer Baek, Nissa Robinson, Karthik Rajasekar
% ENGR 130 Module 2.1 Report
% Section E
% September 24, 2023

%% Lab 1
clear;
clc;
close all;

% Part 2 - Arduino setup, turning off potential light
a = arduino();
writeDigitalPin(a, 'D13', 0)

% Part 3 - LED prompt, in seconds
time_period = input('How many seconds would you like to wait between blinks? ');
    % Blinking process, on and off every desired seconds
for count = 1:1:4
    writeDigitalPin(a, 'D8', 1)
    pause(time_period)
    writeDigitalPin(a, 'D8', 0)
    pause(time_period)
end

% Part 4 - Sensor Test, to ensure wiring is correct
voltage_1 = readVoltage(a,'A0');

    % Sensor minimum, with a piece of paper blocking the sensor 
voltage_2 = readVoltage(a,'A0');
%% Lab 2
clear;
clc;
close all;
a = arduino();

% Part 1 - Construct the Arduino button circuit and test
readDigitalPin(a,'D7');

% Part 2 - Write a script to collect voltage data from the sensor
voltage_vector = [];

for count = 1:100
    if readDigitalPin(a,'D7') == 1
        break
    end
    voltage_vector(count) = readVoltage(a,'A0');
    pause(1)
end

% Part 3 - Convert the voltage data to NTU
ntu_vector = (-260*((voltage_vector).^2))+(1800*voltage_vector)-3000;

mean_voltage = mean(voltage_vector);
mean_ntu = mean(ntu_vector);
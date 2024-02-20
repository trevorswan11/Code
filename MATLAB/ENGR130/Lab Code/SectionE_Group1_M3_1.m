% Jonathan Callejas, Madhav Narayan, Angel Gonzalez, Trevor Swan
% ENGR 130 Module 3.1 Report
% Section E
% October 6, 2023
%% Lab 1
clear;
clc;
close all;
% initialize the arduino 
a = arduino();

% This loop blinks the light 30 seconds, 15 'on', and 15 'off'
for count = 1:15 
    writeDigitalPin(a, 'D8', 1)
    pause(1)
    writeDigitalPin(a, 'D8', 0)
    pause(1)
end

%% Lab 2
clear;
clc;
close all;

% import the data file containing the oscilloscope matrix data
data = readmatrix('SectionE_Group1_M3_1.csv');

% store all but the NaN entries of the oscilloscope data
real_data = data(3:length(data),:);

% use the min and find functions to determine the minimum voltage, its
% index, and the time associated with that index
min_val = min(real_data(:,2));
min_idx = find(real_data(:,2) == min_val);
min_time_idx = real_data(min_idx);

% use the max and find functions to determine the maximum voltage, its
% index, and the time associated with that index
max_val = max(real_data(:,2));
max_idx = find(real_data(:,2) == max_val);
max_time_idx = real_data(max_idx);

% create a time and voltage vector the length of real_data, and use
% built-in functions to determine the mean voltage. 
time = real_data(3:end,1);
voltage = real_data(3:end,2);
mean_voltage = mean(real_data(:,2));

% create a vector with equal length to time full of the mean voltage value
mean_voltage_vector = linspace(mean_voltage,mean_voltage,numel(time));

% plot voltage vs. time, along with the minimum value, maximum value, and
% mean voltage horizontal line on one graph
hold on
plot(time, voltage,'k')
plot(max_time_idx,max_val,'bo')
plot(min_time_idx,min_val,'rs')
plot(time,mean_voltage_vector,'m--')

% label the graph with appropriate units, a title, and a legend
title('Voltage of Piezoelectric film')
xlabel('Time (seconds)')
ylabel('Voltage (volts)')
legend('signal voltage','maximum voltage','minimum voltage','mean voltage')
hold off

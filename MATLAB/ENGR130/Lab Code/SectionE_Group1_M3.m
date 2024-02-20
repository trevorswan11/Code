% Jonathan Callejas, Madhav Narayan, Angel Gonzalez, Trevor Swan
% ENGR 130 Module 3 Report
% Section E
% October 13, 2023
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

%% Lab 3
clear;
clc;
close all;
a = arduino();

% record the voltage 4 times a second (4Hz) for 10 seconds
voltage = zeros(1,40);
for count = 1:40
    voltage(count) = readVoltage(a,'A0');
    pause(0.25)
end

% define necessary constants and vectors for plotting data
threshold = 0.15;
thresh_vector = linspace(threshold,threshold,40);
time = linspace(0,10,40);
voltage_abv = voltage(voltage > threshold);
time_abv = time(voltage > threshold);

% plot voltage vs. time on the same figure as the data points above the
% threshold and a straight line at the threshold
plot(time, voltage, 'b-')
hold on
plot(time_abv, voltage_abv, 'ro')
plot(time, thresh_vector, 'k--')
xlabel('Time, seconds')
ylabel('Voltage, Volts')
title('Knock Data Recorded over 10 sec at 4Hz')
legend('original data','points above threshold','threshold value')

number_of_knocks = 0;
% loop through the votage readings and identify a knock if the current
% voltage point is greater than threshold and the previous point is below it
for point = 2:40
    current_point = voltage(point);
    previous_point = voltage(point-1);
    if current_point > threshold && previous_point < threshold
        number_of_knocks = number_of_knocks + 1;
    end
end

% the loop doesnt account for the first point, so this conditional counts a
% knock if the first voltaeg reading is above the threshold
if voltage(1) > threshold
    number_of_knocks = number_of_knocks + 1;
end

% display the number of knocks to the user
fprintf('The number of knocks recorded is %i.\n',number_of_knocks)
%% Lab 4
clear;
clc;
close all;
a = arduino();

% for ease of use and presentation, the reading was taken at 4Hz for 5
% seconds insetad of 10, this is reflected in the contants later in this
% code as well
voltage = zeros(1,20);
for count = 1:20
    voltage(count) = readVoltage(a,'A0');
    pause(0.25)
end

% define necessary constants and vectors for plotting data
threshold = 0.01;
big_threshold = 0.04;
thresh_vector = linspace(threshold,threshold,20);
time = linspace(0,10,20);

% these commands find the voltages above the normal threshold value and
% their indicies
voltage_abv = voltage(voltage > threshold);
time_abv = time(voltage > threshold);

% plot voltage vs. time on the same figure as the data points above the
% threshold and a straight line at the threshold. although this is not
% required by the practical user of this device, it is helpful for
% presentation and varifying responses
plot(time, voltage, 'b-')
hold on
plot(time_abv, voltage_abv, 'ro')
plot(time, thresh_vector, 'k--')
xlabel('Time, seconds')
ylabel('Voltage, Volts')
title('Knock Data Recorded over 10 sec at 4Hz')
legend('original data','points above threshold','threshold value')

number_of_knocks = 0;
true_number_of_knocks = 0;
% loop through the votage readings and identify a knock if the current
% voltage point is greater than threshold and the previous point is below it
for point = 2:20
    current_point = voltage(point);
    previous_point = voltage(point-1);
    if current_point > threshold && previous_point < threshold
        number_of_knocks = number_of_knocks + 1;
    end
    % this second nested contional records a knock to a separate variable
    % if it is above a larger threshold value
    if current_point > big_threshold && previous_point < big_threshold
        true_number_of_knocks = true_number_of_knocks + 1;
    end
end

% the loop doesn't account for the first point, so this conditional counts a
% knock if the first voltaeg reading is above the thresholds
if voltage(1) > threshold
    number_of_knocks = number_of_knocks + 1;
end
if voltage(1) > big_threshold
    true_number_of_knocks = true_number_of_knocks + 1;
end


% display the number of knocks to the user
fprintf('The number of knocks recorded is %i.\n',number_of_knocks)

% the led connected to the arduino will blink if there is more than one
% knock detected, regardless of intensity
pause_time = 0.03;
if number_of_knocks > 0
    for count_light = 1:3
        writeDigitalPin(a,'D13',0)
        writeDigitalPin(a,'D13',1)
        pause(1)
    end
end
writeDigitalPin(a,'D13',0)

% this conditional plays a tune on a passive buzzer if there is a knock
% recorded that is above the big threshold value 
if true_number_of_knocks > 0 
    fprintf('There is someone urgently trying to get in!\n')
    for count = 1:3
        playTone(a,'D3',391.995,1)
        pause(pause_time)
        playTone(a,'D3',0,0)
        playTone(a,'D3',466.164,1)
        pause(pause_time)
        playTone(a,'D3',0,0)
        playTone(a,'D3',523.25,1)
        pause(pause_time)
        playTone(a,'D3',0,0)
        playTone(a,'D3',466.164,1)
        pause(pause_time)
        playTone(a,'D3',0,0)
        playTone(a,'D3',391.995,1)
        pause(pause_time)
        playTone(a,'D3',0,0)
        pause(pause_time/3)
    end 
end

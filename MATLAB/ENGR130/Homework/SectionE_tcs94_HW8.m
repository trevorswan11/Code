% Trevor Swan
% ENGR 130 Homework 8
% October 17, 2023
%% Problem 2
clear;
clc;
close all;

load('HW8_gait_data.mat')

    % a) Is there an obvious relationship between stride length and cadence?
figure(1)
plot(stride_length,cadence, 'k.')
xlabel('Stride Length, cm')
ylabel('Cadence, seconds')
title("Individual Patient's Stride Length and Cadence")
fprintf('A graph of cadence against stride length, as seen in figure 1, does not indicate a relationship between the two.\n')

    % b) Is the stride length normally distributed?
stride_range = max(stride_length) - min(stride_length);
fprintf('The range of the stride lengths is %.2f cm.\n',stride_range)

% there needs to be 8 segments, or ranges total, so this command needs to
% output 9 numbers to have 8 spaces (ranges) for the data
range_segements = linspace(min(stride_length),max(stride_length),9);

% loop through the data, for every point that is both above the current
% threshold and less than or equal to the next boundary
number_segment = zeros(1,8);
for range = 1:8
    for idx = 1:length(stride_length)
        if stride_length(idx) > range_segements(range) && stride_length(idx) <= range_segements(range + 1)
            % add one to the indicy of the range that is counting # of
            % occurances/segment
            number_segment(range) = number_segment(range) + 1;
        end
    end
end

% plot and label the bar graph
figure(2)
bar(number_segment)
xticks(0.6:8.4)
xticklabels(range_segements)
xlabel('Average Stride Length of a Patient, cm')
ylabel('Frequency')
title('Distribution of Patient Stride Lengths')
fprintf('It is clear that, through the bar graph, the data reflects a roughly normal distribution.\n')

    % c) Determine discrepancies in pateint data
% because cadence has units of steps/minute, dividing 60 by cadence yields
% seconds/step, where step is equivalent to stride
expected_stride = 60 ./ cadence;

% find the sum of the swing and stance phases to calculate the recorded
% time per stride for each patient, units of seconds/stride
recorded_stride = swing + stance;

% determine the absolute difference between expected and recorded stride time
diff = abs(expected_stride - recorded_stride);

% determine which pateints have data that violats the 0.01s difference
patients_disc = patient(diff > 0.01);
num_disc = numel(patients_disc);
fprintf('There are <strong>%i</strong> patients with discrepant data and their ID numbers are:\n',num_disc)

% Display the patient number information in a neat and orderly fashion
for idx = 1:(num_disc - 1)
    fprintf('<strong>%i</strong>, ',patients_disc(idx))
    if mod(idx, 12) == 0
        fprintf('\n')
    end
end
fprintf('and <strong>%i</strong>.\n',patients_disc(end))
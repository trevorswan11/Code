%% Problem 2 - Engineers & PSI
clear;
clc;
close all;

% load the given data file into script
load('HW6_stress_test.mat')

% PART A - prompt the user for their desired minimum PSI value and design number
threshold_value = input('Enter the threshold value: ');
simulation_number = input('Enter the simulation number (1-5): ');

% If the simulation number entered is not equal to one of the allowed values
% display an error message and stop the code from running
if simulation_number ~= 1 && simulation_number ~= 2 && simulation_number ~= 3 ... 
    && simulation_number ~= 4 && simulation_number ~= 5
    error('Please enter an integer value between 1 and 5 for the simulation number.')
end

% use logical indexing to find all the values in the given deisgn that are
% less than the desired threshold value.
desired_design = DesignData(:,simulation_number);
below_threshold = desired_design(desired_design < threshold_value);

% tell the user how many values were below their threshold in the desired
% design and display the values below the threshold in a list format
length_below = numel(below_threshold);
fprintf('Simulation %i generated values below the threshold %i times.\n'...
    ,simulation_number,length_below)
fprintf('\nThe pressure values for simulation %i below the thershold of %.2f PSI are:\n' ...
    ,simulation_number,threshold_value)

% inform the user if their thershold is above any values and display if
% these values exist
if numel(below_threshold) == 0
    disp('There are no values below your threshold.')
else

    % This for loop is useful for printing the below_threshold data to the
    % screen without taking too much space. Every 10 entires creates a new
    % line, with the last data point finished with the word 'and' to flow
    % nicely
    for display = 1:(numel(below_threshold)-1)
    fprintf('%i, ', below_threshold(display))
        if mod(display,10) == 0
        fprintf('\n')
        end
    end
    fprintf('and %i\n', below_threshold(end))

    % This save command only occurs if there is data in the below_threshold
    % vector
    save('values_below_thershold','below_threshold')
end

% PART B - Set up preliminary vectors so there is not a matlab suggestion
minimum = zeros(1,5);
time_idx_min = zeros(1,5);
time_min = zeros(1,5);

maximum = zeros(1,5);
time_idx_max = zeros(1,5);
time_max = zeros(1,5);

% These two matrices account for a possible extra min and/or max for each
% design
overflow_max = zeros(5,1);
overflow_min = zeros(5,1);

% Determine the minimum and maximum value for each simulation along with the
% time at which they occur. Array values are .1 seconds apart
for idx = 1:5
    maximum(idx) = max(DesignData(:,idx));
    times_idx_max = find(DesignData(:,idx) == max(DesignData(:,idx)));
    time_idx_max(idx) = times_idx_max(1);
    time_max = time_idx_max.*0.1;

    % there is a chance the maximum occurs at two times, I accounted for this
    % by created a nested conditional to house these extra times in
    % an overflow matrix
    if numel(times_idx_max) > 1
        overflow_max(idx) = times_idx_max(2);
    end
    % this code opperates the same way as that in the first part of this loop    
    minimum(idx) = min(DesignData(:,idx));
    times_idx_min = find(DesignData(:,idx) == min(DesignData(:,idx)));
    time_idx_min(idx) = times_idx_min(1);
    time_min = time_idx_min.*0.1; 

    % this is the same logic as found above, excpet for minimum data
    % instead
    if numel(times_idx_min) > 1
        overflow_min(idx) = times_idx_min(2);
    end
end

% This step converts the index data for the overflow into seconds
overflow_max_time = overflow_max.*0.1;
overflow_min_time = overflow_min.*0.1;

% Display the data values found in the previous loop
for idx2 = 1:5
    fprintf('\nSimulation %i:\nMaximum value: %.0f PSI at %.1f s.\nMinimum value: %.0f PSI at %.1f s.\n'...
        ,idx2, maximum(idx2), time_max(idx2), minimum(idx2), time_min(idx2))

    % This conditional statemnts displays any overflow data only if it exists
    % for a given simulation.
    if overflow_max_time(idx2) ~= 0
        fprintf('Simulation %i had its maximum value occur at %.1f s as well.\n', ...
            idx2, overflow_max_time(idx2))
    end

    % This conditional statement follows the one above, only with the
    % overflow_min being looked at instead
    if overflow_min_time(idx2) ~= 0
        fprintf('Simulation %i had its minimum value occur at %.1f s as well.\n', ...
            idx2, overflow_min_time(idx2))
    end
end
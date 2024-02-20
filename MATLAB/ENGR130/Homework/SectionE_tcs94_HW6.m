% Trevor Swan
% ENGR 130 Homework 6
% October 3, 2023
%% Problem 1 - MATLAB Grader
clear;
clc;
close all;

% housekeeping commands would go here, but we leave them out to keep Grader happy

%bring in data from data file - it is random here to simulate some data
%vector that is provided. It is not known what values will be in the vector
%nor how many values it will contain, so the length is random as well.
A = randi([1,100],1,500);

%in one line, find how many elements of vector A are equal to 4
Num_of_4s = numel(A(A==4));

%in one line, find how many elements of vector A are divisible by 5
Div_by_5 = numel(A(mod(A,5)==0));

%in one line, sum all of the elements of the vector A that are greater than
%50.
Sum_Over_50 = sum(A(A>50));

%in one line, find the mean value of all elements of the vector A that are
%less than 30.
Mean_Under_30 = mean(A(A<30));

%in one line, find how many elements are equal to the maximum value in the
%vector A
Number_at_Max = numel(A(A==max(A)));

%in one line, create a new vector with all the even-valued elements in A
B = A(mod(A,2)==0 & A~=0);
%% Problem 2 - Engineers & PSI
clear;
clc;
close all;

% load the given data file into script
load('HW6_design_data.mat')

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

    % This for loop prints the data below the requested threshold. Every 10
    % entires printed creates a new line
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

    % This nested conditional accounts for the possibility of two maximums
    % in a data set
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

% PART C - graphing the data for pressure vs. time. A legend is useful for
% discerning the different lines on the graph/plot
time = 0.1:0.1:10;
hold on
for idx3 = 1:5
    plot(time,DesignData(:,idx3),'LineWidth',1.5)
end

xlabel('Time, seconds')
ylabel('Pressure, PSI')
title('PSI of Design Prototypes over Time')
legend('Design 1','Deisgn 2','Design 3','Design 4','Design 5')
%% Problem 3 - Pressure Sensors
clear;
clc;
close all;

% Bring the data regarding the bulkhead and cabin pressures into an
% accessible space. Also assign initial warning counter value
load('HW6_PressureSensors.mat')
total_warnings = 0;

% Start by analyzing the bulkhead pressure list. Considering each value in
% the list are two seconds apart, analyze the first term in the bulkhead
% list. Call this term the current bulkhead pressure
for idx = 1:numel(bulkhead)
    current_bulkhead_pressure = bulkhead(idx);

    % Assign the first term in the cabin pressure list term A and assign term B
    % the succeeding value of term A.
    term_A = cabin(idx);
    term_B = cabin(idx + 1);

    % Calculate the rate of change by subtracting term A from term B and
    % dividing the value by 2. Call this value the current cabin rate of change
    current_cabin_rate = (term_B - term_A)/2;

    % If the current bulkhead pressure is less than 60 and the current cabin
    % rate of change is greater than 5, add one to a counter called total warnings
    if current_bulkhead_pressure < 60 && abs(current_cabin_rate) > 5
        total_warnings = total_warnings + 1;
    end
end

% If the total warnings value is equal to 0, tell the user there is no
% action required
if total_warnings == 0
    fprintf('No action is needed.\n')

    % If the total warnings value is between 1 and 20, tell the user to
    % initiate the “potential risk” protocol
elseif total_warnings <= 20
    fprintf('Start the "potential risk" protocol.\n')

    % If the total warnings value is 21 or greater, tell the user to initiate
    % the “probable risk” protocol
else
    fprintf('With a total warning count of %i, start the "probable risk" protocol.\n',total_warnings)
end
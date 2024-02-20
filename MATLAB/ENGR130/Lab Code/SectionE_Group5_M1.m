% Trevor Swan - tcs94
% ENGR 130 Module 1 Report
% Section E
% September 15, 2023
    %% Lab 1
%% Problem 1 - Variable operators
clear;
clc;
close all;

% Create and store resistor values in different variables 
R1 = 80;
R2 = 10;
R3 = 15;

% Calculate the combined resistance
RT = 1/(1/R1 + 1/R2 + 1/R3);
%% Problem 2 - Matrices
clear;
clc;
close all;

% a) use the colon operator to store integer values 1 to 5
a2 = 1:1:5;

% b) create a matrix 
b2 = [2 3;4 9; 4 11];

% c) create a given matrix using vector a2
c2 = a2([1 3;2 3;4 5]);

% d) vector addition
d2 = c2 + b2;

% e) subtract addition
e2 = c2 - b2;

% f) matrix multiplication
f2 = b2 .* c2;

% g) maximum of a column
g2 = max(c2);

% h) maximum of a row
h2 = max(c2,[],2);

% i) max of an entire matrix
i2 = max(max(c2));

% j) sum of all the elemnts
j2 = sum(sum(c2));
%% Problem 3 - User input and output
clear;
clc;
close all;

% prompt the user for the usage
x = input('How many kWh do you use each month? \n');

% cost of the electricity
charge = 0.066;
c = charge*x;

% output
fprintf('Your charge for %.2f kWh will be $%.2f.\n',x,c);
    %% Lab 2
%% Problem 4 - Vectors and Loops
clear;
clc;
close all;

% create a vector containing random integers
random_vector = randi([1,100],1,10);

% find the sum of the random integers without built in functions
random_sum = 0;
for idx = 1:10
    random_sum = random_sum + random_vector(idx);
end 

% compute the average of the integers
random_average = random_sum/10;

% use a for loop to calculate the radicand and find the standard deviation
sum_diff_sq = 0;
for calculation = 1:10
    sum_diff_sq = sum_diff_sq+(random_vector(calculation)-random_average)^2;
end
std_dev = sqrt(sum_diff_sq/9);

% display the results to the user
fprintf(['The average of 10 random digits from 1 to 100, using sheer force is %.1f, \nand the standard' ...
    ' devaition calculated using this same method is %.1f.\n'],random_average,std_dev);

% compute and display the computer's results
computer_avg = mean(random_vector);
computer_std = std(random_vector);
fprintf(['\nThe average of 10 random digits from 1 to 100, using MATLAB fucntions is %.1f, \nand the standard' ...
    ' deviation calculated using the same method is %.1f.\n'],computer_avg,computer_std);

% check to see if both methods yielded the same output
if std_dev==computer_std && random_average==computer_avg
    test = 'success';
else 
    test = 'failure';
end 
%% Problem 5 - Vector Indicies and Loops
clear;
clc;
close all;

% ask the user to input a vector, and create vector of that length of zeros
vector1 = input('Please input a vector with a length of your chosing. It must be in [brackets].\n');
vector2 = zeros(1,numel(vector1));

% create a variable that contains the length of the vector
length_var = numel(vector1);

% rewrite v2 with teh reverse order of v1, diplaying the output
for reverse = 1:1:length_var
    vector2(reverse) = vector1(length_var - (reverse - 1));
end

% display the users original vector followed by the reversed vector
 fprintf('Your original vector is:\n')
 disp(vector1)
 fprintf('Your new, reversed vector is:\n')
 disp(vector2)
    %% Lab 3
%% Problem 6 - Conditionals
clear;
clc;
close all;

% ask the user to input a number from 0 to 12
wind_value = input('Please input an integer value from 0 to 12: ');

% check to see if the value is in the bounds of the question asked
if wind_value < 0
    error('The number you entered was less than the allowed value.');
elseif wind_value > 12
    error('The number you entered was more than the allowed value.');
% display the correct message based on the wind value entered
elseif wind_value == 0
    fprintf('\nThe strength of the wind you entered is %.0f.\nThere is no wind.\n',wind_value);
elseif wind_value <= 6
    fprintf('\nThe strength of the wind you entered is %.0f.\nThere is a breeze.\n',wind_value);
elseif wind_value <= 9
    fprintf('\nThe strength of the wind you entered is %.0f.\nThis is a gale.\n',wind_value);
elseif wind_value <= 11
    fprintf('\nThe strength of the wind you entered is %.0f.\nIt is a storm.\n',wind_value);
else
    fprintf('\nThe strength of the wind you entered is %.0f.\nHello, herricane!\n',wind_value);
end
%% Adruino Housekeeping
clear;
clc;
close all;

% initialize the arduino
a = arduino();
writeDigitalPin(a, 'D13', 0)
%% Arduino Code

% Lighting cycles
for cycle = 1:5 
    % turn on light on arduino
    writeDigitalPin(a, 'D13', 1)

    % pause on 'on' for 0.75 sec
    pause(0.75)
    
    % turn off light on arduino
    writeDigitalPin(a, 'D13', 0)

    % pause on 'off' for 0.5 sec
    pause(0.5)
end
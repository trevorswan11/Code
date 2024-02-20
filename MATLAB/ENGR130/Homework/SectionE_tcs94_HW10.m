% Trevor Swan
% ENGR 130 Homework 10
% November 9, 2023

%% Problem 1 - MATLAB Grader
clc;
clear;
close all; 

% create test vector and call function
vec = [1 2 3 4 5 5 5 5 6 5 7 8 9 10 5];
[value, indexes] = mode_analysis(vec);

%% Problem 2 - Fibonacci Sequence
clc;
clear;
close all;

% ask the user for the number of terms they would like and call function
terms = input(['Please input a positive integer for the number of terms of' ...
    ' the fibonacci sequence you would like to examine: ']);
fibonacci_gen_info(terms)

%% Problem 3 - Botanist
clc;
clear;
close all;

% load provided data and call function
load('HW10_heights.mat')
plant_growth_analysis(species_A_heights, species_B_heights, species_C_heights)

%% Functions
% Problem 1 Function
function [value, indexes] = mode_analysis(vec)
% This function dcetermines the indeces of the locations of the mode in a vector
% Format of call: mode_analysis(vec)
% Inputs - 1 vector
%       vec - vector of unknown length with one mode
% Outputs - 2 values
%       value - value of the mode of the vector
%       indexes - indeces of this mode in the vector

value = mode(vec);
idx = 1:length(vec);
indexes = idx(vec == value);
end

% Problem 2 Functions
function fibonacci_gen_info(terms)
% This function calls two other functions to gather info about the
% fibonacci sequence
% Format of call - fibonacci_gen_info(terms)
% Input - 1 value
%       terms - the number of terms of the sequence requested 
% Note: terms must be a positive integer
% Output - none

% Check to make sure the inputted number is a positive number
if terms - floor(terms) ~= 0 || terms <=0
    error('Please make sure your input is a positive integer.')
end

% Call the next two functions
[fibvec, fibsum] = fibonacci_sequence(terms);
fibonacci_display(fibvec, fibsum)

end

function [fibvec, fibsum] = fibonacci_sequence(terms)
% This function finds the sequence and sum of the fibonacci sequence based
% on a requested number of terms
% Format of call: fibonacci_sequence(terms)
% Input - 1 value
%       terms - the number of terms of the sequence requested 
% Note: terms must be a positive integer
% Output - 2 values
%       fibvec - vector containing terms number of terms of the fib
%       sequence
%       fibsum - sum of the vector produced above

% Initialize sequence
fib1 = 0;
fib2 = 1;

% Account for user requesting one or two terms
if terms == 1
    fibvec = fib1;
    fibsum = fib1;
elseif terms == 2
    fibvec = [fib1 fib2];
    fibsum = sum(fibvec);
else 
    % initialize the vector 
    fibvec = zeros(1,terms);
    fibvec(2) = fib2;

    % generate the remaining terms in the sequence by adding the previous
    % two terms together to get the current term
    for idx = 3:terms
        fibvec(idx) = fibvec(idx-1) + fibvec(idx-2);
    end
    fibsum = sum(fibvec);
end
end

function fibonacci_display(fibvec, fibsum)
% This function displays the number of terms, the vector itself, and its
% sum
% Format of call: fibonacci_display(fibvec, fibsum)
% Input - 2 values
%       fibvec - vector with values of the fibonacci sequence
%       fibsum - sum of the fibonnaci vector
% Output - no values, only display

% Determine the number of terms in the vector and display it
number_fib = length(fibvec);
fprintf('The number of terms in the sequence is %i.\n',number_fib)

% Display the terms to the user
fprintf('The terms in the sequence are as follows:\n[')
for count = 1:number_fib-1
    fprintf('%i',fibvec(count))
    % The vector is displayed with commas to save space, the next line
    % makes sure the display is not running out of the command window
    if mod(count,13) == 0
       fprintf('...\n')
    else
        fprintf(', ')
    end
end
fprintf('%i]',fibvec(end))

% Display the sum of the terms to the user
fprintf('\nThe sum of the first %i terms of the fibonnaci sequence is %i.\n', number_fib, fibsum)
end

% Problem 3 Function
function plant_growth_analysis(A, B, C)
% This function provides information regarding 3 inputted plant height data
% Format of call: plant_growth_analysis(A, B, C)
% Inputs: 3 vectors
%       A - height data corresponding to species A
%       B - height data corresponding to species B
%       C - height data corresponding to species C
% Output: none
%       Only displays information to the screen

% Create a vector containing the species names
species_names = ["A" "B" "C"];

% determine the max height reached for each species
maximum_A = max(A);
maximum_B = max(B);
maximum_C = max(C);

% put all maximum heights in a vector with 1 corresponding to A, 2 to B, and 3 to C
maximums = [maximum_A maximum_B maximum_C];

% find all indicies where the height is greater than 100. the first day is
% considered day 0, so the true day must be -1 due to matlab arrays
idx1 = 1:numel(A);
Aover100 = idx1(A >= 100);
day_A = Aover100(1) - 1;

idx2 = 1:numel(B);
Bover100 = idx2(B >= 100);
day_B = Bover100(1) - 1;

idx3 = 1:numel(C);
Cover100 = idx3(C >= 100);
day_C = Cover100(1) - 1;

% create a vector of the day data, with 1 corresponding to A, 2 to B, and 3 to C
daybreak100 = [day_A day_B day_C];

% determine which species reaches the highest height using the max command
[species_max, max_species] = max(maximums);
max_species = species_names(max_species);

% determine the day the tallest species hits its maximum height. The day
% should be -1 because of matlab indicies. Each species should have its own
% index vector to account for possible mismatched arrays.
if max_species == "A"
    idxA = 1:length(A);
    species_day_max = idxA(A == species_max) - 1;
elseif max_species == "B"
    idxB = 1:length(B);
    species_day_max = idxB(B == species_max) - 1;
else
    idxC = 1:length(C);
    species_day_max = idxC(C == species_max) - 1;
end

% print the inforamtion found above to the screen
for species = 1:3
    fprintf('Species %s reaches a maximum height of %.2fcm.\n',species_names(species), maximums(species))
    fprintf('It takes species %s a total of %i days to pass a height of 100 cm.\n\n',species_names(species), daybreak100(species))    
end

% print the species and day the species hits its greatest height
fprintf('Species %s reaches the greatest height at any time.\nThis Maximum Height is reached on day %i.\n',max_species, species_day_max)

end
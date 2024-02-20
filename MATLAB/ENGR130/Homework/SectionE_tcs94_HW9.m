% Trevor Swan
% ENGR 130 Homework 9
% October 20, 2023
%% Question 2 - Dice
clc;
clear;
close all;

% Prompt the user for dice information and call function
number_roll = input('How many dice should be rolled at once? ');
number_side = input('How many sides on the dice? ');
roll_total = dice_roll(number_roll, number_side);

% use correct spelling convenctions based on # of dice rolled
if number_roll == 1
    fprintf('\nYour %i roll of %i-sided dice summed to %i.\n',number_roll, number_side, roll_total)
else
    fprintf('\nYour %i rolls of %i-sided dice summed to %i.\n',number_roll, number_side, roll_total)
end

%% Question 3 - MATLAB Grader
clc;
clear;
close all;

x=5;
my_fact(x)

function factorial = my_fact(x)
% This function finds the factorial of an inputted positive integer
% Format of call: my_fact(x)
% Input: 1 variable
%       x - positive integer to find factorial of
% Output: 1 variable
%       factorial - result of taking the factorial of x

currentprod = 1;
while x > 1
    % factorial is calculated by n * (n-1) * (n-2)... all the way to 1
    currentprod = x * currentprod;
    x = x - 1;
end
factorial = currentprod;
end

%% Function for Question 2
function dice_total = dice_roll(num, sides)
% This function sums the sides of random dice with n sides
% Format of call: dice_roll(num, sides)
% Input: 2 variables
%       num - number of dice rolled
%       sides - number of sides per dice (uniform)
% Output: 1 Variable
%       dice_total - sum of simulated dice rolls

rand_rolls = randi([1,sides],1,num);
dice_total = sum(rand_rolls);
end


%% Lambert W Function
clear;
clc;
close all;

% Prompt the user to enter a number
input_number = input('Enter a number: ');

% Set the maximum number of iterations
max_iterations = 100;

% Tolerance for convergence
tolerance = 1e-6;
x = input_number;
x_prev = x;

% Iteratively calculate the Lambert W function using Newton's method
for i = 1:max_iterations
    x = x - ((x * exp(x) - input_number) / (exp(x) * (x + 1) - (x + 1)));
    
    % Check for convergence
    if abs(x - x_prev) < tolerance
        break;
    end
    
    x_prev = x;
end

% Display the result
fprintf('W(%.2f) = %.5f\n',input_number, x)

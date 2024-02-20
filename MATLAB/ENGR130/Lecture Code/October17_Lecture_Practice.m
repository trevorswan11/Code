%% Lecture Prep 8
clc; 
clear;
close all;

n = input('');
n_squared = square_it(n);

%% misc notes
% function [Output Variables] = function_name(Input Variables)
% % comment block
% code
% % additional comments
% end


%% Practice Problem 1
clc;
clear;
close all;

rad = input('What is the radius of your circle? ');
fprintf('The area of a circle of radius %.1f is %.3f.\n', rad, circle_area(rad))

%% Practice Problem 2
clc;
clear;
close all;

base = 5;
height = 8;
fprintf('The area of a right triangle with base length %i and height %i is %.1f.\n',base, height, tri_area(base, height))

%% Practice Problem 3
clc;
clear;
close all;

a = input('Please input the coef of the x^2 term: ');
b = input('Please input the coef of the x term: ');
c = input('Please input the constant term: ');

[r1, r2] = roots(a, b, c);
if isreal(r1) == 1 && isreal(r2) == 0
    fprintf('A quadratic with an x^2 coef of %.1f, x coef of %.1f, and constant of %.1f\nhas roots of %.2f and %.2f+%.2fi.\n', ...
        a, b, c, r1, real(r2), imag(r2))
elseif isreal(r1) == 0 && isreal(r2) == 1
    fprintf('A quadratic with an x^2 coef of %.1f, x coef of %.1f, and constant of %.1f\nhas roots of %.2f+%.2fi and %.2fi.\n', ...
        a, b, c, real(r1), imag(r1), r2)
elseif isreal(r1) == 0 && isreal(r2) == 0
    fprintf('A quadratic with an x^2 coef of %.1f, x coef of %.1f, and constant of %.1f\nhas roots of %.2f+%.2fi and %.2f+%.2fi.\n', ...
        a, b, c, real(r1), imag(r1), real(r2), imag(r2))
else
    fprintf('A quadratic with an x^2 coef of %.1f, x coef of %.1f, and constant of %.1f\nhas roots of %.2f and %.2f.\n', ...
        a, b, c, r1, r2)
end

%% functions - must come at the end of a script file
function square_val = square_it(num) % from lecture prep 
% This function calculates the square of a number
% Format of call: square(num)
% Input
%   num - number to be squared
% Output
%   square_val, the square of the number
square_val = num*num;
end

function area = circle_area(radius) % function lecture practice 1
% This function calculates the area of a circel given its radius 
% Format of call: circle_area(radius)
% Input
%   radius - radius of desired circle
% Output
%   area, the area of the circle
area = pi*radius^2;
end

function triangle_area = tri_area(base, height)
% Calculates the area of a right triangle
% Format of call: tri_area(base, height)
% Inputs: 2 scalar values
%               base and height
% Outputs: area of the triangle
triangle_area = (1/2)*base*height;
end

function [root1,root2] = roots(a, b, c)
% calculates the roots of a quadratic function
% format of call: roots(a, b, c)
% Inputs: 3 coefficients of a quadratic
%           a, b, c
% Outputs: both roots of a quadratic
%           [root1, root2]
root1 = (-b + sqrt(b^2-4*a*c))/(2*a);
root2 = (-b - sqrt(b^2-4*a*c))/(2*a);
end
%% housekeeping comands
clear;
clc;
close all;

%% Bugs
% a bug is something that prevents a code from running to completion, it
% can be something that is present or hidden. They can be tricky to find
% sometimes. To minimize bugs: create an algorithm, comment, readable code
% with white space and variable names, and 'reese's pieces' approach -
% writing the code in small pieces and then putting those small pieces
% together.

% start by writing an algorithm
% turn the algorithm into a list of comments in a script file
% write a bit of code under each comment and see if it works. continue if
% successful, revise is if not. This is the recees pieces approach
%% Example 1 - Minimum value without using minimum function
clear;
clc;
close all;

% values will be provided
vector_length = randi([10,20],1);
vector = randi([1,50],1,vector_length);

% set the minimum value equal to the first value in the list
minimum_value = vector(1);

% look at the next value in the list and call it the current value
for idx = 2:vector_length
    if vector(idx) < minimum_value
        minimum_value = vector(idx);
    end
% if the current value is less than the minimum value, reset the minimum
% value to the current value

end
% compare with built in fucntion and print your minimum value to the screen
true_min = min(vector);
fprintf('The true minimum value is %i.\n',true_min)
fprintf('Your generated minimum value is %i.\n',minimum_value)




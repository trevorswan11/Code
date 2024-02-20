%% Quick Lecture Notes/Housekeeping
clear;
clc;
close all;
%% Lecture Practice Problem 1
clear;
clc;
close all;

count = 0;
asleep = 0;
while asleep == 0
    Q = input('Are you up?\n','s');
    if (Q=='y') || (Q=='Y')
        count = count + 1;
        fprintf('\n%i\n',count)
    else
        asleep = 1;
    end
end

%% WHAT TO EXPECT ON THE MIDTERM EXAM

% scalars, vectors, matrices
% know what the basic built in functions do 
% rational and logical operators
% plot questions with labels, titles, data markers, legends, polyfit, polyval
    % SPEND SOME TIME ON THE POLYFIT AND POLYVAL
% selection, loops, load and save
    % NO READMATRIX OR WRITEMATRIX
% practice packet provided. It is not a recipe for the exam
    % great for basic practice, not representative of exam topic weight
% look to practice exam, lecture prep, adn formative quizzes
% lab questions on the module 2 lab only

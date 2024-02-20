% Trevor Swan
% ENGR 130 Homework 4
% September 17, 2023
%% Problem 2 - MATLAB Grader
clear;
clc;
close all;

% vectors for initial vitamin D, final vitamin D, and minutes of sun
D_init = [68.68 59.26 43.74 18.54 8.71 59.20 69.82 40.07 33.27 68.45 7.46 43.48 68.65 56.84 57.46 11.03 33.56 26.72 4.75];
D_final = [66.30 63.66 46.24 16.91 15.72 62.49 70.80 38.24 38.16 73.75 9.67 49.51 67.09 61.09 55.97 11.67 30.80 30.23 8.48];
sun = [147 40 146 78 263 175 266 120 48 90 105 175 172 106 258 33 203 86 220];

% calculate the (hopeful) increase in vitamin D for each participant
% store in a vector called diff
diff = D_final - D_init;

% compute the ratio of increase in vitamin D level to minutes of sunlight
% for each participant.  Store in a vector called ratio.
ratio = diff./sun;

% compute the average of these ratios; store in variable called avg
avg = sum(ratio)/19;

% initialize counters for each category of vitamin D level to zero
defic = 0;
inadeq = 0;
adeq = 0;
excess = 0;

% use a loop and if structure to determine how many participants have
% final vitamin D levels in each of the 4 categories.
for index1 = 1:1:19
    if D_final(index1) <= 12
        defic = defic + 1;
    elseif D_final(index1) <= 20
        inadeq = inadeq + 1;
    elseif D_final(index1) <= 50
        adeq = adeq +1;
    else
        excess = excess + 1;
    end 
end

% display the amount of participants per group
fprintf(['The number of patients per group is:\n0 to 12 ng/mL (deficient): %i\n12.01 to 20 ng/mL (inadequate):' ...
    ' %i\n20.01 to 50 ng/mL (adequate): %i\n50.01 or more ng/mL (excessive): %i\n'],defic,inadeq,adeq,excess)
%% Problem 3 - Class Grades
clear;
clc;
close all;

% Class Scores copy-paste
score = [59, 70, 88, 95, 92, 76, 94, 87, 82, 91, 88, 65, 86, 93, 88, 96, 92, 87, 30, 80];

% preliminary code to create length vector and start counters
class_size = numel(score);
A_count = 0;
B_count = 0;
C_count = 0;
D_count = 0;
F_count = 0;

% check each student's score and display the student's score and letter
% grade
for index1 = 1:class_size
    % 90 <= A < 100
    if score(index1) >= 90
        fprintf('\nStudent %i: Score %i, Grade A',index1,score(index1))
        A_count = A_count + 1;
    % 80 <= B < 90
    elseif score(index1) >= 80
        fprintf('\nStudent %i: Score %i, Grade B',index1,score(index1))
        B_count = B_count + 1;
    % 70 <= C < 80    
    elseif score(index1) >= 70
        fprintf('\nStudent %i: Score %i, Grade C',index1,score(index1))
        C_count = C_count + 1;
    % 60 <= D < 70
    elseif score(index1) >= 60
        fprintf('\nStudent %i: Score %i, Grade D',index1,score(index1))
        D_count = D_count + 1;
    % 0 <= F < 60
    else
        fprintf('\nStudent %i: Score %i, Grade F',index1,score(index1))
        F_count = F_count + 1;
    end
end

% calculate and display the class's average 
% (the mean is taken twice to accomodate possible matrix arrays)
class_average = mean(mean(score));
fprintf('\n\nThe average score for the class is %.1f.\n',class_average)

% calculate the percentage of teh glass that recieved each letter grade
A_percent = (A_count/class_size)*100;
B_percent = (B_count/class_size)*100;
C_percent = (C_count/class_size)*100;
D_pecent = (D_count/class_size)*100;
F_percent = (F_count/class_size)*100;

% create vectors to store the percent data and letters
percentages = [A_percent, B_percent, C_percent, D_pecent, F_percent];
letter_grades = ['A','B','C','D','F'];

% display the percent of the classs that got each letter grade
for index2 = 1:5
    fprintf('%.1f percent of the class earned a(n) %s.\n',percentages(index2),letter_grades(index2))
end
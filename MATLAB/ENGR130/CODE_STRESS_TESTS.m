%% Class Grades Stress Tests
clear;
clc;
close all;

score = randi([50,99],50300);

class_size = numel(score);
A_count = 0;
B_count = 0;
C_count = 0;
D_count = 0;
F_count = 0;

for index1 = 1:class_size
    if score(index1) >= 90 
        A_count = A_count + 1;
    elseif score(index1) >= 80
        B_count = B_count + 1;
    elseif score(index1) >= 70
        C_count = C_count + 1;
    elseif score(index1) >= 60
        D_count = D_count + 1;
    else
        F_count = F_count + 1;
    end
end

class_average = mean(mean(score));
fprintf('\n\nThe average score for the class is %.1f.\n',class_average)

A_percent = (A_count/class_size)*100;
B_percent = (B_count/class_size)*100;
C_percent = (C_count/class_size)*100;
D_pecent = (D_count/class_size)*100;
F_percent = (F_count/class_size)*100;

percentages = [A_percent, B_percent, C_percent, D_pecent, F_percent];
letter_grades = ['A','B','C','D','F'];

for index2 = 1:5
    fprintf('%.1f percent of the class earned a(n) %s.\n',percentages(index2),letter_grades(index2))
end
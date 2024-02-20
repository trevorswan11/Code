%% Test Macro Performance
clc;
clear;
close all;

test = input('Would you like to test the macro? ','s');
correct = 0;
failure = 0;
trials = 0;

while (test == 'y' || test == 'Y') && trials < 1000
    clc;
    macro = input('',"s");
    if macro == "/data get entity KyoshiYoshi Pos[1]"
        correct = correct + 1;
    else
        failure = failure + 1;
    end 
    trials = trials + 1;
end 

percent_correct = (correct/trials)*100;
fprintf("%.2f % of the trials were correct.\n",percent_correct)

% Per MATLAB Testing, It appears the function can run at a 99.9% success
% rate while maintaining a reading of 11.7262163 data points/second

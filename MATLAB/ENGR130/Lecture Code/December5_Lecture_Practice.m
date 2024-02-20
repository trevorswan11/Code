%% Final Exam 
% Review Session in Clapp108 on Sunday 12/17 from 2pm-4pm. 
% Review packet to be handed out
% Final exam will be hekd in Allen Autorium in Ford
%   12/19 from 3:30pm to 6:30pm

%% Lecture Practice 1 - Logical Indexing
clc;
clear;
close all;

% create initial buggy year vector. It contains the yearss 1950-2016, but
% from 2000 onwards, the computer had an error to report 2000 as 1900, 2001
% as 1901, and so on.
year = [1950:1:1999, 1900:1:1916];

% use logical indexing to sort out the buggy years
good_years = year(year>=1950);

% use logical indexing to make a buggy vector
bug_years = year(year<=1916);

% correct the list
corrected_list = [good_years, bug_years+100];

%% Lecture Practice 2 - Curve Fitting
clc;
clear;
close all;

% create test data
v_meas = 1:20;
F_meas = v_meas.^2;

% F = Apv^2
% polyfit generates coefs, polyval evaluates the x
fit_coef = polyfit(v_meas, F_meas, 2);
F_fit = polyval(fit_coef, v_meas);

% plot the original and fitted data on the same graph with labels
plot(v_meas, F_meas, '.b'); hold on
plot(v_meas, F_fit, '--r'); hold off
title("Model of the Velocity and Force of a Gas in a Tube")
xlabel("Velocity")
ylabel("Force")
legend("Original Data","Fitted Data")

%% Lecture Practice 3 - Files and Strings
clc;
clear;
close all;

% open the file for reading
infile = fopen('book.txt','r');

% initialize counter variables
less = 0;
fours = 0;
more = 0;

% get input phrase
for k = 1:43
    In{k} = fscanf(infile, '%s', 1);
end
fclose(infile);

% compare lengths. idx should be a logical vector that contains a 1 where a
% word is not equal to 4 ltters long, and a 0 when it is exactly 4 letters
% long
idx = zeros([1,length(In)]);
for i = 1:length(In)
    if strlength(In{i}) < 4
        less = less + 1;
        idx(i) = 1;
    elseif strlength(In{i}) == 4
        fours = fours + 1;
        idx(i) = 0;
    else
        more = more + 1;
        idx(i) = 1;
    end
end
fprintf('Analysis Results:\n\tFour: %i\n\tLess: %i\n\tMore: %i\n',fours, less, more)

% pull out words equal to 4 letters
Out = In(idx == 1);

% create a new file that contains the bok wiht words that are not 4 letters
% long
outfile = fopen('Smallbook.txt', 'w');
for k = 1:length(Out)
    fprintf(outfile, '%s ', Out{k});
end
fclose(outfile);

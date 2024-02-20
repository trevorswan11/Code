%% Housekeeping
clear;
clc;
close all;

%% Logical vectors and Matrices
clear;
clc;
close all;

orig = [ 2 -4 9 -7 0];
positives = orig > 0;

% we can use this for logical indexing 
vec = [5 9 3 4 6 11];
big_ones = vec(vec>5);

% issue with this is that we lose the index of the original number... 
clear; clc;

% this is logical indexing broken up into two parts:
vec = [5 9 3 4 6 11];
is_big = vec > 5;
big_ones2 = vec(is_big);

% or you can do this in one step
big_ones_logical = vec(vec>5);
%% Lecture Practice Problem 1
clear;
clc;
close all;

load('HW6_design_data.mat');
writematrix(DesignData,'HW6_design_data.csv')

d = readmatrix('HW6_design_data.csv');

%% Lecture Practice Problem 2
clear;
clc;
close all;

values = [6 8 2 0 6 0 7];
non_zeros = values(values~=0);

%% Lecture Practice Problem 3
clear;
clc;
close all;

psi_mat = readmatrix('HW6_design_data.csv');
below_1850 = psi_mat(psi_mat(:,3) < 1850);
len = numel(below_1850);

maxs = zeros(1,5);
mins = zeros(1,5);
pos = zeros(1,5);

for idx = 1:1:5
    maxs(idx) = max(psi_mat(:,idx));
    fprintf('\nThe max of column %i is %i.',idx,maxs(idx))
    mins(idx) = min(psi_mat(:,idx));
    fprintf('\nThe min of %i is %i.',idx,mins(idx))

end
[M,I] = max(psi_mat,[],:,"linear");
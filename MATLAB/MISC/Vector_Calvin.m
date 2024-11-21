%% Random and Sort
clc;
clear A;

rng('shuffle');
A = randperm(1000);
sorted = sort(A);


%% Discussing Vectors
clc;
clear;
close all;

vector_components = zeros(2,3);
variables = ["x" "y" "z"];
for vector_num = 1:2
    for comp = 1:3
        fprintf("Input the value of the %s component of vector %i: ", variables(comp),vector_num)
        vector_components(vector_num,comp) = input('');
        clc;
    end
end

vector1 = vector_components(1,:);
vector2 = vector_components(2,:);
[dotp, crossp, mag1, unit1, mag2, unit2] = vecinf(vector1, vector2);

%% rref tester
clc;
clear;
close all;

a = 2;

function [dotp, crossp, mag1, unit1, mag2, unit2] = vecinf(vec1, vec2)
% This function calculates necessary and useful info for two given vectors
% Call of function: vecinf(first 3d vector, second 3d vector)
% Input: 2 values
%       vec1 - 1x3 vector with real values
%       vec2 - 1x3 vector with real values
% Output: 6 values
%       dotp and crossp - dot and cross products of the vectors,
%       respectively
%       mag1 unit1 and mag2 unit2 - magnitude and unit vector of vector 1
%       and two, respectively

dotp = sum(vec1.*vec2);
crossp = [vec1(2)*vec2(3)-vec1(3)*vec2(2), -1*(vec1(1)*vec2(3)-vec1(3)*vec2(1)) vec1(1)*vec2(2)-vec1(2)*vec2(1)];
mag1 = sqrt(sum(vec1.^2));
unit1 = vec1./mag1;
mag2 = sqrt(sum(vec2.^2));
unit2 = vec2./mag2;
end





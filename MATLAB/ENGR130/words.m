clc;
clear;
close all;

maximum = factorial(26);
dividers = 524288;
set_break = linspace(1,maximum,dividers);
for idx = 1:(dividers - 1)
    sets(idx,:) = set_break(idx):1:(set_break(idx + 1) - 1);
end
sets(idx + 1) = setbreak(idx):1:set_break(idx + 1);
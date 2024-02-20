%% September 12 Lab Practcie
clear;
clc;
close all;
%% For and while loops
% for loops are definite, while loops are indefinite
for count= 1:1:4
    fprintf('The count is %i.\n',count);
end
% count = starting number:step size:ending number
for count= 1:5
    fprintf('I will not talk in class!');
end
%% making vectors with loops
% You dont need to fill out a vector like this in your code matlab just
%likes it. there will be times this semester where you dont know the vector
%length and you will be left with a suggestion like in "%%nested for loops"
vec=[0 0 0 0];
for idx=1:4
    vec(idx)=idx*2;
end
disp(vec)
%% nested for loops
eagle = zeros(1,3);
for row=1:4
    for col=1:3
        eagle(row,col)=row^col;
    end
end
disp(eagle);
%% loops and vector output
v=[2 4 6 7 8 9];
for k = 2:2:numel(v)
    fprintf('%i ',v(k))
end 
%% feet and yards
for yards=1:2
    for feet=1:1:3
        fprintf('%i feet\n',((yards-1)*3)+feet)
    end
    fprintf('%i yards\n',yards)
end
%% While loops
number = 0;
while number<5
    fprintf('%i is an acceptable number.\n',number);
    number=number+3;
end

fprintf('The end.\n');
%% What is the output
tomato=0;
basil=3;
mozzarella=5;

while basil<mozzarella
    mozzarella=mozzarella+2;
    basil=basil+3;
    tomato=tomato+1;
    fprintf('Mozzarella=%i, Basil=%i, Tomato=%i\n',mozzarella,basil,tomato)
end

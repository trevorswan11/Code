%% Lecture Practcie September 12
clear;
clc;
close all;
%% Conditionals
number=input('Please input a positive number! ');
if number <0
    x=abs(number);
    number=x;
    fprintf("Your number was not positive and you clearly can't listen to directions. Your number is now %i.",number)
end 
sqaure=sqrt(number);
fprintf('\n   Your number is %.2f and the square root of your number is %.5f.\n',number,sqaure)
%% practice 1
random=randi([1,10],1);
prompt=input('Please choose a number between 1 and 10.\n');
if 1>prompt || prompt>10
    error('Your number was outside of the bounds! How about you listen to directions next time!')

% you need "elseif" to be in these statements if there are multiple else
elseif prompt<random
    fprintf('Your number was less than the computers random number.\n')

% else if statements have conditon in a place similar to if statements
elseif prompt>random
    fprintf('Your number was greater than the computers random number.\n')

else
    fprintf('Your number was equal to the computers random number.\n')
end
%% practice 2
letter=input('Please enter an x\n','s');
% any string of characters need to be put in single quotes
if letter~='x'
    error('The letter you entered was not an x!')
end
%% practice 4
% ask the user what their name is
name=input('What is your name?\n','s');

% ask the user for 5 hw grades
grade1=input('Please enter your 1st homework grade: \n');
grade2=input('Please enter your 2nd homework grade: \n');
grade3=input('Please enter your 3rd homework grade: \n');
grade4=input('Please enter your 4th homework grade: \n');
grade5=input('Please enter your 5th homework grade: \n');

% store the users hw grades in a vector
grades=[grade1 grade2 grade3 grade4 grade5];

% calculate the average of the hw grades w/ and w/o lowest grade
hw_avg=mean(grades);
hw_avg_drop=(sum(grades)-min(grades))/4;

% letter grade
if hw_avg_drop>=90
    let_grade="A";
elseif hw_avg_drop>=80
    let_grade="B";
elseif hw_avg_drop>=70
    let_grade="C";
elseif hw_avg_drop>=60
    let_grade="D";
elseif hw_avg_drop>=50
    let_grade="E";
else 
    let_grade="F";
end

% display the academic information for the student
fprintf(['%s, your Raw Score is %.2f and your Score on a curve is %.2f. Your letter' ...
    ' grade for the course is %s.\n'],name,hw_avg,hw_avg_drop,let_grade);
   


%% Lecture Prep 9
% declare variables, do opperations, and pass info to function sin main
% sript. Function sdo their own work and then often pass information back
% to the main script. The function could call another nested function as
% well. The main script and each function has its own memory. Calling a
% function scopys information in to the fucntion smemeory so that it can
% copy information from itself to send back for the output. The memories
% are separate! All variables inside each function and the main script are
% all local, so variables can actually have the same name but different
% values if one is in the main script and the other is in a function.

% Functions with matrix input:
% adding a dot in your function makes the function more robust, as now the
% code will be able to accept vector and scalar inputs

% Functions with multiple outputs
% make sure outputs are in [square brackets] and separated by commas

clear;
clc;
close all;

triangle_bases = [5 3 7];
triangle_heights = [2 3 2];

area_calc = tri_area(triangle_bases, triangle_heights);

my_vector = randi([1,100],1,100);
[a,b,c] = sevens(my_vector);

%% Lecture Practice 1
clear;
clc;
close all;

helloworld

%% Lecture Practice 2
clear;
close all; 
clc;

%User input to start the loops
%***'s' needed for string inputs
play_again = input("Want to play? Answer Y or N (in CAPS)\n",'s'); 
%***set the current value of wins, losses, and draws to 0
wins=0;
losses=0;
draws=0;

%Allows the game to be replayed based on user's choice
while play_again == "Y"
%***function should take the curretn number of wins losses and draws and
%update those numbers based on the output of each game
[wins, losses, draws] = game(wins, losses, draws);

% resetting variable that replays the loop
%***'s' needed for string inputs
play_again = input("Want to play again? Answer Y or N\n",'s');
end 

%display when game ends
disp("Thanks for Playing!")
fprintf('You won %d time(s), lost %d time(s), and had %d draw(s)!\n',wins,losses,draws)

%% Lecture Practice 3
clc;
clear;
close all;
int = input('Please enter a postive integer: ');
[fibvec, sumfib] = fibonacci(int);

%% Functions

function area_calc = tri_area(base, height) %Lecture Prep Q1
% calculates the area of a triangle 
% Format of call: tri_area(base, height)
% Inputs: 2 values
%       base and height
% Output: area od the triangle

area_calc = 0.5*(base).*(height);
end

function [less, greater, equal] = sevens(vector) %Lecture Prep Q2
% This function determines the number of elements in a vector greater than,
% less than, and equal to 7
% Format of call: sevens(vector)
% Input: 1 value
%       vector of desired length
% Output: 3 values
%       less - number of values less than 7
%       greater - number of values greateer than 7
%       equal - number of values equal to 7

less = numel(vector(vector < 7));
greater = numel(vector(vector > 7));
equal = numel(vector(vector == 7));
end

function helloworld %Lecture Practice 1
disp('Hello World')
end

function [wins, loses, draws] = game(amt_win, amt_loss, amt_draw) %Lecture Practice 2
%Plays rock, paper, scissors game between computer and user
%***Inputs: 3 inputs
%           amt_win, amt_loss, amt_draw - # of current wins losses and
%           draws, respectively
%Outputs: number of wins, loses, and draws for all games played
%Format of call: [wins, loses, draws] = game()

%Creating computers play
options = ["ROCK","PAPER","SCISSORS"]; %creates options for the computer
index = randi([1,3]); %picks a random integer index from 1 to 3 
computer_output = options(index); %uses index to choose from options
%***identify the puase time for the rest of the function
n = 1;

%Setting Up Score Counters
%***To keep track of all games played this session, set wins to current
%   amount, not zero.
loses=amt_loss;
wins=amt_win;
draws=amt_draw;

%Asking for response
your_play = input("ROCK, PAPER, or SCISSORS? (must type in all caps)\n",'s');
fprintf('The Computer Plays: %s\n ',computer_output)
pause(n)

if computer_output == your_play
    disp("DRAW!")
    pause(n)
    draws=draws+1;
elseif computer_output == "ROCK" && your_play ~= "PAPER"
    disp("You lose!")
    loses = loses+1;
    pause(n)
elseif computer_output == "SCISSORS" && your_play ~= "ROCK"
        disp ("You lose!")
        loses = loses + 1;
        pause(n)
elseif computer_output == "PAPER" && your_play ~= "SCISSORS"
    disp("You lose!")
    loses = loses + 1;
    pause(n)
else
    disp("You win!")
    wins = wins + 1;
    pause(n)
end

end

function [fibvec, sumfib] = fibonacci(posint)
if posint == 1
    fibvec = 0;
    sumfib = 0;
elseif posint == 2
    fibvec = [0 1];
    sumfib = 1;
else
    fibvec = zeros(1,posint);
    f1prev = 1;
    f2prev = 0;
    fibvec = [0 1];
    for idx = 3:posint
        fibvec(idx) = f1prev + f2prev;
        f1prev = fibvec(idx);
        f2prev = f1prev;
    end
    sumfib = sum(fibvec);
end

end





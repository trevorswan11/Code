%% Rock, Paper, Scissors
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

function [wins, loses, draws] = game(amt_win, amt_loss, amt_draw)
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





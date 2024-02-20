%% Slot Machine
clc;
clear;
close all;
rng('shuffle')

% declare amount of money
starting_amount = 10;

% run the game
slots_game(starting_amount)

%% Functions
function slots_game(money)
% This function runs the slot machine game based on starting money
% Format of call: slots_game(money)
% Inputs: 1 value
%       money - the starting money of the player
% Output: none for this specific function

% the game works with even amounts only, so subtract one from odd numbers
if mod(money, 2) ~= 0
    money = money - 1;
end

% explain the game to the user and how they win
fprintf(['Welcome to my MATLAB slot machine!\nBased on your amount of money, you will be able to' ...
    ' do a certain number of spins.\nSpins "cost" $2, but you will make a profit of $2 for winning.\n' ...
    'The winning spin is AAA, and you have a low chance of winning, so gamble responsibly :)\n'])
input('\nPress enter to continue...')
clc;

% the amount entered must be a whole number, so if a floating poiint is
% entered, the value should be rounded down, and made even if not already
% even
if money ~= floor(money)
    money = floor(money);
    if mod(money ,2) ~= 0
        money = money - 1;
    end
end

% allow user to play by setting play again to true
play_again = 1;
total_wins = 0;

% continually prompt the user if the want to play or not until they say no
% or run out of money
while money >= 2 && play_again
    current_money = money;
    % tell the useer how much money they have and prompt them for number of
    % spins
    fprintf('You have $%i, how many spins would you like to do? ',money)
    spins = input('','s');
    spins = str2double(spins);
    % call the related lottery functions
    [outcomes, money] = slot_machine(spins, money);
    spins_won = slot_display(outcomes, current_money);
    money = profit(spins_won, money);
    
    % if the user has enough money, call the function that asks the user
    % if they would like to play again
    if money >= 2
        play_again = keep_going(money);
    end
    total_wins = total_wins + spins_won;
end

% call the game end function to let the user be free
game_end(money, total_wins)

end

function [spin_matrix, money_left] = slot_machine(num, money_start)
% This function outputs slot machine results and money remaining
% Format of call: slot_machine(num)
% Inputs: 2 values
%       money_start - the amount of money the user has currently
%       num - the number of spins the user wants to do
% Outputs: 2 values
%       spin_matrix - matrix with rows corresponsing to spin number and
%       columns being the value of each slot, A B C
%       money_left - the money the user has left after spins

% check to see if the user enetred a number at all. The user has the
% ability to request any quantity of spins using words or spaces, convert
% anything that isnt useable to the number 1
if isempty(num) == 1 || isnan(num) || isinf(num)
    num = 1;
    fprintf('The number of rolls you inputted was unclear, so you will do one spin only.\n\n')
end

% check to see if the user is overdrafting
if money_start - num*2 < 0
    % if the user wants to spin more than they have, reduce their money to
    % 0, set spin to their max amount, and tell the user what happened
    money_left = 0;
    num = money_start/2;
    fprintf(['You didnt have enough money to do the requested number of spins...\n' ...
        'You will instead spin %i times, the max amount with your financial situation.\n\n'],num)
else
    money_left = money_start - num*2;
end

fprintf('<strong>Weclome to the slot machine!</strong>\n');
input('Press enter to continue to the game...')

% initialize spin matrix and options
options = ["A" "B" "C" "B" "C" "B" "C" "B" "C" "B" "C" "B" "C" "B" "C" "B" "C" "B" "C" "A"];
spin_matrix = strings([num, length(options)]);

% randomly select the letter for each slot for each spin
for spin_num = 1:num
    for slot = 1:3
        spin_matrix(spin_num,slot) = options(randi([1,3]));
    end
end

end

function win_counter = slot_display(spin_matrix, money_now)
% This function displays the slot machine results to the screen
% Format of call: slot_display(spin_matrix)
% Inputs: 2 values
%       spin_matrix - results of the spins for the user
%       money - the users current amount of money
% Outputs: 1 value
%       win_counter - total number of wins the user has

% use the size command to see how many spins there are
[spins,~] = size(spin_matrix);

% flip through each letter and land on the rolled one for each spin to
% simulate an actual slot machine
options = ["A" "B" "C"];
win_counter = 0;

% This loop should display the values for n number of spins
for spin_num = 1:spins
    current = strings();

    % There are 3 slots in the slot machine, each with their own letter
    for slot_num = 1:3
        % The slots should be riffled through to represent a real slot
        % machine
        for times = 1:9
            % Each slot has three options and should go through each
            % option multiple times for dramatism
            for letter_display = 1:3
                clc;
                % Display title and spin number every time
                fprintf('<strong>MATLAB Slots</strong>\n')
                fprintf('---%i---\n',spin_num)
                if letter_display == 1
                    fprintf('%s',options(letter_display))
                else
                    fprintf('%s  ',current)
                    fprintf('%s',options(letter_display))
                    % Computers run extremly quickly and this pause command
                    % allows the user to see the numbers flying by
                    pause(0.05)
                end
            end
        end
        clc;
        % These print statements are here for appearance sake once again
        fprintf('<strong>MATLAB Slots</strong>\n')
        fprintf('---%i---\n',spin_num)
        % Assign a slot in the current vector the actual spin data to
        % display for the next time a spin is displayed
        current(slot_num) = spin_matrix(spin_num,slot_num);
        fprintf('%s  ',current)
    end
    % check to see if the current spin is a win (AAA) or not, and add 1 to
    % a counter if a spin is detected
    if current(1) == "A" && current(2) == "A" && current(3) == "A"
        win_counter = win_counter + 1;
        fprintf('\n\nWow! You Won $2!\n')
        % give the user $2 profit and display their new amount if they win
        money_now = money_now + 2;
        fprintf('You now have $%i!\n',money_now)
    else
        money_now = money_now - 2;
        fprintf('\n\nYou lost $2 this time around.\nYou now have $%i.',money_now)
    end
    % Pause for a short time to allow the user to understand their outcome
    pause(3)
end
end

function money_new = profit(winning_spins, money_current)    
% This function determines the amount of money gained
% Format of call: profit(winning_spins, money_current)
% Inputs: 2 Values
%       winning_spins = user's total number of wins
%       money_current = amount of money user has after gameplay
% Output: 1 value
%       money_new - amount of money useer has after calculations

% add $4 to the users money per win, as they should win $2, thus making a
% $2 profit over the $2 they spent to play the game
money_new = winning_spins*4 + money_current;
fprintf('\nYou now have $%i.\n',money_new)
end

function play_again = keep_going(money)
% This function asks the user if they would like to play again and send
% information to the main script 
% Format of call: keep_going()
% Input: 1 value
%       money - the current amount of money the user has
% Output: 1 value
%       1 means keep going, 0 means stop

% ask the user if they would like to play again
clc;
fprintf('You currently have $%i...\n',money)
question = input('Would you like to play again? (y/n) ','s');

% make sure the code can interperet any form of the letter y
if question == "y" || question == "Y"
    play_again = 1;
% make sure the code can interperet the full word
elseif question == "yes" || question == "YES"
    play_again = 1;
% make sure the code can interperet someone wanting to use logical values
elseif question == "1"
    play_again = 1;
else
    % if the user days anything other than a positive response, stop play
    play_again = 0;
end
end

function game_end(ending_money,winnings)
% This function ends the game and thanks the user for playing
% Format of call: game_end
% Inputs: 1 value
%       ending_money - the amount of money the user has at the end of the
%       game
%       winnings - the total number of wins the user had
% Outputs: none

% tell the user how much money they ended with and wish them well
clc;
fprintf('Dearest user, \nYou ended with $%i, having %i total wins.',ending_money, winnings)
fprintf('\n\nThank you for trying my MATLAB slot machine.\nHave a lovely day.\n')
end

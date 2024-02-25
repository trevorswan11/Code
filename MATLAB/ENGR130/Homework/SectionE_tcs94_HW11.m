% Trevor Swan
% ENGR 130 Homework 11
% November 13, 2023

%% Problem 1 
clc;
clear;
close all;
rng('shuffle')

% prompt the user for how many tickets they would like to buy
amount = input('How many tickets would you like to purchase? ');

% tell the user the probability they win the lotto based on the number of
% tickets they bought. this is founc by dividing the total number of
% tickets bought by the total number of outcomes - which is 100000
probability = (amount/100000) * 100;
fprintf('Purchasing %i tickets, you have a %.3f chance of winning.\n',amount, probability)

% Pause the script for dramatic effect
fprintf('Drum roll please...\n')
pause(1)

% call the lotto related functions based on user input
your_tickets = lotto_ticket(amount);
lotto_win = jackpot();
won_lotto = check_win(your_tickets, lotto_win);

% Display a message to the screen depending on winning status
if won_lotto == 1
    fprintf('Congrats! you won the lotto!\n')
else
    fprintf('Darn! You didnt win this time!\n')
end

% This next line converts the number to a string and finds its length to
% get the number of digits in the winning ticket
digits = numel(num2str(lotto_win));

% The winning ticket should be a 5 digit number, so add zeros to the
% beginning of each number less than five digits
if digits == 1
    fprintf('The winning ticket number was 0000%i.\n',lotto_win)
elseif digits == 2
    fprintf('The winning ticket number was 000%i.\n',lotto_win)
elseif digits == 3
    fprintf('The winning ticket number was 00%i.\n',lotto_win)
elseif digits == 4
    fprintf('The winning ticket number was 0%i.\n',lotto_win)
else
    fprintf('The winning ticket number was %i.\n',lotto_win)
end

%% Problem 2
clc;
clear;
close all;
rng('shuffle')

% declare amount of money
starting_amount = 100;

% run the game
slots_game(starting_amount)

%% Functions
% Problem 1 Functions
function ticket_vector = lotto_ticket(num)
% This function an amount of generates 5-digit lottery numbers
% Format of call: lotto_ticket(num)
% Input: 1 value
%       num - number of tickets being bought
% Output: 1 vetcor
%       ticket_vector - column vector containing lotto numbers

% check to see if the inputted value is a positve integer that is less than
% the total number of winning tickets
if num < 0 || num - floor(num) ~= 0 || num >= 99999
    error('Please enter a positive integer.')
end

if num > 1
    % Initialize the two vectors, setting each to -1 to avoid false
    % positives in the future
    ticket_vector = zeros([num,1]) - 1;
    used_numbers = zeros([num,1]) - 1;
    
    % set the first ticket to a random number between 0 and 99999, and add
    % that value to a list of used numbers
    ticket_vector(1) = randi(99999);
    used_numbers(1) = ticket_vector(1);

    for ticket = 2:num
        % Set the next ticket value using a random number and declare a 
        % current value for ease of use
        ticket_vector(ticket) = randi(99999);
        current_ticket = ticket_vector(ticket);
        
        % check the current value against all of the previous numbers,
        % 're-rolling' if there is a match
        while any(current_ticket == used_numbers)
            current_ticket = randi(99999);
        end

        % Add the possibly new ticket to the lotto list and that of used 
        % numbers
        ticket_vector(ticket) = current_ticket;
        used_numbers(ticket) = ticket_vector(ticket);
    end
else
    % if the user requested to buy only one ticket, generate one value only
    ticket_vector = randi(99999);
end
end

function winner = jackpot()
% This function generates a winning lotto ticket number
% Format of call: winning_ticket()
% Input: No input
% Output: 1 value
%       winner - value of the winning lotto ticket

% This random number generator finds a single number from a random number
% of random numbers
number = randi([1,9999]);
rand_idx = randi(number);
possibles = randi(99999,1,number);
winner = possibles(rand_idx);
end

function winning_ticket = check_win(tickets_bought, jackpot_val)
% This function determines if the player won the lotto or not
% Format of call: check_win(tickets_bought, jackpot_val)
% Inputs: 2 values
%       tickets_bought - a vector containing the values of the users
%       purchased tickets
%       jackpot_val - the winning lotto ticket number
% Output: 1 value
%       winning_ticket - 1 correpsonds to win, 0 corresponds to lose

% initialize winning ticket value and index for while loop
winning_ticket = 0;
idx = 1;

% loop through the vector until a winner is found or all have been checked
while winning_ticket ~= 1 && idx <= length(tickets_bought)
    % check each ticket against the jackpot, indicating a win if there is a
    % match at any point
    if tickets_bought(idx) == jackpot_val
        winning_ticket = 1;
    else
        % If a winning ticket is not found, go onto the next index
        winning_ticket = 0;
        idx = idx + 1;
    end
end
end

% Problem 2 Functions
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

% continually prompt the user if the want to play or not until they say no
% or run out of money
while money >= 2 && play_again
    current_money = money;
    % tell the useer how much money they have and prompt them for number of
    % spins
    fprintf('You have $%i, how many spins would you like to do? ',money)
    spins = input('');
    % call the related lottery functions
    [outcomes, money] = slot_machine(spins, money);
    spins_won = slot_display(outcomes, current_money);
    money = profit(spins_won, money);
    
    % if the user has enough money, call the function that asks the user
    % if they would like to play again
    if money >= 2
        play_again = keep_going(money);
    end
end

% call the game end function to let the user be free
game_end(money)

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

% check to see if the user enetred a number at all
if isempty(num) == 1 || isstring(num) == 1
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
spin_matrix = strings([num, 3]);
options = ["A" "B" "C"];

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
                fprintf('----%i----\n',spin_num)
                if letter_display == 1
                    fprintf('%s\t',options(letter_display))
                else
                    fprintf('%s\t',current)
                    fprintf('%s\t',options(letter_display))
                    % Computers run extremly quickly and this pause command
                    % allows the user to see the numbers flying by
                    pause(0.05)
                end
            end
        end
        clc;
        % These print statements are here for appearance sake once again
        fprintf('<strong>MATLAB Slots</strong>\n')
        fprintf('----%i----\n',spin_num)
        % Assign a slot in the current vector the actual spin data to
        % display for the next time a spin is displayed
        current(slot_num) = spin_matrix(spin_num,slot_num);
        fprintf('%s\t',current)
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

function game_end(ending_money)
% This function ends the game and thanks the user for playing
% Format of call: game_end
% Inputs: 1 value
%       ending_money - the amount of money the user has at the end of the
%       game
% Outputs: none

% tell the user how much money they ended with and wish them well
clc;
fprintf('Dearest user, \nYou ended with $%i.',ending_money)
fprintf('\n\nThank you for trying my MATLAB slot machine.\nHave a lovely day.\n')
end

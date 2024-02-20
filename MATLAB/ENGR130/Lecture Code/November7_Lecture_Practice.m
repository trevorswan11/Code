%% Lecture Prep 9
clc;
clear;
close all;

% random numbers are useful for modeling or creating games. They are also
% useful for simulating data that you dont yet have
% rand - generates a random number between 0 and 1, not end inclusive 
%       inputting: var = rand generates a random number and stores it
% randi - radnom positve integer generator

% say you want to generate numbers between a cetain range
randtemp = 96 + rand*(102.5 - 96);
% this format is as follows:
% var = min + rand*(max - min)

% say you want to generate random integers
% randi(n), where n is an inclusive max and 1 is inclusive min

% randi([m,n]) is inclusive where m in min and n is max

% random 15 stripes of 5-9in in length
stripe_lengths = randi([5,9],1,15);

% Question: Just how random are the random numbers the computer gives us?

%% Lecture Practice 1
clc;
clear;
close all;

% Find a random group out of 55 groups, then one random student in the
% group of 4 selected
group = randi(55);
student = randi(4);
fprintf('The question goes to group %i, student %i.\n',group, student)

%% Random Numbers?
clc;
clear;
close all;

% initialize vectors
x = zeros(1,5);
y = x;

% reponeing matlab will reset the random numbers, and it will be equal
for k = 1:5
    x(k) = randi(10);
end

% We want to 'seed' the random numbers so that the random numbers are more
% random
rng('shuffle') % seed the program at some point before rand is ran

% rng seeds the generator. 'shuffle' cewates a seed based on the current
% time
for k = 1:5
    y(k) = randi(10);
end

% Seed once for problem in homework. Put it in the top of the problems,
% like right underneath the housekeeping commands
%% The Price is right
clc;
clear;
close all;
rng('shuffle')

fprintf('Welcome to the Big Wheel!\n')
players = input('How many players: ');
cash = zeros(1,players);

for idx = 1:players
    option = "N";
    cash(idx) = wheel_spin();
    % If you have the max amount of cash, you are greater than everyone
    if cash(idx) == max(cash)
        option = input('Would you like to spin again? (Y for yes, N for no)\n', 's');
    end
    if option == "Y" || cash(idx) < max(cash)
        spin_two = wheel_spin;
        cash(idx) = AddTwo(cash(idx), spin_two);
    end
end

FindWinner(cash)

%% Functions 
function dollar = wheel_spin() % the price is right
% This function creates a random integer from 0.05 to 1 that is a multiple
% of 5
% Format of call: wheel_spin()
% Input: No Inputs
% Output: 1 value
%       dollar: random multiple of 5 from 0.05 to 1

random_number = randi(20);
dollar = random_number/20;
fprintf('You spun %.2f!\n',dollar)
end

function new_total = AddTwo(first, new)
% this function finds the total of two spins
% Format of call: Addtwo(first, new)
% Inputs: 2 values
%       first - first spin value
%       new - second spin value
% Output: 1 value
%       new_total - new total of the spins

new_total = first + new;
fprintf('Your new total is %.2f.\n',new_total)

% check to see if the player exceeded a dollar or not
if new_total > 1.00
    new_total = 0;
    fprintf('Your total exceeded $1.00, and you got no money.\n')
end
end

function FindWinner(cash_vector)
% This function find the winner of the game
% Format of call: FindWinner(cash_vector)
% Input: 1 vector
%       vector containing cash values of players
% Ouput: no ouput just text

[winning, winner_player] = max(cash_vector);
fprintf('\NPLayer %i won with $%.2f.\N',winner_player, winning)
end
%% Misc Testing
clc;
clear;
close all;

x = 1:10;
y1 = x.^2;
y2 = x.^3;

p = polyfit(x,y1,3);
fit = polyval(p, x);

fprintf('%.2ft^3-%.2ft^2+%.2ft-%.2f\n',p(1),-p(2),p(3),-p(4))

figure()
subplot(3,1,1)
plot(x,y1)
subplot(3,1,2)
plot(x,y2)
subplot(3,1,3)
plot(x,fit)

figure()
x = 0:.2:12;
Y = [besselj(1,x); besselj(2,x); besselj(3,x)];
plot(x,Y);
legend('First','Second','Third','Location','southeast')

x = 0;
while true
    if x == 10
        break
    end
    x = x + 1;
end

%% Practice 3
clc;
clear;
close all;

% Given (raw) data - time is in seconds, velocity is in miles per hour
time = [0 1 2 2.249 3 4 4.5 5 5.758 6 6.35 7 8 8.652 9 10 11 11.194 12 12.6 13 13.376 14];
velocity_mph = [0 13 25 28 38 50 55.5 59.5 66 68 70 75 81 84 86 90.5 95 96.215 98 100 102 103.499 105];

% Convert from miles per hour to meters per second (this is the correct
% conversion)
velocity_meters_per_second = velocity_mph * 0.44704;

% Determine the coefficients of a third order fit to the velocity (m/s) data
% vs.time
coeff = polyfit(time,velocity_meters_per_second,3);
fit_timeval = linspace(0,14,100);
fit_values = polyval(coeff,fit_timeval);

% Display the third order fit values as a function of t, or time
fprintf(['The third order fit can be represented by the equation %.2ft^3 -%.2ft^2 + %.2ft - %.2f, ' ...
    'where "t" represents time.\n'], coeff(1), -coeff(2), coeff(3), - coeff(4))

% Plot velocity vs. time from the raw data, as well as the third order fit vs.
% time, on the same graph, with appropriate title and labels
figure()
subplot(3,1,1)
hold on
plot(time,velocity_meters_per_second,'or')
plot(fit_timeval,fit_values,'-k')
hold off
title('Velocity of Tesla Model 3 at Max Acceleration vs. Time')
xlabel('Time (s)')
ylabel('Velocity (mph)')

% Location and southeast puts the legend in the bottom right corner of the graph
legend('Measured Data', 'Third Order Fit', 'Location', 'southeast')

% Calculate position of the car (in meters) using the idea of integration, and
% calculate acceleration of the car (in meters per second squared) using
% derivatives. This is based on the time values used for the third order fit,
% not the original time data. It is assumed that the original position of the
% car is zero.
for i = 1:length(fit_timeval)
    position(i) = (coeff(1) / 4 * fit_timeval(i).^4) + (coeff(2) / 3 * ...
        fit_timeval(i).^3) + (coeff(3) / 2 * fit_timeval(i).^2) + (coeff(4) * ...
        fit_timeval(i));
    acceleration(i) = (coeff(1) * 3 * fit_timeval(i).^2) + (coeff(2) * 2 * ...
        fit_timeval(i)) + (coeff(3));
end

% Plot position vs. time on the same figure, with appropriate title and labels
subplot(3,1,2)
plot(fit_timeval,position,'-k')
title('Calculated Position of Tesla Model 3 vs. Time Using Third Order Fit Values')
xlabel('Time (s)')
ylabel('Position (m)')

% Plot acceleration vs. time on the same figure, with appropriate title and labels
subplot(3,1,3)
plot(fit_timeval,acceleration,'-k')
title('Calculated Acceleration of Tesla Model 3 vs. Time Using Third Order Fit Values')
xlabel('Time (s)')
ylabel('Acceleration (m/s^{2})')

%% Problem 4
clear;
clc;
close all
rng('shuffle'); % seed the generator

% set the winning score and current score
winning_score = 45;
current_score = 0;
num_turns = 0;

% indicates whether the game should continue (0) or has been won (1)
game_won = 0;

% simulates the game
while(game_won == 0)
    num_turns = num_turns + 1;

    % calls dice roll function
    fprintf("\nRolling the dice...\n");
    pause(1);
    current_roll = roll_dice();

    if(current_roll == 7) % resets score if 7 is rolled
        fprintf("You rolled 7! Score resets :(\n");
        current_score = 0;
    else % adds roll sum to score and prints out total score
        current_score = current_score + current_roll;
        fprintf("Your current score is %i.\n", current_score);
    end
    % if winning score has been reached, ends game
    if(current_score >= winning_score)
        game_won = 1;
        fprintf("You won the game!\n");
    end
    % saves current score and number of turns in all_rolls vector
    all_rolls(1,num_turns) = current_score;
    all_rolls(2,num_turns) = num_turns;
end
fprintf("It took you %i turns to win the game!", num_turns);

% plots total score over all turns with a line to show the winning score
figure()
plot(all_rolls(2,:), all_rolls(1,:), "-x");
yline(45);
ylabel("Score");
xlabel("Turn Number");
xlim([1 num_turns]);
ylim([0 50]);

%% Problem 5
clc;
clear;
close all;

%This is the seed generator, it guarantees a a random number each time
%MATLAB is started (this does not change).
rng('shuffle')

%Create two variables for the two dice, in which a random value from 1-4 is
%stored in each.
dice_1 = randi([1,4]);
dice_2 = randi([1,4]);

%Create the variable for the sum of the numbers stored in the two variables
%for the dice.
correct_sum = dice_1 + dice_2;

%Call the 'psychic_powers' function created below.
[p_abilities] = psychic_powers(dice_1, dice_2,correct_sum);

%% Problem 6 Function
clc;
clear;
close all;

% Define your daily calorie goal
total_calories = 2000;

% Initialize variables for tracking calories consumed and number of meals eaten
calories_consumed = 5;
num_meals = 0;

% Prompt the user to enter the number of calories for each meal and update
% variables
% Create a loop that asks for the number of calories eaten in a meal
while true
    calories = input('Enter the number of calories in your meal (or enter 0 to finish): ');
    if calories == 0
        break; %If user inputs 0, then the if statement is broken
    end

    %Define the variable for the number of calories eaten, which the amoun
    % of calories inputted added to the stored number of calories consumed
    calories_consumed = calories_consumed + calories;

    %Add one to the stored the number of meals, and sets that
    % back the the variable for the number of meals
    num_meals = num_meals + 1;
end
%Define the average number of calories per meal and number of calories
% left in the day
avg_calories_per_meal = calories_consumed / num_meals;
calories_left = total_calories - calories_consumed;

% Display the results
fprintf('You have %i calories left for the day.\n', calories_left);
fprintf('Your average meal has %.2f calories.\n',avg_calories_per_meal);

%% Problem 7
clear;
clc;
close all;

% Prevents the computer from generating 'pseudorandom' numbers
rng('shuffle')

% Computer randomly generates an integer in the range of 1-100 for the user to
% guess correctly
computer_guess = randi([1, 100]);

% Boolean that stores whether the user's guess matches up with the computer's
% guess. Seeing as we haven't started playing yet, this must be false (this
% line ISN'T wrong!)
isCorrect = false;

% Program gives the user 5 attempts to guess the computer's number correctly
% For each attempt, program tells user if their guess is too high/low or if
% they got it right (after which they exit loop)
for guess_number = 1:5
    % Using guess_number (counter variable) as vector index, this vector stores
    % the guess turn #
    num_guesses(guess_number) = guess_number;

    % Program asks user to input their guess for this particular turn
    user_guess = input("I'm thinking of a number. Give me a number in the range of 1-100: ");

    % Using guess_number (counter variable) as vector index, this vector stores
    % the user guess for each of their attempts
    stored_guesses(guess_number) = user_guess;

    % If user guesses correctly for their given turn
    % At this point, guessing game is over
    if user_guess == computer_guess
        % Display statement congratulating user and telling them the number of
        % tries it took
        fprintf('Congrats, you got my number right! It took you %i tries. I will get you next time... \n', guess_number)

        % Since user's guess correctly matches up w/ computer guess, this
        % variable must now become true
        isCorrect = true;

        % Finish the guessing game and exit the loop
        % If the user guesses correctly before all 5 attempts are used, break
        % prevents them from continuing to play
        break
        % Else, if user's guess is greater than the computer's guess
        % Display statement telling them their guess is too high
    elseif user_guess > computer_guess
        fprintf('Your guess is too high. \n')
        % Finally, if user's guess is less than the computer's guess
        % Display statement telling them their guess is too low
    elseif user_guess < computer_guess
        fprintf('Your guess is too low. \n')
    end
end
% If the user couldn't guess the computer's number in 5 tries or less
% Displays statement letting them know what the computer's number was
if isCorrect == false
    fprintf(['Unfortunately, you were not able to guess my number in 5 tries or' ...
        'less. The number was %i. Better luck next time! \n'], computer_guess)
    pause(0.5) % Pause before plotting
end

% Plotting time!
% Ensures multiple graphs can be plotted in the same figure
hold on

% Plot the guess #s (x-axis) vs. the user's guesses for all of their guess
% attempts using the vectors created earlier in the loop
plot(num_guesses, stored_guesses, "*b")
yline(computer_guess, 'LineWidth', 2) % Plot a line that shows the computer's
% guess (so the user can visually see how close/far their guesses were
% relative to the computer guess)
% Add appropriate titles, legend for the multiple plots, axis labels, and axis
% limits for the graph
title("User Guesses Relative to the Computer Guess")
legend("User Guesses", "Computer Guess")
xlabel("Guess #")
ylabel("User Guess (from 1-100)")
ylim([-10 110])
xlim([0 6])

%% Problem 8
clc;
clear;
close all;

% This asks the user for their number, creates a vector of 100 numbers from
% 1-100, and creates an empty vector where the multiples of the user's number
% will be stored.
user_num = input('Type in an integer number between 1 and 100.\n');
test_nums = linspace(1, 100, 100);
multiples = [];

% The loop tests each number from 1-100 to see if the user's number is a
% multiple of them.
for i = 1:length(test_nums)
    divide_by = test_nums(i);
    remainder = rem(user_num, divide_by);
    if remainder == 0
        multiples(end + 1) = divide_by;
    end
end
% This will tell the user if their number is prime and what their number is
% a multiple of.
if length(multiples) == 2
    fprintf('Your number is a prime number! Its only two multiples are:\n')
    disp(multiples)
else
    fprintf('Your number is a multiple of these numbers:\n');
    disp(multiples)
end

%% Problem 10
clc;
clear;
close all;

%Squirrel height and weight data
height= [14, 15, 16.3, 17, 17.5, 18, 18, 18.9, 20, 22];
weight = [2.3, 1.2, 1.5, 2.8, 1.9, 1.2, 1.7, 2.4, 1, 3.0];

%Calculate the average height and weight
average_height = sum(height)/length(height);
average_weight = sum(weight)/length(weight);

%Print the averages
fprintf('The average height is %f\n', average_height)
fprintf('The average weight is %f\n', average_weight)

% Plot the height and weight data
figure()
plot(height, weight)
title('Weight vs. Height')
xlabel('Height (in)')
ylabel('Weight (lb)')

%% Functions
% Problem 4 Function
function score = roll_dice()
% function to simulate rolling two dice
% format of call: roll_dice()
% inputs: none
% outputs: score (sum of two dice)
% randomly generate dice values and print them out

die1 = randi(6);
die2 = randi(6);
fprintf("You rolled a %i and a %i.\n", die1, die2);
% return the sum of the dice
score = die1+die2;
end

% Problem 5 Function
function [abilities] = psychic_powers(d_1, d_2, c_sum)
%Create 'psychic_powers' function, with output abilities, and inputs 'd_1',
%'d_2', and 'c_sum'.

%Prompt the used to guess the sum of the two numbers
user_guess = input('Enter a guess for the sum: \n');

%If the user guesses the number, print that they have psychic abilities, if
%the do not, give them a second chance. If they guess the sum on the second
%chance, print they might be psychic, since they failed on the first try.
%If they fail again, print they do not have psychic abilities.
if user_guess == c_sum
    abilities = fprintf('YOU HAVE PSYCHIC ABILITIES');

    %If the user enters a number that does not fit the parameters of the
    %possible sums of the dice numbers, prompt them to guess again, but print
    %that this will be their only chance. If they guess, print they might be
    %psychic since they failed the first time. If the do not guess, print they
    %are not psychic.
elseif user_guess < 2 || user_guess > 8
    fprintf('NOT A VALID ANSWER\n');
    fprintf('GUESS AGAIN, THIS WILL BE YOUR ONLY CHANCE\n')
    guess_again = input('Enter a guess for the sum:\n');
    if user_guess == c_sum
        abilities = fprintf('YOU MIGHT HAVE PSYCHIC ABILITIES\n');
    elseif guess_again ~= c_sum
        abilities = fprintf('YOU DO NOT HAVE PSYCHIC ABILITIES\n');
    end

elseif user_guess ~= c_sum
    fprintf('YOU HAVE ONE GUESS LEFT\n');
    second_guess = input('Enter another guess for the sum: \n');
    if second_guess == c_sum
        abilities = fprintf('YOU MIGHT HAVE PSYCHIC ABILITIES\n');
    else
        abilities = fprintf('YOU DO NOT HAVE PSYCHIC ABILITIES\n');
    end



end
end

% Problem Function
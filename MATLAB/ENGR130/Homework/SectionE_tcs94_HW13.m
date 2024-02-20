% Trevor Swan
% ENGR 130 Homework 13
% November 29, 2023

%% Problem 1 - MATLAB Grader
clc;
clear;
close all; 

% bring in data from file and reorganize it into two vectors
% call the new vector of floating point numbers float_vec
% call the new vector of integers int_vec
% note that you will need more lines than the number of blank lines
%   that the file starts with
% use a file ID of in_data because it will keep MATLAB
%    grader happy
in_data = fopen("UnknownNumbers.txt", 'r');
for idx = 1:20 
    float_vec(idx) = fscanf(in_data, '%f', 1);
    int_vec(idx) = fscanf(in_data, '%i', 1);
end
fclose(in_data);

% find the number of elements in each new vector.
% call the number of floating point elements num_float.
% call the number of integer elements num_int.
num_float = numel(float_vec);
num_int = numel(int_vec);

%% Problem 2 - Football Sacks Game
clc;
clear;
close all;

% open the provided file for reading adn store headers in junk file
football_data = fopen("HW13_SackLeaders.txt", 'r');
junk_headers = fscanf(football_data, '%s', 4);

% loop through the txt file and disregard strings, storing sacks and then
% games in their respective games
for idx = 1:20
    junk_col = fscanf(football_data, '%s', 2);
    sacks(idx) = fscanf(football_data, '%f', 1);
    games(idx) = fscanf(football_data, '%i', 1);
end
fclose(football_data);

% calculate the number of sacks/game for the players and call user function
sacks_per_game = sacks./games;
sorted_spg = Big2SmallSort(sacks_per_game)

% prompt the user for their favorite players sack # and amount of games,
% and calculate the number of sacks per game for that player
fprintf('Think of your favorite player...\n')
fav_sacks = input('Enter the number of sacks for that player: ');
fav_games = input('Enter the number of games that player has played: ');

% calculate the sacks per game for the users favorite player
user_spg = fav_sacks/fav_games;
fprintf('\nYour Players Sacks/Game was calculated to be %.4f',user_spg)

% add the users data to the sorted data, and resort it
hypothetical_spg = [sorted_spg user_spg];
hypothetical_sort = Big2SmallSort(hypothetical_spg);

% use logical indexing to determine the placement of the users data
idx = 1:length(hypothetical_sort);
ranking = idx(user_spg == hypothetical_sort);

% display the ranking data to the user, accounting for possibly equal data
if numel(ranking) == 1
    fprintf('\nYour player ranks #%i out of the current data as of 11/20/23.\n',ranking)
else 
    fprintf('\nYour player tied for rank #%i out of the current data as of 11/20/23.\n',ranking(1))
end

% Store the data in a file to be submitted
user_data = fopen("HW13_answer.txt", 'w');
fprintf(user_data, ['The current user has a favorite player with the following stats:' ...
    '\nNumber of Sacks: %f\nGames Played: %i\nSacks per Game: %f'],fav_sacks, fav_games, user_spg);
fclose(user_data);

function sorted_data = Big2SmallSort(input_data)
% This function sorts a vector into data from greatest to least
% Format of call: sorted_data = Big2SmallSort(input_data)
% Input: 1 vector
%       input_data - unsorted sacks/game data for football players
% Output: 1 vector
%       sorted_data - sorted vector from biggest to smallest 

% determine the number of items that will need to be sorted
numsort = length(input_data);

% assign initial conditions to the loop so that it repeats until sorted
switched = 1;
check = 1;
idx = 1;

% the loop should continue if a value has been switched or if the vector is
% being checked
while switched == 1 || check == 1
    if input_data(idx) < input_data(idx + 1)
        % if the next value is less than the current, switch the places
        placehold = input_data(idx);
        input_data(idx) = input_data(idx + 1);
        input_data(idx + 1) = placehold;
        switched = 1;
        check = 0;

        % the index should never be greater than the second largest index
        if idx <= numsort - 2
            idx = idx + 1;
        else
            % reset the index to initialize a checking run after the vector
            % has been fully looked through
            idx = 1;
            check = 1;
        end

    % if there isnt a value mismatch, proceed to the next index
    else
        if idx <= numsort - 2
            idx = idx + 1;
        else
            % reset the index to initialize a checking run
            idx = 1;
            switched = 0;
            check = 1;
        end
    end
    % falsify while conditions if check gets to the end of the of the
    % vector
    if (check == 1 && idx == numsort - 1) && switched == 0
        check = 0;
        switched = 0;
    end
end

% assign the sorted data to the output variable
sorted_data = input_data;
end

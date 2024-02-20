% Trevor Swan
% ENGR 130 Homework 14
% November 5, 2023

%% Problem 1 - MATLAB Grader
clc;
clear;
close all; 

in_phrase = "yo this code is so cool";
% code to call the function
out_phrase = myFunction(in_phrase);

%% Problem 2 - Star Data
clc;
clear;
close all;

% open the provided file for reading and run it through user function
star_data = fopen('HW14_StarData.txt','r');
[name, dist, spectype, appmag] = organizeStar(star_data);
fclose(star_data);

% calculate the percentage of K type stars using user function
percentK(spectype);

% use a user function to display the distance information
distLimit(name, dist);

% Use a user written function to convert the appmag data
absmag = AbsoluteMag(appmag, dist);

% Ask the user to name a star using a function that makes sure its valid 
[user_star, star_idx] = inputStar(name);

% Based on their input, display that star s name, its distance from the 
% earth, its apparent magnitude, and its absolute magnitude
fprintf('Here is the relevent information for your inputted star:\n')
fprintf('\tName: %s\n',name{star_idx})
fprintf('\tDistance from Earth: %.3f Parsecs\n',dist(star_idx))
fprintf('\tApparent Magnitude: %.3f\n', appmag(star_idx))
fprintf('\tAbsolute Magnitude: %.3f\n', absmag(star_idx))

% open a new file for writing
star_calcs = fopen('StarCalcs.txt', 'w');
for star_num = 1:length(name)
    % print the same data from the original data, including absoluet meag,
    % to the new file
    fprintf(star_calcs, '%s\t%.7f\t%s\t%.5f\t%.5f\n', ...
        name{star_num}, dist(star_num), spectype{star_num}, appmag(star_num), absmag(star_num));
end
fclose(star_calcs);



%% Functions
% Problem 1 Function
function[encoded] = myFunction(message)
% this function converts an inputted message into an encoded one based on
% provided rukes
% Format of call: [encoded] = myFunction(message)
% Input: 1 string
%       message - regular english message string type variable
% Output: 1 string
%       encoded - encoded message in string type variable

% account for a surplus of spaces leading or following message
message = strtrim(message);

% divide the message into the words that make it up
words = split(message);

% create a variable containing all vowels 
vowels = 'aAeEiIoOuU';

% sift through the words vector and alter words based on encoding rules
for word = 1:length(words)
    if words{word}(1) == "Y" || words{word}(1) == "y"
        words{word} = strcat(words{word}(2:end),'yay');
    % check to see if the first letter is a vowel
    elseif any(words{word}(1) == vowels) 
        words{word} = strcat(words{word},'sis');
        % if the above statemnts did not work, the only option is a leading
        % consonant
    else
        starting = words{word}(1);
        words{word} = strcat(words{word}(2:end),starting,'bro');
    end
end

% assign the output variable the encoded message
for idx = 1:length(words)
    encoded(idx) = words(idx);
end
end

% Problem 2 Functions
function [names, distances, spectral_t, apparent_m] = organizeStar(file_ID)
% This function sorts a provided file_ID into needed lists
% format of call: [names, distances, spectral_t, apparent_m] = organizeStar(file_ID)
% Input: 1 file
%       file_ID - provided file that has been opened for reading
% Output: 4 vectors
%       names - names of the provided stars
%       distances - distances the stars are from the earth, in parsecs
%       spectral_t - spectral type of the star
%       apparent_m - apparent magnitude of the stars

% Disregard or throw away the first line of headers
junk = fscanf(file_ID, '%s', 4);

% Sort the list into different lists that correspond to the headers
% previously removed
for stars = 1:21
    names{stars, 1} = fscanf(file_ID, '%s', 1);
    distances(stars, 1) = fscanf(file_ID, '%f', 1);
    spectral_t{stars, 1} = fscanf(file_ID, '%s', 1);
    apparent_m(stars, 1) = fscanf(file_ID, '%f', 1);
end

end

function percentK(spectral_type)
% This function determines the percent of stars with spectral type K
% Format of call: percentK(spectral_type)
% Inputs: 1 vector
%       spectral_type: n length vector with single character or string inputs
% Ouput: None
%       only message displayed to the command window
% Analyze the spectype list first, count the number of K spectral types 
% found until the list has been fully analyzed
num_k = 0;
idx1 = 1;

while idx1 <= 21
    if spectral_type{idx1} == "K"
        num_k = num_k + 1;
    end
    idx1 = idx1 + 1;
end

% Tell the user the percentage of the total amount of stars that have 
% a spectral type of K
fprintf('%.2f%% of the stars in the sample have a Spectral Type of K.\n',num_k/length(spectral_type))
end

function distLimit(names, distances)
% This function displays disnatce data to the user based on provided
% vectors
% Format of call: distLimit(names, distances)
% Inputs: 2 vectors
%       names - names of the provided stars
%       distances - distances the stars are from the earth, in parsecs
% Output: None
%       only text displayed to the command window

% find where the name sol comes up in the list of names, store its index
findsol = 0;
idx2 = 1;
while findsol == 0 && idx2 <= 21
    % if sol is found in the name vector, take note of its existance & idx
    if names{idx2} == "sol" || names{idx2} == "Sol"
        findsol = 1;
        sol_idx = idx2;
    end
    idx2 = idx2 + 1;
end

% if the end of the list is reached without a true sol value, tell the user
if findsol == 0 && idx2 == 2
    fprintf('Sol is not included in the provided data.')
end
    
% create a new vector named omit_sol, and remove the value of the index
% that contained sol
omit_sol = distances;
omit_sol(sol_idx) = [];

% determine the min and max distances ignoring sol, keeping track of index
[small_dist, small_idx] = min(omit_sol);
[big_dist, big_idx] = max(omit_sol);

% Tell the user the small distance data - idx is plus one because the name
% vector contains sol still
fprintf('The closest star to the earth is: %s\n',names{small_idx + 1})
fprintf('It is %.2f parsecs away.\n\n',small_dist)

% tell the user the big distance data - idx is plus one because the name
% vector contains sol still
fprintf('The furthest star to the earth is: %s\n',names{big_idx + 1})
fprintf('It is %.2f parsecs away.\n\n',big_dist)
end

function absolute = AbsoluteMag(apparent, distance)
% This function converts apparent magnitude to absoluet magnitude
% Format of call: absoulte = AbsoluteMag(apparent, distance)
% Input: 2 vectors
%       apparent - floating point vector of apparent magnitudes of stars
%       distance -  stars distance from earth, in parsecs
% Output: 1 vector
%       absolute - floating point, converted vector, of absolute mags

% assign the output variable to zeros to make matlab happy
number_stars = length(distance);
absolute = zeros([number_stars, 1]);

% loop through vectors and convert the data, storing continuously
for convert = 1:length(distance)
    absolute(convert, 1) = apparent(convert) - 5*log10(distance(convert)) + 5;
end
end

function [star_name, index] = inputStar(names)
% This function prompts the user for an input and checks to see if it was
% valid
% Format of call: [star_name , lowercase, index] = inputStar(names)
% Inputs: 1 vector
%       names - vector containing all star names
% Output: 2 variables
%       star_name - properly capitalized name of star
%       index - index of the inputted star in the name vector

lowercase = lower(names);
star_input = "";

% ask for a star name input for as long as an incorrect name is inputted
while strcmp(star_input, lowercase) == 0
    % prompt the user for a star name and make it lowercase
    star_input = input('Input the name of the star you want to know about: ','s');
    star_input = lower(star_input);
    
    % tell the user to enter a valid name if there is no match
    if strcmp(star_input, lowercase) == 0
        disp('Please enter a valid name for a star...')
    end
end
star_name = star_input;

% use logical indexing to determine the index of the requested star
indicies = 1:length(names);
index = indicies(strcmp(star_input, lowercase));
end
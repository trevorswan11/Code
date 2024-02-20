%% Lecture Quick Notes
clc;
clear;
close all;

% characters are generally jsut one "keyboard item"
%   singel quotes should be used for characters
% strings can be any length with any type of characetrs
%   must be in double quotes

%% Character Vectors
clear;
clc;

wiz = 'Albus Dumbledore';

% because wiz is in single quotes, it is a series of characters
check_w_size = size(wiz)
check_w_length = length(wiz)

%% Strings
clear;
clc;

my_string = "twine"

% the string is only one unit or element in a variable 
check_twine = size("twine")
check_size = size(my_string)
check_length = length(my_string)

%% Practice 1 - Ranking with Characters & Strings
clear;
clc;

name_1 = 'Henry Jones, Jr.';
name_2 = "Indy";
init_1 = 'H';
init_2 = "IJ";
x = 'Junior';

% test to see what happens with operations
A = length(init_1);                 % A = 1
B = length(init_2);                 % B = 1
C = length(x) - length(name_2);     % C = 5
D = length(name_1);                 % D = 16

fprintf(' A:  %i, B:  %i, C:  %i, D:  %i\n\n', A, B, C, D);


%%  Run above using size

E = size(init_1);
F = size(init_2);
G = size(x) - size(name_2);
H = size(name_1);

fprintf(' E:  %i, F:  %i, G:  %i, H:  %i\n', E, F, G, H);

%% Extract a character vector from a string by using { } brackets

clear;
clc;

my_string = "Susi Lab is the place to be!"; % this is a 1 x 1 string scalar

ans1 = my_string(1) % traditional indexing pulls the whole string

ans2 = my_string{1} % curly bracket indexing makes a single-cell character vector

ans3 = my_string{1}(6:8) % can index a curly bracket to pull separate characters

% coming back to this later
ans4 = my_string{1}(10:end)

new_str = strcat("think[box] ", ans4)



%% Some helpful functions when using Characters and Strings!

% strlength returns the number of characters in the string
clear;
clc;

test_string = "This is a test.";

test_length = length(test_string)
test_size = size(test_string)
test_strlength = strlength(test_string)

%% 

% strcat will join characters or strings - concatenate
clear;
clc;

strspace = strcat("HARRY ","POTTER") % note the space character included here
chrspace = strcat('Ron ', 'Weasley') % but character white space will be dropped
%%

%strtrim will remove both leading and trailing blanks
clear;
clc;

strtrim ('     Goodbye blank characters!     ')

%% 

%strcmp will compare two strings and return a logical operator denoting if
%the strings are identical or not.
clear;
clc;

word1 = 'wand'
word2 = 'WAND'
word3 = "wand"


%for strcmp the entire character array must be identical
comp1 = strcmp(word1, word2) % comp1 = 0

%you can compare char arrays and strings
comp2 = strcmp(word1, word3) % comp2 = 1

%% 

% split divides a string at any white space character
% (ie. space, tab, or new line)
clear;
clc;

phrase =  "It was a dark and stormy night."
words = split(phrase)

% spaces taken out betweeen words, not leading or trailing ones
phrase2 = "              asl;dkfjaslkdjfla            a;skkdjfjlasjdf;lasjfl                 "
words2 = split(phrase2)
disp(words(5))


%%
%strfind(string, substring): finds all occurrences of the substring 
%within the string; returns a vector of the indices of the beginning 
%of the strings, or an empty vector if the substring is not found
clear;
clc;

strfind("abcde", 'd')

strfind("abcdefghi", "ef")

strfind("abcdabceabcdabcdddd", "ab")

strfind("abcd", "e")


%%
%strrep(string, oldsubstring, newsubstring): finds all occurrences of 
%the old substring within the string, and replaces with the new substring
%the old and new substrings can be different lengths
clear;
clc;

strrep("abcdefg", "e", "x")

%count(string, substring): counts the number of occurrences of a 
%substring within a string
count("abcdabceabcdabcdddd", "d")


%% Practice 2, Part 1
clear;
clc;

a = "pre";
b = "natal";
c = strcat(a,b);
fprintf('%s + %s = %s\n', a, b, c)


%% Practice 2, Part 2
clear;
clc;

quote = "Everything has an end except bananas which have 2";
disp(quote)
sliced = split(quote)';
for idx = length(sliced):-1:1
    fprintf('%s ', sliced(idx))
end
fprintf('\n')

%% Practice 3
clc;
clear;

% you need to use the curly brackets to read in strings from txt files
in_data = fopen('Students2.txt', 'r');

% read in individual headers
for header = 1:4
    data{header} = fscanf(in_data, '%s', 1);
end

% read in the individual items from teh columns
for idx = 1:9
    name{idx} = fscanf(in_data, '%s', 1);
    ID(idx) = fscanf(in_data, '%i', 1);
    house{idx} = fscanf(in_data, '%s', 1);
    GPA(idx) = fscanf(in_data, '%f', 1);
end
fclose(in_data);

% find the Gryphindors
for k = 1:9
    if house{k} == "G"
        fprintf('%s\n', name{k})
    end
end
fprintf('---------\n')
% finid teh students that start with an H
for w = 1:9
    if name{w}(1) == "C" || name{w}(1) == 'H' || GPA(w) > 3.5
        fprintf('%s\n', name{w})
    end
end
        
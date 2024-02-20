%% Lecture Prep Notes
% nicknames must be given to files when using fopen or any f command
% fopen() to open and get file ID
% fprintf() to write
% fscanf() to read files
% fclose() when complete
clear;
clc;
close all;

% get information from the user 
name = input('What is your name?\n','s');
num = input('What is your favorite integer?\n');

% save the information to a file
out_file = fopen('records.txt','w'); % open file for writing 'w'

    % send information to the file
    fprintf(out_file, '%s has a favorite integer of %i.\n', name, num);

fclose(out_file); % close file

f_ID = fopen("in.txt",'r'); % open the file for reading 'r'
x = fscanf(f_ID, '%f', 2); % '%f' for floating point and 2 for first two values in the file to be stored. 
    % reading files start at top left and read across rows
fclose(f_ID);

data = fopen('mydata.txt', 'w');
fprintf(data, '%i %i', 2, 1);
fclose(data);
%% Lecture Activity 1
clc;
clear;
close all;

test1 = fopen('mydata.txt', 'r');
% A = fscanf(test1);
fclose(test1);

%% Lecture Practice 1
clc;
clear;
close all;

% open txt file for reading
school = fopen('Student_info.txt', 'r');
for k = 1:10
    studentID(k) = fscanf(school, '%i', 1);
    year(k) = fscanf(school, '%i', 1);
    GPA(k) = fscanf(school, '%f', 1);
end
fclose(school);

%% Lecture Practice 2
clc;
clear;
close all;

% Dealing with Excess data
    %   read into a vector
        %   junk = fscanf(in_data, '%s', 4)
    %   scan, but do not keep
        %   fscanf(in_file, '%f', 1)

music = fopen("Stations.txt", 'r');

% read and discard headers, initialize index
junk = fscanf(music, '%s', 4);
k = 0;

% start by reading station name to see if its the end of file, do not store
while fscanf(music, '%s', 1) ~= '#'
    k = k+1;
    time(k) = fscanf(music, '%f',1);
    t_up(k) = fscanf(music, '%i',1);
    t_down = fscanf(music, '%i',1);
end

fclose(music);
% note: on the exam, you will only need to find ends of files with an
% indicator or certain number of rows




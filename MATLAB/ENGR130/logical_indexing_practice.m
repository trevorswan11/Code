%% vector practice
clear;
clc;

vec = randi([1,10],1,10);
maxi = max(vec);
mini = min(vec);
avg = mean(vec);
idx = 1:1:numel(vec);

max_idx = idx(vec == maxi);
disp(max_idx)

min_idx = idx(vec == mini);
disp(min_idx)

abv_avg = idx(vec>avg);
disp(abv_avg)

%% matrix practice
clear;
clc;
count = 0;
while count <= 100
%  set up counters for checking code
correct_counter = 0;
working_at = 0;

% set up matrix and constants to be used
matrix = randi([1,100],10);
avg = mean(mean(matrix));
use_avg = round(avg);
matrix_size = numel(matrix);
index = 1:matrix_size;

% use logical indexing to find values of @avg, >avg, <avg
abv_avg = matrix(matrix > avg).';
below_avg = matrix(matrix < avg).';
at_avg = matrix(matrix == use_avg).';

% use logical indexing to find indicies of @avg, >avg, <avg
where_abv = index(matrix >= avg);
where_below = index(matrix <= avg);
where_at = index(matrix == use_avg);

% brute force @avg and double check
indicies_at = [];
val = 1;
for idx = 1:matrix_size
    if matrix(idx) == use_avg
        indicies_at(val) = idx;
        val = val + 1;
    end
end
if indicies_at == where_at
    disp('at is equal')
    correct_counter = correct_counter + 1;
elseif isempty(indicies_at) && isempty(where_at)
    disp('at is empty, but equal')
    correct_counter = correct_counter + 1;
    working_at = working_at + 1;
end

% brute force >avg and double check
indicies_abv = [];
val = 1;
for idx = 1:matrix_size
    if matrix(idx) >= avg
        indicies_abv(val) = idx;
        val = val + 1;
    end
end
if indicies_abv == where_abv 
    disp('above is equal')
    correct_counter = correct_counter + 1;
end

% brute force <avg and double check
indicies_blw = [];
val = 1;
for idx = 1:matrix_size
    if matrix(idx) <= avg
        indicies_blw(val) = idx;
        val = val + 1;
    end
end
if indicies_blw == where_below
    disp('below is equal')
    correct_counter = correct_counter + 1;
end

% find command for at
at_found = find(matrix == use_avg).';
if at_found == where_at
    disp('find found at right')
    correct_counter = correct_counter + 1;
elseif isempty(at_found) == isempty(where_at)
    disp('find found at right, they are empty')
    correct_counter = correct_counter + 1;
    working_at = working_at + 1;
end

% find command for abv
abv_found = find(matrix >= avg).';
if abv_found == where_abv
    disp('find found above right')
    correct_counter = correct_counter + 1;
end

% find command for blw
blw_found = find(matrix <= avg).';
if blw_found == where_below
    disp('find found below right')
    correct_counter = correct_counter + 1;
end

% display messages based on how well the user's code works
if working_at == 2
    disp('Your working average correctly aligned with no values')
elseif working_at == 1
    disp('Something is wrong with your working average')
else
    disp('Your working average worked as intended')
end

if correct_counter == 6
    disp('Your code worked perfectly!')
elseif correct_counter > 4
    disp('Your code was partially correct!')
elseif correct_counter > 2
    disp('There are significant errors in your code!')
else
    disp('Your code does not work at all!')
end
count = count +1;
end
%% 
clear;
clc;
close all;

x = -1;
y = -2;
z = 3;

a = x > y +z


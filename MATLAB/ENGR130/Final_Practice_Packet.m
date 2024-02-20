%% Creating a Stations.txt file
clc;
clear;
close all;

stations = fopen('Stations.txt','w');
fprintf(stations, 'Stations\tHrs\tThumbs_Up\tThumbs_Down\n');
fprintf(stations, 'Styx\t32\t14\t4\n');
fprintf(stations, 'Beatles\t24.5\t31\t6\n');
fprintf(stations, 'Simon&Garfunkle\t15.2\t40\t3\n#');
fclose(stations);

%% Creating NewFile.mat
values = [1:2:20, 20:3:30, 40:6:100];
save('NewFile.mat','values')

%% Creating datafile.mat
exams = randi([50,100],[1,186]);
save('datafile.mat', 'exams')

%% Problem 1
clc;
clear;
close all;

(5*6 < 35) & (3-2 > 5)

%% Problem 2
clc;
clear;
close all;

vec = linspace(2,55,30);

%% Problem 3
clc;
clear;
close all;

title_1 = 'A Study in Scarlet';
title_2 = "The Sign of the Four";
name_1 = 'Sherlock Holmes';
name_2 = "Dr. Watson";
author = 'Doyle';

answer_1 = length(title_1) > length(title_2)
answer_2 = length(name_1)/3 == length(author)
answer_3 = strcmp(name_2, title_2) == 0

%% Problem 4
clc;
clear;
close all;

Snoopy = 0;

fprintf('Please help prepare Thanksgiving dinner.\n')

for Woodstock = 1:2:8
    while Snoopy <= 5
        fprintf('Toast slices: %i\n', Snoopy)
        Snoopy = Snoopy + 2;
    end
end

%% Problem 5
clc;
clear;
close all;

num = input('input a positive integer: ');
sequence = Fibonacci(num);

%% Problem 6
clc;
clear;
close all;

in_data = fopen('Stations.txt','r');
for headers = 1:4
    header{headers} = fscanf(in_data, '%s', 1);
end

k = 0;
x = 1;

fprintf('total_time_listening\ttotal_t_up\ttotal_t_down\n')
while fscanf(in_data, '%s', 1) ~= '#'
    k = k + 1;
    time(k) = fscanf(in_data, '%f', 1);
    t_up(k) = fscanf(in_data, '%i', 1);
    t_down(k) = fscanf(in_data, '%i', 1);
    total_time_listening = sum(time);
    total_t_up = sum(t_up);
    total_t_down = sum(t_down);
    fprintf('%.1f\t%i\t%i\n',total_time_listening,total_t_up,total_t_down)
end
fclose(in_data);

%% Problem 7
clc;
clear;
close all;

Browns = [7, 13, 0, 9];
Guardians = [5, 4, 2, 10];
Cavaliers = [88, 75, 101, 92];

points = [Browns; Guardians; Cavaliers];
Browns(3) = 14;
road_trip = Cavaliers([2,3]);
answer_d = Cavaliers./Browns;

%% Problem 8
a = 1:20;
b = linspace(20, 1, 20);
c = a./b;
e = 1:length(b);

for d = 1:length(b)
    e(d) = e(d) * 5;
end

figure()
plot(b, a)

figure()
plot(c, a)

figure()
plot(d, a)

figure()
plot(e, a)

%% Problem 9
clc;
clear;
close all;

load('NewFile.mat');

final = values(end);
bottom = .25*final;
top = .75*final;

for c = 1:length(values)-1
    %***this first conditional should be where the current value is greater
    % than the bottom and the previous value is less than or equal to the bottom (?)
    if values(c+1) >= bottom && values(c) < bottom
        start = c + 1;
    end
    if values(c+1) >= top && values(c) < top
        ending = c;
    end
end

range = ending - start

%% Problem 10
clc;
clear;
close all;

load('datafile.mat')

%*** order of outputs in function call was reversed
[high_score, how_many] = max_info(exams);
percent = 100 * how_many/length(exams);

fprintf('Top score on exam: %i.\n', high_score)
fprintf('Percent of class with score: %.2f.\n', percent)

%% Problem 11
clc;
clear;
close all;

period = input('Please enter how many seconds the object will fall: ');

fprintf('Number of free fall seconds: %.1f\n',period);

g = 9.81;

sec = 1;
while sec <= period
    dist = (1/2)*g*sec^2;
    Isaac(1, sec) = sec;
    Isaac(2, sec) = dist;
    sec = sec + 1;
end

plot(Isaac(1,:), Isaac(2,:))

%% Problem 12
clc;
clear;
close all;

phi = -90 + rand(1,100)*(90--90);
theta = 0 + rand(1,100)*(360-0);
radius = 0 + rand(1,100)*(100-0);
observation_num = randi([1,100]);

fprintf('The coordinates for observation #%i are phi = %.6f deg, theta = %.6f deg, and r = %.6f ly',observation_num, phi(observation_num), theta(observation_num), radius(observation_num))

%% Functions 
function x = Fibonacci(N) % problem 5 function
    x = zeros(1,N);
    x(1) = 1;
    for n = 1:N-1
        x(n+2) = x(n) + x(n+1);
    end
end

function [top, number] = max_info(data) % problem 10 function
    number = 0;
    top = max(data);
    
    for k = 1:length(data)
        if data(k) == top
            number = number + 1;
        end
    end
end

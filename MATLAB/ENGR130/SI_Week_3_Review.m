%% Housekeeping comands
clear;
clc;
close all;

%% While/for loop practice 
clear;
clc;
close all;

% while loop
idx1 = 1;
vector_length = 2000;
vector1 = 1:1:2000;
while idx1 <= vector_length
    vector1(idx1) = vector1(idx1)^2;
    idx1 = idx1 + 1;
end

% for loop
vector2 = 1:1:2000;
for idx2 = 1:2000
    vector2(idx2) = vector2(idx2)^2;
end

% check vectors
if vector1 == vector2
    disp('The vectors are equal!')
else
    error('The written code does not work.')
end

pause(5)
%% Poker mania
clear;
clc;
close all;

% ask the user what theyre name is
name = input('What is your name?\n','s');

% random bets
randombets = randi([1,100],4,7);
round1_sum = sum(randombets(1,:));
round2_sum = sum(randombets(2,:));
round3_sum = sum(randombets(3,:));
round4_sum = sum(randombets(4,:));
total_pot = sum(sum(randombets));
avg_bet = mean(mean(randombets));
bet_vector = [round1_sum round2_sum round3_sum round4_sum];

% pre-game decision
cont_response = 'y';
response = input('\nIt is the first round. It is recommended that you keep playing... y or n: ','s');
if response == 'n'
    disp('I dont know why you stopped playing before the game even started.')
end

% mid-game decisions
for roundnumber = 2:4
    if bet_vector(roundnumber) > bet_vector(roundnumber-1)
        response = input("This round's total is higher than the previous round's total " + ...
            "and it is recommended that you stop playing... y or n: ",'s');
        if response == 'n'
            break
        end
    elseif bet_vector(roundnumber) == bet_vector(roundnumber-1)
        response = input("This round's total is equal to the previous round's total " + ...
            "and you have a high chance of winning if you continue... y or n: ",'s');
        if response == 'n'
            break
        end
    else
        response = input("This round's total is lower than the previous round's total " + ...
            "and it is recommended that you keep playing... y or n: ",'s');
        if response == 'n'
            break
        end
    end
end

% end game
fprintf('Good Game, %s! The total pot size was %i, and the average bet was %.1f.\n',name, total_pot, avg_bet)

pause(5)
%% Vital Checks
clear;
clc;
close all;

% copy-paste data
PR = [95 104 102 63 92 39 98 23 50 25 30 111 97 55 125 23 68];
BR = [31 32 17 30 29 13 11 24 39 19 27 15 32 16 24 31 37];
len = numel(PR);


% create misc vectors and vars
bad_pr = zeros(1,len);
bad_br = zeros(1,len);
num_near = 0;
num_die = 0;
num_alive = 0;

% run condidtional statemnts for each pateient
for test = 1:len
bad_pr(test) = (PR(test) < 60 || PR(test) > 100);
bad_br(test) = (BR(test) < 12 || BR(test) > 25);
end

% determine patients condition
for patient = 1:1:len
    if bad_pr(patient) && bad_br(patient)
        patient_status{patient} = 'nearing the light';
        fprintf('\nPatient %i has a pulse rate of %i and a breathing rate of %i.\nThe patient is %s.' ...
            ,patient, PR(patient), BR(patient),patient_status{patient})
        num_near = num_near + 1;
    elseif bad_pr(patient) || bad_br(patient)
        patient_status{patient} = 'dying';
        fprintf('\nPatient %i has a pulse rate of %i and a breathing rate of %i.\nThe patient is %s.' ...
            ,patient, PR(patient), BR(patient),patient_status{patient})
        num_die = num_die + 1;
    else
        patient_status{patient} = 'alive and well';
        fprintf('\nPatient %i has a pulse rate of %i and a breathing rate of %i.\nThe patient is %s.' ...
            ,patient, PR(patient), BR(patient),patient_status{patient})
        num_alive = num_alive + 1;
    end
end

% Display final information
fprintf(['\n\nAll %i patients have been accounted for. Amount/gorup are as follows:\n' ...
    'Alive and well: %i\nDying: %i\nNearing the light: %i\n'],len,num_alive,num_die,num_near)

pause(5)
%% Test your understadning
clear;
clc;
close all;

% Problem 1
x = 1;
y = 10;
while x <= y
x = x + 1;
y = y - 2;
end
fprintf('X = %i.\nY = %i.\n',x,y)

% problem 2
m = 0;
h = 0;
for z = 1:3
    for w = 3:5
        h = h + 1;
    end
    m = m + w*z;
end
fprintf('M = %i.\nH  = %i.\nZ = %i.\nW = %i.\n',m,h,z,w)
% Calc-oholics: Calculus in Minecraft
%% Experiment 1 - Unreliable
clc;
clear;
close all;

% Gather Data From Experiments
blocks = [25 50 75 100 150 200 256];
time = [1.316166667 1.976875 2.486708333 3.040541667 3.7955 4.660333333 5.480208333];
velocity = [18.99455489 25.29244388 30.16035254 32.88887671 39.52048478 42.91538517 46.71355256];  

% Plot time vs. blocks
figure()
plot(blocks, time)
title('Vertical Travel Time based on Hand Timing')
xlabel('Blocks (m)')
ylabel('Time (s)')

% Plot velocity vs blocks and velocity vs time in one subplot
figure()
subplot(2,1,1)
plot(blocks, velocity)
title('Velocity at Set Heights')
xlabel('Blocks (m)')
ylabel('Velocity (m/s)')

subplot(2,1,2)
plot(time, velocity)
title('Hand Timed Velocity over Time')
xlabel('Time (s)')
ylabel('Velocity (m/s)')

%% Experiment 2 Results
clc;
close all;

% Input Trial Results
height = [10000 20000 30000];
player_rods = [325 644 963];
player = "Time taken for a Player to Fall";

% Determine the data for the player experiment data
[player_time, player_terminal] = experiment_data(height, player_rods, player);

%% Experiment 3 Results
clc;
close all;

% Input Trial Results
height = [10000 20000 30000];
arrow_rods = [262 512 762];
arrow = "Time Taken for an Arrow to Fall";

% Determine the data for the arrow experiment data
[arrow_time, arrow_terminal] = experiment_data(height, arrow_rods, arrow);

%% Experiment 4 Results
clc;
close all;

% Input Trial Results
height = [10000 20000 30000];
item_rods = [631 1255 1881];
item = "Time Taken for an Item to Drop";

% Determine the data for the item experiment data
[item_time, item_terminal] = experiment_data(height, item_rods, item);

%% Experiment 5 Results
clc;
close all;

% Input Trial Results
height = [10000 20000 30000];
armor_rods = [325 644 963];
armor = "Time Taken for an Item to Drop";

% Determine the data for the item experiment data
[armor_time, armor_terminal] = experiment_data(height, armor_rods, armor);

%% Experiment 6 Results
clc;
close all;

% Input Trial Results
sand_height = 170:43:342;
sand_rods = [16 19 21 25 27];
sand = "Time Taken for an Item to Drop";

% Determine the data for the item experiment data
[sand_time, sand_terminal] = experiment_data(sand_height, sand_rods, sand);

%% Save All Experiment Results
save("MATH223_Constants.mat", "player_terminal", "arrow_terminal", "item_terminal", "sand_terminal")
save("MATH223_Time_Data.mat", "player_time", "arrow_time", "item_time", "sand_time")
clc;
clear;
close all;

% It should be noted that falling blocks were not rigourously tested, and
% should really be 40m/s, instead of 38m/s

%% Gravity Calculator
clc;
clear;
close all;

int_height = input('Height: ');
int_velocity = input('\nVelocity: ');
time = input('\nTime: ');

g = gravity(int_height, int_velocity, time);

%% Mathmatica Input Generator
clc;
clear;
close all;

% y = vt + v^2/g(e^-gt/v) -v^2/g
y = input('y = ');
v = input('v = ');
t = input('t = ');

fprintf('Solve[%i == %.6f*%.1f + (%.6f^2)*(1/g)*(Exp[-g*%.1f/%.6f] - 1), g]\n\n' ...
    ,y,v,t,v,t,v)

% get g value from mathmatica
g = input('g = ');

% display numerator of dg/dt
fprintf('Solve[dt==(-%.6f*(1-Exp[-%.7f*(%.1f/%.6f)]))/',v,g,t,v)

% display denominator of dg/dt
fprintf('(((%.6f^2)/(%.7f^2))*(1-Exp[-%.7f*(%.1f/%.6f)])-((%.1f*%.6f)/%.7f)*(Exp[-%.7f*(%.1f/%.6f)]))]\n\n', ...
    v,g,g,t,v,t,v,g,g,t,v)

% display numerator of dg/dv
fprintf('Solve[dv==(%.1f+2*(%.6f/%.7f)*Exp[-%.7f*(%.1f/%.6f)]+%.1f*Exp[-%.7f*(%.1f/%.6f)]-2*(%.6f)/(%.7f))/', ...
    t,v,g,g,t,v,t,g,t,v,v,g)

% display denominator of dg/dv
fprintf('(((%.6f^2)/(%.7f^2))*(1-Exp[-%.7f*(%.1f/%.6f)])-((%.1f*%.6f)/%.7f)*(Exp[-%.7f*(%.1f/%.6f)]))]\n\n', ...
    v,g,g,t,v,t,v,g,g,t,v)

% get the partial derivative values
dt = input('dg/gt = ');
dt = abs(dt);

dv = input('dg/dv = ');
dv = abs(dv);

y1= input('y1 = ');
t1 = input('t1 = ');
t_uncert = 0.4;
v_uncert = ((y - y1)/(t - t1)^2)*(t_uncert);

% display the uncertainty equation
fprintf('Solve[uncert==Sqrt[%.7f*0.4^2 + %.7f*%.7f^2]]\n',dt,dv,v_uncert)

%% functions
function terminal = terminal_v(height, time)
% This function determines the terminal velocity of a data set
% Format of call: terminal_v(height, time)
% Inputs: 2 vectors
%       height - numerator of slope/velocity unit
%       time - denominator of slope/velocity unit
%  ex. height in meters, time in seconds -> v = m/s
% Output: 1 value
%       terminal - terminal velocity of the object in question

% check to see if the vectors are equal length
[rowx, colx] = size(height);
[rowy, coly] = size(time);
if (rowx ~= rowy) || (colx ~= coly)
    error('Please Use Vectors of equal Dimensions.')
end

% Determine the line of best fit for the data
linear_coef = polyfit(time, height, 1);
terminal = linear_coef(1);
end

function [time, terminal_velocity] = experiment_data(height, rods, str)
% This function determines the time vector for height and the items
% Terminal
% Format of call: experiment_data(height, rods)
% Inputs: 3 values
%       height - vector of heights the subject was dropped from 
%       rods - number of rods outputted based on height data
%       str - string variable for graph name
% Outputs: 2 values
%       time - calculated time vector for the heights
%       terminal_velocity - terminal sped obtained by the object

% Each item pumped through a hopper takes 0.4 seconds
time = 0.4*rods;
terminal_velocity = terminal_v(height, time);

% Create a seubplot with 'str' as the table
figure()
subplot(2,1,1)
plot(rods, height, 'b')
title(str)
ylabel('Height (m)')
xlabel('Time (rods)')

subplot(2,1,2)
plot(time, height, 'g')
title(str)
ylabel('Height (m)')
xlabel('Time (s)')
end

function g = gravity(height, velocity, time)
% This function uses an add on to solve an equation
% Format of call: gravity(height, velocity, time)
% Inputs: 3 values
%       Height: Change in Height
%       velocity: Initial Velocity
%       time: Total time taken 
% Output: 1 value
%       g = gravity the object experiences

% set up constants for the equation
vt = velocity*time;
vsq = velocity^2;
ntov = -time/velocity;

% solve for g
syms g
g = solve(vt + (vsq/g)*exp(-g*ntov) - vsq/g -height,g,"ReturnConditions",false);

end

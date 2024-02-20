%% Lecture Discussion
% feed-forward control loops dont take current system state into account.
% feed-back control loops loop back and send current system state back to
% the system. 

% PID controllers take in the current error, predicted future error, and 
% past error to accurately control a system. They do this using integrals
% (I), and derivatives (D). Using the current rate of change and summing up
% the previous error's allows the machine to determine and control the
% system. P stands for proportional. error is the amount a system is away
% from its command or goal

%% Code debugging tips
% make it readable
% test small bits as a time
% remove semicolons
% test functions in main script first

% pressing 'run to here' on a line and then running the code will make
% matlab stop at the line. then you can press 'step-in' when paused in
% order to take a look in a matlab functions workspace. 
% 
% logical errors occur when the code runs but not correctly. clicking lines
% and turning them red allow you to set breakpoints and step into functions


% Jack Shapiro, Lily Kagy, Trevor Swan, Karis Mao
% ENGR 130 Module 6.2 Report
% Section E
% December 3rd, 2023

%% Lab 1
clc;
clear;
close all;

% load matlab data
load ('MEASURED_RPM.mat');

% plot RPM vs time
figure()
grid on
plot (time, RPM);
yline (1000,'-b');
ylim ([0,1200]);
xlabel('Response Time (s)')
ylabel('RPM (rotations/minute)')
title('Typical unit-step response of a control system')

% calls the function
[RPM_final, maxOs, rise_t, set_t] = getWaveformData (RPM, time);

%% Lab 2 - P Controller
clc;
clear;
close all;

% test p control with p = 20
[P_time1,P_RPM1] = control_system('P', 20);
plot(P_time1, P_RPM1); hold on
[P_RPM_f1, PmaxOs1, P_rise_1, P_set_1] = getWaveformData (P_RPM1, P_time1);

% test p control with p = 40
[P_time2,P_RPM2] = control_system('P', 40);
plot(P_time2, P_RPM2)
[P_RPM_f2, PmaxOs2, P_rise_2, P_set_2] = getWaveformData (P_RPM2, P_time2);

% test p control with p = 60
[P_time3,P_RPM3] = control_system('P', 60);
plot(P_time3, P_RPM3)
[P_RPM_f3, PmaxOs3, P_rise_3, P_set_3] = getWaveformData (P_RPM3, P_time3);

% test p control with p = 80
[P_time4,P_RPM4] = control_system('P', 80);
plot(P_time4, P_RPM4)
[P_RPM_f4, PmaxOs4, P_rise_4, P_set_4] = getWaveformData (P_RPM4, P_time4);

% test p control with p = 100
[P_time5,P_RPM5] = control_system('P', 100);
plot(P_time5, P_RPM5); hold off
[P_RPM_f5, PmaxOs5, P_rise_5, P_set_5] = getWaveformData (P_RPM5, P_time5);

% add appropriate labels to the plot
title('P Control')
xlabel('Response Time (s)')
ylabel('RPM (Rotations/min)')
legend('P = 20', 'P = 40', 'P = 60', 'P = 80', 'P = 100')

%% Lab 2 - PD Controller
clc;
clear;
close all;

% set optimal p value
opt_p = 40;

% test PD control with D = 4
[PD_time1,PD_RPM1] = control_system('P', opt_p, 'D', 4);
plot(PD_time1, PD_RPM1); hold on
[PD_RPM_f1, PDmaxOs1, PD_rise_1, PD_set_1] = getWaveformData (PD_RPM1, PD_time1);

% test PD control with D = 8
[PD_time2,PD_RPM2] = control_system('P', opt_p, 'D', 8);
plot(PD_time2, PD_RPM2)
[PD_RPM_f2, PDmaxOs2, PD_rise_2, PD_set_2] = getWaveformData (PD_RPM2, PD_time2);

% test PD control with D = 12
[PD_time3,PD_RPM3] = control_system('P', opt_p, 'D', 12);
plot(PD_time3, PD_RPM3)
[PD_RPM_f3, PDmaxOs3, PD_rise_3, PD_set_3] = getWaveformData (PD_RPM3, PD_time3);

% test PD control with D = 16
[PD_time4,PD_RPM4] = control_system('P', opt_p, 'D', 16);
plot(PD_time4, PD_RPM4)
[PD_RPM_f4, PDmaxOs4, PD_rise_4, PD_set_4] = getWaveformData (PD_RPM4, PD_time4);

% test PD control with D = 20
[PD_time5,PD_RPM5] = control_system('P', opt_p, 'D', 20);
plot(PD_time5, PD_RPM5); hold off
[PD_RPM_f5, PDmaxOs5, PD_rise_5, PD_set_5] = getWaveformData (PD_RPM5, PD_time5);

% add appropriate labels to the plot
title('PD Control')
xlabel('Response Time (s)')
ylabel('RPM (Rotations/min)')
ylim([760, 1300])
legend('D = 4', 'D = 8', 'D = 12', 'D = 16', 'D = 20')

%% Lab 2 - PID Controller
clc;
clear;
close all;

% set optimal p and d values
opt_p = 40;
opt_d = 20;

% test PID control with I = 4
[PID_time1,PID_RPM1] = control_system('P', opt_p, 'D', opt_d, 'I', 4);
plot(PID_time1, PID_RPM1); hold on
[PID_RPM_f1, PIDmaxOs1, PID_rise_1, PID_set_1] = getWaveformData (PID_RPM1, PID_time1);

% test PID control with I = 8
[PID_time2,PID_RPM2] = control_system('P', opt_p, 'D', opt_d, 'I', 8);
plot(PID_time2, PID_RPM2)
[PID_RPM_f2, PIDmaxOs2, PID_rise_2, PID_set_2] = getWaveformData (PID_RPM2, PID_time2);

% test PID control with I = 12
[PID_time3,PID_RPM3] = control_system('P', opt_p, 'D', opt_d, 'I', 12);
plot(PID_time3, PID_RPM3)
[PID_RPM_f3, PIDmaxOs3, PID_rise_3, PID_set_3] = getWaveformData (PID_RPM3, PID_time3);

% test PID control with I = 16
[PID_time4,PID_RPM4] = control_system('P', opt_p, 'D', opt_d, 'I', 16);
plot(PID_time4, PID_RPM4)
[PID_RPM_f4, PIDmaxOs4, PID_rise_4, PID_set_4] = getWaveformData (PID_RPM4, PID_time4);

% test PID control with I = 20
[PID_time5,PID_RPM5] = control_system('P', opt_p, 'D', opt_d, 'I', 20);
plot(PID_time5, PID_RPM5); hold off
[PID_RPM_f5, PIDmaxOs5, PID_rise_5, PID_set_5] = getWaveformData (PID_RPM5, PID_time5);

% add appropriate labels to the plot
title('PID Control')
xlabel('Response Time (s)')
ylabel('RPM (Rotations/min)')
ylim([960, 1040])
legend('I = 4', 'I = 8', 'I = 12', 'I = 16', 'I = 20')

%% Lab 3 - Manual Control
clc;
clear;
close all;

% load the data from the oscilloscope
data = readmatrix("t0123_t4.csv");

% determine the length ofo the data, and get rid of unusable entries
limit = length(data);
real_data = data(4:limit, 2);

% create a distance vector using the function, create a time vector from
% data
distance = volt2dist(real_data);
time = data(4:limit, 1) + 20;

% convert to column vectors and call function from lab 2
distance = distance';
time = time';
[finalHeight3, maxOvershoot3, riseT3, settlingT3] = getWaveformData(distance, time);

%% Lab 4 - PID Tuning
clc;
clear;
close all;

% taking values from scope
values = readmatrix("scope_e1b.csv");

% removing unusable data from the beginning of vector
data = [values(638: end, :)];

% seperating data into 2 vectors
distance = data(4:length(data), 2);
time = data(4:length(data), 1) + 20; % added 20 to adjust for negative times

% convert to column vectors and call function from lab 2
distance = distance';
time = time';
[finalHeight4, maxOvershoot4, riseT4, settlingT4] = getWaveformData(distance, time);



%% Functions
% Lab 1 function
function [finalRPM, maxOvershoot, tr, ts] = getWaveformData(RPM, time)
% This function calculates the final RPM, max
% Format of call: getWaveformData (RPM, time)
% Input: 2 values
%       RPM - RPmM vector of the system
%       time - time vector of the system
% Output: 4 values
%       finalRPM - the final RPM reached
%       maxOveshoot - the calculated max overshoot
%       tr - the rise time
%       ts - the settling time

% calculates final RPM
finalRPM = RPM (end);

% calculates maximum overshoot
maxOvershoot = (max(RPM)-finalRPM)*100 / finalRPM;

% calculates the rise time
%calculates the 10% and 90% final value values
lower_tr = 0.1 * finalRPM;
upper_tr = 0.9 * finalRPM;

% finds the value where RPM crosses 10% of its final value
tr_idx1 = 1;
while RPM (tr_idx1) <= lower_tr
    tr_idx1 = tr_idx1 + 1;
end

%finds the value where RPM crosses 90% of its final value
tr_idx2 = 1;
while RPM (tr_idx2) <= upper_tr
    tr_idx2 = tr_idx2 + 1;
end

% calculates the rise time by finding the difference in the times at 10%
% final value and 90% final value
tr = (time(tr_idx2)- time(tr_idx1));

% calculates settling time
% calculates RPM values that are 105% and 95% of the final RPM
upper_ts = 1.05 * finalRPM;
lower_ts = 0.95 * finalRPM;

% loop that determines the index value that the RPM was outside the specified
% range
for idx = 1: length (RPM)
    if RPM(idx) > upper_ts || RPM(idx) < lower_ts
        ts_idx = idx + 1;
    end
end

% determines the time where the RPM begins to be inside the range
ts = time(ts_idx);
end

% Lab 2 function
function [new_time, new_RPM] = control_system(varargin)
%CONTROL_SYSTEM simulates either manual or PID control of a DC Motor
%
% Syntax:
%   [new_time, new_RPM] = control_system('P', Kp)
%   [new_time, new_RPM] = control_system('P', Kp, 'D', Kd)
%   [new_time, new_RPM] = control_system('P', Kp, 'I', Ki, 'D', Kd)
%
% Inputs:
%    'P' - Callout the fact that the next input is proportional gain value
%    Kp - The value of proportional gain
%    'I' - Callout the fact that the next input is integral gain value
%    Ki - The value of integral gain
%    'D' - Callout the fact that the next input is derivative gain value
%    Kd - The value of derivative gain
%
% Outputs:
%    new_time - The resulting x-axis of time values in a vector from simulation
%    new_RPM - The resulting y-axis values of simulated RPM control in a vector from simulation
%
% Example:
%   Simulate the Proportional Gain at 50
%   [t, y] = control_system('P', 50)
%   Simulate a PD Control with values of 20 and 5
%   [t, y] = control_system('P', 20, 'D', 5)

% April 2020; Last revision: 10-November-2020
%
%------------- BEGIN CODE --------------

Kp = 0;
Ki = 0;
Kd = 0;

switch nargin
    case 2
        switch varargin{1}
            case 'P'
                Kp = varargin{2};
            case 'I'
                Ki = varargin{2};
            case 'D'
                Kd = varargin{2};
            otherwise
                warning("This is not a valid character!")
        end
    case 4
        switch varargin{1}
            case 'P'
                Kp = varargin{2};
            case 'I'
                Ki = varargin{2};
            case 'D'
                Kd = varargin{2};
            otherwise
                warning("This is not a valid character!")
        end
        
        switch varargin{3}
            case 'P'
                Kp = varargin{4};
            case 'I'
                Ki = varargin{4};
            case 'D'
                Kd = varargin{4};
            otherwise
                warning("This is not a valid character!")
        end
        
        case 6
        switch varargin{1}
            case 'P'
                Kp = varargin{2};
            case 'I'
                Ki = varargin{2};
            case 'D'
                Kd = varargin{2};
            otherwise
                warning("This is not a valid character!")
        end
        
        switch varargin{3}
            case 'P'
                Kp = varargin{4};
            case 'I'
                Ki = varargin{4};
            case 'D'
                Kd = varargin{4};
            otherwise
                warning("This is not a valid character!")
        end
        
        switch varargin{5}
            case 'P'
                Kp = varargin{6};
            case 'I'
                Ki = varargin{6};
            case 'D'
                Kd = varargin{6};
            otherwise
                warning("This is not a valid character!")
        end
        
    otherwise
        warning("Invalid amounts of arguments! Check format of function.")
end

if Kp <= 100 && Kd <= 20 && Ki <= 20
    
    s = tf('s');
    P = 1/(1*s^2 + 1.125*s + 1);
    C = pid(Kp, Ki, Kd);
    T = feedback(P*C,1);
    
    [y, t] = step(T,20);
    new_time = t;
    new_RPM = y*1000;
else
    new_time = -1;
    new_RPM = -1;
    warning("The controller cannot use one of your values! It is too high!")
end

end

% Lab 3 Functions
function distance = volt2dist(voltage)
% This function takes in a voltage vector input and converts it to distance
% Format of call: distance = volt2dist(voltage)
% Input: 1 vector
%       voltage - measured voltage data (in Volts)
% Output: 1 vector
%       distance - converted voltage vector to distance vector (in cm)

% Input the manual control data from lab
sensor = [1.26, 1.51, 1.96, 2.36];
% fan_speed = [32, 33, 35, 39];
height = [6 9 11.5, 14.25];

% use manual data to create a fit line for the eqaution
fit_coef = polyfit(sensor, height, 2);

% use the coefs calculated to convert any inputed V vector to cm
distance = polyval(fit_coef, voltage);
end
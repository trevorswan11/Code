%% Housekeeping comands
clear;
clc;
close all;

%% Problem 1
clear;
clc;
close all;

% Given data
hold on
height_data = [0 1.7 1.95 2.6 2.92 4.04 5.24];
flow_data = [0 2.6 3.6 4.03 6.45 11.22 30.61];

% plot given data 
plot(height_data,flow_data,'r--','LineWidth',2)
xlabel('Height, feet')
ylabel('Flow, feet^3/second')
title('Rate of flow based on height of an unknown liquid')

% find linear fit line and plot on same figure
linear_fit = polyfit(height_data,flow_data,1);
linear_data = polyval(linear_fit, height_data);
plot(height_data, linear_data,'m:','LineWidth',2)

% find quadratic fit line and plot on same figure 
quad_fit = polyfit(height_data,flow_data,2);
quad_data = polyval(quad_fit, height_data);
plot(height_data, quad_data,'g-.','LineWidth',2)

% find cubic fit line and plot on same figure
cubic_fit = polyfit(height_data,flow_data,3);
cubic_data = polyval(cubic_fit, height_data);
plot(height_data,cubic_data,'k','LineWidth',1.5)

% create a legend
legend('Original data','Linear fit eq','Quadratic fit eq','Cubic fi eq')
hold off
%% Problem 2
clear;
clc;
close all;

% given data
hold on
days = 1:1:20;
blood_pressure = [173.28 175.4 176.92 177.93 178.55 178.87 178.98 179 179.02 179.13 179.45 180.07 ...
    181.08 182.6 184.72 187.53 191.15 195.67 201.18 207.8];

% plot given data
plot(days, blood_pressure,'m--','LineWidth',3)
xlabel('Days')
ylabel('Blood pressure, mmHg')
title("A patient's bloodpressure over 20-day period")

% fit equation
fit_coef = polyfit(days, blood_pressure,3);
fit_data = polyval(fit_coef,days);
plot(days,fit_data,'k-')
legend('original data','fit equation')
hold off

% plot predictive line
figure(2)
d = [21 22 23 24 25];
days_25 = polyval(fit_coef,d);
day_pred = 1:25;
bp_pred = [fit_data days_25];
plot(day_pred,bp_pred,'k','LineWidth',2)
xlabel('Days')
ylabel('Blood pressure, mmHg')
title('A patients predicted blood pressure over time')

% find the day when the pateint becomes death
hyper = 0;
for count1 = 1:20
    if blood_pressure(count1) > 180
        day_hyper = count1;
        hyper = 1;
        break
    end
end

% check if patient becomes healthy again
unhyper = 0;
for count2 = count1:20
    if blood_pressure(count2) < 180
        day_un_hyper = count2;
        unhyper = 1;
        break
    end
end

% display the patients bp if they go hyper
if hyper == 1
    fprintf(['The patient started to experience hypertensive crisis blood pressure ' ...
        'on day %i.\nTheir predicted blood pressure in 5 days is %.2f mmHg.\n'],day_hyper,days_25(5))
        if unhyper == 1
            fprintf(['The patient stopped experiencing hypertensive crisis blood pressure' ...
        'on day %i.\nTheir predicted blood pressure in 5 days is %.2f mmHg.\n'],day_un_hyper,days_25(5))
        end
else
    fprintf(['The patient is healthy, and their predicted blood pressure 5 days from now ' ...
        'is %.2f mmHg.\n\n'],days_25(5))
    if days_25(5) > 180
        fprintf('Due to the magnitude of the predicted blood pressure, it is suggested that action is taken.')
    end
end
%% 

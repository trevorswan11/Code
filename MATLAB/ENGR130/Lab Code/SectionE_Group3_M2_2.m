% Trevor Swan, Jennifer Baek, Nissa Robinson, Karthik Rajasekar
% ENGR 130 Module 2.2 Report
% Section E
% October 1, 2023

%% Lab 3
clear;
clc;
close all;
a = arduino();

% Part 1 - copy and paste from the previous lab. Read the voltage 4 times a second for 25 seconds, 
% storing the voltage in a vector 
voltage_vector = zeros(1,100);
for count = 1:100
    voltage_vector(count) = readVoltage(a,'A0');
    pause(1/4)
end

% Part 2 - calculate the mean voltage with built-in fucntions and display
% it to the user. This should be done in a separate section if you are
% collecting data from independent types of water
mean_voltage = mean(voltage_vector);
disp(mean_voltage)

% record initial data vectors. mean_voltages contains experimentally
% gathered data from lab periods
ntu_values = [1000 500 250 0];
mean_voltages = [2.7928 3.1493 3.2495 3.4499];

% plot the ntu values against the mean voltages, using descriptive labels
hold on 
plot(mean_voltages,ntu_values,'ro','LineWidth',3)
xlabel('Voltage')
ylabel('NTU')
title("Relationship Bewteen Voltage and NTU")

% We used a 100 length vector to create an accurate fit line. The varibale
% x_fit is used to plot the data agaiinst more data points, leading to 
% a more accurate reading 
x_fit = linspace(2.7928,3.4499,100);
voltage_fit = polyfit(mean_voltages, ntu_values,2);
voltage_equa = polyval(voltage_fit, x_fit);

% plot the fit line on the same plot as the original data
plot(x_fit, voltage_equa)
    %  We expected to get a quadratic fit line from our data, 
    % but we got a linear equation instead. A note on this
    % can be found in the lab report PDF

% create a legend for the plot
legend('data 1','quadratic fit')
hold off

% display the NTU equation to the user, reading the first, second, and
% third terms from the voltage_fit vector for each fpritnf entry
fprintf('NTU = %.2fv^2 + %.2fv + %.2f\n',voltage_fit(1), voltage_fit(2), voltage_fit(3))
%% Lab 4 - Filter Testing
clear;
clc;
close all;
a = arduino();

% Part 1 - create a vector containing voltage values that are read from the
% sensor every .2 seconds for 1 second. This is useful for testing our 
% water filter to see a possible NTU difference
voltage_vector = zeros(1,5);
for count = 1:5
    voltage_vector(count) = readVoltage(a,'A0');
    pause(.2)
end

% calculate the mean voltage using built-in functions
voltage_value = mean(voltage_vector);

% calculate the mean ntu value. This is our equation from the previous part
NTU_value = -31.66*voltage_value^2 - 1348.83*voltage_value + 5018.32;

% display the voltage and ntu values, with the first fprintf entry being
% voltage, and the second being ntu.
fprintf('The Voltage is %.3f\nThe NTU value is %.3f\n',voltage_value,NTU_value)
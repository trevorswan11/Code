%% ENGR145 HW4 Graph
clear;
% clc;
close all;

wt_percent = [0 20 40 60 80 90 95 100];
solidus_temperature = [1085 1019 972 934 911 928 974 1064];
liquidus_temperature = [1085 1042 996 946 911 942 984 1064];
y_max = linspace(1085,1085,8);
y_min = linspace(900, 900, 8);

hold on
plot(wt_percent,solidus_temperature,'r','LineWidth',1.5)
plot(wt_percent,liquidus_temperature,'b','LineWidth',1.5)
xlabel('Composition, wt% Au','FontSize',15)
ylabel('Temperature, C','FontSize',15)
title('Copper-Gold Phase Diagram','FontSize',20)
axis([0 100 900 1100])
grid on

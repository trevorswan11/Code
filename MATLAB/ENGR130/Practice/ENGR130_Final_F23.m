%% The Basics
clear;
clc;
close all;

% create a 10 length vector with a min of 1 and max of 90
vec1 = linspace(1,90,10);

% test different '.' opperations
vec2 = [vec1;vec1];
vec3 = rand([2,10]).*vec2;

%% Input/Output
clear;
clc;
close all;

% disp checking
a = [1 2 3];
disp(a)

b = 'hello';
disp(b)

c = "hello world";
disp(c)

disp('a')
disp("b")

%% Loops
clc;
clear;
close all;

a = 1;
count = 0;
while a
    disp('c')
    a = randi([1,80],[1,7]);
    if a(randi([1,7])) == 60
        a = 0;
    else 
        a = 1;
    end
    count = count + 1;
end

%% Plotting
clc;
clear;
close all;

x = 0:0.001:100;
y1 = sin(x);
y2 = cos(x);

plot(x,y1,'b'); hold on;
plot(x,y2,'r')

% Limit commands
xlim([0,100])
ylim([-1.2,1.2])

% important specifiers
xlabel('X-Axis')
ylabel('Y-Axis')
title('plot of sin(x) and cos(x)')
legend('sin(x)','cos(x)')

% text command
text(1, sin(1), '(1,sin(1))')
text(1,cos(1),'(1,cos(1))'); hold off

%% Logical Operations
clc;
clear;
close all;

a = 1:6;
b = 1:2:12;

c = b(b > a);

%% Files
clc;
clear;
close all;

% load and save
load("alt_temps1.mat","altitudes")
a = [1 2 3];
b = [4 5 6];
save("alphabet.mat", "a", "b")

writematrix(altitudes, "altitudes.txt")

%% Random Values
clc;
clear;
close all;

% randi testing
a = randi([1,30],[80,1]);
a(a==1)

%% Angel Review
clc;
clear;
close all;
h = 0;
z = 0;
x = 0;
y = 0;

fprintf('x\ty\tz\tw\th\n')
for w = 5:7
    if h ~= 0
        x = h;
    end
    while h >= 2 && h <= 4
        y = x^2;
        h = h + h;
    end
    z = z*x + 1;
    h = h + 2;
    fprintf('%i\t%i\t%i\t%i\t%i\n',x,y,z,w,h)
end
plot(1:10,1:10)
function1

function function1
clc;
clear;
close all;
end
       


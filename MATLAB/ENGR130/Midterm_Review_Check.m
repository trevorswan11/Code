%% count

count = 0;

while count <= 12
    disp(count)
    count = count + 4;
end
disp('---------------------------------------------------------')

count = 2;
while count < 12
    disp(count)
    count = count + 3;
end
disp('---------------------------------------------------------')

for idx = 1:5:100
    fprintf('%i, ',v2(idx))
    if mod(idx,4) == 3
        break
    end
end
%% figure matching
clc;
clear;
close all;

b = 1:100;
c = 1:5:500;
d = [b(1:50), c(51:100)];

figure()
plot(b,c)
figure()
plot(d,c)
figure()
plot(c,b)
figure()
plot(d,b)

%% line commands
close all;

plot(b,c,'>')
hold on
plot(b)
hold off
%% 
clc;
close all;
clear; 

x = 1:0.001:10;
y = exp(x);
fit_coef = polyfit(x,y,100);
fitted = polyval(fit_coef, x);
clc;
plot(x,y,'r')
hold on
plot(x,fitted,'b')
legend('original','fitted')
hold off

check = input('What value would you like to check? ');
fit_check = polyval(fit_coef, check);
actual = exp(check);
fprintf('fit: %.5e and actual: %.5e\n',fit_check, actual)
perc_error = abs(actual - fit_check)/actual*100;
disp(perc_error)
figure(2)
plot(x,y,x,y.^2)

v = [2*x^2 3*x x+4 exp(x)];
%% 
clear;
clc;
close all;


e = 2345;
fprintf('%c\n',e)

if e == 657
    disp('wow')
elseif e <=656
    disp('wtf')
end
%% 
idx = 0;
for count = 1:100
    disp('a')
    while count == 50
        disp('b')
        idx = idx + 1;
        if idx == 50
            break
        elseif idx == 25
            disp('c')
        end
    end
end

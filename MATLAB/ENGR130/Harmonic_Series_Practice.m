%% Harmonic Series
terms=input('How many terms of the harmonic series would you like? \n');
n=linspace(1,1,terms);
d=linspace(1,terms,terms);
length=width(n);
h=n./d;
S=sum(h);
fprintf ('The sum of the first %i terms of the harmonic sequence is %.4f.\n',terms,S);

%% Unusual thing
x=-2:.001:2;
y=(sqrt(cos(x)).*cos(200*x)+sqrt(abs(x))-0.7).*(4-x.*x).^.01;
plot(2*x,2*y)
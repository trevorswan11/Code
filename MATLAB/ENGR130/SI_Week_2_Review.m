%% Vector Step-Size
step_size=input("What would you like the step size to be?\n");
v1=0:step_size:1;
disp(v1)
%% Vector Value-Number
num_val=input("How many values would you like you rvector to contain?\n");
v2=linspace(0,1,num_val);
disp(v2)
%% Local Pet Store
Inventory=[16 12 82 212];
Prices=[50 40 30 20];
Pet_Names=["dogs" "cats" "birds" "fish"];
store_mat=[Inventory;Prices;Inventory.*Prices];
column_number=input("What column would you like from the Pet Store Matrix?\n");
data_requested=store_mat(:,column_number);
pet_requested=Pet_Names(column_number);
fprintf("The pet store has %i %s, which cost $%i each. The financial total for %s is $%i.\n"...
    ,data_requested(1),pet_requested,data_requested(2),pet_requested,data_requested(3));
%% Physicist Maniac
raw_q1=input("What is the value of the first charge? (units of C)\n");
raw_q2=input("What is the value of the second change? (units of C)\n");
r=input("What is the distance, in meters, between the two charges?\n");
q1=abs(raw_q1);
q2=abs(raw_q2);
k=8.99e9;
F=(k*q1*q2)/(r^2);
fprintf("The electric force for two point charges with charges of %.2e C and %.2e C that" + ...
    " are %.2e m apart is:\n%.2f N-C.\n",raw_q1,raw_q2,r,F);
fprintf("In scientific notation with the correct number of significant figures," + ...
    " the electric force is:\n%.2e N-C.\n",F);
%% scrpit that greets you
name=input("What is your name?\n",'s');
fprintf("Hi %s, nice to meet you.\n",name);
%% script that tests the trig identity cos2+sin2=1
angle=input("What angle would you like to test?\n");
c=cos(angle)^2;
s=sin(angle)^2;
sum=c+s;
disp(sum)
if sum==1
    disp("Success");
else 
    disp("Failure");
end
%% nth value of a vector
my_vector=(0:2:20);
my_value=input("What value would you like to extract?\n");
my_vector_value=my_vector(my_value);
disp(my_vector_value)
%% script that asks a user for the row and column of a matrix and displays output
row_number=input("What row would you like?\n");
column_number=input("What column would you like?\n");
the_matrix=[1 0 0;0 1 0; 0 0 1];
requested_number=the_matrix(row_number,column_number);
fprintf('Your number is %i.\n',requested_number);
%% you know what ur doing kinda
disp("Good Job! You got this!")
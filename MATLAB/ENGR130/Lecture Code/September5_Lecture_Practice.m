

%% THR and Age Example
age=input('Please enter youor age in years: ');
THR=(220-age)*0.6;
fprintf('A person %.0f years old has a target heart rate of %.1f beats/min. \n',age,THR);

%% Wavelength Example
wavel_1=6.626e-34/(1.673e-27*2.7e4);
wavel_2=6.626e-34/(1.672e-27*4.3e5);
wavel_avg=(wavel_1+wavel_2)/2;
fprintf('The mass of both protons is 1.672e-27kg.\nThe speed of proton one is 2.70e7 km/s and has a wavelength of %.4f nm.\nThe speed of proton two is 4.3e8 km/s and has a wavelength of %.4f nm.',wavel_1,wavel_2)
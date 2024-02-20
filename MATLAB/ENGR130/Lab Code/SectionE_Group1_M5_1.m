% Karis Mao, Jack Shapiro, Lillian Kagy, Trevor Swan
% ENGR 130 Module 5.1 Report
% Section E
% November 5, 2023

%% Lab 1 report
clc;
clear;
close all;

% Define the time step and time vector
dt = 1/1024;
time = 0:dt:(1-dt);

% Define given Amplitude and Frequency Vectors
amplitudes = [1 20 5 3 17 1.5];
frequencies = [12 17 20 5 15 5];
y_comp = getComposite(frequencies, amplitudes, time);

% Perform the fourier transform of y_comp
[f_comp, Y_comp] = ENGR130_Fourier(time, y_comp);

% Graph the Data obtained from the fourier transform in a stem plot
figure()
stem(f_comp, Y_comp)
xlim([0,30])
title("Frequency-Domain Graph of Summed Sinusoidal Signals")
xlabel('Frequencies (Hz)')
ylabel('Amplitude')

% Use a function to create a matrix that contians major frequencies and
% amplitudes. Set threshold to 0.5 to include possible rounding errors
threshtest = .5;
major_matrix = getComponentParams(f_comp, Y_comp, threshtest);

% Analyze the Mystery Data with the fourier transform
load("mystery_signal.mat")
[f_mystery, Y_mystery] = ENGR130_Fourier(time, mystery);

% Create a new figure that contains two plots with the first subplot plot
% being the mystery signal against time
figure()
subplot(2, 1, 1)
plot(time, mystery)
xlabel('Time (s)')
ylabel('Amplitude')
title('The Mystery Signal in the Time Domain')

% The second subplot plots the mystery signal in the frequency domain
subplot(2, 1, 2)
stem(f_mystery, Y_mystery)
title("Frequency-Domain Graph of Summed Sinusoidal Signals")
xlabel('Frequencies(Hz)')
ylabel('Amplitude')

% Limits are placed on the graph to make it look more readable
xlim([0, 405])
ylim([0, 11])

% Assign a threshold of 4 to pull out major frequencies
thresh = 4;
mystery_major = getComponentParams(f_mystery, Y_mystery, thresh);

% separate the two halves of the outputted matrix 
mystery_freq = mystery_major(:,1);
mystery_amp = mystery_major(:,2);

% run the mystery major components through the composite function to get a
% composite signal
mystery_composite = getComposite(mystery_freq, mystery_amp, time);

% subtract the restored data from the original file to only be left with noise
restored = mystery - mystery_composite;

% Plot the noise with appropriate titles and save graph
figure()
plot(time, restored)
title('Noise')
xlabel('Time (s)')
ylabel('Amplitude')

%% Lab 3
clc;
clear;
close all;

% load the data from the oscilloscope
heartbeat_data = readmatrix("Module5_2.csv");

% Separate the two parts of the matrix
time = heartbeat_data(3:end, 1);
voltage = heartbeat_data(3:end, 2);

% Plot the useable data
plot(abs(time), voltage, 'b')
title('Recorded Heartrate Data from Oscilloscope')
xlabel('Time (s)')
ylabel('Voltage (Volts)')

% analyze the data using the fourier transform
[frequencies, amplitudes] = ENGR130_Fourier(time, voltage);

% plot the data on a stem plot
figure()
stem(frequencies, amplitudes)
xlabel('Frequencies (Hz)')
ylabel('Amplitudes')
title('Fourier Transform of Heartbeat Data')
xlim([0,10])
ylim([0,0.4])

% Determine the major frequencies and amplitudes of the heartbeats
beat_thresh = 0.15;
heartbeat_matrix = getComponentParams(frequencies, amplitudes, beat_thresh);
major_freqs = heartbeat_matrix(:,1);
major_amps = heartbeat_matrix(:,2);

% omit the first frequency/amplitude, as it is not relevant for heart beats
major_freqs_omit = major_freqs(2:end);
major_amps_omit = major_amps(2:end);

%% Lab 4 - HR sData Plots
clc;
clear;
close all;
a = arduino();

% load provided HR data
normalhr = readmatrix("ECG_norm.csv");
normaltime = normalhr(:,1);
normalb = normalhr(:,2);

ECG1 = readmatrix("ECG1.csv");
ecg1time = ECG1(:,1);
ecg1b = ECG1(:,2);

ECG2 = readmatrix("ECG2.csv");
ecg2time = ECG2(:,1);
ecg2b = ECG2(:,2);

ECG3 = readmatrix("ECG3.csv");
ecg3time = ECG3(:,1);
ecg3b = ECG3(:,2);

ECG4 = readmatrix("ECG4.csv");
ecg4time = ECG4(:,1);
ecg4b = ECG4(:,2);

ECG5 = readmatrix("ECG5.csv");
ecg5time = ECG5(:,1);
ecg5b = ECG5(:,2);

ECG_plot(normaltime, normalb)
title('Normal')

ECG_plot(ecg1time, ecg1b)
title('ECG1')

ECG_plot(ecg2time, ecg2b)
title('ECG2')

ECG_plot(ecg3time, ecg3b)
title('ECG3')

ECG_plot(ecg4time, ecg4b)
title('ECG4')

ECG_plot(ecg5time, ecg5b)
title('ECG5')

close all
%% Detect Normal HR
thresh = 4350;
idxnorm = 1:numel(normalb);

numbeatsnorm = idxnorm(normalb == thresh);
timepeaksnorm = normaltime(numbeatsnorm);

bpmnorm = 60/(timepeaksnorm(2) - timepeaksnorm(1));

if bpmnorm >= 60 && bpmnorm <= 100
    writeDigitalPin(a, "D10", 1)
    pause(5)
end
writeDigitalPin(a, "D10", 0)

%% Detect Tachyarrhythmia
% Detect tachyarrhythmia
thresh = 4350;
idx1 = 1:numel(ecg1b);

numbeats = idx1(ecg1b == thresh);
timepeaks = ecg1time(numbeats);

bpm1 = 60/(timepeaks(3) - timepeaks(2));

n = 0.5;
if bpm1 > 100
    writeDigitalPin(a, "D12", 1)
    for count = 1:5
        writeDigitalPin(a,"D11", 1)
        pause(n)
        writeDigitalPin(a,"D11", 0)
        pause(n)
    end
end
writeDigitalPin(a, "D12", 0)

%% Detect Braddarhythmia 
times5 = ecg5time(islocalmax(ecg5b));
cycle = times5(2) - times5(1);

bpm5 = 60/cycle 

%% Functions
function y = getSinusoid(f, A, t)
% This function generates a Sinusoidal Graph using a provided equation and based on given data
% Format of call getSinusoid(f, A, t)
% Inputs - 3 values
%       f - frequency value, A - amplitude value, t - time vector
% Output - 1 value
%       y - sinusoidal signal vector 

y = A * sin(2*pi*f.*t);
end

function composite = getComposite(frequencies, amplitudes, time)
% This function adds sinusoidal signals together into one composite signal
% Format of call getComposite(frequencies, amplitudes, time)
% Inputs - 3 values
%       frequencies - frequency vector
%       amplitudes - amplitde vector
%       time - time vector
% Note - frequency and aplitude vectors must be the same size
% Output - 1 value
%       composite - composite signal vector

% prevent the function from running if the vector sizes are different
if numel(frequencies) ~= numel(amplitudes)
    error('Your frequency and amplitude functions are different lengths!')
end

% initialize the composite vector
composite = zeros(1,numel(time));

% loop through the frequency and amplitude vectors, calling the getSinusoid
% function to gather a sinusoid signal for each pair of values.
for idx = 1: numel(frequencies)
    % sum all of the sinusoid signals together as the loop continues
    composite = composite + getSinusoid(frequencies(idx), amplitudes(idx), time);
end
end

function matrix = getComponentParams(f, y, thresh)
% This function takes two vector inputs and returns a matrix of values
% above a certain amplitude threshold
% Format of call getComponentParams(f, y, thresh)
% Inputs - 3 values
%       f - frequency vector
%       y - amplitude vector
%       thresh - amplitude threshold
% Output - 1 value
%       matrix - 2D matrix containing amplitudes and their corresponsing
%       frequencies that broke the threshold

% logically index the values above the threshold and their frequency indices
idx = 1:numel(y);
abvamp = y(y >= thresh);
corfreq = f(idx(y >= thresh));

% format the matrix into a useable format
matrix = [corfreq;abvamp]';
end

function [f, Y] = ENGR130_Fourier(t, signal)
%ENGR130_Fourier uses Matlab's fft() function together with additional
%post-processing steps to convert from time to frequency domain for an
%input signal.
%
% Syntax:
%   [f, Y] = ENGR130_Fourier(t, signal) 
%
% Inputs:
%    t      - A time vector with uniform stepsize (likely generated by MATLAB
%             or collected by the oscilloscope
%    signal - The corresponding voltage values for each point in t.
%             This array must be the same size as t_in.
%
% Outputs:
%    f     - A frequency vector corresponding to the fft of signal
%    Y     - The amplitude contributions at each frequency f.
%
% Example Usage:
%   [frequency, Amplitude] = ENGR130_Fourier(time, Voltage_collected)

% Nov 2020; Last revision: Rhoads 10-28-2022
%
%------------- BEGIN CODE --------------

% Change column vectors to row vectors
[rows, ~] = size(signal);
if rows > 1
    signal = signal';
    t = t';
end

% Look for poor importing (Titles, names, not numbers...)
t_new = t(~isnan(t));

shift = length(t) - length(t_new);
t = t_new - min(t_new);
signal = signal(1:end - shift);

% define dt for given time step
dt = t(2) - t(1);

% create time vector for given time
xq = 0:dt:max(t);

% Have to get unique points out of the way,
%    although there should be none.
[time_u, index] = unique(t); 
vq = interp1(time_u, signal(index), xq);

% If the data doesn't start right away, there are issues
% Specifically, NaN shows up and clutters the DC Offset
signal = vq(~isnan(vq));

% Starting to do the Fourier transform
N = length(signal);

% define sampling frequency, Hz
fs = 1/dt;

% We need an even number of samples, so just chop off the excess
if rem(N, 2) ~= 0
    signal = signal(1:end-1);
end

% total number of samples
N = length(signal);

% discrete Fourier transform
Y = fft(signal);

% create the frequency variable
f = fs/N * (0:N/2);

% shorten DFT to only half of the values (positive Frequencies)
Y = Y(1:N/2+1);

% Normalize the Fourier transform, take absolute magnitude
Y = abs(Y)/N;

% correct normalization b/c only using half the frequncies.
Y(2:end) = 2 * Y(2:end);


end

function ECG_plot(time, HRdata)
figure()
plot(time, HRdata)
xlabel('Time (s)')
ylabel('ECG Signal')
end
import matplotlib.pyplot as plt
import numpy as np
from scipy import stats
# import seaborn as sns
from scipy.optimize import curve_fit

def linear(x, m):
    return m*x


filename = "5_23_24_D3_Tapping_5x5V.txt"
threshold = 0.05
min_plateau_length = 25
min_gap_length = .01
VToCheck = 'T'

def find_plateaus(voltage_data, threshold, min_plateau_length, min_gap_length):
    plateaus = []
    plateau_start = None
    plateau_sum = 0
    plateau_length = 0

    for i, voltage in enumerate(voltage_data):
        if voltage > threshold:
            if plateau_start is None:
                plateau_start = i
            plateau_sum += voltage
            plateau_length += 1
        else:
            if plateau_start is not None:
                if plateau_length >= min_plateau_length:
                    plateau_average = plateau_sum / plateau_length
                    plateaus.append((plateau_average, plateau_start, i-1))
                plateau_start = None
                plateau_sum = 0
                plateau_length = 0

        if i > 0 and voltage < min_gap_length and voltage_data[i-1] > min_gap_length:
            if plateau_start is not None:
                if plateau_length >= min_plateau_length:
                    plateau_average = plateau_sum / plateau_length
                    plateaus.append((plateau_average, plateau_start, i-1))
                plateau_start = None
                plateau_sum = 0
                plateau_length = 0

    return plateaus


file = open(filename, 'r')

Vtop = []
Vbot = []
time_series_bot=[]
time_series_top=[]


for line in file.readlines():
    line = line.strip('\n').split(",")
    VToCheck=str(VToCheck)
    print(line)
    if line[2] == 'B':
        Vbot.append(float(line[0]))
        time_series_bot.append(float(line[1]))
    if line[2] == 'T':
        Vtop.append(float(line[0]))
        time_series_top.append(float(line[1]))

if VToCheck == 'T':
    plt.plot(Vtop)
elif VToCheck == 'B':
    plt.plot(Vbot)
else:
    print("VToCheck must be either T or B, cannot plot")
plt.show()

voltage_data = []
if VToCheck == 'T':
    voltage_data = Vtop
elif VToCheck == 'B':
    voltage_data = Vbot
else:
    print("VToCheck must be either T or B, cannot get volt data")

plateaus = find_plateaus(voltage_data, threshold,
                         min_plateau_length, min_gap_length)

# Count the number of plateaus
num_plateaus = len(plateaus)
print("Number of plateaus:", num_plateaus)

# Extract average values for each plateau
average_values = [plateau[0] for plateau in plateaus]
print("Average values for each plateau:", average_values)

print(type(average_values))
#swap col and rows
#average_values_copy=[]
#for i in range(1,6):
#  for j in range(1,6):
#    average_values_copy.append(average_values[5*j-i])

print("average_values: "+str(average_values))
#print("average_values_copy: "+str(average_values_copy))
#average_values=average_values_copy


# Plot the original voltage series
plt.figure(figsize=(12, 6))


# Mark the average time value and average voltage value for each plateau
plt.plot(range(len(voltage_data)), voltage_data, label='Voltage Series')
plt.xlabel('Increment', fontsize=25)
plt.ylabel('Voltage', fontsize=25)
plt.tick_params(labelsize=25, width=2, length=7)
# plt.legend()
#plt.title('Voltage Series with Plateaus')
#plt.grid(True)
plt.tight_layout()

vavg = []
for average, start, end in plateaus:
    time_values = range(start, end+1)
    voltage_values = [voltage_data[i] for i in time_values]
    average_time = (start + end) / 2
    plt.plot(time_values, voltage_values,
             label=f"Plateau {average_time:.2f}s, Avg: {average:.2f}V")
    plt.scatter(average_time, average, color='red')
    vavg.append(average)

#vavg.insert(0, .1)
#vavg.insert(26, 4.3)
#vavg.pop(0)
#vavg.pop(15)
#vavg.pop(1)
#vavg[8] = 3.3
plt.show()

count = 0
Vavg_map = np.zeros([5, 7], dtype=float)
Vavg_column = np.zeros([7], dtype=float)
Vstd_column = np.zeros([7], dtype=float)
if VToCheck == "T":
    pos = np.array([0.0, 0.5, 1.0, 1.5, 2, 2.5,3], dtype=float)
if VToCheck == "B":
    pos = np.array([2.5, 2, 1.5, 1, 0.5], dtype=float)
print(len(vavg))


Vavg_map[:,0] = vavg[0]
Vavg_map[:,-1] =vavg[-1]
vavg.pop(0)
vavg.pop(-1)

print(Vavg_map)
print('hello')
for i in range(5):
    for j in range(5):
        if VToCheck == "T": 
            Vavg_map[i, j+1] = vavg[count]
        if VToCheck == "B":
            Vavg_map[j, i] = vavg[count]
        count += 1

        
print(Vavg_map)
print('hello')
      
         
if VToCheck == "B":
    Vavg_map = np.rot90(Vavg_map)

for i in range(7):    
    if VToCheck == "B":
        Vavg_column[i] = np.mean(Vavg_map[i, :])
        Vstd_column[i] = np.std(Vavg_map[i, :])
    elif VToCheck == "T":
        Vavg_column[i] = np.mean(Vavg_map[:, i])
        Vstd_column[i] = np.std(Vavg_map[:, i])

plt.figure(figsize=(12, 9))
plt.imshow(Vavg_map, vmin=0, vmax=5, cmap='viridis')
#plt.xticks((0.5, 1.0, 1.5, 2.0, 2.5))

# Plot the original voltage series

plt.xlabel('Position (0.5 cm)', fontsize = 25)
plt.ylabel('Position (0.5 cm)', fontsize = 25)
cbar = plt.colorbar()
cbar.set_label('Voltage (V)', fontsize=25)
cbar.ax.tick_params(labelsize=25)

plt.tick_params(labelsize=25, width=2, length=7)
plt.tight_layout()
plt.show()

res = stats.linregress(pos, Vavg_column)
pos_full = np.array([0.0, 0.25, .75, 1.25, 1.75, 2.25, 3.0], dtype=float)
#popt, pcov = curve_fit(linear, pos, Vavg_column)
#print(res)

plt.xlabel('Position (cm)', fontsize=25)
plt.ylabel('Voltage (V)', fontsize=25)
plt.tick_params(labelsize=25, width=2, length=7)
plt.ylim((0,5))
plt.xlim((0,3))
plt.errorbar(pos, Vavg_column, yerr=Vstd_column,
             linestyle="None", marker='o', color='k')
plt.plot(pos_full, res[0]*pos_full + res[1], 'r', label="V = %.2f x + %.2f\nR$^2$= %.4f" % (res[0], res[1], res[2]**2))
plt.legend(loc='upper left', frameon=False, fontsize=20)
plt.show()

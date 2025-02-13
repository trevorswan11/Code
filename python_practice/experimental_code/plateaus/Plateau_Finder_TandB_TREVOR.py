import matplotlib.pyplot as plt
import numpy as np
from scipy import stats
from typing import List
from os import path

# globals
filename = path.join('.', '5_23_24_D3_Tapping_5x5V.txt')
"""Note"""
threshold = 0.05
"""Note"""
V_to_check = ''
"""Note"""
valid_flags = ['T', 'B']
"""Note"""
min_plateau_length, min_gap_length = 25, 0.01

def find_plateaus(voltage_data: List[float], 
                  threshold: float, 
                  min_plateau_length: float, 
                  min_gap_length:float) -> List[tuple]:
    """Identifies the voltage plateaus in a given data set

    Args:
        voltage_data (List[float]): An array of voltage data
        threshold (float): The minimum voltage to be considered for a plateau
        min_plateau_length (float): The minimum length of a plateau to be logged
        min_gap_length (float): The minimum voltage drop to break a plateau

    Returns:
        List[tuple]: A list containing information about each found plateau as (avg v, start i, end i)
    """    
    # set the array to return and the initial values for plateau analysis
    plateaus = []
    plateau_start = None
    plateau_sum = plateau_length = 0
    
    # loop through the entire voltage data
    for i, voltage in enumerate(voltage_data):
        # check if the current voltage exceeds the set threshold
        if voltage > threshold:
            if plateau_start is None: plateau_start = i
            # increment the sum and length of the plateau
            plateau_sum += voltage
            plateau_length += 1
        # only enter if the voltage is too low and a plateau has been entered
        elif plateau_start is not None:
            # check if the plateau meets the minimum length set
            if plateau_length >= min_plateau_length:
                # calculate the avg value of the plateau and add it to the list
                plateau_avg = plateau_sum / plateau_length
                plateaus.append((plateau_avg, plateau_start, i - 1))
            # reset the values set for plateau analysis
            plateau_start = None
            plateau_sum = plateau_length = 0
        
        # check if the distance between current and prev point is sufficiently large
        big_enough = voltage < min_gap_length and voltage_data[i - 1] > min_gap_length
        if i > 0 and big_enough and plateau_start is not None:
            # check if the plateau meets the minimum length set
            if plateau_length >= min_plateau_length:
                # calculate the avg value of the plateau and add it to the list
                plateau_avg = plateau_sum / plateau_length
                plateaus.append((plateau_avg, plateau_start, i - 1))
            # reset the values set for plateau analysis
            plateau_start = None
            plateau_sum = plateau_length = 0
    
    return plateaus

def read_voltage_data() -> List[float]:
    """Reads the voltage data of gl0bally specified type.

    Raises:
        ValueError: If the global V_to_check is not T or B

    Returns:
        List[float]: Returns the voltage_data if no errors were encountered
    """    
    # create lists to store data for both B and T from the data
    Vtop, Vbot = [], []
    time_series_top, time_series_bot = [], []
    with open(filename, 'r') as file:
        # loop through all of the lines in the file
        for line in file.readlines():
            # format and print the line
            line = line.strip('\n').split(',')
            print(line)
            # check only the desired voltage, at the end of each line
            if line[-1] == 'T':
                # Voltage is in first column, time is in second
                Vtop.append(float(line[0]))
                time_series_top.append(float(line[1]))
            elif line[-1] == 'B':
                Vbot.append(float(line[0]))
                time_series_bot.append(float(line[1]))
        
        # prep the data for plotting and returning
        if V_to_check == 'T':
            plt.plot(Vtop)
            voltage_data = Vtop
        elif V_to_check == 'B':
            plt.plot(Vbot)
            voltage_data = Vbot
        else:
            raise ValueError('V_to_check must be either T or B!')
        
        # plot and return the data
        plt.show()
        return voltage_data

def plateau_analysis(plateaus: List[tuple]) -> tuple[int, List[float]]:
    """Returns the number of plateaus and all of the average plateau values

    Args:
        plateaus (List[tuple]): The plateau data with avg values in the first column

    Returns:
        tuple[int, List[float]]: The results formatted as (number of plateaus, avg_values)
    """        
    num_plateaus = len(plateaus)
    print('Number of plateaus:', num_plateaus)
    avg_values = [plateau[0] for plateau in plateaus]
    print('Average values for each plateau:', avg_values)
    return (num_plateaus, avg_values)

def plot_original_series(voltage_data: List[float], plateaus: List[tuple], legend: bool = False) -> List[float]:
    """Creates a plot of the voltage data and highlights and extracts the average values.

    Args:
        voltage_data (List[float]): The voltage data from the file
        plateaus (List[tuple]): The plateaus calculated and determined through analysis
        legend (bool, optional): Determines whether or not to show the legend. Defaults to False for readability.

    Returns:
        List[float]: The list of the extracted average values from each plateau
    """    
    # plot the original series
    plt.figure(figsize=(12,6))
    plt.plot(range(len(voltage_data)), voltage_data, label='Voltage Series')
    
    # plot each plateau as a scatter, including the average at the average time
    v_avg = []
    for average, start, end in plateaus:
        v_avg.append(average)
        # should account for 0 based indexing to determine data
        time_values = range(start, end + 1)
        # find the values in each plateau
        voltage_values = [voltage_data[i] for i in time_values]
        average_time = (start + end) / 2
        
        # plot the time and voltage values in the range and add the (avg_time, avg) point 
        plt.plot(time_values, voltage_values, label=f"Plateau: {average_time:.2f}s, Avg: {average:.2f}V")
        plt.scatter(average_time, average, color='red')
        
    # format the plot for readers convenience
    plt.title('Voltage Series with Plateaus')
    plt.grid(True)
    plt.xlabel('Increment', fontsize=25)
    plt.ylabel('Voltage', fontsize=25)
    plt.tick_params(labelsize=25, width=2, length=7)
    if legend: plt.legend()
    
    # show the plot and return the average voltages
    plt.show()
    return v_avg

def average_voltage_analysis(v_avg: List[float]) -> tuple[np.ndarray]:
    """Calculates the average and std dev of the voltage values from the experiment 

    Args:
        v_avg (List[float]): The average voltage data

    Raises:
        ValueError: If the global V_to_check is not T or B

    Returns:
        tuple[np.ndarray]: Returns analysis output as (pos, V_avg_map, V_avg_column, V_std_column). 
    """    
    # check if the v to check is valid
    if V_to_check not in valid_flags: raise ValueError('V_to_check must be either T or B!')
    # Create an average map, column, and an std column for the data
    V_avg_map = np.zeros([5,7], dtype=float)
    V_avg_column = np.zeros([7], dtype=float)
    V_std_column = np.zeros([7], dtype=float)
    
    # establish the arrays to use (predetermined)
    T_arr = [0.0, 0.5, 1.0, 1.5, 2, 2.5, 3]
    B_arr = [2.5, 2, 1.5, 1, 0.5]
    pos = np.array(T_arr if V_to_check == 'T' else B_arr, dtype=float)
    
    # extract values from v_avg
    V_avg_map[:,0], _ = v_avg[0], v_avg.pop(0)
    V_avg_map[:,-1], _ = v_avg[-1], v_avg.pop(-1)
    
    # ! CLARIFY
    count = 0
    for i in range(5):
        for j in range(5):
            V_avg_map[i if V_to_check == 'T' else j, j + 1 if V_to_check == 'T' else i] = v_avg[count]
            count += 1
    if V_to_check == 'B': V_avg_map = np.rot90(V_avg_map)
    
    # Calculate mean and std values
    for i in range(7):
        if V_to_check == 'T':
            V_avg_column[i] = np.mean(V_avg_map[:, i])
            V_std_column[i] = np.std(V_avg_map[:, i])
        elif V_to_check == 'B':
            V_avg_column[i] = np.mean(V_avg_map[i, :])
            V_std_column[i] = np.std(V_avg_map[i, :])
    return (pos, V_avg_map, V_avg_column, V_std_column)
    
def plot_voltage_analysis(pos: np.ndarray, V_avg_map: np.ndarray, V_avg_column: np.ndarray, V_std_column: np.ndarray) -> None:
    """Plots an analysis of the data as a heatmap and then a regression model

    Args:
        pos (np.ndarray): The
        V_avg_map (np.ndarray): The average voltages
        V_avg_column (np.ndarray): The column averages of the voltages
        V_std_column (np.ndarray): THe column stds of the voltages
    """        
    # Add the data to the figure
    plt.figure(figsize=(12,9))
    plt.imshow(V_avg_map, vmin=0, vmax=5, cmap='viridis')
    plt.xticks(tuple(i / 2 for i in range(1, 6)))
    
    # Plot the voltage series with labeled axes and colors for presentation
    plt.xlabel('Position (0.5 cm)', fontsize=25)
    plt.ylabel('Position (0.5 cm)', fontsize=25)
    cbar = plt.colorbar()
    cbar.set_label('Voltage (V)', fontsize=25)
    cbar.ax.tick_params(labelsize=25)
    plt.tick_params(labelsize=25, width=3, length=7)
    plt.tight_layout()
    plt.show()
    
    # Plot the average data for statistical analysis
    res = stats.linregress(pos, V_avg_column)
    pos_full = np.array([0.0, 0.25, .75, 1.25, 1.75, 2.25, 3.0], dtype=float)
    plt.xlabel('Position (cm)', fontsize=25)
    plt.ylabel('Voltage (V)', fontsize=25)
    plt.tick_params(labelsize=25, width=2, length=7)
    plt.ylim((0,5))
    plt.xlim((0,3))
    
    # ! CLARIFY
    plt.errorbar(pos, V_avg_column, yerr=V_std_column,
             linestyle='None', marker='o', color='k')
    plt.plot(pos_full, res[0]*pos_full + res[1], 'r', label='V = %.2f x + %.2f\nR$^2$= %.4f' % (res[0], res[1], res[2]**2))
    plt.legend(loc='upper left', frameon=False, fontsize=20)
    plt.show()
    
if __name__ == '__main__':
    default_V = 'T'
    V_to_check = input('Input the V_to_check: ') or default_V
    voltage_data = read_voltage_data()
    plateaus = find_plateaus(voltage_data, threshold, 
                             min_plateau_length, min_gap_length)
    plateau_analysis(plateaus)
    v_avg = plot_original_series(voltage_data, plateaus)
    plot_voltage_analysis(*average_voltage_analysis(v_avg))
    
import pandas as pd
import numpy as np

def generate_data(min, max, rxn_rate):
    # rate = k * A ^ n
    k = 0.3                                                         # rate constant
    conc_vals = range(min, max, 1)                                  # concentration range scaled up
    rate = lambda a: k * (a ** rxn_rate) + np.random.uniform(-0.5, 0.5)
    rate_data = []
    scaled_conc = []
    for conc in conc_vals:
        true_conc = conc / 1000
        scaled_conc.append(true_conc)                                # append the actual conc value
        rate_data.append(rate(true_conc))                            # calculate the corresponding reaction rate
    rxn_rate_data = {
        'Concentration (M)' : scaled_conc,
        'Reaction Rate' : rate_data
    }
    df = pd.DataFrame(rxn_rate_data)
    df.to_csv('sample_files/reaction_kinetics.csv', index = False)
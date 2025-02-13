import os, sys
sys.path.append(os.path.abspath(os.path.join(os.path.dirname(__file__), '..')))                             # allows importing across projects
import project_one.basic_data_analysis as bda
import kinetics_model_creation as kmc
import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
from sklearn.model_selection import train_test_split
from sklearn.linear_model import LinearRegression
from sklearn.metrics import r2_score

def linear_regression(path: str) -> list:
    df = pd.read_csv(path)
    X = df[['Concentration (M)']]
    y = df['Reaction Rate']
    X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.33, random_state=42)              # 33 percent testing
    model = LinearRegression()
    model.fit(X_train, y_train)                                                                             # train the model based on training data
    y_pred = model.predict(X_test)                                                                          # predict a data point and analyze it
    r2 = r2_score(y_test, y_pred)                                                                           # (true value, predicted value) to generate from model
    mse = bda.calculate_mse(y_test, y_pred)                                                                 # scikit learn's mse is deprecated
    return [X_test, y_pred, r2, mse, model.coef_[0], model.intercept_]

def plot_with_regression(path: str, model: list = None):
    data = pd.read_csv(path)
    plt.figure(figsize=(10,10))
    plt.xlabel(data.columns[0])
    plt.ylabel(data.columns[1])
    plt.title('Reaction Kinetics Analysis')
    plt.scatter(data.iloc[:,0], data.iloc[:,1], color = 'green', s = 0.1, label = 'Experimental Data')
    plt.plot(model[0], model[1], color='black', label='Regression line')
    plt.legend()
    model_results = f'Mean Squared Error: {model[3]:.2f}\nR-squared: {model[2]:.2f}\nFit Line: y = {model[4]:.5f}x + {model[5]:.5f}'
    plt.gca().text(0.05, 0.95, model_results, transform = plt.gca().transAxes,                              # add the mse and r2 data to a little rounded text box on the graph
                fontsize = 12, verticalalignment = 'top',
                bbox = dict(boxstyle = 'round', facecolor = 'white', alpha = 0.5))
    plt.show()

if __name__ == "__main__":
    kmc.generate_data(2000, 30000, 1)
    path = 'sample_files/reaction_kinetics.csv'
    model = linear_regression(path)
    plot_with_regression(path, model)
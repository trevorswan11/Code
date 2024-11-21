#include <iostream>
#include <stdlib.h>
#include <string.h>

using namespace std;

/**
 * A simple change calculator for cashiers.
 * 
 * @author Trevor Swan
 */
namespace ChangeCalc
{
    /**
     * Calculates the change due between the cost and the amount paid.
     * 
     * @param paid The amount paid
     * @param cost The cost of the item
     * @return The change as a double
     */
    double getChange(double paid, double cost)
    {
        return paid - cost;
    }

    /**
     * Returns the number of dollars in the change.
     * 
     * @param change The amount of change
     * @return The number of dollars per denomination
     */
    int* getNumDollarReturn(double change)
    {
        // Declare useful variables
        int dollars = (int) change;
        int denominations[] = { 100, 50, 20, 10, 5, 1 };
        int* dollarArray = new int[6];

        // Find the number of dollars per denomination
        for (int i = 0; i < 6; i++)
        {
            dollarArray[i] = dollars / (denominations[i]);
            dollars = dollars % (denominations[i]);
        }

        // Return the number of dollars
        return dollarArray;
    }

    /**
     * Returns the number of coins in the change.
     * 
     * @param change The amount of change
     * @return The number of coins per denomination
     */
    int* getNumCoinReturn(double change)
    {
        // Declare useful variables
        int coins = (int) (100 * (change - (int) change));
        cout << coins << " "<< change << "\n";
        int denominations[] = { 25, 10, 5, 1 };
        int* coinArray = new int[4];

        // Find the number of dollars per denomination
        for (int i = 0; i < 4; i++)
        {
            coinArray[i] = coins / (denominations[i]);
            coins = coins % (denominations[i]);
        }
        return coinArray;    
    }

    /**
     * Prints the change to the console.
     * 
     * @param dollarAmount The number of dollars per denomination
     * @param coinAmount The number of coins per denomination
     */
    void printChange(int *dollarAmount, int *coinAmount)
    {
        // Declare string representations of the denominations
        string dollarDenominations[] = {"Hundred", "Fifty", "Twenty", "Ten", "Five", "One"};
        string coinDenominations[] = {"Quarter", "Dime", "Nickel"};

        // Display the change for dollars
        for (int i = 0; i < 6; i++)
        {
            if (dollarAmount[i] > 0)
            {
                cout << dollarAmount[i] << " " << dollarDenominations[i] << (dollarAmount[i] == 1 ? " " : "s ") <<"\n";
            }
        }
        cout << "\n";

        // Display the change for coins
        for (int i = 0; i < 4; i++)
        {
            if (coinAmount[i] > 0 && i != 3)
            {
                cout << coinAmount[i] << " " << coinDenominations[i] << (coinAmount[i] == 1 ? " " : "s ") << "\n";
            }

            // Pennies have different singular/plural rules
            else if (coinAmount[i] > 0 && i == 3) 
            {
                cout << coinAmount[i] << " "<< (coinAmount[i] == 1 ? "Penny" : "Pennies") << "\n";
            }
        }
    }
}

int main(int argc, char const *argv[])
{
    // Declare paid and cost and determine cost
    double paid, cost;
    cout << "Enter the cost of the product: ";
    cin >> cost;

    // Determine how much was paid
    cout << "Enter the amount of paid: ";
    cin >> paid;

    // Calculate and display the change
    double change = ChangeCalc::getChange(paid, cost);
    cout << "Change: " << change << "\n" << endl;

    // Tell the cashier how to give the change
    cout << (change == 0 ? "No change, have a nice day!" : "Here is your change: ") << "\n" << endl;
    ChangeCalc::printChange(ChangeCalc::getNumDollarReturn(change), ChangeCalc::getNumCoinReturn(change));

    return 0;  
}
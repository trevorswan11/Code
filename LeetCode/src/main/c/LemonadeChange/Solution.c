#include <stdio.h>
#include <stdbool.h>

bool lemonadeChange(int* bills, int billsSize) {
    // Declare counters for fives and tens
    int five = 0, ten = 0;
    
    // Loop through bills until false is returned or success
    for (int i = 0; i < billsSize; i++) {
        // Increase 5 count immediately if given
        if (bills[i] == 5) {
            five++;
        } 
        
        // Return false if given 10 and there are no 5s
        else if (bills[i] == 10) {
            if (five == 0) {
                return false;
            }
            five--;
            ten++;
        } else {
            // If 20, check for a aten and a 5 to give change
            if (ten > 0 && five > 0) {
                ten--;
                five--;
            } 
            
            // May not have 10s, so check for 3 5s to give change
            else if (five >= 3) {
                five -= 3;
            } 
            
            // Otherwise return false
            else {
                return false;
            }
        }
    }
    return true;
}
#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

class Solution {
public:
    int heightChecker(vector<int>& heights) {
        // Create a sorted copy of the heights vector
        vector<int> expected = heights;
        sort(expected.begin(), expected.end());
        
        // Count the number of indices where heights[i] != expected[i]
        int count = 0;
        for (int i = 0; i < heights.size(); i++) {
            if (heights[i] != expected[i]) {
                count++;
            }
        }
        
        return count;
    }
};
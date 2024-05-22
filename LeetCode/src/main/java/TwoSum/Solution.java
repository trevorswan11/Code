package TwoSum;

import java.util.HashMap;

/**
 * Given an array of integers nums and an integer target, return indices of the
 * two numbers such that they add up to target.
 * 
 * You may assume that each input would have exactly one solution, and you may
 * not use the same element twice.
 * 
 * You can return the answer in any order.
 * 
 * @author Trevor Swan
 * @category LeetCode Challenge - Easy
 */
public class Solution {
    /**
     * Uses a one-pass hash map to accomplish challenge in O(n) time
     * 
     * @param nums   The array of numbers that is at least of length 1
     * @param target The target sum in the array
     * @return The position of the target
     */
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> complements = new HashMap<>();
        
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];

            if (complements.containsKey(complement)) {
                return new int[] { complements.get(complement), i };
            }

            complements.put(nums[i], i);
        }
        // Return the original array if nothing was found
        return nums;
    }

    /**
     * A brute for approach to accomplish in O(n^2) time.
     * 
     * @param nums   The array of numbers that is at least of length 1
     * @param target The target sum in the array
     * @return The position of the target
     */
    public int[] twoSumBrute(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[] { i, j };
                }
            }
        }
        return null;
    }
}

/**
 * Removes all duplicate elements from a sorted array
 * 
 * @param {number[]} nums a sorted array of integers
 * @return {number} the number of duplicate elements
 */
var removeDuplicates = function(nums) {
    // Check for an empty array or null
    if (nums == null || nums.length == 0) {
        return 0;
    }
    
    // Declare local variables to be used
    const original = Array.from(nums);
    var shift = 0;

    // Loop through the array looking for duplicates
    for (let i = 0; i < nums.length; i++) {
        if (nums[shift] != nums[i]) {
            shift++;
            nums[shift] = nums[i];
        }
    }

    // Check for an array that has all unique elements
    for (let i = 0; i < original.length; i++) {
        if (original[i] != nums[i]) {
            return shift + 1;
        }
    }

    // Otherwise return the shift amount plus one to account of 0 index
    return 0;
};

console.log("There are " + removeDuplicates([0,0,1,1,1,2,2,3,3,4]) + " duplicate elements in the array [0,0,1,1,1,2,2,3,3,4]");
console.log("There are " + removeDuplicates([1,2,3,4]) + " duplicate elements in the array [1,2,3,4]");
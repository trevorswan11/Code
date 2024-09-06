/**
 * @param {number[]} nums
 * @return {number}
 */
var singleNumberMap = function(nums) {
    let map = new Map();
    for (let i = 0; i < nums.length; i++) {
        if (map.has(nums[i])) {
            map.set(nums[i], map.get(nums[i]) + 1);
        } else {
            map.set(nums[i], 1);
        }
    }
    for (let [key, value] of map) {
        if (value === 1) {
            return key;
        }
    }
};

/**
 * @param {number[]} nums
 * @return {number}
 */
var singleNumberLoop = function(nums) {
    let result = 0;
    for (let i = 0; i < nums.length; i++) {
        result = result ^ nums[i];
    }
    return result;
};

console.log(singleNumber([2,2,1]))
console.log(singleNumber([4,1,2,1,2]))
console.log(singleNumber([1]))
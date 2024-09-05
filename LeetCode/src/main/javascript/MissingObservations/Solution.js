/**
 * @param {number[]} rolls
 * @param {number} mean
 * @param {number} n
 * @return {number[]}
 */
var missingRolls = function(rolls, mean, n) {
    // Calculate roll sum and difference
    let m = rolls.length;
    let totalSum = mean * (n + m);
    let totalGiven = rolls.reduce((a, b) => a + b);
    let diff = totalSum - totalGiven;

    // Check if diff can be distributed into the rolls
    if (diff < n || diff > 6 * n) {
        return []; 
    }

    // Distribute 1 into each roll, subtract from diff
    let missingRolls = new Array(n).fill(1);
    diff -= n;

    // Distribute the remaining sum, max 5 per die
    for (let i = 0; i < n && diff > 0; i++) {
        let add = Math.min(5, diff);
        missingRolls[i] += add;
        diff -= add;
    }

    return missingRolls;
}

console.log(missingRolls([3, 2, 4, 3], 4, 2)) // [6, 6]
console.log(missingRolls([1, 5, 6], 3, 4)) // [2, 3, 2, 2]
console.log(missingRolls([1, 2, 3, 4], 6, 4)) // []
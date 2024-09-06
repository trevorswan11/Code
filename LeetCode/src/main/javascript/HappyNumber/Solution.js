/**
 * @param {number} n
 * @return {boolean}
 */
var isHappy = function(n) {
    let numIterations = 10;
    let i = 0;
    let currentNumber = n;

    while (i < numIterations) {
        let sum = currentNumber.toString().split('').map(x => Math.pow(parseInt(x), 2)).reduce((a, b) => a + b);
        if (sum === 1) {
            return true;
        }
        currentNumber = sum;
        i++;
    }
    return false;
};
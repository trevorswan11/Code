/**
 * @param {number} n
 * @return {number}
 */
var fib = function(n) {
    let cache = {};
    if (n <= 1) {
        return n;
    } else if (n === 2) {
        return 1;
    } 
    else {
        if (!cache[n]) {
            cache[n] = fib(n - 1) + fib(n - 2);
        }
        return cache[n];
    }  
};
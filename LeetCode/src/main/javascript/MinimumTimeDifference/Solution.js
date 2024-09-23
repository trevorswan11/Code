/**
 * @param {string[]} timePoints
 * @return {number}
 */
var findMinDifference = function(timePoints) {
    // Convert time to total minutes since 00:00
    const toMinutes = (time) => {
        const [hours, minutes] = time.split(':').map(Number);
        return hours * 60 + minutes;
    };
    
    // Convert all times to minutes and sort them
    const minutesArray = timePoints.map(toMinutes).sort((a, b) => a - b);
    
    // Initialize minimum difference as the difference between the first and last time (circular)
    let minDiff = (1440 + minutesArray[0] - minutesArray[minutesArray.length - 1]) % 1440;
    
    // Check the difference between consecutive time points
    for (let i = 1; i < minutesArray.length; i++) {
        const diff = minutesArray[i] - minutesArray[i - 1];
        minDiff = Math.min(minDiff, diff);
    }
    
    return minDiff;
};

console.log(findMinDifference(["23:59","00:00"]))
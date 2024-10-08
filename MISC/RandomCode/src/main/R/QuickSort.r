# QuickSort function
quick_sort <- function(arr) {
  # Base case: arrays of length 0 or 1 are already sorted
  if (length(arr) <= 1) {
    return(arr)
  }
  
  # Choose a pivot (here we take the first element as pivot)
  pivot <- arr[1]
  
  # Partition: elements less than the pivot go to left, greater go to right
  left <- arr[arr < pivot]
  right <- arr[arr > pivot]
  
  # Recursively apply quick_sort on left and right partitions
  return(c(quick_sort(left), pivot, quick_sort(right)))
}

# Example usage:
arr <- c(3, 6, 8, 10, 1, 2, 1)
sorted_arr <- quick_sort(arr)
print(sorted_arr)

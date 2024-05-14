def isPalindrome(self, x):
    if x < 0:
        return False
        
    number = str(x)
    flipped = number[::-1]
    
    for i in range(len(number)):
        if number[i] != flipped[i]:
            return False

    return True
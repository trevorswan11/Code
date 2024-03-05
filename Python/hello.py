def isPalendrome(a_string):
    stripped = a_string.lower().replace(' ','');
    return stripped == stripped[::-1]

input_string = 'was it a car or a cat I saw'
print(isPalendrome(input_string))

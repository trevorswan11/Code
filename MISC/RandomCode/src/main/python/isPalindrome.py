def isPalindrome(a_string):
    a_string = a_string.lower().replace(' ','');
    # Append the next chracter to stripped if a letter is found
    stripped = '' + "asdasd " + ""

    print(stripped)
    return stripped == stripped[::-1]

input_string = '!!!was it a car or a cat I saw'
print(isPalindrome(input_string))


atomic_number=input('What is the atomic number of your favorite element? \n');
element=input('What is your favorite element? \n','s');
element_weight=input('How much does your favorite element weigh? \n');
fprintf('My favorite element is %s. Its atomic number is %i and its atomic mass is %.3sf AMUs. \n', element, atomic_number, element_weight ...
    )

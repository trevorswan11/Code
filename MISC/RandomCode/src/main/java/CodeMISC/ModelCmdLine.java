package CodeMISC;

import java.util.Scanner;

public class ModelCmdLine{
    
    //runtime: O(n)
    //will check if input is a palindrome or not iteratively 
    static boolean palindromeIterative(String input){
        input = input.toUpperCase();
        String word = ""; //will store input flipped around
        for (int i=input.length()-1;i>=0;i--){ //loops from the last character to the first character and adds it to word so it is reversed
            word += input.charAt(i);
        }
        return word.equals(input); //returns boolean value if they are equal or not 
    }

    //runtime: O(n)
    //both have the same runtime, however we can note that the time complexity of this is n/2 if we didn't remove hte coefficients 
    //will check if input is a palindrome or not recursively  
    static boolean palindromeRecursive(String input){
        input = input.toUpperCase(); //makes sure upper cases don't ruin the code
        if (input.length() <= 1){ //base case 
            return true;
        }

        Character front = input.charAt(0); //get front and back character
        Character back = input.charAt(input.length()-1);
        if (front == back){ //check if front and back are the same 
            palindromeRecursive(input.substring(1,input.length()-1)); //if true the function repeats again but with a new front and back
        } else { //if false function ends and input isn't a palindrome
            return false;
        }
        return true;
    }

    //Returns true if one string is the anagram of another string ("silent" is an anagram of "listen" and vice versa)
    //numbers work, no special characters
    static boolean anagramChecker(String x, String y){
        x = x.toUpperCase(); //makes sure upper cases don't ruin the code
        y = y.toUpperCase(); 

        if (x.length() != y.length()) { //if both strings aren't the same length return false
            return false; 
        }

        for (int i=0; i<x.length(); i++){ //loop through each string x and y 
            for (int j=0; j<y.length(); j++) {
                if (x.charAt(i) == y.charAt(j)) { //if chars are equal remove that character from y 
                    y = y.substring(0,j) + y.substring(j+1,y.length());
                    j=y.length(); //to end the inner loop 
                }
            }
        }
        return (y.equals("")); //if y is empty that means all chars found a match, therefore an anagram
    }
    
    //Returns the substring added to the input string given a specified index
    static String addSubstring(String input, String substring, int index){
        if (index > input.length()) { //check for any edge cases for index 
            return "index out of bounds";
        } else if (index < 0) {
            return "index does not exist";
        } else if (index == input.length()){ 
            return input + substring;
        }

        //use java function substring() to get all characters in input before index, then add new substring, then add the rest of the input 
        return (input.substring(0,index) + substring + input.substring(index,input.length()));
    }    

    //Returns the length of a given input string
    static int getLength(String input){
        return input.length();
    }    

    //Returns the number of occurrences a specified substring is in the input string
    static int occurrenceCounter(String input, String substring){
        input = input.toUpperCase(); //makes sure upper cases don't ruin the code
        substring = substring.toUpperCase();
        int count = 0; //will keep count of how many times substring repeats 
        int len = substring.length(); //keeps length of substring for easier reference
        for (int i=0; i<=input.length()-len;i++){ //loops input.length()-len to stay in bounds 
            if (input.substring(i,i+len).equals(substring)) {
                count += 1; //if they equal add to count 
            }
        }
        return count;
    }
    
    //Returns only the sentence string in reverse order of words 
    //(input string is "This is a test." which gives the return string, "test a is This.")
    //"This, is a test!" should output "test a is This,!"?
    static String sentenceReversal(String input){
        Character punctuation = input.charAt(input.length()-1); //will always have a function mark at the end 
        input = input.substring(0,input.length()-1); //get rid of punctuation from the input so it won't mess up the loop
        String[] words = input.split(" "); //splits to string when there is a space character " " 
        String newString = "";
        for (int i=words.length-1; i>=0; i--) { //loop backwards so words are reversed 
            if (i != words.length-1){ //to add the spaces
                newString += " ";
            }
            newString += words[i];  
        }
        return newString + punctuation;
    } 

    //return true if special characters exist 
    static boolean specialCharChecker(String input){
        for (int i=0; i<input.length();i++){
            Character letter = input.charAt(i);
            //if its not one of the valid characters return true 
            if (!(letter >= 'a' && letter <= 'z') && !(letter >= 'A' && letter <= 'Z') && !(letter >= '0' && letter <= '9')){
                    return true; 
            }
        }
        return false; 
    }

    //return true if input is a number  
    static boolean numChecker(String input){
        for (int i=0; i<input.length();i++){
            Character letter = input.charAt(i);
            if (!(letter >= '0' && letter <= '9')){ //if the char isn't a number return true 
                return true;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        //recursive method is more efficient 
        //System.out.println("hello: " + palindromeRecursive("hello"));
        //System.out.println("rotor: " + palindromeRecursive("rotor"));

        System.out.println("Welcome to the App: \n 1. Palindrome Check \n 2. Anagram Check \n 3. Add Substring \n 4. Get Length \n 5. Count Occurrences \n 6. Reverse Sentence \n 7. Quit ");
        boolean run = true;
        Scanner in = new Scanner(System.in); //scanner to read in user input 

        while (run){ //while the user hasn't quit, keep running 
            System.out.println("");
            System.out.print("Choose an option: ");
            String option = in.nextLine();
            System.out.println("");
            //when option is chosen, the function corresponding to the option will be run
            if (option.equals("1")){
                System.out.print("Enter string: ");
                String input = in.nextLine();
                boolean result = palindromeRecursive(input);
                if (result == true){
                    System.out.println(input + " is a palindrome");
                } else {
                    System.out.println(input + " is not a palindrome");
                }
            } else if (option.equals("2")){
                System.out.print("Enter string: ");
                String input1 = in.nextLine();
                System.out.print("Enter another string: ");
                String input2 = in.nextLine();
                if (specialCharChecker(input1) || specialCharChecker(input2)) { //check if there are special chars, otherwise run code like normal 
                    System.out.println("Invalid input");
                } else {
                    boolean result = anagramChecker(input1, input2);
                    if (result){
                        System.out.println(input1 + " and " + input2 + " are anagrams");
                    } else {
                        System.out.println(input1 + " and " + input2 + " are not anagrams");
                    }
                }
            } else if (option.equals("3")){
                System.out.print("Enter string: ");
                String input = in.nextLine();
                System.out.print("Enter substring to be inserted: ");
                String substring = in.nextLine();
                System.out.print("Enter index for substring to be placed: ");  
                String index = in.nextLine();  //need to turn index into an integer 
                if (numChecker(index)) { //if index input aren't numbers, then print invalid 
                    System.out.println("Invalid input");
                } else {
                    String result = addSubstring(input, substring, Integer.parseInt(index));
                    System.out.println("New string: " + result);
                }
            } else if (option.equals("4")){
                System.out.print("Enter string: ");
                String input = in.nextLine();
                int result = getLength(input);
                System.out.println("Length: " + result);
            } else if (option.equals("5")){
                System.out.print("Enter string: ");
                String input = in.nextLine();
                System.out.print("Enter substring for occurrence count: ");
                String substring = in.nextLine();
                int result = occurrenceCounter(input, substring);
                System.out.println(substring + " appeared " + result + " times in " + input);
            } else if (option.equals("6")){
                System.out.print("Enter string: ");
                String input = in.nextLine();
                String result = sentenceReversal(input);
                System.out.println("New string: " + result);
            } else if (option.equals("7")){ //will stop the program 
                System.out.println("Have a good day :)");
                run = false;
            } else {
                System.out.println("Invalid input");
            }
        }
        in.close();
        
    }


}
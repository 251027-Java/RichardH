package com.Revature;

import java.lang.*;
import java.io.*;
import java.util.*;

public class Main
{
/*

    You are given a single string, and it is your job to figure out if that string is a palindrome. A palindrome is a number or word that are the same forwards and backwards (with exact upper/lower casing).

    The checkForPalindrome() method takes in 1 argument:
    s - the string that you are checking to see if it's a palindrome
    Once you've determined whether or not the string is a palindrome, return either true or false.


    Example Input:	Example Output:
    abbabba	true

    Explanation:
    abbabba backwards is abbabba, therefore the string is a palindrome and the method returns true.


    THE PROCESS:


    - accept the input string

        -scanner!
        Scanner scanner = new Scanner(System.in);

    - check if spelled the same forwards and backwards
    String word = scanner.nextLine();

    -- read it backwards, if it's the same as reading forwards, it's equal.

    String reversed = word.Reverse();

    --- take the original string, reverse it
    --- walk through the string, and the reversed string, comparing letter by letter.

    boolean palindrome;

    if (word == reversed){
        - if it is, set TRUE
        palindrome = true;
     } else {
        - if it's not, set FALSE
        palindrome = false;
     }

    -output true or false(boolean)

    System.out.println(palindrome);



















        */

    public static void main(String[] args)
    {
        //accept the input string

            //scanner!
        Scanner scanner = new Scanner(System.in);

        // check if spelled the same forwards and backwards
        String word = scanner.nextLine();

       // read it backwards, if it's the same as reading forwards, it's equal.

        String reversed = "";

        for (int i = word.length()-1; i >= 0; i-- ){
            reversed = reversed + word.charAt(i);
        }
        // take the original string, reverse it
            // walk through the string, and the reversed string, comparing letter by letter.
        System.out.println (reversed);


        boolean palindrome;

        if (word.equals(reversed)){
            // if it is, set TRUE
            palindrome = true;
        } else {
            // if it's not, set FALSE
            palindrome = false;
        }

        //output true or false(boolean)

            System.out.println(palindrome);

        /*
        Scanner sc=new Scanner(System.in);

        //given a single string(word) need to figure out if it is a Palindrome
        //compiler needs to be able to read in both upper and lower case
        //if else statement?

        int sampleInput;
        int result = -404;
        String word;
        sampleInput = sc.nextInt();
        boolean bresult = "false";

        //trying to get the right argument for the if statement
        //the point of the if statement is to see whether the word is the same
        //forwards and backwards
        //if it is, then itll print true
        //if not then false

        if(word.equals){

            System.out.println(true);

        }else if(word){
            System.out.println(bresult);
        }

        //write your Logic here

        //OUTPUT [uncomment & modify if required]
        System.out.println(result);
*/
    }
}

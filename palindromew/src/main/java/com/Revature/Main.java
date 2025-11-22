package com.Revature;

import java.lang.*;
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String sampleInput = sc.nextLine();

        //write your Logic here

        //OUTPUT [uncomment & modify if required]
        checkForPalindrome(sampleInput);
        System.out.println(checkForPalindrome(sampleInput));
    }

    public static boolean checkForPalindrome(String s){
        String word = s.toLowerCase();

        // this will simply reverse the word that is passed through
        // and will make it to toString
        String pali = new StringBuilder(word).reverse().toString();

// here if the word equals the reverse of the word, the system should return true;
// if not the system will return false
        if(word.equals(pali)){
            return true;
        } else {
            return false;
        }
// general return statement for the method, however, getting errors because of it
// pretty confident the code above will work fine
// when true --> system says "unreachable statement"
// when false --> system says "unreachable statement" as well
        //return false;
    }
}
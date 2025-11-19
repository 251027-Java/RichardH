package com.Revature;


import java.util.Scanner;

/*
You are tasked to convert a string into a secret code. Convert letters (ignoring case) and other characters into a secret code using the following rules:

A = 1, B = 2, C = 3, D = 4, E = 5, F = 6, G = 7
All other letters or characters are represented with a '?' in the secret code.

The createSecretCode() method takes in 1 argument:
s - the string that will be converted into the secret code string
Once you've created the secret code version of the string, return the secret code string.


Example Input:	Example Output:
Coding	3?4??7

Explanation:
Following the pattern (and ignoring upper and lower case), the letters are converted as followed:
C=3
O=?
D=4
I=?
N=?
G=7
        */
public class Main {
    static void main() {

        Scanner scanner = new Scanner(System.in);

        String word = scanner.nextLine().toUpperCase();
        String code = "";
        for(int i = 0; i < word.length(); i++){

            if (word.charAt(i) == 'A' || word.charAt(i) == 'a'){
                code += "1";
            } else if (word.charAt(i) == 'B' || word.charAt(i) == 'b'){
                code += "2";
            } else if (word.charAt(i) == 'C' || word.charAt(i) == 'c'){
                code += "3";
            } else if (word.charAt(i) == 'D'){
                code += "4";
            } else if (word.charAt(i) == 'E'){
                code += "5";
            } else if (word.charAt(i) == 'F'){
                code += "6";
            } else if (word.charAt(i) == 'G'){
                code += "7";
            } else {
                code += "?";
            }
        }
        System.out.println(code);


    }
}

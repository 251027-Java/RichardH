import java.lang.*;
import java.io.*;
import java.util.*;

public class Main
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        String sampleInput = sc.nextLine();
        int result = -404;

        //write your Logic here

        //OUTPUT [uncomment & modify if required]
        createSecretCode(sampleInput);
        System.out.println(createSecretCode(sampleInput));
    }

    public static String createSecretCode(String s){

        // Putting the key letter into a set so they can be referenced
//        Set<Character> letters = new HashSet<>();
//        letters.add("a");
//        letters.add("b");
//        letters.add("c");
//        letters.add("d");
//        letters.add("e");
//        letters.add("e");
//        letters.add("g");

        String word = s.toLowerCase();
        String result = "";
        // the idea of these if statements is to see if the word passed
        // has the key letters, the system will print out their corresponding
        // secret code number

        // could probably use a for loop I think which would be more efficient
        // just have to figure out how
        for(int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == 'a') {
                result += "1";
//            } else if (letters.contains(word.charAt("b"))) {
//                System.out.println("2");
//            } else if (letters.contains(word.charAt("c"))) {
//                System.out.println("3");
//            } else if (letters.contains(word.charAt("d"))) {
//                System.out.println("4");
//            } else if (letters.contains(word.charAt("e"))) {
//                System.out.println("5");
//            } else if (letters.contains(word.charAt("f"))) {
//                System.out.println("6");
//            } else if (letters.contains(word.charAt("g"))) {
//                System.out.println("7");
            } else {
                result += "?";
            }
        }
        return result;
    }
}
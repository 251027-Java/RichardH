import java.util.Scanner;

public class LetterGrade {
    public static void main(){
        Scanner scanner = new Scanner(System.in);
        IO.print("Enter your score: ");
        double grade = scanner.nextDouble();


        if (grade < 60) {
            IO.println("F");
        } else if ( grade < 70) {
            IO.println("D");
        } else if ( grade < 80) {
            IO.println("C");
        } else if ( grade < 90) {
            IO.println("B");
        } else {
            IO.println("A");
        }

        scanner.close();
    }
}

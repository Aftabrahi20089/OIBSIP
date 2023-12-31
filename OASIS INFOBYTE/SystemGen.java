import java.util.Random;
import java.util.Scanner;

public class SystemGen {
    public static void main(String[] args) {
        int maxValue = 100;   //Initialize the value

        Random random = new Random();
        int randomNumber = random.nextInt(maxValue + 1);   //maxValue + 1
        Scanner sc = new Scanner(System.in);

        boolean isMatched = false;
        int attempts = 0;
        int score = 100;
        int maxAttempts = 6;

        while (!isMatched && attempts < maxAttempts) {
            System.out.println("Enter the value that you thought between 1 to 100 :");
            int n = sc.nextInt();
            attempts++;

            // Condition for the guessing process
            if (n == randomNumber) {
                System.out.println("The number is matched");
                isMatched = true;
            }
            else if (n > randomNumber) {
                System.out.println("The number is greater than the value generated by system");
            }
            else {
                System.out.println("The number is lesser than the value generated by system");
            }

            // According to the attempt the score will be changed
            if (isMatched) {
                System.out.println("You guessed the number in " + attempts + " attempts.");
                if (attempts == 1) {
                    System.out.println("Score: " + score);
                } else if (attempts == 2) {
                    score = 85;
                    System.out.println("Score: " + score);
                } else if (attempts == 3){
                    score = 70;
                    System.out.println("Score: " + score);
                } else if (attempts == 4){
                    score = 50;
                    System.out.println("Score: " + score);
                } else if (attempts == 5){
                    score = 35;
                    System.out.println("Score: " + score);
                }
            }
        }

        //End the game after 6 attempts
        if (!isMatched) {
            System.out.println("YOU LOST THE GAME");
        }
    }
}

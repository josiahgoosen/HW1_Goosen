/**
 * Documentation:
 * 1) in getName(), copied code from https://www.quora.com/How-do-I-get-input-as-string-in-JAVA to get a string input
 * from the user
 * 2) learned how to use scan.nextInt within the getGuess method via
 * https://beginnersbook.com/2017/09/java-program-to-read-integer-value-from-the-standard-input/
 * 3) Couldn't figure out how to deal with errors when the input in getGuess was not an integer, learned about try
 * and catch here https://www.w3schools.com/java/java_try_catch.asp
 * 4) Learned how to generate a random number here
 * https://stackoverflow.com/questions/5271598/java-generate-random-number-between-two-given-values, copied the code
 * 5) Learned how to use switch statements on https://www.geeksforgeeks.org/switch-statement-in-java/
 *
 */
import java.util.Scanner;
import java.io.*;
import java.util.Scanner;
import java.lang.String;

public class Main {
    /**
     *
     * @param args - command line argument
     */
    public static void main(String[] args) {

        System.out.println("Welcome to the High Low Game!");
        String name = getName();
	    System.out.print("Welcome, " +name);
        System.out.println(", guess a number between 1 and 100");
	    boolean rightAnswer = false;
	    int guessCount = 0;
	    int low = 1;
	    int high = 100;
        int random = (int) (Math.random() * (high - low)) + low;
	    while (!rightAnswer) {
            int guess = getGuess();
            guessCount++;
            if (guess > high){
                System.out.println("You guessed higher than high end of the range. You bufoon.");
            }
            else if (guess > random){
                System.out.println("Too high, guess again.");
                high = guess;
            }
            else if (guess < low){
                System.out.println("You guessed lower than the low end of the range. Not the sharpest tool in the shed, are ya?");
            }
            else if (guess < random){
                System.out.println("Too low, guess again.");
                low = guess;
            }
            else{
                System.out.println("You won!");
                rightAnswer = true;
            }
        }

    }

    /**
     *
     * @return the player's name if they inputted something, if not, returns "Player 1"
     */
    private static String getName(){
        String name;
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter your name: ");
        name = scan.nextLine();
        if (name.equals("")){
            return "Player 1";
        }
        return name;
    }
    private static int getGuess(){
        int guess = 0;
        int goodGuess = 0;
        do {
            try {
                Scanner scan = new Scanner(System.in);
                System.out.println("Enter your guess: ");
                guess = scan.nextInt();
                if (guess > 100 || guess < 1) {
                    System.out.println("Sorry, your guess has to be an integer between 1 and 100.");
                }
                else {
                    goodGuess = 1;
                }
            }
            catch (Exception e){
                System.out.println("Sorry, your guess has to be an integer between 1 and 100.");
            }
        }while (goodGuess == 0);
        return guess;
    }
}

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
 */

import java.util.Scanner;
import java.lang.String;

public class Main {
    /** Main
     * @param args - command line argument
     */
    public static void main(String[] args) {
        boolean answer = true; // keeps track of the answer to if the player wants to play again, while this is true, the game keeps playing again
        int gameCount = 0; // counts the number of games played for the average number of guesses calculation at the end
        int guessCount = 0; // counts the total number of guesses for the average number of guesses calculation at the end
        System.out.println("Welcome to the High Low Game!");
        String name = getName(); // calls getName() method then uses this name inputted by the user for the remainder of the game
        Scanner scan = new Scanner(System.in);
        System.out.println("1 is the low, what would you like to be the high?"); // allows the user to choose what the range will be
        int high = scan.nextInt();
        int highLock = high; // copies the original choice of the high so that while the high changes during gameplay, it can reset if a new game is played
        do {
            int low = 1;
            int localGuessCount = 0; // tracks the guesses taken per round so it can report them at the conclusion of individual rounds
            if (gameCount > 1) {
                System.out.print("Welcome back, " + name); // welcomes the user back if they're on round 2 or above
            } else {
                System.out.print("Welcome, " + name);
            }

            System.out.println(", guess a number between 1 and " + high);
            boolean rightAnswer = false; // initializes that the guess is wrong, this will be changed when they get it right so that the gameplay loop can exit
            int random = (int) (Math.random() * (high - low)) + low; // generates a random number between 1 and the high that the user has chosen
            while (!rightAnswer) { // keeps the loop running until the right answer has been found
                int guess = getGuess(high); // calls the method to get the user guess
                guessCount++;
                localGuessCount++;
                if (guess > high) {
                    System.out.print("You guessed higher than high end of the range. " + name);
                    System.out.println(", are you sure you made it past 3rd grade?");
                } else if (guess > random) {
                    System.out.print("Too high, " + name);
                    System.out.println(", guess again.");
                    high = guess; // updates the high end once the user finds out the right answer is lower than his guess
                } else if (guess < low) {
                    System.out.print("You guessed lower than the low end of the range. Not the sharpest tool in the shed, are ya," + name);
                    System.out.println("?");
                } else if (guess < random) {
                    System.out.print("Too low, " + name);
                    System.out.println(", guess again.");
                    low = guess; // updates the low end once the user finds out the right answer is higher than his guess
                } else {
                    System.out.print("You won in " + localGuessCount);
                    System.out.println(" tries!");
                    rightAnswer = true; // sets rightAnswer to true so that the loop can exit
                }
            }
            answer = playAgain(); // calls a method to figure out if the user wants to play again, if playAgain() returns true, the loop continues
            high = highLock; // re-initializes the higher bound to what the user initially chose
            gameCount++;
        } while (answer);
        System.out.println("Thanks for playing!");
        getStats(gameCount, guessCount, high); // calls method to report the post game stats of the user
    }


    /** getName()
     * This method scans for user input for their name and uses it throughout the game
     * @return the player's name if they inputted something, if not, returns "Player 1"
     */
    private static String getName() {
        String name;
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter your name: ");
        name = scan.nextLine();
        if (name.equals("")) { // if the user simply hits enter without giving himself a name, the default "Player 1" is used
            return "Player 1";
        }
        return name;
    }

    /** getGuess()
     * This method prompts the user for their guess and if it is outside the bounds of 1 and the high, tells them to try again.
     * If the user does not enter an integer, an error is caught and it prompts the same error message, telling the user to
     * enter a number between 1 and the high.
     * @return the user's guess once it is a legal guess
     * @param high value set by the user
     */
    private static int getGuess(int high) {
        int guess = 0; // variable used to store the guess then return it
        int goodGuess = 0; // variable used to track if the guess is good
        do {
            try {
                Scanner scan = new Scanner(System.in);
                System.out.println("Enter your guess: ");
                guess = scan.nextInt();
                if (guess > 100 || guess < 1) {
                    System.out.println("Sorry, your guess has to be an integer between 1 and "+high);
                } else {
                    goodGuess = 1;
                }
            } catch (Exception e) { // if an error is generated by failure to enter an integer, the error message is given and the user tries again
                System.out.println("Sorry, your guess has to be an integer between 1 and "+high);
            }
        } while (goodGuess == 0); // loop continues until the guess is legal
        return guess; // after the user has a successful guess, it is returned
    }

    /** playAgain()
     * This method asks the user if he wants to play again
     * @return true or false -- true if the user wants to play again, false if not
     */
    private static boolean playAgain() {
        String answer; // initialized string to store the user answer
        boolean goodAns = false; // boolean used to track if the user is answering the question properly
        Scanner scan = new Scanner(System.in);
        do {
            System.out.println("Would you like to play again? (yes or no)");
            answer = scan.nextLine();
            switch (answer) {
                case "y":
                    return true;
                case "yes":
                    return true;
                case "Yes":
                    return true;
                case "Y":
                    return true;
                case "n":
                    return false;
                case "N":
                    return false;
                case "no":
                    return false;
                case "No":
                    return false;
                default:
                    goodAns = false; // if none of the acceptable answers are given, the loop continues to ask the user
            }
        } while (!goodAns); // loop continues until a good answer is given
        return false; // redundant but couldn't run without this, even though it will never get to this statement
    }

    /** getStats(int gameCount, int guessCount, int highRange)
     * Void method that takes the game count, guess count, and the user's chosen high range and prints the average and
     * optimal scores, giving different feedback for how close the user was to the optimal score
     * @param gameCount total number of games played
     * @param guessCount total number of guesses throughout all games
     * @param highRange user's chosen high range
     */
    private static void getStats(int gameCount, int guessCount, int highRange) {
        double avg = (double) guessCount / gameCount; // calculates the average score per game
        double optimal = Math.log(highRange) / Math.log(2); // calculates the optimal score
        if (avg > (optimal + 2)) {
            System.out.printf("Your average is: %.1f and the optimal is %.1f. You kinda suck at this game.\n", avg, optimal);
        } else if (avg > optimal) {
            System.out.printf("Your average is: %.1f and the optimal is %.1f. You're not great but you're not terrible either.\n", avg, optimal);
        } else if (avg < (optimal - 2)) {
            System.out.printf("Your average is: %.1f and the optimal is %.1f. Quit your day job, you're good!\n", avg, optimal);
        } else {
            System.out.printf("Your average is: %.1f and the optimal is %.1f. You're alright!\n", avg, optimal);
        }
    }
}

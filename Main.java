// WORDLE GAME ASSIGNMENT - LUKAS CUPSA - March 26, 2022
//Replit Link: https://replit.com/join/cylnwbwajd-lukascupsa

//TO-DO: INTEGRATE PROJECT WITH HTML, THEN CSS

import java.util.Scanner;
import java.io.*;
import java.util.*;

public class Main {

  // Declaring ANSI_RESET so that we can reset the color
  public static final String ANSI_RESET = "\u001B[0m";

  // Declaring the yellow color & customizing
  public static final String ANSI_YELLOW = "\u001B[33m";

  // Declaring the green color & customizing
  public static final String ANSI_GREEN = "\u001B[32m";

  // Declaring the black color & customizing
  public static final String ANSI_BLACK = "\u001B[30m";

  // Setting white background color
  public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

  // Defining userinput
  public static Scanner userInput = new Scanner(System.in);

  public static void main(String[] args) {

    // variable for play again
    boolean playagain = true;

    // while loop for play again
    while (playagain != false) {

      // Greeting user
      greetUser();

      // Calling method to define list array
      String word = wordList();

      // User guess
      String userGuess = userInput.nextLine();

      // making sure it fits parameters
      userGuess = parameterCheck(userGuess);

      // checking user guess & repeating for 6 total tries
      for (int n = 6; n > 0; n--) {

        // calling method check
        System.out.println(check(userGuess, word));

        // checking responses
        if (userGuess.equals(word) == true) {
          // Printing winner
          System.out.println(score(n));
          //Exiting loop
          n = -1;
        }
        // Printing failure
        else if (n == 1) {
          System.out.println(ANSI_RESET + "Better luck next time!");
        }

        // Getting another guess
        else {
          // Calling another guess method
          userGuess = anotherguess(userGuess, n);
        }
      }

      // Printing final word
      System.out.println(ANSI_RESET + "The word was: " + word);

      // Play again
      playagain = playAgain();
    }
  }
  // ALL METHODS

  // method for greeting user
  public static void greetUser() {

    // Greeting user
    System.out.println(ANSI_RESET + "Welcome to Knock-Off Wordle! Try and guess the 5-letter word in 6 tries!");
    
    //Explaining rules
    System.out.println(ANSI_WHITE_BACKGROUND + ANSI_GREEN + "GREEN" + ANSI_RESET + " is correct letter and correct place.");
    
    System.out.println(ANSI_WHITE_BACKGROUND + ANSI_YELLOW + "YELLOW" + ANSI_RESET + " is correct letter and wrong place.");
    
    System.out.println(ANSI_WHITE_BACKGROUND + ANSI_BLACK + "BLACK" + ANSI_RESET + " is incorrect letter.");
    
    //Prompting user
     System.out.println("\nEnter your guess:" + ANSI_WHITE_BACKGROUND + ANSI_BLACK);
    
  }

  // method for list of words (array)
  public static String wordList() {

    // Defining list array and counter
    int count = 0;
    String[] list = new String[14855];

    // Code to grab words from .txt file
    try {
      File myObj = new File("wordleWords.txt");
      Scanner myReader = new Scanner(myObj);
      while (myReader.hasNextLine()) {
        String data = myReader.nextLine();

        // add data to the array
        list[count] = (data);
        count++;

      }
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }

    // random word
    int number = (int) (Math.random() * list.length);
    String chosenWord = list[number];

    // returning the random word
    return chosenWord;
  }

  // method for checking parameters
  public static String parameterCheck(String userGuess) {

    // Defining new string
    String fixedWord = userGuess.toLowerCase();

    // while loop to acquire correct word length
    while (fixedWord.length() != 5) {

      // Stating the error
      System.out.println(ANSI_RESET + "Oops! Please put a 5-letter word!\n" + ANSI_WHITE_BACKGROUND + ANSI_BLACK);

      // Getting fixed input
      fixedWord = userInput.nextLine();
    }

    
    // Defining array for list of words
    int count = 0;
    String[] list = new String[14855];

    // Teacher code to grab words from .txt
    try {
      File myObj = new File("wordleWords.txt");
      Scanner myReader = new Scanner(myObj);
      while (myReader.hasNextLine()) {
        String data = myReader.nextLine();

        // add data to the array
        list[count] = (data);
        count++;

      }
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
     

    // Checking to ensure user inputted actual word
    List<String> listList = new ArrayList<>(Arrays.asList(list));

    while (listList.contains(fixedWord) != true) {
      // Stating the error
      System.out.println(ANSI_RESET + "That word was not found. Please enter a valid 5-letter word!\n" + ANSI_WHITE_BACKGROUND + ANSI_BLACK);

      // Getting fixed input
      fixedWord = userInput.nextLine();
    }

    // returning the fixed input
    return fixedWord;
  }

  // check guess method
  public static String check(String userGuess, String word) {
    //new strings & variables
    String newPhrase = "";
    userGuess = userGuess.toLowerCase();
    String correctWord = word;
    char str[] = word.toCharArray();
    String doubleCase = "";
    boolean check = false;
    String letter;
    //Empty Array for letter & color check
    int[] repeat = new int[26];
    String[] coloring = new String[5];
    //Array to check position
    int[] position = {1,1,1,1,1};
      
    // Used as index in the modified string
      int index = 0;
    
    //checking word for double case
    for (int x = 0; x < word.length(); x++) {
      //Eliminating double cases
        {
          // Check if str[i] is present before it
            int j;
            for (j = 0; j < x; j++) {
                if (str[x] == str[j]) {
                    break;
                }
            }
            // If not present, then add it to result.
            if (j == x) {
                str[index++] = str[x];
            }
        }
      }
    
    //Declaring doubleCase
    doubleCase = String.valueOf(Arrays.copyOf(str, index));

    //Defining counter t
    int t = 0;

    //Going through and checking double letters
      for (int x = 0; x < word.length(); x++) {
        
        //adding value to letter amount to meet double case
        t = (int) word.charAt(x);
        t = t-97;
        repeat[t]++;
        }
    
    //If double case is true
     if (doubleCase.length() != word.length()) {
        check = true;
      }

    //Going through regular process if no double case
    if (check == false) {
    
    //checking user string for correct
      for (int x = 0; x < userGuess.length(); x++) {
        //adding value to letter amount to meet double case
        t = (int) userGuess.charAt(x);
        t = t-97;
      
      //if the letter is correct
      if (userGuess.charAt(x) == correctWord.charAt(x)) {
        coloring[x] = "G";
        //removing it's occurence
        repeat[t]--;
        //marking character as done
        position[x] = 0;
        }
      
      //coloring rest black
      else {
        coloring [x] = "B";
      }
        
      }
      
      //checking user string for yellow
      for (int x = 0; x < userGuess.length(); x++) {
        
        //adding value to letter amount to meet double case
        t = (int) userGuess.charAt(x);
        t = t-97;
  
        //if the letter is in the wrong spot
       if (correctWord.indexOf(userGuess.charAt(x)) > -1 && position[(correctWord.indexOf(userGuess.charAt(x)))] != 0) {
        
        //Checking how many times the letter occurs
        if (repeat[t] != 0) {

        //setting color
        coloring[x] = "Y";

        repeat[t] = 0;
          }
         
      }
    }

        //Coloring letters
        for (int x = 0; x < userGuess.length(); x++) {
          
        //Coloring green
        if (coloring[x].equals("G")) {
          newPhrase += ANSI_GREEN + ANSI_WHITE_BACKGROUND + userGuess.charAt(x) + ANSI_RESET;
          }
          
          //Coloring yellow
          else if (coloring[x].equals("Y")) {
          newPhrase += ANSI_YELLOW + ANSI_WHITE_BACKGROUND + userGuess.charAt(x) + ANSI_RESET;
          }
          
          //Coloring black
          else {
          newPhrase += ANSI_BLACK + ANSI_WHITE_BACKGROUND + userGuess.charAt(x) + ANSI_RESET;
          }
        }
  }
      
  //Special case for double cases
  else {
    
     //Going through guess to assert green
     for (int x = 0; x < userGuess.length(); x++) {
       
      //if the letter is correct
      if (userGuess.charAt(x) == correctWord.charAt(x)) {
        
        //defining t
        t = (int) userGuess.charAt(x);
        t = t-97;

        //adding value to position to ensure no double case
        position[x] = 0;
        repeat[t]--;

        //setting color
        coloring[x] = "G";

        //changing the variable to eliminate repeating green
        letter = String.valueOf(userGuess.charAt(x));
        correctWord = correctWord.replaceFirst(letter, "1");
        }
        //coloring everything else black
        else {
        
          //setting color
          coloring[x] = "B";
      }
    }
     //Going through guess to assert yellow
     for (int x = 0; x < userGuess.length(); x++) {
        
       //defining t
        t = (int) userGuess.charAt(x);
        t = t-97;
       
       //making sure it's not been done and is in the word.
      if (correctWord.indexOf(userGuess.charAt(x)) > -1 && repeat[t] > 0 && position[x] != 0) {

        //setting color
        coloring[x] = "Y";
        //Removing instance
        repeat[t]--;

        }
       }

        
        //Coloring letters
        for (int x = 0; x < userGuess.length(); x++) {
          
        //Coloring green
        if (coloring[x].equals("G")) {
          newPhrase = newPhrase + ANSI_GREEN + ANSI_WHITE_BACKGROUND + userGuess.charAt(x) + ANSI_RESET;
          }
          
          //Coloring yellow
          else if (coloring[x].equals("Y")) {
          newPhrase = newPhrase + ANSI_YELLOW + ANSI_WHITE_BACKGROUND + userGuess.charAt(x) + ANSI_RESET;
          }
          
          //Coloring black
          else {
          newPhrase = newPhrase + ANSI_BLACK + ANSI_WHITE_BACKGROUND + userGuess.charAt(x) + ANSI_RESET;
          }
        }
    }
    //returning the guess
    return newPhrase;
  }

  // another guess method
  public static String anotherguess(String userGuess, int n) {
    System.out.println("Not quite. Attempts left: " + (n - 1) + ANSI_WHITE_BACKGROUND + ANSI_BLACK);
    userGuess = userInput.nextLine();

    // making sure it fits parameters
    userGuess = parameterCheck(userGuess);

    // returning s
    return (userGuess);
  }

  // Playagain method
  public static boolean playAgain() {

    // Prompt user for play again
    System.out.println("Would you like to play again? (answer 'y' or 'n')");

    // User slot choice
    char response = userInput.next().charAt(0);

    // Make sure the user imputs a valid response
    while (response != 'y' && response != 'n') {
      System.out.println("Uh Oh. Please enter a valid response. Would you like to play again? (answer 'y' or 'n')");

      // User slot choice
      response = userInput.next().charAt(0);
    }

    // Check if playing again
    if (response == 'y') {
      return (true);
    } else {
      return (false);
    }
  }
  
// Score method
  public static String score(int n) {
    //Defining variables & setting score
    int x = n*10;
    
    //setting returnable string
    String newScore = "Winner! You got it right! Your score: " + x;
    
    //returning string
    return newScore;
  }
}

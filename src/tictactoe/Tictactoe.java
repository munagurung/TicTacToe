package tictactoe;

import java.util.Scanner;

/**
 *
 * @author Muna Gurung {@literal <munagrg126@gmail.com>}
 */
public class Tictactoe {

    /**
     * Row and Column variables to save user input
     */
    public static int row, col;
    
    /**
     * Player name
     */
    public static char player = 'X';
    
    /**
     * Tic Tac Toe board with 3 rows and 3 column
     */
    public static char[][] gameBoard = new char[3][3];
    
    /**
     * Scanner to get user input
     */
    public static Scanner scan = new Scanner(System.in);
    
    /**
     * Keep track of whether game has started, default value set to false/ not started
     */
    public static boolean gameStarted = false;
    
    /**
     * Keep track of whether game has ended, default value set to false/ not ended
     */
    public static boolean gameEnded = false;
    
    /**
     * Keep track of whether board has default values, default value set to true/ no player moves saved in the board
     */
    public static boolean defaultBoard = true;
    
    /**
     * Tic Tac Toe maximum round number set to 7, 
     * no player will be able to win if game has not ended by round 7 because
     * no more winnable moves left
     */
    public static int MAXIMUM_ROUND_NUMBER = 7;
    
    /**
     * Keeps track of Tic Tac Toe round number
     */
    public static int roundNumber = 1;

    /**
     * Main entry point of the Tic Tac Toe game program
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        WelcomeMessage();
        FillDefaultBoard();
        while (!gameEnded) {
            Play();
            ChangePlayer();
            roundNumber += 1; // increment round number
        }
        DisplayGameBoard();
    }

    /**
     * Display welcome message
     */
    public static void WelcomeMessage() {
        System.out.println("----------------------------------------"
                + "\nWelcome to Tic Tac Toe game. Have fun!"
                + "\n----------------------------------------");
    }

    /**
     * Changes player, player will be either O or X
     */
    public static void ChangePlayer() {
        if (player == 'X') {
            player = 'O';
        } else {
            player = 'X';
        }

    }

    /**
     * Displays message to indicate valid number for row and column input
     */
    public static void DisplayValidNumberMessage() {
        System.out.println("\nYou must enter a number from 1 to 3!");
    }

    /**
     * Prompts for row and col input, checks and save their values if valid.
     * Re-prompts for row and column input if invalid input
     */
    public static void PromptInput() {
        // to check if user has put a valid row/ col num
        boolean isValidNumber = true;
        do {
            DisplayGameBoard();
            System.out.println("\n\nPlayer " + player + " turn. "
                    + "\n\nEnter row and column numbers. ");
            DisplayValidNumberMessage();

            // prompt user to re-enter if invalid number
            while (!scan.hasNextInt()) {
                // subtract user input by 1 because array index starts from 0
                DisplayValidNumberMessage();
                scan.next();
            }
            row = scan.nextInt() - 1;
            // check if the user inputs number in the range of 1 to 3
            // if invalid, prompt user to re-enter again
            // continue this until valid input is received
            while (isValidNumber) {
                if (row >= 4 || row < 0) {
                    DisplayValidNumberMessage();

                    while (!scan.hasNextInt()) {
                        DisplayValidNumberMessage();
                        scan.next();
                    }
                    row = scan.nextInt() - 1;
                } else {
                    isValidNumber = false;
                }
            }

            // prompt user to re-enter if invalid number
            while (!scan.hasNextInt()) {
                // subtract user input by 1 because array index starts from 0
                DisplayValidNumberMessage();
                scan.next();
            }
            col = scan.nextInt() - 1;
            // check if the user inputs number in the range of 1 to 3
            // if invalid, prompt user to re-enter again
            // continue this until valid input is received
            while (isValidNumber) {
                if (col >= 4 || col < 0) {
                    DisplayValidNumberMessage();

                    while (!scan.hasNextInt()) {
                        DisplayValidNumberMessage();
                        scan.next();
                    }
                    col = scan.nextInt() - 1;
                } else {
                    isValidNumber = false;
                }
            }
        } while (!(row < 3 && col < 3));
    }

    /**
     * Marks the game board with user's name(either X or O) with it's given
     * row and col input values
     */
    public static void Play() {
        boolean isFilled = true;

        PromptInput();
        // check if the spot is already filled
        while (isFilled) {
            // if it is already filled
            if (gameBoard[row][col] != '_') {
                System.out.println("Spot taken already by player "
                        + gameBoard[row][col] + "!\nTry again with different row"
                        + " and column number.");
                PromptInput();
            } else {
                gameBoard[row][col] = player; // save player move into the board
                defaultBoard = false; // board has been filled by player
                GameOver(row, col); // check if game is over after the player plays

                isFilled = false;
            }
        }
    }

    /**
     * If there is no more possible moves with max. of 7 rounds, i.e. no 
     * winner, then ends the game with suitable message
     */
    public static void NoWinner() {
        // boolean isEmpty = false; // spot is empty
        int numOfEmptySpots = 0;
        // check if all spot is already filled, foreach loop
        for (int i = 0; i < 3; i++) {

            for (int j = 0; j < 3; j++) {
                // if empty
                if (gameBoard[i][j] == '_') {
                    // isEmpty = true;
                    numOfEmptySpots += 1;
                }
            }
        }
        // if only 2 empty spots left, then there is no winner
        // if the game has not ended then, end the game
        if (numOfEmptySpots == 2 && gameEnded == false) {
            System.out.println("Game over!!! No winners...");
            gameEnded = true;
        } else {
            gameEnded = false;
        }
    }

    /**
     * Checks if game is over after each user row and col input
     * @param row row number input by user
     * @param col column number input by user
     */
    public static void GameOver(int row, int col) {
        // horizontal win
        if (gameBoard[row][0] == gameBoard[row][1]
                && gameBoard[row][0] == gameBoard[row][2]) {
            System.out.println("Game over!!! Congratulations, player " + player
                    + " wins!");
            gameEnded = true;
        } // vertical win
        else if (gameBoard[0][col] == gameBoard[1][col]
                && gameBoard[0][col] == gameBoard[2][col]) {
            System.out.println("Game over!!! Congratulations, player " + player
                    + " wins!");
            gameEnded = true;
        } // diagonal win
        else if (gameBoard[1][1] != '_'
                && gameBoard[0][0] == gameBoard[1][1]
                && gameBoard[0][0] == gameBoard[2][2]) {
            System.out.println("Game over!!! Congratulations, player " + player
                    + " wins!");
            gameEnded = true;
        } else if (gameBoard[1][1] != '_'
                && gameBoard[0][2] == gameBoard[1][1]
                && gameBoard[0][2] == gameBoard[2][0]) {
            System.out.println("Game over!!! Congratulations, player " + player
                    + " wins!");
            gameEnded = true;
        } // no winner 
        else if (roundNumber >= MAXIMUM_ROUND_NUMBER) {
            NoWinner();
        } else {
            gameEnded = false;
        }
    }

    /**
     * Fill board with _ value to prepare the board for game
     */
    public static void FillDefaultBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                gameBoard[i][j] = '_';
            }
        }
    }

    /**
     * Display the game board
     */
    public static void DisplayGameBoard() {
        for (int i = 0; i < 3; i++) {
            System.out.println();
            if (i == 1 || i == 2 || i == 0) {
                System.out.print("| ");
            }

            for (int j = 0; j < 3; j++) {
                System.out.print(gameBoard[i][j] + " | ");
            }
        }
    }
}

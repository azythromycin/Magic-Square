import java.io.*;
import java.util.Scanner;

public class MagicSquare {

    static Scanner input = new Scanner(System.in);

    static int sizeOfMagicSquare = determineUserInput();

    // Method to accurately collect user input on the number of rows (and columns
    // but this is a square so rows
    // would suffice!)
    public static int determineUserInput() {
        // Variable to eat up the invalid input to prevent the infinite loop
        String garbage = "";
        try {
            System.out.print("Enter the number of rows and columns for the magic square: ");
            int userInput = input.nextInt();

            if ((userInput % 2 == 0) || (userInput <= 0)) {
                System.out.println("Magic square can only work with odd scalar values! Please try again: ");
                determineUserInput();
            } else {
                sizeOfMagicSquare = userInput;
            }
        } catch (Exception e) {
            System.out.println("Please enter an integer value only: ");
            garbage = input.next();
            determineUserInput();
        }

        return sizeOfMagicSquare;
    }

    // startRow and Col start off as initial values and then proceed to act like
    // checkpoints throughout the implementation of the updatePosition method
    static int startRow = 0;
    static int startCol = (sizeOfMagicSquare - 1) / 2;

    // The next position coordinates
    static int nextRow = 0;
    static int nextColumn = 0;

    static int[][] theMagicSquare = new int[sizeOfMagicSquare][sizeOfMagicSquare];

    // creating and initializing the magic square with the value 0s
    /*
     * User input: 5
     * 0 0 0 0 0
     * 0 0 0 0 0
     * 0 0 0 0 0
     * 0 0 0 0 0
     * 0 0 0 0 0
     */
    public static void initializingMagicSquare(int size) {
        for (int i = 0; i < sizeOfMagicSquare; i++) {
            for (int j = 0; j < sizeOfMagicSquare; j++) {
                theMagicSquare[i][j] = 0;
            }
        }
    }

    // Calling the methods accordingly to print the final output
    public static void main(String[] args) {
        initializingMagicSquare(sizeOfMagicSquare);
        makeMagicSquare(sizeOfMagicSquare);
        printmagicSquare();
    }

    // This method is a major player in the magic square implementation.
    public static void updatePosition(int size) {
        nextRow = startRow - 1;
        nextColumn = startCol + 1;

        // Edited: Constraints now sit in the update method than the implementation of
        // the magic square (simply because it makes more sense and more readable)
        if (nextRow < 0) {
            nextRow = sizeOfMagicSquare - 1;
        }
        if (nextColumn == sizeOfMagicSquare) {
            nextColumn = 0;
        }
        if (theMagicSquare[nextRow][nextColumn] != 0) {
            nextRow++;
        }
    }

    // This method is responsible for assigning the incremented value (i) in the
    // correct position
    public static void assignValueToBox(int value) {
        theMagicSquare[nextRow][nextColumn] = value;
    }

    public static void makeMagicSquare(int size) {
        int i = 1;
        // Using startRow and startCol here since its 1. The nextRows and columns would
        // take over the rest of the square
        theMagicSquare[startRow][startCol] = i;
        while (i < sizeOfMagicSquare * sizeOfMagicSquare) {
            i++;
            updatePosition(size);
            assignValueToBox(i);
            // Since our nextRows and nextColumns rely on the values of the startRow and
            // startCol, the latter two should be updated to yield accurate results
            startRow = nextRow;
            startCol = nextColumn;
        }
    }

    // Print the magic square
    public static void printmagicSquare() {
        for (int i = 0; i < sizeOfMagicSquare; i++) {
            for (int j = 0; j < sizeOfMagicSquare; j++) {
                System.out.print(" " + theMagicSquare[i][j] + " ");
            }
            System.out.println();
        }
    }
}

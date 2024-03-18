package com.hjss.views;

import com.hjss.exception.InvalidMenuChoiceException;

import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * An abstract class representing a view in the swimming school application.
 * Views are responsible for displaying menus and retrieving user input.
 */
public abstract class View {
    /** The length of the menu options. */
    protected final int length;

    /** The scanner object used to read input from the user. */
    protected Scanner scanner;

    /**
     * Constructs a new View object with the specified menu length.
     *
     * @param length The length of the menu options.
     */
    public View(int length) {
        scanner = new Scanner(System.in);
        this.length = length;
    }

    /**
     * Displays the menu options.
     */
    public abstract void displayMenu();

    /**
     * Retrieves the user's menu choice.
     *
     * @return The user's menu choice.
     */
    public int getMenuChoice() {
        int choice = 0;
        boolean isValidInput = false;

        while (!isValidInput) {
            try {
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();

                if (!isValidMenuChoice(choice)) {
                    throw new InvalidMenuChoiceException("Enter Valid Menu Choice");
                }

                isValidInput = true; // If no exception occurs, input is valid
            } catch (InputMismatchException e) {
                System.out.println("\u001B[31mInvalid input. Please enter a number.\u001B[0m");
            } catch (InvalidMenuChoiceException e) {
                System.out.println("\u001B[31m" + e.getMessage() + "\u001B[0m");
            }

            scanner.nextLine(); // Clear the invalid input from the scanner

        }
        return choice;
    }

    /**
     * Checks if the provided menu choice is valid.
     *
     * @param choice The menu choice to be validated.
     * @return true if the menu choice is valid, false otherwise.
     */
    protected boolean isValidMenuChoice(int choice) {
        return choice >= 0 && choice <= length;
    }

    /**
     * Pads the given number to two digits.
     *
     * @param number The number to be padded.
     * @return The padded number as a string.
     */
    public String padToTwoDigits(double number) {
        DecimalFormat df = new DecimalFormat("00");
        return df.format(number);
    }

}

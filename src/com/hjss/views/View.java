package com.hjss.views;

import com.hjss.exception.InvalidMenuChoiceException;

import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class View {
    private final int length;
    protected Scanner scanner;

    public View(int length) {
        scanner = new Scanner(System.in);
        this.length = length;
    }

    public abstract void displayMenu();

    public int getMenuChoice() {
        int choice = 0;
        boolean isValidInput = false;

        while (!isValidInput) {
            try {
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();

                if (!isValidMenuChoice(choice)) {
                    throw new InvalidMenuChoiceException("Enter Valid Menu Option");
                }

                isValidInput = true; // If no exception occurs, input is valid
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
            } catch (InvalidMenuChoiceException e) {
                System.out.println(e.getMessage());
            }

            scanner.nextLine(); // Clear the invalid input from the scanner

        }
        return choice;
    }

    protected boolean isValidMenuChoice(int choice) {
        return choice >= 0 && choice <= length;
    }

}

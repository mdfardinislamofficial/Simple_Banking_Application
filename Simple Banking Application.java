/****************************************
*
* Student Name: Muhammad Fardin Islam
* Date Due: 11/30/2023
* Date Submitted: 11/29/2023
* Program Name: FinalProject
* Program Description: Simple Banking Application
*
*
****************************************/



package FinalProject;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Date;

public class FinalProject {
    private static double balance = 0.0;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int mainOption;

        do {
            System.out.println("--- Main Menu ---");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Transfer");
            System.out.println("4. Balance");
            System.out.println("5. Exit");
            System.out.print("Enter an option: ");
            mainOption = input.nextInt();

            switch (mainOption) {
                case 1:
                    deposit(input);
                    break;
                case 2:
                    withdraw(input);
                    break;
                case 3:
                    transfer(input);
                    break;
                case 4:
                    System.out.printf("Your current balance is: $%.2f%n", balance);
                    break;
                case 5:
                    System.out.println("Exiting program...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        } while (mainOption != 5);

        input.close();
    }

    public static void deposit(Scanner scanner) {
        int depositOption;
        do {
            System.out.println("--- Deposit ---");
            System.out.println("a. Check");
            System.out.println("b. Cash");
            System.out.println("c. Main menu");
            System.out.print("Select an option: ");
            char option = scanner.next().charAt(0);

            depositOption = option;
            switch (depositOption) {
                case 'a':
                case 'b':
                    System.out.print("Enter the amount to deposit: ");
                    double amount = scanner.nextDouble();
                    balance += amount;
                    logTransaction("Deposit", amount);
                    System.out.printf("Successfully deposited $%.2f%n", amount);
                    break;
                case 'c':
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        } while (depositOption != 'c');
    }

    public static void withdraw(Scanner input) {
        int withdrawOption;
        do {
            System.out.println("--- Withdraw ---");
            System.out.println("a. $100");
            System.out.println("b. $50");
            System.out.println("c. $10");
            System.out.println("d. Main menu");
            System.out.print("Select an option: ");
            char option = input.next().charAt(0);

            withdrawOption = option;
            double amount = 0;
            switch (withdrawOption) {
                case 'a':
                    amount = 100;
                    break;
                case 'b':
                    amount = 50;
                    break;
                case 'c':
                    amount = 10;
                    break;
                case 'd':
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
                    continue;
            }

            if (balance >= amount) {
                balance -= amount;
                logTransaction("Withdraw", amount);
                System.out.printf("Successfully withdrew $%.2f%n", amount);
            } else {
                System.out.println("Insufficient funds.");
            }
        } while (withdrawOption != 'd');
    }

    public static void transfer(Scanner input) {
        int transferOption;
        do {
            System.out.println("--- Transfer ---");
            System.out.println("a. Same Bank");
            System.out.println("b. Different Bank");
            System.out.println("c. Main menu");
            System.out.print("Select an option: ");
            char option = input.next().charAt(0);

            transferOption = option;
            switch (transferOption) {
                case 'a':
                case 'b':
                    System.out.print("Enter the amount to transfer: ");
                    double amount = input.nextDouble();
                    if (balance >= amount) {
                        balance -= amount;
                        logTransaction("Transfer", amount);
                        System.out.printf("Successfully transferred $%.2f%n", amount);
                    } else {
                        System.out.println("Insufficient funds.");
                    }
                    break;
                case 'c':
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        } while (transferOption != 'c');
    }

    public static void logTransaction(String event, double amount) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("log.txt", true))) {
            Date date = new Date();
            writer.write(String.format("%s\t%s\t$%.2f%n", date.toString(), event, amount));
        } catch (IOException e) {
            System.out.println("An error occurred while logging the transaction.");
            e.printStackTrace();
        }
    }
}

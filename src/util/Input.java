package util;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Input {
    private Scanner scanner;

    public Input() {
        scanner = new Scanner(System.in);
    }

    public String getString() {
        String response;

        response = scanner.nextLine();

        return response;
    }

    public String getString(String prompt) {
        System.out.print(prompt);
        return getString();
    }

    public boolean yesNo() {
        String response;

        response = scanner.nextLine();
        return (response.equalsIgnoreCase("yes") || response.equalsIgnoreCase("y"));
    }

    public boolean yesNo(String prompt) {
        System.out.print(prompt);
        return yesNo();
    }

    public int getInt() {
        String response = getString();

        try {
            return Integer.valueOf(response);

        } catch (NumberFormatException nfe) {
            System.out.println("Input must be an integer!");
            return getInt();
        }
    }

    public int getInt(String prompt) {
        System.out.print(prompt);
        return getInt();
    }

    public int getInt(int min, int max) {
        int response;

        try {
            response = getInt();

            if (response < min || response > max) {
                throw new OutOfBoundsException();
            }
        } catch(OutOfBoundsException obe) {

            System.out.printf("Input is outside the range [%d, %d]!\n", min, max);
            return getInt(min, max);
        }

        return response;
    }

    public int getInt(int min, int max, String prompt) {
        System.out.print(prompt);
        return getInt(min, max);
    }

    public double getDouble() {
        String response = getString();
        try {
            return Double.valueOf(response);

        } catch (NumberFormatException nfe) {
            System.out.println("Input must be an double!");
            return getDouble();
        }
    }

    public double getDouble(String prompt) {
        System.out.print(prompt);
        return getDouble();
    }

    public double getDouble(double min, double max) {
        double response;

        try {
            response = getDouble();

            if (response < min || response > max) {
                throw new OutOfBoundsException();
            }
        } catch(OutOfBoundsException obe) {

            System.out.printf("Input is outside the range [%.2f, %.2f]!\n", min, max);
            return getDouble(min, max);
        }

        return response;
    }

    public double getDouble(double min, double max, String prompt) {
        System.out.print(prompt);
        return getDouble(min, max);
    }

    public int getBinary() {
        try {
            return Integer.valueOf(getString(), 2);
        } catch (NumberFormatException nfe) {
            System.out.println("Input must be a binary number!");
            return getBinary();
        }
    }

    public int getBinary(String prompt) {
        System.out.print(prompt);
        return getBinary();
    }

    public int getHex() {
        try {
            return Integer.valueOf(getString(), 16);
        } catch (NumberFormatException nfe) {
            System.out.println("Input must be a hexadecimal number!");
            return getHex();
        }
    }

    public int getHex(String prompt) {
        System.out.print(prompt);
        return getHex();
    }
}

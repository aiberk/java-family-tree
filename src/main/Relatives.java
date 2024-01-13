package main;

import java.io.*;
import java.util.Scanner;

public class Relatives {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt the user for the path to the text file
        System.out.print("Enter the path to the text file: ");
        String filePath = scanner.nextLine();

        try {
            // Create a BufferedReader to read the text file
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));

            // Now, read and print the content of the text file line by line
            System.out.println("Contents of the text file:");
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }

            // Close the BufferedReader
            bufferedReader.close();
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }

        // Close the scanner
        scanner.close();
    }
}

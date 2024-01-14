package main;

import java.io.*;
import java.util.Scanner;

public class Relatives {
    private Tree<Person> familyTree;

    public Relatives() {

    }

    public void processFile(String filePath) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
            boolean processingRelationships = false;
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                if (line.equals("END")) {
                    processingRelationships = !processingRelationships;
                    continue;
                }

                if (!processingRelationships) {
                    // Add each individual with null parents
                    familyTree.addPerson(line, null, null);
                } else {
                    // Establish relationships
                    String[] parts = line.split(" ");
                    String childName = parts[0];
                    String motherName = parts.length > 1 ? parts[1] : null;
                    String fatherName = parts.length > 2 ? parts[2] : null;
                    familyTree.addPerson(childName, motherName, fatherName);
                }
            }

            bufferedReader.close();
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // Scanner scanner = new Scanner(System.in);

        // // Create a Relatives instance
        // Relatives relatives = new Relatives();

        // // Prompt the user for the path to the text file
        // System.out.print("Enter the path to the text file: ");
        // String filePath = scanner.nextLine();

        // // Process the file
        // relatives.processFile(filePath);

        // // Now you can add more user interaction here, like asking for a name and
        // // printing the tree
        // // Example: Print descendants of a person
        // System.out.print("Enter a name to see their descendants: ");
        // String name = scanner.nextLine();
        // Array<Person> descendants = relatives.familyTree.getDescendants(name);
        // System.out.println("Descendants of " + name + ":");
        // for (Person descendant : descendants) {
        // System.out.println(descendant.getName());
        // }

        // // Close the scanner
        // scanner.close();
    }
}

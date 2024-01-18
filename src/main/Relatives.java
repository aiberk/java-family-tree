package main;

import java.io.*;
import java.util.Scanner;

public class Relatives {
    private HashMap<String, TreeNode<Person>> nameMap;
    private Tree<Person> familyTree;

    public Relatives() {
        nameMap = new HashMap<>();
        familyTree = new Tree<>(nameMap);
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
                    // Add each individual with null parents to the tree
                    familyTree.addPerson(line, null, null);
                } else {
                    // Read three lines for each relationship (child, mother, father)
                    String childName = line;
                    String motherName = bufferedReader.readLine();
                    String fatherName = bufferedReader.readLine();

                    if (childName == null || motherName == null || fatherName == null) {
                        break; // Exit if any of the lines is null (end of file or malformed file)
                    }

                    if (!familyTree.personExists(childName)) {
                        familyTree.addPerson(childName, motherName, fatherName);
                    } else {
                        familyTree.updatePersonParents(childName, motherName, fatherName);
                    }
                }
            }

            bufferedReader.close();
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Relatives relatives = new Relatives();

        System.out.print("Enter the path to the text file: ");
        String filePath = scanner.nextLine();
        relatives.processFile(filePath);

        // System.out.print("Enter a name to see their descendants: ");
        // String name = scanner.nextLine();
        // relatives.familyTree.printDescendants(name);
        // relatives.familyTree.printAncestors(name);

        System.out.println("Margaret");
        relatives.familyTree.printDescendants("Margaret");
        relatives.familyTree.printAncestors("Margaret");
        System.out.println(" ");
        System.out.println("James VI & I");
        relatives.familyTree.printDescendants("James VI & I");
        relatives.familyTree.printAncestors("James VI & I");
        System.out.println(" ");
        System.out.println("Henry VII");
        relatives.familyTree.printDescendants("Henry VII");
        relatives.familyTree.printAncestors("Henry VII");

        scanner.close();
    }
}

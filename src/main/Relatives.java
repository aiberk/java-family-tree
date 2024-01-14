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
                    // Add each individual with null parents to the tree and hashmap
                    familyTree.addPerson(line, null, null);

                    // TreeNode<Person> newNode = new TreeNode<>(new Person(line, null, null));
                    // nameMap.put(line, newNode);
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
        Scanner scanner = new Scanner(System.in);
        Relatives relatives = new Relatives();

        System.out.print("Enter the path to the text file: ");
        String filePath = scanner.nextLine();
        relatives.processFile(filePath);

        System.out.print("Enter a name to see their descendants: ");
        String name = scanner.nextLine();
        relatives.familyTree.printDescendants(name);
        relatives.familyTree.printAncestors(name);
        // Array<Person> descendants = relatives.familyTree.printDescendants(name);
        // System.out.println("Descendants of " + name + ":");
        // for (Person descendant : descendants) {
        // System.out.println(descendant.getName());
        // }
        System.out.println(relatives.familyTree.toString());

        scanner.close();
    }
}

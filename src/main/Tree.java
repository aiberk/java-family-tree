package main;

public class Tree {

    public static void main(String[] args) {
        Tree familyTree = new Tree();

        // Manually adding people to the tree
        familyTree.addPerson("John", null, null); // Assuming John is the oldest ancestor
        familyTree.addPerson("Mary", null, null);
        familyTree.addPerson("Alice", "Mary", "John");
        familyTree.addPerson("Bob", "Mary", "John");
        familyTree.addPerson("Charlie", "Alice", null);
        familyTree.addPerson("Diana", "Alice", null);
        familyTree.addPerson("Ethan", null, "Charlie");

        // Testing retrieval using HashMap
        Person charlie = familyTree.getPerson("Charlie");
        if (charlie != null) {
            System.out.println("Found: " + charlie.getName());
            System.out.println("Mother: " + charlie.getMother());
            System.out.println("Father: " + charlie.getFather());

            // Optionally, print children of Charlie if any
            Array<Person> children = charlie.getChildren();
            if (children.getSize() > 0) {
                System.out.println("Children of Charlie:");
                for (int i = 0; i < children.getSize(); i++) {
                    System.out.println(children.get(i).getName());
                }
            } else {
                System.out.println("Charlie has no children.");
            }
        } else {
            System.out.println("Charlie not found in the family tree.");
        }
    }

    private Person root; // Root of the tree, typically the oldest ancestor
    private HashMap nameMap; // Custom hashmap for quick lookup

    public Tree() {
        nameMap = new HashMap();
    }

    public void addPerson(String name, String motherName, String fatherName) {
        Person mother = null;
        Person father = null;

        if (motherName != null) {
            mother = nameMap.get(motherName);
        }
        if (fatherName != null) {
            father = nameMap.get(fatherName);
        }

        Person person = new Person(name, motherName, fatherName);

        if (mother != null) {
            mother.addChild(person);
        }
        if (father != null) {
            father.addChild(person);
        }

        // If the person has no parents, they could be a root
        if (mother == null && father == null && root == null) {
            root = person;
        }

        nameMap.put(name, person);
    }

    public Person getPerson(String name) {
        return nameMap.get(name);
    }

    // Similar methods for printing ancestors, etc.

}

package main;

//TODO: Implement indententation for printing with sb
//TODO: Make sure to first make all nodes from the first list, and then make connections. Otherwise, you might not be able to find a node
public class Tree<T> {

    private TreeNode root; // Root of the tree, typically the oldest ancestor
    private HashMap nameMap; // Custom hashmap for quick lookup

    public static void main(String[] args) {
        HashMap nameMap = new HashMap(); // Initialize the HashMap
        Tree familyTree = new Tree(nameMap); // Pass the HashMap to Tree
        Person person = new Person("John", null, null);
        Person person2 = new Person("Mary", null, null);
        Person person3 = new Person("John I", null, null);
        Person person4 = new Person("John II", null, null);
        nameMap.put(person.getName(), person);
        nameMap.put(person2.getName(), person2);
        nameMap.put(person3.getName(), person3);
        nameMap.put(person4.getName(), person4);

        // Roots, children, grandchildren
        familyTree.addPerson("John", "unknown", "unknown");
        familyTree.addPerson("Mary", "unknown", "unknown");
        familyTree.addPerson("John I", "Mary", "John");
        familyTree.addPerson("John II", "Mary", "John");

    }

    // Constructor now accepts a HashMap
    public Tree(HashMap nameMap) {
        this.nameMap = nameMap;
    }

    public void addPerson(String name, String motherName, String fatherName) {

    }

    public Person getPerson(String name) {
        return nameMap.get(name);
    }

}

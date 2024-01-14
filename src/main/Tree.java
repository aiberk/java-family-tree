package main;

//TODO: Implement indententation for printing with sb
//TODO: Make sure to first make all nodes from the first list, and then make connections. Otherwise, you might not be able to find a node
public class Tree<T> {

    private TreeNode<Person> root;
    private HashMap nameMap;

    public Tree() {
        // Initialize the root if necessary
        this.nameMap = new HashMap();
    }

    public Tree(HashMap nameMap) {
        // TODO Auto-generated constructor stub
    }

    public void addPerson(String name, String motherName, String fatherName) {
        // Check if the person is already in the HashMap
        if (nameMap.containsKey(name)) {
            // Person already exists in the tree, so no need to add again
            return;
        }

        // Create a new TreeNode for the person
        TreeNode<Person> newPersonNode = new TreeNode<>(new Person(name, motherName, fatherName));

        // Set the root if it's not already set
        if (root == null) {
            root = newPersonNode;
        }

        // Add the new person node to the HashMap
        nameMap.put(name, newPersonNode);

        // Link the person to their mother and father if they exist
        TreeNode<Person> motherNode = findPersonNode(motherName);
        if (motherNode != null) {
            motherNode.addChild(newPersonNode);
            newPersonNode.setMother(motherNode);
        }

        TreeNode<Person> fatherNode = findPersonNode(fatherName);
        if (fatherNode != null) {
            fatherNode.addChild(newPersonNode);
            newPersonNode.setFather(fatherNode);
        }
    }

    @SuppressWarnings("unchecked")
    public TreeNode<Person> findPersonNode(String name) {
        if (name == null) {
            return null;
        }
        return (TreeNode<Person>) nameMap.get(name); // Retrieve the node from the HashMap
    }

    public TreeNode<Person> getRoot() {
        return root;
    }

}

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
        TreeNode<Person> newPersonNode = new TreeNode<>(new Person(name, motherName, fatherName));
        if (newPersonNode == null) {
            newPersonNode = new TreeNode<>(new Person(name, motherName, fatherName));
            nameMap.put(name, newPersonNode);
        }

        // Check and update the root if necessary
        if (root == null) {
            root = newPersonNode; // First person added becomes the root
        }

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

    private TreeNode<Person> findPersonNode(String name) {
        // Implement logic to find a TreeNode<Person> by name
        // This is where a HashMap would be useful for quick lookup
        return null; // Placeholder
    }

    public TreeNode<Person> getRoot() {
        return root;
    }

}

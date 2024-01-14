package main;

//TODO: Implement indententation for printing with sb
//TODO: Make sure to first make all nodes from the first list, and then make connections. Otherwise, you might not be able to find a node
public class Tree<T> {

    private Array<TreeNode<Person>> potentialRoots;
    private HashMap nameMap;

    public static void main(String[] args) {
        Tree<Person> tree = new Tree<>();
        tree.addPerson("John", null, null);
        tree.addPerson("Mary", null, null);
        tree.addPerson("Mary II", "Mary", "John");
        tree.addPerson("John II", "Mary", "John");
        tree.addPerson("Martin", "Mary II", "John II");
        tree.addPerson("Jamie", "Mary II", "Martin");
        tree.addPerson("Jamie I", "Mary II", "Jamie");

        tree.printAncestors("Jamie I");
        tree.printDescendants("Jamie I");

    }

    public Tree() {

        // Initialize the root if necessary
        this.nameMap = new HashMap();
        this.potentialRoots = new Array<>();
    }

    public void addPerson(String name, String motherName, String fatherName) {
        if (nameMap.containsKey(name)) {
            // Person already exists in the tree, so no need to add again
            return;
        }

        TreeNode<Person> newPersonNode = new TreeNode<>(new Person(name, motherName, fatherName));
        nameMap.put(name, newPersonNode);

        TreeNode<Person> motherNode = findPersonNode(motherName);
        TreeNode<Person> fatherNode = findPersonNode(fatherName);

        if (motherNode == null && fatherNode == null) {
            // If the person has no known parents, add them to potential roots
            potentialRoots.add(newPersonNode);
        }

        if (motherNode != null) {
            motherNode.addChild(newPersonNode);
            newPersonNode.setMother(motherNode);
        }

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
        return (TreeNode<Person>) nameMap.get(name);
    }

    public Array<TreeNode<Person>> getPotentialRoots() {
        return potentialRoots;
    }

    public void printDescendants(String name) {
        TreeNode<Person> node = findPersonNode(name);
        if (node == null) {
            System.out.println("Person not found in the tree.");
            return;
        }
        System.out.println("Descendants of " + name + ":");
        printDescendantsRecursive(node, 0);
    }

    private void printDescendantsRecursive(TreeNode<Person> node, int level) {
        if (node == null)
            return;
        for (TreeNode<Person> child : node.getChildren()) {
            for (int i = 0; i < level; i++) {
                System.out.print("  "); // Indentation for readability
            }
            System.out.println(child.getData().getName());
            printDescendantsRecursive(child, level + 1);
        }
    }

    public void printAncestors(String name) {
        TreeNode<Person> node = findPersonNode(name);
        if (node == null) {
            System.out.println("Person not found in the tree.");
            return;
        }
        System.out.println("Ancestors of " + name + ":");
        printAncestorsRecursive(node, 0); // Start with depth 0
    }

    private void printAncestorsRecursive(TreeNode<Person> node, int depth) {
        if (node == null || (node.getMother() == null && node.getFather() == null))
            return;

        // Increase depth for the next level of ancestors
        int nextDepth = depth + 1;

        if (node.getMother() != null) {
            printIndentation(nextDepth);
            System.out.println(node.getMother().getData().getName());
            printAncestorsRecursive(node.getMother(), nextDepth);
        }
        if (node.getFather() != null) {
            printIndentation(nextDepth);
            System.out.println(node.getFather().getData().getName());
            printAncestorsRecursive(node.getFather(), nextDepth);
        }
    }

    private void printIndentation(int depth) {
        for (int i = 0; i < depth; i++) {
            System.out.print("  "); // Two spaces for each level of depth
        }
    }

    public void printPersonDetails(String name) {
        TreeNode<Person> node = findPersonNode(name);
        if (node == null) {
            System.out.println("Person not found in the tree.");
            return;
        }

        Person person = node.getData();
        System.out.println("Person: " + person.getName());

        // Print mother's name
        String motherName = (node.getMother() != null) ? node.getMother().getData().getName() : "Unknown";
        System.out.println("Mother: " + motherName);

        // Print father's name
        String fatherName = (node.getFather() != null) ? node.getFather().getData().getName() : "Unknown";
        System.out.println("Father: " + fatherName);

        // Print children's names
        System.out.print("Children: ");
        if (node.getChildren().getSize() == 0) {
            System.out.println("None");
        } else {
            for (int i = 0; i < node.getChildren().getSize(); i++) {
                TreeNode<Person> childNode = node.getChildren().get(i);
                System.out.print(childNode.getData().getName());
                if (i < node.getChildren().getSize() - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println(); // Newline after listing children
        }
    }

}

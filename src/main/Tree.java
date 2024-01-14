package main;

//TODO: Implement indententation for printing with sb
public class Tree<T> {

    private Array<TreeNode<Person>> potentialRoots;
    private HashMap<String, TreeNode<Person>> nameMap;

    public static void main(String[] args) {
        HashMap<String, TreeNode<Person>> familyMap = new HashMap<>();
        Tree<Person> tree = new Tree<>(familyMap);
        tree.addPerson("John", null, null);
        tree.addPerson("Mary", null, null);
        tree.addPerson("Mary II", "Mary", "John");
        tree.addPerson("John II", "Mary", "John");
        tree.addPerson("Martin", "Mary II", "John II");

        tree.addPerson("Jihn", null, null);
        tree.addPerson("Miry", null, null);
        tree.addPerson("Miry II", "Miry", "Jihn");
        tree.addPerson("Jihn II", "Miry", "Jihn");
        tree.addPerson("Mirtin", "Miry II", "Jihn II");

        tree.addPerson("Mirtin II", "Mirtin", "Martin");
        tree.printPotentialRoots();

        // tree.printAncestors("John II");
        // tree.printAncestors("Jihn II");
        // tree.printDescendants("John II");
        // tree.printDescendants("Jihn II");

        // System.out.println(tree);

    }

    public Tree(HashMap<String, TreeNode<Person>> nameMap) {
        this.nameMap = nameMap;
        this.potentialRoots = new Array<>();
    }

    public void printPotentialRoots() {
        for (TreeNode<Person> root : potentialRoots) {
            System.out.println(root.getData().getName());
        }
    }

    public void addPerson(String name, String motherName, String fatherName) {
        // Create or get the node for the person
        TreeNode<Person> personNode = getOrCreateNode(name);

        // Handle mother
        if (motherName != null && !motherName.equalsIgnoreCase("unknown")) {
            TreeNode<Person> motherNode = getOrCreateNode(motherName);
            personNode.setMother(motherNode);
            motherNode.addChild(personNode);
        } else if (motherName == null || motherName.equalsIgnoreCase("unknown")) {
            potentialRoots.add(personNode); // Add as a potential root if mother is unknown
        }

        // Handle father
        if (fatherName != null && !fatherName.equalsIgnoreCase("unknown")) {
            TreeNode<Person> fatherNode = getOrCreateNode(fatherName);
            personNode.setFather(fatherNode);
            fatherNode.addChild(personNode);
        } else if (fatherName == null || fatherName.equalsIgnoreCase("unknown")) {
            potentialRoots.add(personNode); // Add as a potential root if father is unknown
        }
    }

    private TreeNode<Person> getOrCreateNode(String name) {
        if (!nameMap.containsKey(name)) {
            TreeNode<Person> newNode = new TreeNode<>(new Person(name, null, null));
            nameMap.put(name, newNode);
            return newNode;
        }
        return (TreeNode<Person>) nameMap.get(name);
    }

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

    // @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (TreeNode<Person> root : potentialRoots) {
            printTreeRecursive(root, 0, sb);
        }
        return sb.toString();
    }

    private void printTreeRecursive(TreeNode<Person> node, int level, StringBuilder sb) {
        if (node == null) {
            return;
        }

        // Add indentation for each level
        for (int i = 0; i < level; i++) {
            sb.append("  ");
        }

        // Append the person's name
        sb.append(node.getData().getName()).append("\n");
        sb.append("\n");

        // Recursively print children
        for (TreeNode<Person> child : node.getChildren()) {
            printTreeRecursive(child, level + 1, sb);
        }
    }

}

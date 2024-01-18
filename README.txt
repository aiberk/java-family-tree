Recursive Calls

I used recursion to print the descendants and ancestors of a person by using a tree structure
to represent a family tree and a smooth road for node travesal. Also the tree is combined with a hashmap for O(1) lookup time. 
I have two different recursive calls: one for printing the descendants and one for printing the ancestors.  
This is because of the way I setup the nodes containing the information. The node has a father pointer, 
a mother pointer, and a list of children.To get the descendants, I just need to print the children of the node, 
and then recursively call the function on each child.To get the ancestors, I need to print the mother and father
of the node, and then recursively call the function on each of them. Both functions have a depth parameter that 
is used to print the correct indentation for the tree structure and a null check as a base case. Finally both calls have helper funcitons that 
print the correct information per the assignments requirements.

Here are the recursive methods from tree class for your viewing convenience:

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
            System.out.print("  ");
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
    printAncestorsRecursive(node, 0);
}

private void printAncestorsRecursive(TreeNode<Person> node, int depth) {
    if (node == null || (node.getMother() == null && node.getFather() == null))
        return;
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
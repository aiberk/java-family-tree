package main;

public class TreeNode<T> {
    private T data;
    private TreeNode<T> mother;
    private TreeNode<T> father;
    private Array<TreeNode<T>> children;

    public TreeNode(T data) {
        this.data = data;
        this.children = new Array<TreeNode<T>>();
    }

    public void addChild(TreeNode<T> child) {
        this.children.add(child);
    }

    public Array<TreeNode<T>> getChildren() {
        return children;
    }

    public T getData() {
        return data;
    }

    public TreeNode<T> getMother() {
        return mother;
    }

    public void setMother(TreeNode<T> mother) {
        this.mother = mother;
    }

    public TreeNode<T> getFather() {
        return father;
    }

    public void setFather(TreeNode<T> father) {
        this.father = father;
    }

}

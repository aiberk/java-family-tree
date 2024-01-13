package main;

public class Person {
    private String name;
    private String mother;
    private String father;
    private Array<Person> children;

    public Person(String name, String mother, String father) {
        this.name = name;
        this.mother = mother;
        this.father = father;
        this.children = new Array<Person>();
    }

    public void addChild(Person child) {
        this.children.add(child);
    }

    public Array<Person> getChildren() {
        return children;
    }

    public String getName() {
        return name;
    }

    public String getMother() {
        return mother;
    }

    public String getFather() {
        return father;
    }

    public void setFather(String father) {
        this.father = father;
    }

    public void setMother(String mother) {
        this.mother = mother;
    }

    @Override
    public String toString() {
        return name;
    }

}

package main;

public class Person {
    public String name;
    public String mother;
    public String father;

    public Person(String name, String mother, String father) {
        this.name = name;
        this.mother = mother;
        this.father = father;
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

    @Override
    public String toString() {
        return name;
    }

}

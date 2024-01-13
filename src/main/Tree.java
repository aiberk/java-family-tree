package main;

public class Tree {

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

    public Array<Person> getDescendants(String name) {
        Person person = getPerson(name);
        Array<Person> descendants = new Array<>();
        if (person != null) {
            getDescendantsRecursive(person, descendants);
        }
        return descendants;
    }

    private void getDescendantsRecursive(Person person, Array<Person> descendants) {
        Array<Person> children = person.getChildren();
        for (int i = 0; i < children.getSize(); i++) {
            Person child = children.get(i);
            descendants.add(child);
            getDescendantsRecursive(child, descendants);
        }
    }

    // Similar methods for printing ancestors, etc.
    public Array<Person> getAncestors(String name) {
        Person person = getPerson(name);
        Array<Person> ancestors = new Array<>();
        if (person != null) {
            getAncestorsRecursive(person, ancestors);
        }
        return ancestors;
    }

    private void getAncestorsRecursive(Person person, Array<Person> ancestors) {
        String motherName = person.getMother();
        String fatherName = person.getFather();

        if (motherName != null) {
            Person mother = getPerson(motherName);
            if (mother != null) {
                ancestors.add(mother);
                getAncestorsRecursive(mother, ancestors);
            }
        }

        if (fatherName != null) {
            Person father = getPerson(fatherName);
            if (father != null) {
                ancestors.add(father);
                getAncestorsRecursive(father, ancestors);
            }
        }
    }

}

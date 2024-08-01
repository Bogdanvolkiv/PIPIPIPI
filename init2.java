import java.util.ArrayList;
import java.util.List;

class Person {
    private String name;
    private Person mother;
    private Person father;
    private List<Person> children;

    public Person(String name) {
        this.name = name;
        this.children = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public Person getMother() {
        return mother;
    }

    public void setMother(Person mother) {
        this.mother = mother;
    }

    public Person getFather() {
        return father;
    }

    public void setFather(Person father) {
        this.father = father;
    }

    public List<Person> getChildren() {
        return children;
    }

    public void addChild(Person child) {
        this.children.add(child);
        if (this instanceof Female) {
            child.setMother(this);
        } else if (this instanceof Male) {
            child.setFather(this);
        }
    }

    @Override
    public String toString() {
        return name;
    }
}

class Male extends Person {
    public Male(String name) {
        super(name);
    }
}

class Female extends Person {
    public Female(String name) {
        super(name);
    }
}

class FamilyTree {
    private List<Person> people;

    public FamilyTree() {
        this.people = new ArrayList<>();
    }

    public void addPerson(Person person) {
        this.people.add(person);
    }

    public Person findPersonByName(String name) {
        for (Person person : people) {
            if (person.getName().equalsIgnoreCase(name)) {
                return person;
            }
        }
        return null;
    }

    public List<Person> getChildrenOf(String name) {
        Person person = findPersonByName(name);
        if (person != null) {
            return person.getChildren();
        }
        return new ArrayList<>();
    }
}

public class Main {
    public static void main(String[] args) {
        FamilyTree familyTree = new FamilyTree();

        Person john = new Male("John");
        Person jane = new Female("Jane");
        Person jack = new Male("Jack");
        Person jill = new Female("Jill");

        familyTree.addPerson(john);
        familyTree.addPerson(jane);
        familyTree.addPerson(jack);
        familyTree.addPerson(jill);

        john.addChild(jack);
        jane.addChild(jack);
        john.addChild(jill);
        jane.addChild(jill);

        System.out.println("Children of John:");
        for (Person child : familyTree.getChildrenOf("John")) {
            System.out.println(child.getName());
        }

        System.out.println("Children of Jane:");
        for (Person child : familyTree.getChildrenOf("Jane")) {
            System.out.println(child.getName());
        }
    }
}

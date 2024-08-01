Задача номер 2 дополнение 

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

// Интерфейс для работы с файлами
interface FileHandler {
    void writeToFile(String filename, String data);
    String readFromFile(String filename);
}

// Реализация интерфейса FileHandler
class FileHandlerImpl implements FileHandler {
    @Override
    public void writeToFile(String filename, String data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String readFromFile(String filename) {
        StringBuilder data = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                data.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data.toString();
    }
}

// Основной класс, демонстрирующий использование FileHandler
public class Main {
    public static void main(String[] args) {
        FileHandler fileHandler = new FileHandlerImpl();
        
        // Запись в файл
        String filename = "example.txt";
        String dataToWrite = "Hello, this is a test.";
        fileHandler.writeToFile(filename, dataToWrite);
        System.out.println("Data written to file.");

        // Чтение из файла
        String readData = fileHandler.readFromFile(filename);
        System.out.println("Data read from file:");
        System.out.println(readData);
    }
}


Задача номер 3 продолжение 

import java.util.*;

// Класс для представления узла дерева
class TreeNode<T> {
    T value;
    TreeNode<T> left, right;

    public TreeNode(T value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }

    public TreeNode<T> getLeft() {
        return left;
    }

    public TreeNode<T> getRight() {
        return right;
    }

    public T getValue() {
        return value;
    }
}

// Класс для представления бинарного дерева
class BinaryTree<T> implements Iterable<T> {
    private TreeNode<T> root;

    public BinaryTree() {
        this.root = null;
    }

    public void add(T value) {
        root = addRecursive(root, value);
    }

    private TreeNode<T> addRecursive(TreeNode<T> current, T value) {
        if (current == null) {
            return new TreeNode<>(value);
        }

        // Предполагаем, что T реализует интерфейс Comparable
        if (((Comparable<T>) value).compareTo(current.getValue()) < 0) {
            current.left = addRecursive(current.left, value);
        } else if (((Comparable<T>) value).compareTo(current.getValue()) > 0) {
            current.right = addRecursive(current.right, value);
        }
        return current;
    }

    @Override
    public Iterator<T> iterator() {
        return new TreeIterator<>(root);
    }

    private static class TreeIterator<T> implements Iterator<T> {
        private Stack<TreeNode<T>> stack = new Stack<>();

        public TreeIterator(TreeNode<T> root) {
            pushLeft(root);
        }

        private void pushLeft(TreeNode<T> node) {
            while (node != null) {
                stack.push(node);
                node = node.getLeft();
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public T next() {
            TreeNode<T> node = stack.pop();
            pushLeft(node.getRight());
            return node.getValue();
        }
    }
}

// Класс для представления человека
class Person implements Comparable<Person> {
    private String name;
    private Date birthDate;

    public Person(String name, Date birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    @Override
    public String toString() {
        return name + " (" + birthDate.toString() + ")";
    }

    @Override
    public int compareTo(Person other) {
        return this.name.compareTo(other.name);
    }
}

// Класс для работы со списком людей
class PersonList {
    private List<Person> people = new ArrayList<>();

    public void addPerson(Person person) {
        people.add(person);
    }

    public void sortByName() {
        people.sort(Comparator.comparing(Person::getName));
    }

    public void sortByBirthDate() {
        people.sort(Comparator.comparing(Person::getBirthDate));
    }

    public void print() {
        for (Person person : people) {
            System.out.println(person);
        }
    }
}

// Главный класс для тестирования
public class Main {
    public static void main(String[] args) {
        // Создание дерева и добавление людей
        BinaryTree<Person> tree = new BinaryTree<>();
        tree.add(new Person("Alice", new GregorianCalendar(1990, Calendar.JANUARY, 1).getTime()));
        tree.add(new Person("Bob", new GregorianCalendar(1985, Calendar.MARCH, 15).getTime()));
        tree.add(new Person("Charlie", new GregorianCalendar(2000, Calendar.JULY, 30).getTime()));

        // Печать людей в порядке обхода дерева
        System.out.println("People in the tree:");
        for (Person person : tree) {
            System.out.println(person);
        }

        // Создание списка людей и сортировка
        PersonList personList = new PersonList();
        personList.addPerson(new Person("Alice", new GregorianCalendar(1990, Calendar.JANUARY, 1).getTime()));
        personList.addPerson(new Person("Bob", new GregorianCalendar(1985, Calendar.MARCH, 15).getTime()));
        personList.addPerson(new Person("Charlie", new GregorianCalendar(2000, Calendar.JULY, 30).getTime()));

        System.out.println("\nPeople sorted by name:");
        personList.sortByName();
        personList.print();

        System.out.println("\nPeople sorted by birth date:");
        personList.sortByBirthDate();
        personList.print();
    }
}

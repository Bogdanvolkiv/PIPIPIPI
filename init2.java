public class Beverage {
    private String name;
    private int volume;

    public Beverage(String name, int volume) {
        this.name = name;
        this.volume = volume;
    }

    public String getName() {
        return name;
    }

    public int getVolume() {
        return volume;
    }
}
public class HotBeverage extends Beverage {
    private int temperature;

    public HotBeverage(String name, int volume, int temperature) {
        super(name, volume);
        this.temperature = temperature;
    }

    public int getTemperature() {
        return temperature;
    }

    @Override
    public String toString() {
        return "HotBeverage{" +
                "name='" + getName() + '\'' +
                ", volume=" + getVolume() +
                ", temperature=" + temperature +
                '}';
    }
}
public interface VendingMachine {
    HotBeverage getProduct(String name, int volume, int temperature);
}
import java.util.ArrayList;
import java.util.List;

public class HotBeverageVendingMachine implements VendingMachine {
    private List<HotBeverage> inventory = new ArrayList<>();

    public void addProduct(HotBeverage beverage) {
        inventory.add(beverage);
    }

    @Override
    public HotBeverage getProduct(String name, int volume, int temperature) {
        for (HotBeverage beverage : inventory) {
            if (beverage.getName().equals(name) &&
                beverage.getVolume() == volume &&
                beverage.getTemperature() == temperature) {
                return beverage;
            }
        }
        return null; // or throw an exception if product not found
    }
}
import java.util.List;

public interface QueueBehaviour {
    void addPersonToQueue(Person person);
    Person removePersonFromQueue();
}

public interface MarketBehaviour {
    void acceptOrder(Order order);
    void deliverOrder();
}
public class Person {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

public class Order {
    private Person person;
    private String product;

    public Order(Person person, String product) {
        this.person = person;
        this.product = product;
    }

    public Person getPerson() {
        return person;
    }

    public String getProduct() {
        return product;
    }
}
import java.util.LinkedList;
import java.util.Queue;

public class Market implements QueueBehaviour, MarketBehaviour {
    private Queue<Person> queue = new LinkedList<>();
    private Queue<Order> orders = new LinkedList<>();

    @Override
    public void addPersonToQueue(Person person) {
        queue.add(person);
        System.out.println(person.getName() + " added to queue.");
    }

    @Override
    public Person removePersonFromQueue() {
        Person person = queue.poll();
        if (person != null) {
            System.out.println(person.getName() + " removed from queue.");
        }
        return person;
    }

    @Override
    public void acceptOrder(Order order) {
        orders.add(order);
        System.out.println("Order accepted: " + order.getProduct() + " for " + order.getPerson().getName());
    }

    @Override
    public void deliverOrder() {
        Order order = orders.poll();
        if (order != null) {
            System.out.println("Order delivered: " + order.getProduct() + " to " + order.getPerson().getName());
        }
    }

    public void update() {
        // Simulate accepting and delivering orders
        if (!queue.isEmpty() && !orders.isEmpty()) {
            Person person = removePersonFromQueue();
            if (person != null) {
                Order order = orders.stream()
                        .filter(o -> o.getPerson().equals(person))
                        .findFirst()
                        .orElse(null);
                if (order != null) {
                    orders.remove(order);
                    deliverOrder();
                }
            }
        }
    }
}
public class Main {
    public static void main(String[] args) {
        HotBeverageVendingMachine vendingMachine = new HotBeverageVendingMachine();

        HotBeverage tea = new HotBeverage("Tea", 250, 80);
        HotBeverage coffee = new HotBeverage("Coffee", 200, 90);
        HotBeverage hotChocolate = new HotBeverage("Hot Chocolate", 300, 85);

        vendingMachine.addProduct(tea);
        vendingMachine.addProduct(coffee);
        vendingMachine.addProduct(hotChocolate);

        HotBeverage requestedBeverage = vendingMachine.getProduct("Coffee", 200, 90);
        if (requestedBeverage != null) {
            System.out.println("Dispensed: " + requestedBeverage);
        } else {
            System.out.println("Product not found");
        }

        Market market = new Market();

        Person alice = new Person("Alice");
        Person bob = new Person("Bob");

        market.addPersonToQueue(alice);
        market.addPersonToQueue(bob);

        Order order1 = new Order(alice, "Tea");
        Order order2 = new Order(bob, "Coffee");

        market.acceptOrder(order1);
        market.acceptOrder(order2);

        market.update();  // Simulate processing orders
        market.update();
    }
}

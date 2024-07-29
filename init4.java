// Создать наследника реализованного класса ГорячийНапиток с дополнительным полем int температура.
// Создать класс ГорячихНапитковАвтомат реализующий интерфейс
// ТорговыйАвтомат и реализовать перегруженный метод getProduct(int name, int volume, int temperature), выдающий продукт соответствующий имени, объёму и температуре
// В main проинициализировать несколько ГорячихНапитков и ГорячихНапитковАвтомат и воспроизвести логику, заложенную в программе
// Всё вышеуказанное создать согласно принципам ООП, пройденным на семинаре
// ГорячийНапиток.java
public class HotDrink {
    private String name;
    private int volume;

    public HotDrink(String name, int volume) {
        this.name = name;
        this.volume = volume;
    }

    public String getName() {
        return name;
    }

    public int getVolume() {
        return volume;
    }

    @Override
    public String toString() {
        return "HotDrink{name='" + name + "', volume=" + volume + "}";
    }
}

// ГорячийНапитокСТемпературой.java
public class HotDrinkWithTemperature extends HotDrink {
    private int temperature;

    public HotDrinkWithTemperature(String name, int volume, int temperature) {
        super(name, volume);
        this.temperature = temperature;
    }

    public int getTemperature() {
        return temperature;
    }

    @Override
    public String toString() {
        return "HotDrinkWithTemperature{name='" + getName() + "', volume=" + getVolume() + ", temperature=" + temperature + "}";
    }
}

// ТорговыйАвтомат.java
public interface VendingMachine {
    HotDrink getProduct(String name, int volume, int temperature);
}

// ГорячихНапитковАвтомат.java
import java.util.ArrayList;
import java.util.List;

public class HotDrinkVendingMachine implements VendingMachine {
    private List<HotDrinkWithTemperature> drinks;

    public HotDrinkVendingMachine() {
        drinks = new ArrayList<>();
    }

    public void addDrink(HotDrinkWithTemperature drink) {
        drinks.add(drink);
    }

    @Override
    public HotDrink getProduct(String name, int volume, int temperature) {
        for (HotDrinkWithTemperature drink : drinks) {
            if (drink.getName().equals(name) && drink.getVolume() == volume && drink.getTemperature() == temperature) {
                return drink;
            }
        }
        return null; // Если не найдено соответствие
    }
}

// Main.java
public class Main {
    public static void main(String[] args) {
        // Создание автоматов и напитков
        HotDrinkVendingMachine vendingMachine = new HotDrinkVendingMachine();
        
        HotDrinkWithTemperature tea = new HotDrinkWithTemperature("Tea", 250, 85);
        HotDrinkWithTemperature coffee = new HotDrinkWithTemperature("Coffee", 200, 90);
        HotDrinkWithTemperature hotChocolate = new HotDrinkWithTemperature("Hot Chocolate", 300, 80);
        
        // Добавление напитков в автомат
        vendingMachine.addDrink(tea);
        vendingMachine.addDrink(coffee);
        vendingMachine.addDrink(hotChocolate);
        
        // Получение продукта
        HotDrink requestedDrink = vendingMachine.getProduct("Tea", 250, 85);
        if (requestedDrink != null) {
            System.out.println("You got: " + requestedDrink);
        } else {
            System.out.println("The requested drink is not available.");
        }
    }
}


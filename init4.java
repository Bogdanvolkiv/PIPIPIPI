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

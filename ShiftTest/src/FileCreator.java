import java.io.*;
import java.util.List;
import java.util.ArrayList;

public class FileCreator {
    public static void main(String[] args) {
        String outputFilePath = "";
        String outputPrefix = "";

        for (int i = 0; i < args.length - 1; i++) {
            if (args[i].equals("-p")) {
                outputPrefix = args[i + 1];
                i++;
            } else if (args[i].equals("-o")) {
                outputFilePath = args[i + 1];
                i++;
            }
        }

        if (outputFilePath.isEmpty()) {
            System.out.println("Не указан путь для сохранения файла");
            return;
        }

        try (PrintWriter writer = new PrintWriter(new File(outputFilePath, outputPrefix + "hello.txt"))) {
            writer.println("Hello, World!");
            System.out.println("Файл успешно создан по пути: " + outputFilePath);
        } catch (IOException e) {
            System.out.println("Ошибка при создании файла: " + e.getMessage());
        }
    }
}
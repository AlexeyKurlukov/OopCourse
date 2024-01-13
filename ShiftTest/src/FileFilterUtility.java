import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileFilterUtility {
    public static void main(String[] args) {
        String[] fileNames = {"file1.txt", "file2.txt", "file3.txt"};  // файлы сохраняются в текущую папку
        String outputPath = "";
        String outputPrefix = "";

        ArrayList<Integer> integers = new ArrayList<>();
        ArrayList<Double> floats = new ArrayList<>();
        ArrayList<String> strings = new ArrayList<>();

        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-o") && i < args.length - 1) {
                outputPath = args[i + 1];
            } else if (args[i].equals("-p") && i < args.length - 1) {
                outputPrefix = args[i + 1];
                i++;
            }
        }

        if (outputPath.isEmpty()) {
            System.out.println("Не указан путь для сохранения файла");
            return;
        }

        for (String fileName : fileNames) {
            try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                String line;
                while ((line = br.readLine()) != null) {
                    try {
                        int intValue = Integer.parseInt(line);
                        integers.add(intValue);
                    } catch (NumberFormatException e1) {
                        try {
                            double doubleValue = Double.parseDouble(line);
                            floats.add(doubleValue);
                        } catch (NumberFormatException e2) {
                            strings.add(line);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (!integers.isEmpty()) {
            writeToFile(outputPath + outputPrefix + "integers.txt", integers);
        }
        if (!floats.isEmpty()) {
            writeToFile(outputPath + outputPrefix + "floats.txt", floats);
        }
        if (!strings.isEmpty()) {
            writeToFile(outputPath + outputPrefix + "strings.txt", strings);
        }
    }

    private static void writeToFile(String fileName, List<?> data) {
        try (PrintWriter writer = new PrintWriter(fileName)) {
            for (Object obj : data) {
                writer.println(obj);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

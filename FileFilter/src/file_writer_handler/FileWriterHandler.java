package file_writer_handler;

import java.io.*;
import java.util.List;

public class FileWriterHandler {
    private final String outputPath;
    private final boolean isAppendMode;

    public FileWriterHandler(String outputPath, boolean isAppendMode) {
        this.outputPath = outputPath;
        this.isAppendMode = isAppendMode;
    }

    public void writeToFile(String fileName, List<?> data) {
        String filePath;

        if (outputPath != null) {
            filePath = outputPath + File.separator + fileName;
        } else {
            filePath = fileName;
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath, isAppendMode))) {
            for (Object object : data) {
                if (isAppendMode) {
                    writer.append(object.toString()).append("\n");
                } else {
                    writer.println(object);
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }
}
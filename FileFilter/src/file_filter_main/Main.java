package file_filter_main;

import file_writer_handler.FileWriterHandler;
import files_statistics.full_statistics.*;
import files_statistics.short_statistics.ShortStatistics;
import data_handler.*;

import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        List<String> fileNames = new ArrayList<>();

        String outputPath = null;
        String outputPrefix = "";
        boolean isAppendMode = false;
        boolean isShortStats = false;
        boolean isFullStats = false;

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-o" -> {
                    if (i < args.length - 1) {
                        outputPath = args[i + 1];
                        i++;
                    }
                }

                case "-p" -> {
                    if (i < args.length - 1) {
                        outputPrefix = args[i + 1];
                        i++;
                    }
                }

                case "-a" -> isAppendMode = true;
                case "-s" -> isShortStats = true;
                case "-f" -> isFullStats = true;
                default -> fileNames.add(args[i]);
            }
        }

        DataHandler<BigInteger> intDataHandler = new IntegerHandler();
        DataHandler<Double> doubleDataHandler = new DoubleHandler();
        DataHandler<String> stringDataHandler = new StringHandler();

        for (String fileName : fileNames) {
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    intDataHandler.processData(line);
                    doubleDataHandler.processData(line);
                    stringDataHandler.processData(line);
                }
            } catch (IOException e) {
                logger.severe("Ошибка при чтении файла: " + fileName);
                logger.throwing(Main.class.getName(), "main", e);
            }
        }

        FileWriterHandler fileWriterHandler = new FileWriterHandler(outputPath, isAppendMode);

        if (!intDataHandler.getData().isEmpty()) {
            fileWriterHandler.writeToFile(outputPrefix + "integers.txt", intDataHandler.getData());
        }

        if (!doubleDataHandler.getData().isEmpty()) {
            fileWriterHandler.writeToFile(outputPrefix + "doubles.txt", doubleDataHandler.getData());
        }

        if (!stringDataHandler.getData().isEmpty()) {
            fileWriterHandler.writeToFile(outputPrefix + "strings.txt", stringDataHandler.getData());
        }

        if (isShortStats) {
            ShortStatistics shortStatistics = new ShortStatistics(intDataHandler, doubleDataHandler, stringDataHandler);
            shortStatistics.calculateAndShow();
        }

        if (isFullStats) {
            IntegerStatistics integerStatistics = new IntegerStatistics(intDataHandler);
            integerStatistics.calculateAndShow();

            DoubleStatistics doubleStatistics = new DoubleStatistics(doubleDataHandler);
            doubleStatistics.calculateAndShow();

            StringStatistics stringStatistics = new StringStatistics(stringDataHandler);
            stringStatistics.calculateAndShow();
        }
    }
}
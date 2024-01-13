import java.io.*;

public class FindDoubleNumber {
    public static void main(String[] args) {
        try {
            File inputFile = new File("input.txt");
            File outputFile = new File("output.txt");
            FileReader fileReader = new FileReader(inputFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            FileWriter fileWriter = new FileWriter(outputFile);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                if (isDouble(line.trim())) {
                    bufferedWriter.write(line + "\n");
                }
            }

            bufferedReader.close();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean isDouble(String s) {
        try {
            double number = Double.parseDouble(s);
            return number != (int) number;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}

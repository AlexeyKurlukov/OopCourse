import java.io.*;

public class FindNonNumeric {
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
                if (!isInteger(line.trim()) && !isDouble(line.trim())) {
                    bufferedWriter.write(line + "\n");
                }
            }

            bufferedReader.close();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
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

import java.io.*;
import java.util.regex.*;

public class Main {

    public static void main(String[] args) {
     
        String filePath = "C:\\Users\\PMYLS\\Downloads\\Day3.txt";

        int totalSum = 0; // To store the sum of all valid mul() results

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

      
            Pattern pattern = Pattern.compile("mul\\((\\d{1,3}),(\\d{1,3})\\)");


            while ((line = br.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);

              
                while (matcher.find()) {
                    int x = Integer.parseInt(matcher.group(1)); // First number (X)
                    int y = Integer.parseInt(matcher.group(2)); // Second number (Y)
                    totalSum += x * y; // Multiply and add to total sum
                }
            }

            //Final Result
            System.out.println("The total sum of all valid mul() results is: " + totalSum);

        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }
}


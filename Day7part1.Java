import java.io.*;
import java.util.*;

public class Day7Task1 {

    public static void main(String[] args) throws IOException {
 
        BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\PMYLS\\Downloads\\Day7IPFile.txt"));
        String line;
        long totalCalibrationResult = 0;

        while ((line = br.readLine()) != null) {
            String[] parts = line.split(": ");
            long testValue = Long.parseLong(parts[0]); // Use Long instead of Integer
            String[] numbers = parts[1].split(" ");

            // Generate all possible operator combinations (either + or *)
            List<String> operatorCombinations = generateOperatorCombinations(numbers.length - 1);

            boolean isEquationTrue = false;
            // Check each operator combination
            for (String operators : operatorCombinations) {
                if (evaluate(numbers, operators) == testValue) {
                    isEquationTrue = true;
                    break;
                }
            }

        
            if (isEquationTrue) {
                totalCalibrationResult += testValue;
            }
        }

        // Output the result
        System.out.println("Total Calibration Result: " + totalCalibrationResult);
    }

    private static List<String> generateOperatorCombinations(int numOperators) {
        List<String> combinations = new ArrayList<>();
        generateOperatorsRecursively(combinations, "", numOperators);
        return combinations;
    }

    private static void generateOperatorsRecursively(List<String> combinations, String current, int remaining) {
        if (remaining == 0) {
            combinations.add(current);
            return;
        }
        generateOperatorsRecursively(combinations, current + "+", remaining - 1);
        generateOperatorsRecursively(combinations, current + "*", remaining - 1);
    }

    // Method to evaluate the equation with the given operators
    private static long evaluate(String[] numbers, String operators) {
        long result = Long.parseLong(numbers[0]); // Use Long instead of Integer
        for (int i = 0; i < operators.length(); i++) {
            long num = Long.parseLong(numbers[i + 1]); // Use Long instead of Integer
            if (operators.charAt(i) == '+') {
                result += num;
            } else if (operators.charAt(i) == '*') {
                result *= num;
            }
        }
        return result;
    }
}


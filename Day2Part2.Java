import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
       
        String filePath = "C:\\Users\\PMYLS\\Documents\\GACDAY2PROBLEM2.txt";

        List<List<Integer>> rows = new ArrayList<>();


        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Convert each line to a list of integers
                List<Integer> row = new ArrayList<>();
                for (String num : line.split(" ")) {
                    row.add(Integer.parseInt(num));
                }
                rows.add(row);
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
            return;
        }


        int safeCount = 0;
        for (List<Integer> row : rows) {
            if (isMonotonic(row) && hasValidDifferences(row)) {
                safeCount++; // Already safe
            } else if (canBeMadeSafe(row)) {
                safeCount++; // Can be made safe by removing one level
            }
        }


        System.out.println("Number of safe reports: " + safeCount);
    }

    // Function to check if a list is monotonic (strictly increasing or decreasing)
    private static boolean isMonotonic(List<Integer> list) {
        boolean increasing = true;
        boolean decreasing = true;

        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i) >= list.get(i + 1)) {
                increasing = false;
            }
            if (list.get(i) <= list.get(i + 1)) {
                decreasing = false;
            }
        }

        return increasing || decreasing;
    }

    // Function to check if all adjacent differences are between 1 and 3
    private static boolean hasValidDifferences(List<Integer> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            int diff = Math.abs(list.get(i) - list.get(i + 1));
            if (diff < 1 || diff > 3) {
                return false;
            }
        }
        return true;
    }


    private static boolean canBeMadeSafe(List<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            // Create a new list without the element at index i
            List<Integer> newList = new ArrayList<>(list);
            newList.remove(i);

            if (isMonotonic(newList) && hasValidDifferences(newList)) {
                return true; // Found a way to make it safe
            }
        }
        return false; // Cannot make it safe
    }
}

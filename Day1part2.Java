import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
  
        String filePath = "C:\\Users\\PMYLS\\Downloads\\Day2IPtext.txt"; // Replace with your file path

        List<Integer> leftList = new ArrayList<>();
        List<Integer> rightList = new ArrayList<>();

        // Step 1: Read input file and populate the left and right lists
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isLeft = true; // Track which list we're populating
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    isLeft = false; // Switch to reading the right list after an empty line
                    continue;
                }

                if (isLeft) {
                    leftList.add(Integer.parseInt(line.trim()));
                } else {
                    rightList.add(Integer.parseInt(line.trim()));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
            return;
        }

      
        int similarityScore = 0;

        // Count frequencies of numbers in the right list Using Hash Map.
        Map<Integer, Integer> rightFrequencies = new HashMap<>();
        for (int num : rightList) {
            rightFrequencies.put(num, rightFrequencies.getOrDefault(num, 0) + 1);
        }

        for (int num : leftList) {
            if (rightFrequencies.containsKey(num)) {
                similarityScore += num * rightFrequencies.get(num);
            }
        }

        System.out.println("The similarity score is: " + similarityScore);
    }
}

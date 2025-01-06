import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class PrintQueue {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\PMYLS\\Downloads\\Day5question1.txt"; // Update with your file path

        try {
          
            List<String> lines = Files.readAllLines(Paths.get(filePath));

            List<String> rules = new ArrayList<>();
            List<String> updates = new ArrayList<>();
            boolean readingRules = true;

            for (String line : lines) {
                if (line.trim().isEmpty()) {
                    readingRules = false;
                } else {
                    if (readingRules) {
                        rules.add(line);
                    } else {
                        updates.add(line);
                    }
                }
            }

   
            Map<Integer, Set<Integer>> orderRules = new HashMap<>();
            for (String rule : rules) {
                String[] parts = rule.split("\\|");
                int before = Integer.parseInt(parts[0].trim());
                int after = Integer.parseInt(parts[1].trim());

                orderRules.putIfAbsent(before, new HashSet<>());
                orderRules.get(before).add(after);
            }

            // Process updates
            int totalMiddlePageSum = 0;
            for (String updateLine : updates) {
                List<Integer> updatePages = parseUpdatePages(updateLine);
                if (isCorrectOrder(updatePages, orderRules)) {
                    int middlePage = findMiddlePage(updatePages);
                    totalMiddlePageSum += middlePage;
                }
            }

            // Output the result
            System.out.println("Total sum of middle page numbers from correctly ordered updates: " + totalMiddlePageSum);

        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }


    private static List<Integer> parseUpdatePages(String updateLine) {
        List<Integer> updatePages = new ArrayList<>();
        String[] pages = updateLine.split(",");
        for (String page : pages) {
            updatePages.add(Integer.parseInt(page.trim()));
        }
        return updatePages;
    }

    private static boolean isCorrectOrder(List<Integer> updatePages, Map<Integer, Set<Integer>> orderRules) {
        // Check the order of pages in the update
        for (int i = 0; i < updatePages.size(); i++) {
            for (int j = i + 1; j < updatePages.size(); j++) {
                int pageBefore = updatePages.get(i);
                int pageAfter = updatePages.get(j);
                if (orderRules.containsKey(pageBefore) && orderRules.get(pageBefore).contains(pageAfter)) {
                    continue;
                } else {
                    return false; // If any rule is violated, return false
                }
            }
        }
        return true; // All rules are followed
    }

    // Find the middle page in the update
    private static int findMiddlePage(List<Integer> updatePages) {
        int size = updatePages.size();
        return updatePages.get(size / 2); // Middle page index
    }
}


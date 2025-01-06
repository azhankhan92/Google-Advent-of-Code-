import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MAIN {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\PMYLS\\Downloads\\day3part2.txt"; // Update with your file path

        try {
            
            String input = Files.readString(Paths.get(filePath));

          
            Pattern mulPattern = Pattern.compile("mul\\((\\d+),(\\d+)\\)");
            Pattern doPattern = Pattern.compile("do\\(\\)");
            Pattern dontPattern = Pattern.compile("don't\\(\\)");

            boolean mulEnabled = true;
            int sum = 0;

            Matcher mulMatcher = mulPattern.matcher(input);
            int lastProcessedIndex = 0;

            while (mulMatcher.find()) {
                // Check for any "do()" or "don't()" instructions before this "mul"
                String segment = input.substring(lastProcessedIndex, mulMatcher.start());
                lastProcessedIndex = mulMatcher.end();

                if (doPattern.matcher(segment).find()) {
                    mulEnabled = true; // Enable mul instructions
                }
                if (dontPattern.matcher(segment).find()) {
                    mulEnabled = false; // Disable mul instructions
                }

                if (mulEnabled) {
                    int a = Integer.parseInt(mulMatcher.group(1));
                    int b = Integer.parseInt(mulMatcher.group(2));
                    sum += a * b; // Add the result of the multiplication
                }
            }

            System.out.println("Sum of enabled multiplications: " + sum);
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }
}


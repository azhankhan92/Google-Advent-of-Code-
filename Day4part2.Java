import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MAIN {
    public static void main(String[] args) {
        String fileName = "C:\\Users\\PMYLS\\Downloads\\day4part2.txt"; 

        try {
            char[][] grid = readInputFile(fileName);
            int count = countXmasPatterns(grid);
            System.out.println("Number of X-MAS patterns: " + count);
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }

    private static char[][] readInputFile(String fileName) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            return reader.lines()
                    .map(String::toCharArray)
                    .toArray(char[][]::new);
        }
    }

    private static int countXmasPatterns(char[][] grid) {
        int count = 0;
        int rows = grid.length;
        int cols = grid[0].length;


        for (int i = 1; i < rows - 1; i++) {
            for (int j = 1; j < cols - 1; j++) {
                // Check for X-MAS pattern centered at grid[i][j]
                if (isXmasPattern(grid, i, j)) {
                    count++;
                }
            }
        }
        return count;
    }

    private static boolean isXmasPattern(char[][] grid, int i, int j) {
  
        return (matchesMAS(grid[i - 1][j - 1], grid[i][j], grid[i + 1][j + 1]) &&
                matchesMAS(grid[i - 1][j + 1], grid[i][j], grid[i + 1][j - 1]));
    }

    private static boolean matchesMAS(char first, char middle, char last) {

        return (first == 'M' && middle == 'A' && last == 'S') ||
                (first == 'S' && middle == 'A' && last == 'M');
    }
}


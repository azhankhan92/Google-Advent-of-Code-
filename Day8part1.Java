import java.io.*;
import java.util.*;

public class ResonantCollinearity {
    public static void main(String[] args) throws IOException {
        // Read input map from file
        List<String> map = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\PMYLS\\Downloads\\day8ipfile.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                map.add(line);
            }
        }

        int rows = map.size();
        int cols = map.get(0).length();
        List<int[]> antennas = new ArrayList<>();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                char c = map.get(i).charAt(j);
                if (c != '.') {
                    antennas.add(new int[]{i, j, c});
                }
            }
        }

        // Set to store unique antinode positions
        Set<String> antinodes = new HashSet<>();

        // Calculate antinodes
        for (int i = 0; i < antennas.size(); i++) {
            for (int j = i + 1; j < antennas.size(); j++) {
                int[] a1 = antennas.get(i);
                int[] a2 = antennas.get(j);

                // Check if frequencies match
                if (a1[2] != a2[2]) continue;

                int dx = a2[1] - a1[1];
                int dy = a2[0] - a1[0];

                // Ensure collinearity (using twice the distance rule)
                int ax = a1[1] - dx;
                int ay = a1[0] - dy;
                int bx = a2[1] + dx;
                int by = a2[0] + dy;

                if (isValid(ax, ay, rows, cols)) {
                    antinodes.add(ax + "," + ay);
                }
                if (isValid(bx, by, rows, cols)) {
                    antinodes.add(bx + "," + by);
                }
            }
        }

        // Output the result
        System.out.println("Unique antinode locations: " + antinodes.size());
    }

    private static boolean isValid(int x, int y, int rows, int cols) {
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }
}


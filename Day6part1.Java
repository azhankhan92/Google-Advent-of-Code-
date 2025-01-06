import java.io.*;
import java.util.*;

public class Day6Task1 {
    public static void main(String[] args) throws IOException {
        List<String> grid = new ArrayList<>();
        int startX = -1, startY = -1;
        char direction = '^'; // Initial direction is 'up'

     
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\PMYLS\\Downloads\\Day6.Q1.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                grid.add(line);
                // Find the guard's initial position
                int index = line.indexOf('^');
                if (index != -1) {
                    startX = grid.size() - 1;
                    startY = index;
                }
            }
        }

        // Directions: up (0), right (1), down (2), left (3)
        int[] dx = {-1, 0, 1, 0}; // Movement for each direction (up, right, down, left)
        int[] dy = {0, 1, 0, -1}; // Movement for each direction (up, right, down, left)
        Set<String> visited = new HashSet<>();
        int x = startX, y = startY;

      
        visited.add(x + "," + y);

 
        int dir = 0;

        // Simulate the guard's movement
        while (true) {
            // Check the next position based on the current direction
            int newX = x + dx[dir];
            int newY = y + dy[dir];

            // If the new position is out of bounds, break the loop (exit the grid)
            if (newX < 0 || newX >= grid.size() || newY < 0 || newY >= grid.get(newX).length()) {
                break;
            }

          
            if (grid.get(newX).charAt(newY) == '#') {
                // If there's an obstacle, turn right (90 degrees)
                dir = (dir + 1) % 4; // Turn right: up -> right -> down -> left -> up
            } else {
                // If there's no obstacle, move forward
                x = newX;
                y = newY;
                visited.add(x + "," + y);
            }
        }

        System.out.println("Number of distinct positions: " + visited.size());
    }
}


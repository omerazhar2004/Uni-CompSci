// public class testCoursework {
//     private int n;
//     private int[][] square;

//     private void getValues(int n) {
//         this.n = n;
//         this.square = new int[n][n];
//         System.out.println("Array initialized with size: " + n + "x" + n); // Debugging output
//     }

//     private void generateSquare(int n) {
//         int x = 0; // Adjusting the initial x position to 0
//         int y = n / 2; // Correct initial y position to center
//         square[x][y] = 1;
//         System.out.println("Initial position: x = " + x + ", y = " + y); // Debugging output

//         for (int i = 2; i <= n * n; i++) {
//             int newX = (x - 1 + n) % n; // Move up and wrap around
//             int newY = (y + 1) % n; // Move right and wrap around

//             if (square[newX][newY] != 0) { // If the position is occupied
//                 newX = (x + 1) % n; // Move down instead
//                 newY = y; // Stay in the same column
//             }

//             square[newX][newY] = i;
//             x = newX;
//             y = newY;

//             // Debugging output
//             System.out.println("i: " + i + ", x: " + x + ", y: " + y);
//         }
//     }

//     private void printSquare() {
//         for (int i = 0; i < n; i++) {
//             for (int j = 0; j < square[i].length; j++) {
//                 System.out.print(square[i][j] + "\t");
//             }
//             System.out.println();
//         }
//     }

//     private int getOddArgument(String[] args) {
//         int n = Integer.parseInt(args[0]);
//         System.out.println("Input argument: " + n); // Debugging output

//         if (n % 2 != 0) {
//             return n;
//         } else {
//             throw new IllegalArgumentException("You can only enter an odd number.");
//         }
//     }

//     public static void main(String[] args) {
//         testCoursework modified = new testCoursework();

//         try {
//             int n = modified.getOddArgument(args);
//             modified.getValues(n);
//             modified.generateSquare(n);
//             modified.printSquare();
//         } catch (IllegalArgumentException e) {
//             System.out.println(e.getMessage());
//         } catch (ArrayIndexOutOfBoundsException e) {
//             System.out.println("Array Index Out of Bounds: " + e.getMessage());
//         } catch (Exception e) {
//             System.out.println("Exception: " + e.getMessage());
//         }
//     }
// }

public class Q1a_24092763 {
    private int n;
    private int[][] square;

    public void getValues(int n) {
        this.n = n;
        this.square = new int[n][n];
    }

    public void generateSquare(int n) {
        int x = 0;
        int y = n / 2;
        square[x][y] = 1;
        for (int i = 2; i <= n * n; i++) {
            int newX = (x - 1 + n) % n;
            int newY = (y + 1) % n;
            if (square[newX][newY] != 0) {
                newX = (x + 1) % n;
                newY = y;
            }
            square[newX][newY] = i;
            x = newX;
            y = newY;
        }
    }

    public void printSquare() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < square[i].length; j++) {
                System.out.print(square[i][j] + "\t");
            }
            System.out.println();
        }
    }
}

class errorHandling extends Q1a_24092763 {
    public int oddArgument(String[] args) {
        int n = Integer.parseInt(args[0]);
        if (n % 2 != 0) {
            return n;
        } else {
            throw new IllegalArgumentException("You can only enter an odd number.");
        }
    }

    public static void main(String[] args) {
        errorHandling modified = new errorHandling();
    
            int n = modified.oddArgument(args);
            modified.getValues(n);
            modified.generateSquare(n);
            modified.printSquare();

    }
}

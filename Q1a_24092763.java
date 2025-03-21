public class Q1a_24092763{
    private int n;
    private int [][] square;      // declared private field to store these values as they get updated throughout the code
    public static void main(String[] args) {
        errorHandling modified = new errorHandling();
        try{
    
            int n = modified.validateInput(args);
            n = modified.oddArgument(n);
            modified.getValues(n);
            modified.generateSquare(n);
            modified.printSquare();
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }
        catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }
    public void getValues(int n){
        this.n = n;
        this.square = new int[n][n];     // retrieving the private variables through constructor to make them accessable for other methods
        
    }
    public void generateSquare(int n){    //algorithm for generating a magic square based on the value off odd number entered by the user in the command prompt.
        int x = 0;
        int y = n / 2;
        square[x][y] = 1;
        for (int i = 2; i <= n * n; i ++){
            int newX = (x - 1 + n) % n;
            int newY = (y + 1) % n;
            if (square[newX][newY] != 0){
                newX = (x + 1) % n;
                newY = y;
            }
            square[newX][newY] = i;
            x = newX;
            y = newY;
        }
    }
    public void printSquare(){
        for (int i = 0; i < n; i ++){
            for (int j = 0; j < square[i].length; j ++){     // helps to decide the size of square based on value of n)
                System.out.print( square[i][j] + "\t");   
            }
            System.out.println();     //prints the generated square.
        }
    }

}
class errorHandling extends Q1a_24092763{
    public int oddArgument(int n){
        if (n % 2 != 0 && n > 0){          // condition for odd number detection and for giving an error in case number is not odd.
            return n;
        }
        else{
            throw new IllegalArgumentException("You can only enter a positive odd number.");
        }

    }
    public int validateInput(String[] args) {
        try {
            int n = Integer.parseInt(args[0]);         // ensures first argument in command prompt is the required input
            return n;
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Error: Non-integer input provided. Please enter a valid integer.");   //error message if user input is a non-integer value.
        }
    }
    
}
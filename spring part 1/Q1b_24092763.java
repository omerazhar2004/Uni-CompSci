import java.util.Random;
import java.util.Scanner;
public class Q1b_24092763 {
    private int n;
    private int [][] square;          //Main private variable to store the 2Darray and to allow it to be updated when it gets shuffled or when userinput impacts the position of elements inside it.
    public int[][] tempSquare;        // Only stores the version of the 2D array that was generated before it gets shuffled later on, this is so the game can compare the final version of the square field with this field inorder to decide whether the victory consition was met.
    public static void main(String[] args) {
        inputHandling modified = new inputHandling();
        gameMechanics newVersion = new gameMechanics();
        int n = modified.validateInput();
        n = modified.oddInput(n);
        newVersion.getValues(n);
        newVersion.generateSquare(n);
        newVersion.squareShuffling();
        System.out.println("Your goal is to rearrange the elements in this square until the sum of all rows, columns, and diagonals are identical.");
        System.out.println("Choose the row and column of the element you want to move and the direction you want to move it to. e.g. 1 2 u\nFor direction choose from the following:\nu for up\nd for down\nL for left\nr for right\ne to end the game and see if your square is a magic square.");
        newVersion.validatedUserInput();
        newVersion.victoryCondition();

    }
    public void getValues(int n){
        this.n = n;
        this.square = new int[n][n];
        this.tempSquare = new int[n][n];     //main constructor method
        
    }
    public int getN(){
        return n;                 // Getter method to make private fields accessible    
    }
    public int [][] getSquare(){
        return square;
    }
    public void generateSquare(int n){       //Algorithm for generating the square
        int x = 0;
        int y = n / 2;
        square[x][y] = 1;
        tempSquare[x][y] = 1;
        for (int i = 2; i <= n * n; i ++){
            int newX = (x - 1 + n) % n;
            int newY = (y + 1) % n;
            if (square[newX][newY] != 0){
                newX = (x + 1) % n;
                newY = y;
            }
            square[newX][newY] = i;
            tempSquare[newX][newY] = i;
            x = newX;
            y = newY;
        }
    }
    public void printSquare(){       //for printing the current likely recently updated version of the square field.         
        for (int i = 0; i < n; i ++){
            for (int j = 0; j < square[i].length; j ++){
                System.out.print( square[i][j] + "\t");
            }
            System.out.println();
        }
    }
    
}
class inputHandling extends Q1b_24092763{
    public int oddInput(int n) {         // for checking for odd number in user input
        Scanner correctInput = new Scanner(System.in);
        // System.out.print("Enter an odd Integer value:\n ");
        while(n % 2 == 0 || n < 0) {
            System.out.print("Invalid input, you can only enter an odd integer value.\n ");
            if(correctInput.hasNextInt()){
                n = correctInput.nextInt();
            }
            else{
                correctInput.next();
            }
        }
        return n;
    }
    public int validateInput() {                // for checking if user inout is an integer value
        Scanner correctInput = new Scanner(System.in);
        System.out.print("Enter an odd Integer value: ");
        Boolean validInput = false;
        int n = 0;
        while(!validInput) {
            if(correctInput.hasNextInt()){
                n = correctInput.nextInt();
                validInput = true;
            }
            else{
                System.out.print("Invalid input, you can only enter an odd integer value.\n ");
                correctInput.next();
            }
    
        }
        return n;
    }
}
class gameMechanics extends inputHandling{
    public int chosenElement;
    public int rowInput;
    public int colInput;           //These fields store the values necessary for later on determining what direction each user input will cause elements to move.
    public void squareShuffling(){
        int n = getN();              //retreiving private field from getter methods in superclass
        int square[][] = getSquare();
        Random rand = new Random();
        int shuffleCount = 0;     // initialised this value before the while loop.
        while(shuffleCount <= n * n){    // in the code below you can see that the while loop keeps adding 1 to the shuffleCount value after the while loop has iterated across the whole 2D array.
            for(int i = 0; i < square.length; i ++){
                for(int j = 0; j < square[i].length; j ++){
                    int randomRow = rand.nextInt(square.length);
                    int randomCol = rand.nextInt(square[i].length);
                    int temp = square[i][j];
                    square[i][j] = square[randomRow][randomCol];
                    square[randomRow][randomCol] = temp;

                }
            }
            shuffleCount += 1;     // one added until it reaches or exceeds a value equivalent to n * n in which case the while loop terminates.
        }
        System.out.println("Randomly shuffled magic square: \n");
        for (int i = 0; i < n; i ++){
            for (int j = 0; j < square[i].length; j ++){
                System.out.print( square[i][j] + "\t");
            }
            System.out.println();          //prints out the recently shuffled version which changed the square field in superclass
        }
    }
    public void validatedUserInput(){   // for handling all the user related input for this game.
        Scanner correctInput = new Scanner(System.in);
        Boolean correctDecision = false;
        int square[][] = getSquare();
        int n = getN();
        while(!correctDecision) { // outermost while loop to repeatedly ask player for all necessary input until player decided to terminate the process which will be explained below.
            System.out.print("Enter input in the provided format to continue editing your square or ending the game. e.g. 1 2 u\nWarning: Only end the game if your confident that your square is a magic square as you will not be able to retry again.\n");
            String userInput = correctInput.nextLine().trim();  
                if(userInput.matches("\\d+ \\d+ [ULDREuldre]")){    //ensures that user enters atleast 3 inputs to avoid an error message. 
                    String[] parts = userInput.split("\\s+"); // to correctly interpret user inputs in a line.              
                    int rowInput = Integer.parseInt(parts[0]) - 1;
                    int colInput = Integer.parseInt(parts[1]) - 1;
                    String userChoice = parts[2].toLowerCase();  // assigning position to each input in the input line provided by the user.
                    if(rowInput >=0 && rowInput < square.length && colInput >= 0 && colInput < square[0].length){  //ensure that the row and column inputs are not outside the range of the 2D array and also ensure that the inputs are integer values.
                        if(userChoice.equalsIgnoreCase("e")){        // this if loop checks if player wants to terminate the game by entering "e" in this case.
                            correctDecision = true;               //Change to boolean value terminates the outermost while loop and ends the game.
                            System.out.println("Finalised magic square:\n ");
                            printSquare();
                        }
                        else{
                        Boolean validInput = false;    //absence of "e" means outermost while loop proceeds and therefore user is asked for further inout which deals with the direction he wants his chosen element to go in, thus allowing him to rearrange the square. This updated version of square is stored in the square field in the superclass.
                        int newRow = rowInput;             
                        int newCol = colInput; // these integer values are initialised before the while loop below as they will be used for calculations of positions of elements here.
                        
            
                            while(!validInput) {
                                if(userChoice.equalsIgnoreCase("u")){
                                
                                    newRow = (rowInput - 1 + n) % n;  
                                    validInput = true;   // change to this boolean value means inner while loop is terminated if a valid direction is entered, but the outer loop continues, and therefore player is asked to enter input again including the row and column inputs. After that it comes back to this point where player can re enter the desired direction he wants his element to go to, unless he terminates the outermost while loop by choosing "e".
                                }
                                else if(userChoice.equalsIgnoreCase("d")){
                                    
                                    newRow = (rowInput + 1 + n) % n;    //rowInput and colInput are both also public fields fr this superclass and thus accessable to make necessary calculations for determining the output of each direction if entered.
                                    validInput = true;
                                }
                                else if(userChoice.equalsIgnoreCase("r")){
                            
                                    newCol = (colInput + 1 + n) % n;
                                    validInput = true;
                                }
                                else if(userChoice.equalsIgnoreCase("l")){
                                
                                    newCol = (colInput - 1 + n) % n;
                                    validInput = true;
                                }
                                else{
                                    System.out.print("You can only choose between the options u, d, l, r for going up, down, left and right respectively.\nEnter direction again. "); 
                                    userChoice = correctInput.nextLine().trim();  // asking for input again, this causes the game to start from the outermost while loop for this method.
                                } 
                                int temp = square[rowInput][colInput];
                                square[rowInput][colInput] = square[newRow][newCol];   
                                square[newRow][newCol] = temp;         // this updates the private square field in the superclass
                                printSquare();                    // prints the new magic square after recent user interaction
                            }
                        }   

                    }


                        else{
                            System.out.print("Invalid input, enter a valid value for the row and column number of your desired element.\n ");
                                correctInput.nextLine();     //clears the recent input so user can enter input again. This also restarts from the outermost while loop for this method.
                        }
                }
            else{
                System.err.println("Invalid input format you can only enter input in the following format e.g 1 2 u.");      // linked to the first if statement of the outermost while loop, if user enters more or less than 3 inputs, it all reverts back to the beginning of the outermost while loop and player is asked to enter the row, column input and direction input again.
            }
        }
    }
    public boolean isMagicSquare() {
        int square[][] = getSquare();
        int n = square.length;
    
        // Calculate the target sum using the first row
        int targetSum = 0;
        for (int j = 0; j < n; j++) {
            targetSum += square[0][j];
        }
    
        // Check all row sums
        for (int i = 0; i < n; i++) {
            int rowSum = 0;
            for (int j = 0; j < n; j++) {
                rowSum += square[i][j];
            }
            if (rowSum != targetSum) {
                return false; // Not a magic square
            }
        }
    
        // Check all column sums
        for (int j = 0; j < n; j++) {
            int colSum = 0;
            for (int i = 0; i < n; i++) {
                colSum += square[i][j];
            }
            if (colSum != targetSum) {
                return false; // Not a magic square
            }
        }
    
        // Check diagonal sums
        int mainDiagonalSum = 0;
        int secondaryDiagonalSum = 0;
        for (int i = 0; i < n; i++) {
            mainDiagonalSum += square[i][i];
            secondaryDiagonalSum += square[i][n - 1 - i];
        }
        if (mainDiagonalSum != targetSum || secondaryDiagonalSum != targetSum) {
            return false; // Not a magic square
        }
    
        // If all checks passed, it's a magic square
        return true;
    }
    
    public void victoryCondition() {
        if (isMagicSquare()) {
            System.out.println("Congratulations! Your square is indeed a magic square.");
        } else {
            System.out.println("Oops, your final square does not match the original square. Better luck next time.");
        }
    }
}


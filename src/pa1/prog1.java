//Name: Jisub Chung
//COMP 282 - T/TH
//Assignment #1
// DATE THIS WAS TURNED IN
//Description: Finished contents of Sudoku Solver

package pa1;

class Spot {
	 private int row, col;
	 
	 public Spot(int row, int col) {
		 
	 }
	 
	 public void setRow(int row) {
		 this.row = row;
	 }
	 
	 public void setCol(int col) {
		 this.col = col; 
	 }
	 
	 public int getRow() {
		return row;
	 }
	 
	 public int getCol() {
		 return col;
	 }
}

class sudoku {
	 
	 private int board[][];
	 
	 // default constructor -- I never seem to use it....
	 public sudoku() {
	 }
	 
	 // Construct a new sudoku puzzle from a string
	 // This piece of code might be useful to you:
	 // (int) (s[row].charAt(col + col/3)) - 48
	 public sudoku(String s[]) { //s[] is entered from left to right
		 //iterating through s[]
		 for (int counter=0; counter < s.length; counter++) {
			 
		 }
	 }
	 
	 // Copy constructor
	 public sudoku(sudoku p) {
	 }
	 
	 // Hint: use String.valueOf( i ) to convert an int to a String
	 public String toString() {
		 return("I DONT KNOW WHAT THIS IS SUPPOSED TO BE");
	 }
	 
	 // for easy checking of your answers
	 public String toString2() {
		 String result = new String();
	 	for (int row = 0; row < 9; row++) {
	 			for (int col = 0; col < 9; col++) {
	 				result = result + String.valueOf(board[row][col]);
	 			}
	 	}
	 	return result;
	 }

	 // create rotated sudoku puzzle – used by my test programs
	 public void rotate() {
		 int[][] temp = new int[9][9];
	 	int row, col;
	 	for (row = 0; row < 9; row++) {
		 	for (col = 0; col < 9; col++) {
		 		temp[col][8-row] = board[row][col];
	 		}
	 	}
	 	for (row = 0; row < 9; row++) {
	 		for (col = 0; col < 9; col++) {
			 	board[row][col] = temp[row][col];
	 		}
	 	}
	 }
	 
	 // Does the current board satisfy all the sudoku rules?
	 public boolean isValid() {
		 //this iterates through the row/col/box
		 for (int i=0; i < 9; i++) {
			 //this iterates through numbers 1-9
			 for (int j=1; j <= 9; j++) {
				 //a board is complete if...
				 //1. each row has numbers 1-9
				 if (this.doesRowContain(i, j) == false) {
					 return false;
				 }
				 //2. each col has numbers 1-9
				 if (this.doesColContain(i, j) == false) {
					 return false;
				 } 
				 //3. each box has numbers 1-9
				 //notation on the col is used to move to all three boxes in respective row
				 if (this.doesBoxContain(i/3, (i/3)*3, j) == false) {
					 return false;
				 }
			 }
		 }
		 return true;
	 }
	 
	 // Is this a solved sudoku?
	 public boolean isComplete() {
		 //a solved sudoku has all spots filled in
		 for (int i=0; i < board.length; i++) {
			 //any of the three checks will be sufficient (row, col, box)
			 if (this.doesRowContain(i, 0)) {
				 return false;
			 }
		 }
		 return true;
	 }
	 
	 // return true if val appears in the row of the puzzle
	 private boolean doesRowContain(int row, int val) {
		 //Note: counting row/col in base 0
		 for(int i=0; i < 9; i++) {
			 if(board[row][i] == val) {
				 return true;
			 }
		 }
		 return false;
	 }
	 
	 // return true if val appears in the col (column) of the puzzle
	 private boolean doesColContain(int col, int val) {
		 //Note: counting row/col in base 0
		 for(int i=0; i < 9; i++) {
			 if(board[i][col] == val) {
				 return true;
			 }
		 }
		 return false;
	 }
	 
	 // return true if val appears in the 3 x 3 box
	 private boolean doesBoxContain(int row, int col, int val) {
		 //Note: counting row/col in base 0
		 // because 9 boxes can be targetted by 81 different possibilities,
		 // thus the 3*(xxx/3) notation is used
		 for (int i=3*(row/3); i < (3*(row/3))+3; i++) {
			 for (int j=3*(col/3); j < (3*(col/3))+3; j++) {
				 if (board[i][j] == val) {
					 return true;
				 }
			 }
		 }
		 return false;
	 }
	 
	 // return n if n is the only possible value for this spot
	 // return 0 otherwise
	 private int fillSpot(Spot sq) { 
		 
		 return 0;
	 }
	 
 	 // return a valid spot if only one possibility for val in row
	 // return null otherwise
	 private Spot rowFill(int row, int val) {
		 
		 return null;
	 }
	 
	 // return a valid spot if only one possibility for val in col
	 // return null otherwise
	 private Spot colFill(int col, int val) {
		 
		 return null;
	 }
	 
	 // return a valid spot if only one possibility for val in the box
	 // return null otherwise
	 private Spot boxFill(int rowbox, int colbox, int val) {
		 
		 return null;
	 }
	 
	 public void solve() {
		 
	 }
	 
	 // who are you? Put your name here!
	 public static String myName() {
		 return "Jisub Chung";
	 }
}

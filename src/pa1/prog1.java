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
		 System.out.println("1111111111111111");
		 return("222222222222222");
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
		 
		 return false;
	 }
	 
	 // Is this a solved sudoku?
	 public boolean isComplete() {
		 
		 return false;
	 }
	 
	 // return true if val appears in the row of the puzzle
	 private boolean doesRowContain(int row, int val) {

		 return false;
	 }
	 
	 // return true if val appears in the col (column) of the puzzle
	 private boolean doesColContain(int col, int val) {

		 return false;
	 }
	 
	 // return true if val appears in the 3 x 3 box
	 private boolean doesBoxContain(int row, int col, int val) {

		 return false;
	 }
	 
	 // return n if n is the only possible value for this spot
	 // return 0 otherwise
	 private int fillSpot(Spot sq) { // return a valid spot if only one possibility for val in row
		 
		 return 0;
	 }
	 
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

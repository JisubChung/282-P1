//Name: Jisub Chung
//COMP 282 - T/TH
//Assignment #1
// DATE THIS WAS TURNED IN
//Description: Finished contents of Sudoku Solver

package pa1;

class Spot {
	 private int row, col;
	 
	 public Spot(int row, int col) {
		 setRow(row);
		 setCol(col);
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
	
	//board[row][col]
	private int board[][];
	
	//PROF: default constructor -- I never seem to use it....
	public sudoku() {
		board = new int[9][9];
	}
	
	//PROF: Construct a new sudoku puzzle from a string
	//PROF: This piece of code might be useful to you:
	//PROF: (int) (s[row].charAt(col + col/3)) - 48
	public sudoku(String s[]) { 
		board = new int[9][9];
		//s[] is entered from left to right
		//iterating through s[]
		String carriage;
		//spot goes up to 80, correlating to each spot on the board
		int spot = 0;
		for (int index = 0; index < s.length; index++) {
			//this carriage carries each line of the sudoku board
			carriage = s[index];
			for (int counter=0; counter < carriage.length(); counter++) {
				//this condition exists because there are spaces in carriage
				if (counter == 3 || counter == 7) {
				}
				else {
					board[spot/9][spot%9] = Integer.parseInt(carriage.substring(counter, counter+1));
					spot++;
				}
			}
		}
	}
	
	//PROF: Copy constructor
	public sudoku(sudoku p) {
		this.board = p.board; 
	}
	
	//PROF: Hint: use String.valueOf( i ) to convert an int to a String
	public String toString() {
		String printedBoard = "";
		System.out.println("\n");
		for (int i = 0; i <= 8; i++) {
			printedBoard+=(""+board[i][0]+board[i][1]+board[i][2]+" | "+board[i][3]+board[i][4]+board[i][5]+" | "+board[i][6]+board[i][7]+board[i][8] + "\n");
			if (i == 2 || i == 5) {
				printedBoard+="---------------\n";
			}
		}
		return(printedBoard);
	}
	
	//PROF: for easy checking of your answers
	public String toString2() {
		 String result = new String();
	 	for (int row = 0; row < 9; row++) {
	 			for (int col = 0; col < 9; col++) {
	 				result = result + String.valueOf(board[row][col]);
	 			}
	 	}
	 	return result;
	 }
	//PROF: create rotated sudoku puzzle – used by my test programs
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
	
	//PROF: Does the current board satisfy all the sudoku rules?
	public boolean isValid() {
		 boolean isValid = true;
		 int spot = 0, val = 1;
		 //val < 9, because there are only 9 values, and val is incremented at the beginning
		 while (isValid && val <= 9) {
			 //For reference: row = spot/9, col = spot%9, box = 3*(spot/3) & 3*(spot%3)
			 //first we check to see if the row/col/box has val
			 if (doesRowContain(spot/9, val)) {
				 //count will keep track of the count of val there are for the row/col/box
				 int count = 0;
				 int col = 0;
				 //if val is a unique entry in the row of the board, then count will = 1
				 //the above "if" statement will ensure that count will be at least 1
				 while (count <= 1 && col < 9) {
					 if (board[spot/9][col] == val) {
						 count++;
					 }
					 col++;
				 }
				 if (count >= 2) {
					 isValid = false;
				 }
			 }
			 if (doesColContain(spot%9, val)) {
				 //count will keep track of the count of val there are for the row/col/box
				 int count = 0;
				 int row = 0;
				 //if val is a unique entry in the row of the board, then count will = 1
				 //the above "if" statement will ensure that count will be at least 1
				 while (count <= 1 && row <= 8) {
					 if (board[row][spot%9] == val) {
						 count++;
					 }
					 row++;
				 }
				 if (count >= 2) {
					 isValid = false;
				 }
			 }
			 if (doesBoxContain(3*(spot/3), 3*(spot%3), val)) {
				 //count will keep track of the count of val there are for the row/col/box
				 int count = 0;
				 //there is actually no reason other than readability for row and col to be two different variables
				 int row = 0, col = 0;
				 //if val is a unique entry in the row of the board, then count will = 1
				 //the above "if" statement will ensure that count will be at least 1
				 while (count <= 1 && row <= 8) {
					 if (board[(3*(spot/3))+(row/3)][(3*(spot%3))+(col%3)] == val) {
						 count++;
					 }
					 row++;
					 col++;
				 }
				 if (count >= 2) {
					 isValid = false;
				 }
			 }
			 spot++;
			 val++;
		 }
		 return isValid;
	 }
	
	//PROF: Is this a solved sudoku?
	public boolean isComplete() {
		 boolean completed = true;
		 int row = 0;
		 //a solved sudoku has all spots filled in. In other words: no 0's in any spot
		 while (completed && row < 9) {
			 if (doesRowContain(row,0)) {
				 completed = false;
			 }
			 row++;
		 }
		 return completed;
	 }
	
	//PROF: return true if val appears in the row of the puzzle
	private boolean doesRowContain(int row, int val) {
		 //Note: counting row/col in base 0
		 boolean valid = false;
		 int spot = 0;
		 while(valid == false && spot < 9 && row < 9) {
			 if (board[row][spot] == val) {
				 valid = true;
			 }
			 spot++;
		 }
		 return valid;
	 }
	
	//PROF: return true if val appears in the col (column) of the puzzle
	private boolean doesColContain(int col, int val) {
		 //Note: counting row/col in base 0
		 boolean valid = false;
		 int row = 0;
		 while(valid == false && row < 9) {
			 if (board[row][col] == val) {
				 valid = true;
			 }
			 row++;
		 }
		 return valid;
	 }
	
	//PROF: return true if val appears in the 3 x 3 box
	private boolean doesBoxContain(int row, int col, int val) {
		 boolean valid = false;
		 //Note: counting row/col in base 0
		 // because 9 boxes can be targetted by 81 different possibilities,
		 // thus the 3*(xxx/3) notation is used
		 //All nine spots are accessed by the permutations of 9%3 and 9/3
		 int spot = 0;
		 while (valid == false && spot < 9 && row < 9) {
			 if (board[3*(row/3)+spot/3][3*(col/3)+spot%3] == val) {
				 valid = true;
			 }
			 spot++;
		 }
		 return valid;
	 }
	
	//PROF: return n if n is the only possible value for this spot
	//PROF: return 0 otherwise
	//The purpose of fillSpot is for the "Naked Single"
	private int fillSpot(Spot sq) {
		 int count = 1, single = 0;
		 while (count <= 9) {
			 //check if the row/col/box has a value
			 if (doesRowContain(sq.getRow(),count) || doesColContain(sq.getCol(),count) || doesBoxContain(sq.getRow(),sq.getCol(),count)) {
				count++; 
			 }
			 //check if the spot has multiple possible values
			 else if (single != 0) {
				 single = 0;
				 count = 10;
			 }
			 else {
				single = count;
				count++;
			 }
			 
		 }
		 return single;
	 }
	
	//PROF: return a valid spot if only one possibility for val in row
	//PROF: return null otherwise
	private Spot rowFill(int row, int val) {
		 Spot fillThis = null; 
		 //Trivial Case: the row is full & the row already has val
		 if (doesRowContain(row,0) == true && doesRowContain(row,val) == false) {
			int col = 0;
			while (col < 9) {
				//if box contains val, then move over to the next box
				if(doesBoxContain(row,col,val)) {
					col+=3; 
				}
				//if spot is filled, then move over to next col
				else if(board[row][col] != 0) {
					col++;
				}
				//if col contains val, then move to next col
				else if (doesColContain(col,val)) {
					col++;
				}
				//if there are 2 >= spots for val to go
				else if (fillThis != null) {
					fillThis = null;
					col = 99;
				}
				else {
					fillThis = new Spot(row, col);
					col++;
				}
			}
		 }
		 return fillThis;
	 }
	
	//PROF: return a valid spot if only one possibility for val in col
	//PROF: return null otherwise
	private Spot colFill(int col, int val) {
		 Spot fillThis = null;
		 //Trivial Case, the col is full & the col already has val
		 if (doesColContain(col,0) == true && doesColContain(col,val) == false) {
			int row = 0;
			while (row < 9) {
				//if box contains val, then move over to the next box
				if(doesBoxContain(row,col,val)) {
					row+=3; 
				}
				//if spot is filled, then move over to next col
				else if(board[row][col] != 0) {
					row++;
				}
				//if row contains val, then move to next col
				else if (doesRowContain(row,val)) {
					row++;
				}
				//if there are 2 >= spots for val to go
				else if (fillThis != null) {
					fillThis = null;
					row = 9;
				}
				else {
					fillThis = new Spot(row, col);
					row++;
				}
			}
		 }
		 return fillThis;
	 }
	
	//PROF: return a valid spot if only one possibility for val in the box
	//PROF: return null otherwise
	private Spot boxFill(int rowbox, int colbox, int val) {
		if (rowbox ==3 && colbox == 3 && val == 6)
			System.out.print("");
		 Spot fillThis = null;
		 //Trivial Case, the box is full & the box already has val
		 if (doesBoxContain(rowbox,colbox,0) && doesBoxContain(rowbox,colbox,val) == false) {
			 //these are created to point exclusively at the top left corner in the box
			 rowbox=3*(rowbox/3);
			 colbox=3*(colbox/3);
			 int counter = 0;
			 while (counter < 9) {
				//if spot contains val, then move over to the next box
				if(doesRowContain(rowbox+(counter/3),val)) {
					counter+=3;
				}
				//if spot is filled, then move over to next spot
				else if(board[rowbox+(counter/3)][colbox+((counter)%3)] != 0) {
					counter++;
				}
				//if col contains val, then move to next spot
				else if (doesColContain(colbox+((counter)%3),val)) {
					counter++;
				}
				//if there are 2 >= spots for val to go
				else if (fillThis != null) {
					fillThis = null;
					counter = 9;
				}
				else {
					fillThis = new Spot(rowbox+(counter/3), colbox+((counter)%3));
					counter++;
				}
			 }
		 }
		 return fillThis;
	 }
	
	public void solve() {
		boolean trySolve = true;
		boolean boardChanged = true;
		Spot fillThis;
		while (trySolve) {
			//the board is not solvable if there is a mistake
			if (isValid() == false) {
				trySolve = false;
			}
			//In the case of a valid board that hasn't had any changes made it is unsolvable
			else if (boardChanged == false) {
				trySolve = false;
			}
			//finally if the board is both valid and complete then we're done
			else if (isComplete()) {
				trySolve = false;
			}
			else {
				boardChanged = false;
				//iterating through each value
				int row, col;
				for (int val = 1; val <= 9; val++) {
					row = 0; 
					for (;row < 9; row++) {
						if (row == 0 && val == 0) {
							System.out.print("");
						}
						fillThis = rowFill(row, val);
						if (fillThis != null) {
							board[row][fillThis.getCol()] = val;
							boardChanged = true;
						}
						col = 0;
						for (; col < 9; col++) {
							if (col == 0 && val == 9) {
								System.out.print("");
							}
							fillThis = colFill(col, val);
							if (fillThis != null) {
								board[fillThis.getRow()][col] = val;
								boardChanged = true;
							}
							fillThis = boxFill(row, col, val);
							if (fillThis != null) {
								board[fillThis.getRow()][fillThis.getCol()] = val;
								boardChanged = true;
							}
							if (board[row][col] == 0) {
								if (val == fillSpot(new Spot(row,col))) {
									board[row][col] = val;
									boardChanged = true;
								}
							}
							System.out.print("");
						}	
					}
				}
			}
		}
	}
	 
	//PROF: who are you? Put your name here!
	public static String myName() {
		return "Jisub Chung, don't forget to change date turned in";
	}
}

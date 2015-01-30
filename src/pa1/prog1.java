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
		return("I DONT KNOW WHAT THIS IS SUPPOSED TO BE");
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
		 int row,col,val;
		 row=col=val=0;
		 //val < 9, because there are only 9 values, and val is incremented at the beginning
		 while (isValid && val < 9) {
			 val++;
			 if(doesRowContain(row,val) == false) {
				 isValid = false;
			 }
			 if(doesColContain(col, val) == false) {
				 isValid = false;
			 }
			 if(doesBoxContain(row, col, val) == false) {
				 isValid = false;
			 }
			 row++;
			 col++;
		 }
		 return isValid;
	 }
	
	//PROF: Is this a solved sudoku?
	public boolean isComplete() {
		 boolean completed = true;
		 int row = 0;
		 //a solved sudoku has all spots filled in. In other words: no 0's in any spot
		 while (completed && row < 9) {
			 completed = doesRowContain(row,0);
			 row++;
		 }
		 return completed;
	 }
	
	//PROF: return true if val appears in the row of the puzzle
	private boolean doesRowContain(int row, int val) {
		 //Note: counting row/col in base 0
		 boolean valid = false;
		 int spot = 0;
		 while(valid == false && spot < 9) {
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
		 int spot = 0;
		 while(valid == false && spot < 9) {
			 if (board[spot][col] == val) {
				 valid = true;
			 }
			 spot++;
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
		 while (valid == false && spot < 9) {
			 if (board[3*(row/3)+(spot%3)][3*(row/3)+(spot/3)] == val) {
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
				 count = 9;
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
					fillThis = new Spot(rowbox, colbox);
					counter++;
				}
			 }
		 }
		 return fillThis;
	 }
	
	public void solve() {
		boolean notFinished = true;
		Spot rowSpot, colSpot, boxSpot;
		int singleFill = 0;
		while(notFinished) {
			for(int index = 1; index <= 9; index++) {
				for(int val = 1; val <= 9; val++) {
					rowSpot=rowFill(index,val);
					colSpot=colFill(index,val);
					boxSpot=boxFill(index/3,index%3,val);
					//I'm kind of cheating with the variable names for this one
					singleFill=fillSpot(new Spot(index,val));
					if (rowSpot != null) {
						board[rowSpot.getRow()][rowSpot.getCol()] = val;
						rowSpot = null;
					}
					if (colSpot != null) {
						board[colSpot.getRow()][colSpot.getCol()] = val;
						colSpot = null;
					}
					if (boxSpot != null) {
						board[boxSpot.getRow()][boxSpot.getCol()] = val;
						boxSpot = null;
					}
					if (singleFill != 0) {
						board[index][val] = singleFill;
						singleFill = 0;
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

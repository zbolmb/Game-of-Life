/*Board class contains private int array and allows the creation of a board 
 * Contains basic getters and setters for the Number of col and rows of the array
 * can set or get a spot within the array, given row and col and has methods to check if 
 * the spot is occupied or not and if its within the array boundaries
 * JingJing Li 11/24
 * Period 6 Java
 */
public class Board {
	private int[][] board;

	//a board constructor that receives two int values as the width and height
	public Board(int rowNum, int colNum){ 
		board = new int[rowNum][colNum];
	}
	//a board constructor that receives an int array 
	public Board(int[][] a){
		board = a;
	}
	//toString allows board to be printed as a array starting and ending with []
	public String toString(){
		String s = "";
		for(int row = 0; row < board.length; row++){
			for(int col = 0; col < board[row].length; col++){
				s += board[row][col] + " ";
			}
			s += "\n";
		}
		return s;

	}
	//setSpot changes the int value of the spot on the board given a rowNum, colNum and int value of pieceType
	public void setSpot(int rowNum, int colNum, int pieceType){
		board[rowNum][colNum] = pieceType;
	}
	//getSpot gets the int value pieceType given rowNum and ColNum
	public int getSpot(int rowNum, int colNum){
		return board[rowNum][colNum];
	}
	//if given spot of rowNum and colNum is not a 0, returns true(is Occupied)
	public boolean isOccupied(int rowNum, int colNum){
		return(board[rowNum][colNum] != 0);
	}
	//constructs a new empty array, and passes those values to the current board; clearing the board
	public void clear(){
		int[][] clearBoard = new int[board.length][board[0].length];
		board = clearBoard;
	}
	//gets the numbers of rows of the board
	public int getNumRows(){
		return board.length;
	}
	//gets the number of columns of the board (assuming its a rectangle)
	public int getNumCol(){
		return board[0].length;
	}
	//given a row and col number, checks if the spot is non negative and within the array boundaries
	public boolean isValidSpot(int row, int col){
		return((row >=0 && col >= 0) && (row < board.length && col < board[row].length));
	}
	//returns a copy of the current board
	public int[][] copy(){
		int[][] newA = new int[getNumRows()][getNumCol()];
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board[i].length; j++){
				newA[i][j] = board[i][j];
			}
		}
		return newA;
	}
}

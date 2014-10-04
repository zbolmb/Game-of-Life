/*The GameOfLife class contains all rules for the Game Of Life
 * contains methods that can compute the number of occupied spots surrounding a spot
 * the next generation of a given board and contains many methods that are the exact
 * same methods in Board class and are delegated to them
 * JingJing Li 11/24
 * period 6 java
 */
public class GameOfLife {
	private Board board;

	//GameOfLife constructor given two int values that correspond to the rows and cols of the board
	public GameOfLife(int rows, int col){
		board = new Board(rows, col);
	}
	//GameOfLife constructor given an int array
	public GameOfLife(int[][] a){
		board = new Board(a);
	}
	//GameOfLife constructor given a board
	public GameOfLife(Board board){
		this.board = board;
	}
	/*NumOccupiedNeighbors receives two int values(row, col) and finds the number of spots surrounding
	that spot that is occupied
	returns int value
	 */
	public int NumOccupiedNeighbors(int row, int col){
		int NumOccupiedNeighbors = 0;
		for(int i = row - 1; i < row + 2; i++){
			for(int j = col - 1; j < col + 2; j++){
				if(board.isValidSpot(i,j)){
					if(board.isOccupied(i, j))
						NumOccupiedNeighbors++;
				}
			} 
		}
		if(board.isOccupied(row, col))
			NumOccupiedNeighbors --;
		return NumOccupiedNeighbors;
	}
	/*is Alive receives the row and col number and checks if that spot is alive(filled) or dead(empty)
	 * alive = 3 surrounding dots (returns true)
	 * dead = 4 or more / 1 or less surrounding dots(returns false)
	 * if 2 surrounding dots, returns true if spot is occupied, false if not
	 */
	public boolean isAlive(int row, int col){
		if(NumOccupiedNeighbors(row, col) == 3)
			return true;
		else if(NumOccupiedNeighbors(row,col) > 3 || NumOccupiedNeighbors(row,col) < 2)
			return false;
		else
			return (isOccupied(row,col));

	}
/*nextGen creates the next generation of the given GameOfLife board
 * creates temporary board and sets that board based off of actual board
 * no return
 */
	public void nextGen(){
		GameOfLife temp = new GameOfLife(board.copy());
		for(int i = 0; i < getNumRows(); i++){
			for(int j = 0; j < getNumCol(); j++){
				if(isAlive(i, j))
					temp.setSpot(i, j, 1);
				else
					temp.setSpot(i, j, 0);
			}
		}
		board = temp.getBoard();
	}
//clear clears the board, delegated to clear method in Board class
	public void clear() {
		board.clear();

	}
//gets board
	public Board getBoard() {
		return board;
	}
//sets board given a board
	public void setBoard(Board board) {
		this.board = board;
	}
//set spot of board, delegated to setSpot of Board class
	public void setSpot(int row, int col, int pieceType){
		board.setSpot(row, col, pieceType);
	}
//toString delegated to Board class
	public String toString(){
		return board.toString();
	}
//delegated to Board class
	public int getNumRows(){
		return board.getNumRows();
	}
//delegated to Board class
	public int getNumCol(){
		return board.getNumCol();
	}
//delegated to Board class
	public boolean isOccupied(int rowNum, int colNum){
		return board.isOccupied(rowNum, colNum);

	}
/*receives an int row and int col and changes the state of that spot
 * if empty, fill it ; if filled, changes to empty
 * used for the MousePressed method in Display
 */
	public void changeState(int row, int col){
		if(board.isOccupied(row, col))
			board.setSpot(row, col, 0);
		else 
			board.setSpot(row, col, 1);
	}
//delegated to Board Class
	public Board copy(){
		Board copy = new Board(board.copy());
		return copy;
	}

}

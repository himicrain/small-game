
// Implements a Noughts and Crosses board with positions numbered as follows:
//  0 | 1 | 2 
// ---+---+---
//  3 | 4 | 5
// ---+---+---
//  6 | 7 | 8
public class Board {
    public static final char X_TOKEN = 'X'; // Contains a character that represents X
    public static final char O_TOKEN = 'O'; // Contains a character that represents O
    public static final char EMPTY = ' '; // Contains a character that represents and empty position
    public int numberOfOccupied;
    
    public char[][] turns = new char[3][3];

    // Constructor for empty board
    public Board() {
    	
    	for(int i=0;i<3;i++){
    		for(int j=0;j<3;j++){
    			this.turns[i][j] = Board.EMPTY;
    		}
    	}
    	
    }

    // Constructor for board contained in string c
    public Board(String c) {
    	
    	
    	char[] temp = c.toCharArray();
    	int i,j;
    	for(int p=0;p<temp.length;p++){
    		i = (int) p/3;
    		j = p%3;
    		this.turns[i][j] = temp[p];
    	}
    	
    	numberOfOccupied = getNumOfOccupied();
    }
    
    
    public int getNumOfOccupied(){
    	int num = 0;
    	for(int i=0;i<3;i++){
    		for(int j=0;j<3;j++){
    			if(turns[i][j] != Board.EMPTY){
    				num ++;
    			}
    		}
    	}
    	
    	return num;
    }
    
    
    
    // returns true if there are three consecutive X's in a row
    // otherwise false
    boolean isWinForX() {
    	return isWinFor(Board.X_TOKEN);
    }

    // returns true if there are three consecutive O's in a row
    // otherwise false
    boolean isWinForO() {
    	return isWinFor(Board.O_TOKEN);
    }

    // returns true if there are three consecutive c's in a row
    // otherwise false
    boolean isWinFor(char c) {
    	
		/*
		 * for row
		 * */
		
		int win = 0;
		for(int i=0;i<3;i++){
			for(int j =0;j<3;j++)
				if(this.turns[i][j] == c)
					win ++ ;
			
			if(win == 3){
				return true;
			}
			win = 0;
		}
		
		
		//for col
		win = 0;
		for(int i=0;i<3;i++){
			for(int j =0;j<3;j++){
				if(this.turns[j][i] == c){
					win ++ ;
				}
			}
			if(win == 3){
				return true;
			}
			win = 0;
		}
		
		
		// *
		//  *
		//   *
		win = 0;
		for(int i=0;i<3;i++){
			if(this.turns[i][i] == c){
				win ++ ;
			}
		}
		
		if(win == 3){
			return true;
		}
		
		
		//   *
		//  *
		// *
		win = 0;
		for(int i=0;i<3;i++){
			if(this.turns[i][2-i] == c){
				win ++ ;
			}
		}
		
		if(win == 3){
			return true;
		}
		
		return false;
    }

    // returns true if board is full and no three consecutive X's or O's in a row
    boolean isDraw() {
    	if(isFull()){
	    	boolean x = isWinForO();
	    	boolean y = isWinForX();
	    	
	    	if(x == false && y == false){
	    		return true;
	    	}else{
	    		return false;
	    	}
    	}
    	
    	return false;
    	
    	
    	
    }

    boolean isFull() {
    	int count = 0;
    	for(int i=0;i<3;i++){
    		for(int j=0;j<3;j++){
    			if(this.turns[i][j] == Board.EMPTY){
    				count ++;
    			}
    		}
    	}
    	
    	if(count == 0){
    		return true;
    	}else{
    		return false;
    	}
    	
    	
    }

    boolean isGameOver() {
    	
    	if(isWinForO() || isWinForX() || isDraw()){
    		return true;
    	}
    	
    	return false;
    }

    // returns true if pos is a valid position number from 1 to 9 and space
    // is not empty
    boolean isOccupied(int pos) {
    	
    	if(pos <0 || pos >= 9){
    		return false;
    	}
    	
    	
    	char temp = getPos(pos);
    	
    	if(temp != Board.EMPTY){
    		return true;
    	}else{
    		return false;
    	}
    	
    	
    }

    char getPos(int pos) {
    	
    	int i = (int)pos/3;
    	int j = pos%3;
    	
    	return this.turns[i][j];
    }

    // places character c in position pos of board and returns new board
    // if this position is unoccupied.
    // Returns null if invalid pos or if position is occupied.
    Board move(int pos, char c) {
    	
    	if(isOccupied(pos) || pos <0 || pos >8){
    		return null;
    	}
    	
    	Board temp =new Board();
    	
    	for(int i=0;i<3;i++){
    		for(int j=0;j<3;j++){
    			temp.turns[i][j] = this.turns[i][j];
    		}
    	}
    	
    	int x = (int)pos/3;
    	int y = pos%3;
    	
    	temp.turns[x][y] = c;
    	
    	temp.numberOfOccupied = temp.getNumOfOccupied();
    	
    	
    	return temp;
    	
    	
    	
    }

    // Prints out board with internal borders, e.g.
    // X | X | O 
    //---+---+---
    // O | O | X 
    //---+---+---
    // O | O | X 
	void print() {
    	

		for(int i=0;i<3;i++){
			System.out.print(" ");
			for(int j=0;j<2;j++){
				System.out.print(this.turns[i][j]+ " | ");
			}
			System.out.print(this.turns[i][2]+ " \n");
			
			if(i<2){
				System.out.println("---+---+---");
			}
		}
    	
    	
    	
    }

    // Prints out board with internal borders and with numbers at empty positions, e.g.
    // X | 1 | O 
    //---+---+---
    // O | O | 5 
    //---+---+---
    // O | 7 | X 
    void printWithNumbers() {
    	
		for(int i=0;i<3;i++){
			System.out.print(" ");
			for(int j=0;j<2;j++){
				if(this.turns[i][j] != Board.EMPTY)
					System.out.print(this.turns[i][j]+ " | ");
				else {
					System.out.print((i*3+j)+ " | ");
				}
			}
			
			if(this.turns[i][2] != Board.EMPTY)
				System.out.print(this.turns[i][2]+ " \n");
			else {
				System.out.print((i*3+2)+ " \n");
			}
			
			if(i<2){
				System.out.println("---+---+---");
			}
		}
    }

// Only to test this class
    private void printOutcome() {
        if (isWinForX())
            System.out.println("X wins");
        if (isWinForO())
            System.out.println("O wins");
        if (isDraw())
            System.out.println("Is a draw");
    }

    public static void main(String[] args) {
        //Creates empty board
        Board board = new Board();
        board.print();
        board.printOutcome();

        System.out.println("Place O in position 1");
        board = board.move(1, O_TOKEN);
        board.print();
        board.printOutcome();

        // Creates 3x3 board X X O
        //                   O O X
        //                   O O X
        board = new Board("XXOOOXOOX");
        board.print();
        board.printOutcome();
        for (int i = 0; i < 9; i++) {
            System.out.println("Position " + i + " contains " + board.getPos(i));
        }
        // Creates board and puts string in it if called as follows:
        // java Board string
        if (args.length < 1)
            return;
        board = new Board(args[0]);
        board.print();
        board.printOutcome();
    }
}

// java Board "XOXOOXOXO"
// gives the following:
//    |   |   
// ---+---+---
//    |   |   
// ---+---+---
//    |   |   
// Place O in position 1
//     O |   |   
//    ---+---+---
//       |   |   
//    ---+---+---
//       |   |   
//                             X | X | O 
//                            ---+---+---
//                             O | O | X 
//                            ---+---+---
//                             O | O | X 
// O wins
// Position 0 contains X
// Position 1 contains X
// Position 2 contains O
// Position 3 contains O
// Position 4 contains O
// Position 5 contains X
// Position 6 contains O
// Position 7 contains O
// Position 8 contains X
//                             X | O | X 
//                            ---+---+---
//                             O | O | X 
//                            ---+---+---
//                             O | X | O 
// Is a draw

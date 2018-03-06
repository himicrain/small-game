import java.util.ArrayList;

public class TreeNode {
	private Board board = null;
	private char nextTurn = ' ';
	private char winFor = Board.EMPTY;
	private int nextMove ;
	//private char currentTurn = ' ';
	
	
	//public HashMap<Integer, Integer> Pos2Index = new HashMap<Integer, Integer>();
	public ArrayList<Integer> Pos = new ArrayList<Integer>();
	public ArrayList<TreeNode> Childs = new ArrayList<TreeNode>();
	
	private int currentPos = 0;
    
    // Constructor
    public TreeNode() {
    }

    // Sets the board stored in the node
    public void setBoard(Board board) {
    	this.board = board;
    }

    // Returns the board stored in the node
    public Board getBoard() {
		return this.board;
    }

    // Sets who has the next turn for this position, X or O,
    // (depends on the rule for the game)
    public void setNextTurn(char nextTurn) {
    	this.nextTurn = nextTurn;
    }

    // Returns X or O depending on who has the next turn for this position
    // (depends on the rule for the game)
    public char getNextTurn() {
		return this.nextTurn;
    }

    // Sets for which player this is a winning position
    // (X or O or ' ' if a draw)
    public void setWinFor(char winFor) {
    	this.winFor = winFor;
    }

    // Returns X if this position is a win for X,
    // O if a winning position for O,
    // ' ' if a draw
    public char getWinFor() {
    	return this.winFor;

    }

    // Sets the winning or drawing move to be made if this position is
    // a winning or drawing move for the player who has the next turn
    public void setNextMove(int nextMove) {
    	this.nextMove = nextMove;
    }

    // Returns the winning or drawing move to be made if this position is
    // a winning or drawing move for the player who has the next turn
    public int getNextMove() {
		return this.nextMove;
    }

    // Sets the child node corresponding to next move pos
    public void setChild(int pos, TreeNode child) {

    	this.Pos.add(pos);
    	this.Childs.add(child);
    }

    // Returns the child node corresponding to next move pos
    public TreeNode getChild(int pos) {
    	
    	if(this.Pos.size() == 0){
    		return null;
    	}

    	int index = this.Pos.indexOf(pos);

    	return this.Childs.get(index);
 
    }
    
    public void reset(){
    	this.currentPos = 0;
    }
    
    public int next(){
    	
    	int temp = this.currentPos;
    	if(temp >= this.Pos.size()){
    		return -1;
    	}
    	
    	this.currentPos ++;
    	
    	return temp;

    	
    }
    
    
    
    
    
}

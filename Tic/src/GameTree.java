
// Computes a Noughts-and-Crosses game tree

public class GameTree { 
    public TreeNode root = null; // the root of the tree
    public char[] turn; // stores the rule according to how turns are made.
    public int turnPos = 0;

    
    public GameTree(String turns) {
    	this.turn = turns.toCharArray();
    	
    	root = new TreeNode();
    	root.setNextTurn(this.turn[0]);
    	root.setBoard(new Board());
    	
    	makeGameTreeAt(root);
    	
    }
        
    // Creates a GameTree with root at node
    public void makeGameTreeAt(TreeNode node) {

    	if(node.getBoard().isWinForO()){
    		node.setWinFor(Board.O_TOKEN);
    		return ;
    	}
    	
    	if(node.getBoard().isWinForX()){
    		node.setWinFor(Board.X_TOKEN);
    		return;
    	}
    	
    	if(node.getBoard().isFull()){
    			node.setWinFor(Board.EMPTY);   
    			
    	}else{
    			node.setNextTurn(this.turn[turnPos]);
    			this.turnPos ++;
    			
    			for(int i=0;i<9;i++){
    				if(!node.getBoard().isOccupied(i)){
    					TreeNode temp = new TreeNode();
    					Board tempBoard = node.getBoard().move(i, node.getNextTurn());
    					temp.setBoard(tempBoard);
    					node.setChild(i, temp);
    				}
    			}
    			
    			char[] wins = new char[node.Pos.size()];
    			int np= node.next(),i=0;
    			while(np != -1){
        			TreeNode nextChild = node.Childs.get(np);
        			makeGameTreeAt(nextChild);
        			i++;
        			np = node.next();
    			}
    			
    			node.reset();
    			np = node.next();
    			i=0;
    			while(np != -1){
        			int index = np;
        			TreeNode nextChild = node.Childs.get(index);
        			char tempWin = nextChild.getWinFor();
        			wins[i] = tempWin;
        			i++;
        			np = node.next();
    			}
    			

    			char winfor = '-';
    			int move = 0;
    			for(i=0;i<wins.length;i++){
    				if(wins[i] == node.getNextTurn()){
    					winfor = node.getNextTurn();
    					move = i;
    					break;
    				}
    				
    				if(wins[i] == Board.EMPTY && winfor != Board.EMPTY){
    					winfor = Board.EMPTY;
    					move = i;
    				}
    			}
    			
    			if(winfor == '-'){
    				winfor = node.getNextTurn() == Board.O_TOKEN? Board.X_TOKEN : Board.O_TOKEN;
    			}
    			
    			node.setWinFor(winfor);
    			node.setNextMove(node.Pos.get(move));
    			
    			this.turnPos --;
    		}
    }

    public char getTurn(int n) {
    	return this.turn[n];
    }

    public char winner() {
		return root.getWinFor();
    }

    // prints the whole tree
    public void print() {  
    	print(this.root);
    	
    }
    
    // recursively prints the items of the tree, indenting by depth
    public void print(TreeNode node) {
    	
    	printNode(node);
    	
    	/*if(node.getBoard().numberOfOccupied == 1){
    		System.out.println("---------------------------------");
    	}*/
    	
    	node.getBoard().print();
		System.out.println();
		System.out.println();
    	if(node.getChild(node.getNextMove()) == null){
    		return ;
    	}else{
    		node.reset();
    		int i=0;
    		while(node.next() != -1){
    			print(node.Childs.get(i));
    			i++;
    		}
    	}
    }
    
    // prints only the information of node
    public void printNode(TreeNode node) {
        if (node == null) {
            return;
        }
        //最后一步
        if (node.getNextTurn() == Board.EMPTY) {
            if (node.getWinFor() == Board.EMPTY) {
                System.out.println("The game is a draw.");
            } else {
                System.out.println(node.getWinFor() + " wins.");
            }
        } else if (node.getWinFor() == Board.EMPTY) {
                System.out.print(node.getNextTurn() + " can force a draw by playing ");
                System.out.println(node.getNextMove() + ".");
        } else if (node.getWinFor() == node.getNextTurn()) {
            System.out.print(node.getWinFor() + " can force a win by playing ");
            System.out.println(node.getNextMove() + ".");
        } else {
            System.out.println(node.getNextTurn() + " cannot force a win or a draw.");
        }
    }
    
    // For testing the GameTree class
    public static void main(String[] args) {

        if (args.length < 1)
            return;
        String turns = args[0];
        // Check if input string consist only of X and O
        for (int i = 0; i < turns.length(); i++) {
            if (turns.charAt(i) != Board.X_TOKEN && turns.charAt(i) != Board.O_TOKEN) {
                System.out.println("Invalid turns");
                return;
            }
        }
        if (turns.length() < 9) {
            // if turns too short, lengthen it by alternating from
            // last character. E.g. for a 3x3 board, XXO is lengthened
            // to XXOXOXOXO
            char lastChar = Board.X_TOKEN;
            if (turns.length() > 0) {
                lastChar = turns.charAt(turns.length()-1);
                for (int i = turns.length() - 1; i < 9; i++) {
                    if (lastChar == Board.X_TOKEN)
                        lastChar = Board.O_TOKEN;
                    else
                        lastChar = Board.X_TOKEN;
                    turns += lastChar;
                }
            }
        }
        System.out.println(turns);
        GameTree gameTree = new GameTree(turns);
        gameTree.print();
    }
}


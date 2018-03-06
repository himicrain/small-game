

public class AllNaughtsAndCrosses {
	
	public int XWin = 0;
	public int OWin = 0;
	public int Draw = 0;
	public GameTree gameTree = null;
	
	
	public String generateHexStr(int num){
		String str = Integer.toBinaryString(num);
		String temp = "";
		for(int i=0;i<str.length();i++){
			if(str.charAt(i) == '1'){
				temp+=Board.X_TOKEN;
			}else {
				temp+=Board.O_TOKEN;
			}
		}
		
		for(int j=0;j<8-str.length();j++){
			temp = Board.O_TOKEN + temp;
		}
		
		temp = Board.X_TOKEN + temp;
		return temp;
		
	}
	
	public void summary(){
		for(int i=0;i<Math.pow(2, 8);i++){
			
			String temp = generateHexStr(i);
			//System.out.println(temp);
			gameTree = new GameTree(temp);
			char winner = gameTree.winner();
			
			if(winner == Board.O_TOKEN){
				OWin ++;
			}else if(winner == Board.X_TOKEN){
				XWin ++;
			}else{
				Draw ++;
				System.out.println("the string will lead to draw : "+temp);
			}
			//System.out.println("has finished " + i +" strings");
		}
		
		System.out.println("O player wins " + OWin + "  times ");
		System.out.println("X player wins " + XWin + "  times ");
		System.out.println("Draw " + Draw + "  times ");
		
		
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		AllNaughtsAndCrosses all = new AllNaughtsAndCrosses();
		all.summary();

	}

}

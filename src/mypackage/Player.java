package mypackage;


public class Player {
	
	int[] positions = new int[4];   //Position aus eiegener Sicht 
        int num;
	
	public Player(int num){
            this.num = num;
            for (int i = 0; i < 4; i++) {
                positions[i] = 0;	//Startposition 
            }
	}
        
        // gibt position der Figur zurueck aus der Sicht von Spieler 0
        public int generalPosition(int figur){
            return positions[figur] + num * 10;
        }
}

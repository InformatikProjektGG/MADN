package mypackage;


public class Player {
	
    /**
    * Position aus eigener Sicht 
     */
    int[] positions = new int[4];
    
    /**
     * Nummer des Spielers (0-3)
     */
    private int num;
	
    /**
     * erstellt einen neuen Spieler. Alle Figurebn befinden sich auf den Startpositionen
     * @param num Nummer des Spielers (0-3)
     */
    public Player(int num){
        this.num = num;
        for (int i = 0; i < 4; i++) {
            positions[i] = 0;	//Startposition 
        }
    }
        
    // gibt position der Figur zurueck aus der Sicht von Spieler 0
    public int generalPosition(int figur){
        int[] generalPositions = new int[4];
        if(positions[figur] > 0 && positions[figur] < 40){
            generalPositions[figur] = positions[figur] + num * 10;
            if(generalPositions[figur] > 39){
                generalPositions[figur] -= 39;
            }
        }
        return generalPositions[figur];
    }
}

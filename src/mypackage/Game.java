package mypackage;


public class Game {
    Player[] player;
    
    public Game(int numPlayers) {
        //create player array
        player = new Player[numPlayers];
        for (int i = 0; i < numPlayers; i++) {
            player[i] = new Player(i);
        }
    }
    
    public Actions checkActions(int wuerfelZahl, int playerNumber) {
        boolean figurRausstellen = false;
        if (wuerfelZahl == 6) {
            //Figur Rausstellen koennte moeglich sein
            for (int i = 0; i < 4; i++) {
                if (player[playerNumber].positions[i] == 0) {
                    figurRausstellen = true;
                    break;
                }
            }
        }
        
        int[] figurZiehen = new int[4];
        for (int figur = 0; figur < 4; figur++) {
            //fuer jede eigene Spielfigur
            figurZiehen[figur] = wuerfelZahl;
            // check if erlaubt
            // check eigene Figuren
            for (int eigeneFigur = 0; eigeneFigur < 4; eigeneFigur++) {
                if (player[playerNumber].positions[eigeneFigur] == player[playerNumber].positions[figur] + wuerfelZahl) {
                    //eigene Figur steht bereits auf dem Feld
                    figurZiehen[figur] = 0;
                    break;
                }
            }

            //check Spielfeld Ende
            if (player[playerNumber].positions[figur] + wuerfelZahl >= 43) {
                figurZiehen[figur] = 0;
            }
            
            // check noch nicht draussen
            if (player[playerNumber].positions[figur] == 0) {
                figurZiehen[figur] = 0;
            }
        }
        
        Actions actions = new Actions(figurRausstellen, figurZiehen);
        return actions;
    }
    
    public Positions moveFigur(int wuerfelZahl, int playerNumber, int figurNumber) {
        if(player[playerNumber].positions[figurNumber] == 0){
            //stelle Figur raus
            player[playerNumber].positions[figurNumber] = 1;
        }else{
            //stelle Figur (wuerfelZahl) Felder weiter
            player[playerNumber].positions[figurNumber] += wuerfelZahl;
        }
        
        int newPosition = player[playerNumber].generalPosition(figurNumber);

        // check if gegnerische Figuren geschlagen
        for (int gegner = 0; gegner < 4; gegner++) {
            if (gegner != playerNumber) {
                for (int figur = 0; figur < 4; figur++) {
                    if (player[gegner].generalPosition(figur) == newPosition) {
                        // gegnerische FIgur geschlagen
                        player[gegner].positions[figur] = 0;
                        break;
                    }
                }
            }    
        }
        
        // return new positions
        return new Positions(player);
    }
}

package mypackage;


public class Game {
    private Player[] players;
    
    /**
     * Erstellt neues Spiel; Figuren befinden sich auf den Startpsoitionen
     * @param numPlayerss Anzahl der Spieler (inklusive Computer gesteuerter Spieler)
     */
    public Game(int numPlayers) {
        //create players array
        players = new Player[numPlayers];
        for (int i = 0; i < numPlayers; i++) {
            players[i] = new Player(i);
        }
    }
    
    /**
     * bestimmt die erlaubten Spielzuege
     * @param wuerfelZahl gewuerfelte Zahl
     * @param playersNumber Nummer des Spielers, der gewuerfelt hat
     * @return Actions Object mit zulaessigen Spielzuegen
     */
    public Actions checkActions(int wuerfelZahl, int playersNumber) {
        boolean figurRausstellen = false;
        if (wuerfelZahl == 6) {
            //Figur Rausstellen koennte moeglich sein
            for (int i = 0; i < 4; i++) {
                if (players[playersNumber].positions[i] == 0) {
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
                if (players[playersNumber].positions[eigeneFigur] == players[playersNumber].positions[figur] + wuerfelZahl) {
                    //eigene Figur steht bereits auf dem Feld
                    figurZiehen[figur] = 0;
                    break;
                }
            }

            //check Spielfeld Ende
            if (players[playersNumber].positions[figur] + wuerfelZahl >= 43) {
                figurZiehen[figur] = 0;
            }
            
            // check noch nicht draussen
            if (players[playersNumber].positions[figur] == 0) {
                figurZiehen[figur] = 0;
            }
        }
        
        Actions actions = new Actions(figurRausstellen, figurZiehen);
        return actions;
    }
    
    /**
     * bewegt eine Spielfigur; unerlaubte Zuege werden auch durchgefuert!
     * @param wuerfelZahl gewuerfelte Zahl
     * @param playersNumber Nummer des Spielers, der gewuerfelt hat 
     * @param figurNumber Nummer der Spielfigur, die gezogen werden soll
     * @return Positions object, mit den neuen Positionen aller Figuren
     */
    public Positions moveFigur(int wuerfelZahl, int playersNumber, int figurNumber) {
        if(players[playersNumber].positions[figurNumber] == 0){
            //stelle Figur raus
            players[playersNumber].positions[figurNumber] = 1;
        }else{
            //stelle Figur (wuerfelZahl) Felder weiter
            players[playersNumber].positions[figurNumber] += wuerfelZahl;
        }
        
        int newPosition = players[playersNumber].generalPosition(figurNumber);

        // check if gegnerische Figuren geschlagen
        for (int gegner = 0; gegner < 4; gegner++) {
            if (gegner != playersNumber) {
                for (int figur = 0; figur < 4; figur++) {
                    if (players[gegner].generalPosition(figur) == newPosition) {
                        // gegnerische Figur geschlagen
                        players[gegner].positions[figur] = 0;
                        break;
                    }
                }
            }    
        }
        
        // return new positions
        return new Positions(players);
    }
    
    /**
     * stellt eine Figur auf das Spielfeld
     * @param playersNumber Nummer des Spielers, der gewuerfelt hat
     * @return Positions object, mit den neuen Positionen aller Figuren
     */
    public Positions figurRausstellen(int playersNumber){
        for(int i = 0; i < 4; i++){
            if(players[playersNumber].positions[i] == 0){
                //stelle figur i raus
                players[playersNumber].positions[i] = 1;
                break;
            }
        }
        
        // return new positions
        return new Positions(players);
    }
}

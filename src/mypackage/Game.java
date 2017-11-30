package mypackage;


public class Game {
    private Player[] players;
    
    private int currentPlayer = 0;  //Spieler, der gerade dran ist; Spieler 0 am Anfang
    
    /**
     * Erstellt neues Spiel; Figuren befinden sich auf den Startpsoitionen
     * @param numPlayers Anzahl der Spieler (inklusive Computer gesteuerter Spieler)
     */
    public Game(int numPlayers) {
        //create players array
        players = new Player[numPlayers];
        for (int i = 0; i < numPlayers; i++) {
            players[i] = new Player(i);
        }
    }
    
    /**
     * wuerfelt und bestimmt die erlaubten Spielzuege
     * @param playersNumber Nummer des Spielers, der gewuerfelt hat
     * @return Actions Object mit zulaessigen Spielzuegen
     */
    public Actions nextPlayer() {
        int wuerfelZahl = (int) Math.round(Math.random() * 6 + 0.5);
        int[] figurZiehen = new int[4];
        for (int figur = 0; figur < 4; figur++) {
            //fuer jede eigene Spielfigur
            figurZiehen[figur] = wuerfelZahl;
            // check if erlaubt
            // check eigene Figuren
            for (int eigeneFigur = 0; eigeneFigur < 4; eigeneFigur++) {
                if (players[currentPlayer].positions[eigeneFigur] == players[currentPlayer].positions[figur] + wuerfelZahl) {
                    //eigene Figur steht bereits auf diesem Feld
                    figurZiehen[figur] = 0;
                    break;
                }
            }

            //check Spielfeld Ende
            if (players[currentPlayer].positions[figur] + wuerfelZahl >= 43) {
                figurZiehen[figur] = 0;
            }
            
            // check noch nicht draussen
            if (players[currentPlayer].positions[figur] == 0) {
                figurZiehen[figur] = 1;
            }
        }
        
        Actions actions = new Actions(figurZiehen, wuerfelZahl, currentPlayer);
        currentPlayer++;
        return actions;
    }
    
    /**
     * bewegt eine Spielfigur; unerlaubte Zuege werden auch durchgefuert!
     * @param anzahlFelder moegliche anzahl felder nach checkActions
     * @param playersNumber Nummer des Spielers, der gewuerfelt hat 
     * @param figurNumber Nummer der Spielfigur, die gezogen werden soll
     * @return Positions object, mit den neuen Positionen aller Figuren
     */
    public Positions moveFigur(int anzahlFelder, int playersNumber, int figurNumber) {
        if(players[playersNumber].positions[figurNumber] == 0){
            //stelle Figur raus
            players[playersNumber].positions[figurNumber] = 1;
        }else{
            //stelle Figur (wuerfelZahl) Felder weiter
            players[playersNumber].positions[figurNumber] += anzahlFelder;
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
    
}

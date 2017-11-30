package mypackage;


public class Actions {
    /**
     * Anzahl Felder, die eine Figur gezogen werden darf; 0 bedeuted unmoeglich
    */
    public int[] figurZiehen = new int[4];	
    public int wuerfelZahl;
    public int currentPlayer;
	
    public Actions(int[] figurZiehen, int wuerfelZahl, int currentPlayer){
	this.figurZiehen = figurZiehen;
        this.wuerfelZahl = wuerfelZahl;
        this.currentPlayer = currentPlayer;
    }
}

package mypackage;


public class Actions {
    /**
     * Anzahl Felder, die eine Figure gezogen werden darf; 0 bedeuted unmoeglich
    */
    public int[] figurZiehen = new int[4];	
	
    public Actions(int[] figurZiehen){
	this.figurZiehen = figurZiehen;
    }
}

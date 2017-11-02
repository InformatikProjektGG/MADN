package mypackage;


public class Actions {
    /**
     * true, wenn eine Figur rausgestellt werden darf
     */
    public boolean figurRaustellen;
    /**
     * Anzahl Felder, die eine Figure gezogen werden darf; 0 bedeuted unmoeglich
    */
    public int[] figurZiehen = new int[4];	
	
    public Actions(boolean figurRaustellen, int[] figurZiehen){
	this.figurRaustellen = figurRaustellen;
	this.figurZiehen = figurZiehen;
    }
}

package mypackage;


public class Positions{
    
    int[][] positions;
    
    public Positions(Player[] players){
        this.positions = new int[players.length][4];
        for(int player = 0; player < players.length; player++){
            this.positions[player] = players[player].positions;
        }
    }
}

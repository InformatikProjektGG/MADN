
public class Game {
	Player[] player;
	
	public Game(int numPlayers){
		player = new Player[numPlayers];
		for(int i = 0; i < numPlayers; i++){
			player[i] = new Player();
		}
	}
	
	public Actions checkActions(int wuerfelZahl){
		boolean figurRausstellen = false;
		if(wuerfelZahl == 6){
			//Figur Rausstellen koennte moeglich sein
			for(int i = 0; i < 4; i++){
				if(player[0].positions[i] == 0){
					figurRausstellen = true;
					break;
				}
			}
		}
		
		
		int[] figurZiehen = new int[4];
		for(int i = 0; i < 4; i++){
			figurZiehen[i] = wuerfelZahl;
		}
		
		Actions actions = new Actions(figurRausstellen, figurZiehen);
		return actions;
	}
}

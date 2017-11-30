package mypackage;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;


public class MenuListener implements ActionListener {

    Oberflaeche oberflaeche;

    public MenuListener(Oberflaeche oberflaeche) {
        this.oberflaeche = oberflaeche;
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println("hello wolrd");
        Oberflaeche.hinweisHinzufuegen("hello world");
        switch(e.getActionCommand()){
            case "Wuerfeln":
                Actions actions = Oberflaeche.game.nextPlayer();
                int wuerfelZahl = actions.wuerfelZahl;
                int player = actions.currentPlayer;
                Oberflaeche.hinweisHinzufuegen("Spieler " + player + " hat eine " + wuerfelZahl + " gewuerfelt");
                if (player == 0) {
                    //Spieler ist dran
                    if ((actions.figurZiehen[0] == 0) && (actions.figurZiehen[1] == 0) && (actions.figurZiehen[2] == 0) && (actions.figurZiehen[3] == 0)) {
                        Oberflaeche.hinweisHinzufuegen("Leider kannst du keine Figur bewegen");
                    }
                } else {
                    //KI ist dran
                    KI.decideAction(actions, null);
                }
                break;
                
            case "Start":
                System.exit(0);
                break;
                
            case "Figur1":
                /*Actions actions = oberflaeche.game.checkActions(wuerfelZahl, playerNumber);
                System.out.println(wuerfelZahl);
                Actions actions = oberflaeche.game.checkActions(wuerfelZahl, 0);
                System.out.println(actions.figurRaustellen);
                if (actions.figurRaustellen == true) {
                    oberflaeche.game.moveFigur(wuerfelZahl, 0, 0);
                } else {
                    System.out.println("Warten Sie, bis die anderen Spieler gew�rfelt haben, und w�rfeln Sie dann erneut!");
                }*/
                break;
        }
    }
}

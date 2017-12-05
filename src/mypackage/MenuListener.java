package mypackage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;


public class MenuListener implements ActionListener {


    public MenuListener() {
    }

    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case "Wuerfeln":
                Actions actions = Oberflaeche.game.wuerfeln();
                //Oberflaeche.updateButtonStates(actions);
                int wuerfelZahl = actions.wuerfelZahl;
                int player = actions.currentPlayer;
                if (Oberflaeche.game.getCurrentPlayer() == 0) {
                    //Spieler ist dran
                    Oberflaeche.hinweisHinzufuegen("Du hast eine " + wuerfelZahl + " gewuerfelt");
                    if (actions.keinZugMoeglich) {
                        if(Oberflaeche.game.getAnzahlWuerfe() < 3){
                        Oberflaeche.hinweisHinzufuegen("Leider kannst du keine Figur bewegen. Du darfst noch "
                                + (3 - Oberflaeche.game.getAnzahlWuerfe()) + " einmal wuerfeln");
                        }else{
                            //Alle Versuche gescheitert -> naechster spieler
                            Oberflaeche.hinweisHinzufuegen("Spieler " 
                                    + Oberflaeche.game.getCurrentPlayer() + " ist jetzt dran");
                            
                            wuerfelAutomatisch(1000);
                        }
                    }else{
                        
                        Oberflaeche.hinweisHinzufuegen("Waehle eine Figur aus, die du bewegen moechtest");
                    }
                    Oberflaeche.updateButtonStates(actions);
                } else {
                    //KI ist dran
                    int ausgewaehlteFigur = KI.decideAction(actions, null);
                    if(ausgewaehlteFigur >= 0 && ausgewaehlteFigur <4){
                        Positions newPositions = Oberflaeche.game.moveFigur(ausgewaehlteFigur);
                        Oberflaeche.updatePositions();
                        Oberflaeche.hinweisHinzufuegen("Spieler " + player 
                            + " hast seine " + ausgewaehlteFigur + ". Figur um "
                            + wuerfelZahl + " Felder nach vorne bewegt");
                    }
                    
                    
                    if(Oberflaeche.game.getCurrentPlayer() != 0){
                        if(Oberflaeche.game.getIstErneuterVersuch()){
                            //nicht verzoegern
                            wuerfelAutomatisch(0);
                        }else{
                            wuerfelAutomatisch(2000);
                        }
                    }
                    Oberflaeche.updateButtonStates(null);
                }
                //Oberflaeche.updateButtonStates(null);
                
                break;
                
            case "End":
                System.exit(0);
                break;
                
            case "jbutton_figur1":
                Oberflaeche.game.moveFigur(0);
                Oberflaeche.updatePositions();
                //automatisch wuerfeln
                //actionPerformed(new ActionEvent(this, 0, "Wuerfeln"));
                wuerfelAutomatisch(1000);
                break;
                
            case "jbutton_figur2":
                Oberflaeche.game.moveFigur(1);
                Oberflaeche.updatePositions();
                wuerfelAutomatisch(1000);
                break;
                
            case "jbtton_figur3":
                Oberflaeche.game.moveFigur(2);
                Oberflaeche.updatePositions();
                wuerfelAutomatisch(1000);
                break;
                
            case "jbutton_figur4":
                Oberflaeche.game.moveFigur(3);
                Oberflaeche.updatePositions();
                wuerfelAutomatisch(1000);
                break;
        }
        
    }

    /**
     * ruft actionPerformed mit "Wuerfel" command nach delay auf
     * @param delay laenge der verzoegerung
     */
    private void wuerfelAutomatisch(int delay) {
        Oberflaeche.updateButtonStates(null);
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
            @Override
            public void run() {
                actionPerformed(new ActionEvent(this, 0, "Wuerfeln"));
            }
        }, delay);
    }
}

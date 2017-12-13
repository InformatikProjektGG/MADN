package mypackage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ButtonListener implements ActionListener {
	Startbildschirm startbildschirm;
	public ButtonListener(Startbildschirm startbildschirm) {
		this.startbildschirm = startbildschirm;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand() == "Start") {
			startbildschirm.setVisible(false);
	    	Oberflaeche hauptfenster = new Oberflaeche();     	//Hauptfenster generieren
	        /*hauptfenster.setLocation(9,16);
	        hauptfenster.setSize(660, 700);
	        hauptfenster.setVisible(true);*/
		}
		if (e.getActionCommand() == "Einstellungen") {
			System.exit(0);
		}
		if (e.getActionCommand() == "Hilfe") {
			System.exit(0);
		}
	}
	
}

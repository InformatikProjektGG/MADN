package mypackage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class ButtonListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Start":
                new Oberflaeche();
                break;
            case "Einstellungen":
                System.exit(0);
                break;
            case "Hilfe":
                JOptionPane.showMessageDialog(null, "Spielhilfe");
        }
    }

}

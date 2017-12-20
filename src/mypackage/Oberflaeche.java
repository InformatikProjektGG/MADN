package mypackage;

import java.awt.*;
import javax.swing.*;

public class Oberflaeche {

    static JPanel jpanel_spielfeld;
    static JLabel jlabel_hinweis;
    static JButton jbutton_figur0;
    static JButton jbutton_figur1;
    static JButton jbutton_figur2;
    static JButton jbutton_figur3;
    static JButton jbutton_wuerfeln;
    JFrame jframe;
    static int theme;
    public static Game game = new Game(4);

    public Oberflaeche(int theme) {
        jframe = new JFrame("Mensch Ärgere Dich Nicht");
        Container c = jframe.getContentPane();
        c.setLayout(new BorderLayout());

        //add JPanel fuer Hinweise
        JPanel jpanel_hinweise = new JPanel();
        jpanel_hinweise.setPreferredSize(new Dimension(660, 40));
        jlabel_hinweis = new JLabel("original: ");
        jpanel_hinweise.add(jlabel_hinweis);
        c.add(jpanel_hinweise, BorderLayout.SOUTH);

        //add JPanel fuer das Spielfeld
        jpanel_spielfeld = new SpielbrettJPanel(game, theme);
        jpanel_spielfeld.setPreferredSize(new Dimension(950, 660));
        c.add(jpanel_spielfeld);

        addButtons(c);

        jframe.setSize(1080, 720);
        jframe.setVisible(true);

        hinweisHinzufuegen("Du darfst jetzt würfeln!");
    }

    private void addButtons(Container c) {
        JPanel jpanel_controlPanel = new JPanel();
        jpanel_controlPanel.setLayout(new GridLayout(10, 1));
        OberflaecheButtonListener menuListener = new OberflaecheButtonListener();

        //add Start button
        JButton jbutton_end = new JButton("End");
        jbutton_end.addActionListener(menuListener);
        jbutton_end.setActionCommand("End");

        jbutton_wuerfeln = new JButton("   Würfeln   ");
        jbutton_wuerfeln.addActionListener(menuListener);
        jbutton_wuerfeln.setActionCommand("Wuerfeln");

        jbutton_figur0 = new JButton("Figur 1");
        jbutton_figur0.addActionListener(menuListener);
        jbutton_figur0.setActionCommand("jbutton_figur1");

        jbutton_figur1 = new JButton("Figur 2");
        jbutton_figur1.addActionListener(menuListener);
        jbutton_figur1.setActionCommand("jbutton_figur2");

        jbutton_figur2 = new JButton("Figur 3");
        jbutton_figur2.addActionListener(menuListener);
        jbutton_figur2.setActionCommand("jbutton_figur3");

        jbutton_figur3 = new JButton("Figur 4");
        jbutton_figur3.addActionListener(menuListener);
        jbutton_figur3.setActionCommand("jbutton_figur4");

        jpanel_controlPanel.add(jbutton_end);
        jpanel_controlPanel.add(jbutton_wuerfeln);
        jpanel_controlPanel.add(jbutton_figur0);
        jpanel_controlPanel.add(jbutton_figur1);
        jpanel_controlPanel.add(jbutton_figur2);
        jpanel_controlPanel.add(jbutton_figur3);

        c.add(jpanel_controlPanel, BorderLayout.EAST);

        //disable alle Figuren Buttons
        jbutton_figur0.setEnabled(false);
        jbutton_figur1.setEnabled(false);
        jbutton_figur2.setEnabled(false);
        jbutton_figur3.setEnabled(false);
    }

    /**
     * Aendert den Text des Hinweis JLabels
     *
     * @param text neuer Text
     */
    public static void hinweisHinzufuegen(String text) {
        jlabel_hinweis.setText("Hinweis: " + text);
    }

    /**
     * enables/disables buttons nach actions object
     *
     * @param actions erlaubte actions des Spielers Wenn actions==null werden
     * alle Figuren buttons disabled
     */
    public static void updateButtonStates(Actions actions) {
        if (actions == null) {
            jbutton_figur0.setEnabled(false);
            jbutton_figur1.setEnabled(false);
            jbutton_figur2.setEnabled(false);
            jbutton_figur3.setEnabled(false);

            //enable wuerfel button, wenn Spieler dran ist
            if (game.getCurrentPlayer() == 0) {
                jbutton_wuerfeln.setEnabled(true);
            } else {
                jbutton_wuerfeln.setEnabled(false);
            }
        } else {
            if (game.getCurrentPlayer() == 0) {
                //disable wuerfel button, da actions bereits verfuegbar ist
                jbutton_wuerfeln.setEnabled(false);
                //enable buttons fuer figuren, welche gezogen werden koennen
                if (actions.figurZiehen[0] != 0) {
                    jbutton_figur0.setEnabled(true);
                } else {
                    jbutton_figur0.setEnabled(false);
                }

                if (actions.figurZiehen[1] != 0) {
                    jbutton_figur1.setEnabled(true);
                } else {
                    jbutton_figur1.setEnabled(false);
                }

                if (actions.figurZiehen[2] != 0) {
                    jbutton_figur2.setEnabled(true);
                } else {
                    jbutton_figur2.setEnabled(false);
                }

                if (actions.figurZiehen[3] != 0) {
                    jbutton_figur3.setEnabled(true);
                } else {
                    jbutton_figur3.setEnabled(false);
                }
            }
        }
    }

    /**
     * updated das Spielbrett mit dem neuen Spielstand
     */
    public static void updateSpielbrett() {
        jpanel_spielfeld.repaint();
    }
}

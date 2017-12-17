package mypackage;

import java.awt.*;
import javax.swing.*;

public class Oberflaeche {

    /*Array, das mit den ersten beiden Koordinaten jedes Spielfeld abdeckt
    und mit der dritten Dimension die x und y Koordinate des jeweiligen Feldes anzeigt*/
    public static int[][][] xy = new int[11][11][2];
    public static boolean[][] is = new boolean[11][11]; //Array, das angibt, welche Felder von xy kein Spielfeld darstellen
    static JPanel jpanel_spielfeld;
    static JLabel jlabel_hinweis;
    static JButton jbutton_figur0;
    static JButton jbutton_figur1;
    static JButton jbutton_figur2;
    static JButton jbutton_figur3;
    static JButton jbutton_wuerfeln;
    static JFrame jframe;

    public static Game game = new Game(4);

    public Oberflaeche() {
        jframe = new JFrame("Mensch Ärgere Dich");
        Container c = jframe.getContentPane();
        c.setLayout(new BorderLayout());
        spielfeldErstellen();   //Spielfeld hinzufuegen

        //add JPanel fuer Hinweise
        JPanel jpanel_hinweise = new JPanel();
        jpanel_hinweise.setPreferredSize(new Dimension(660, 40));
        jlabel_hinweis = new JLabel("original: ");
        jpanel_hinweise.add(jlabel_hinweis);
        c.add(jpanel_hinweise, BorderLayout.SOUTH);
        
        //add JPanel fuer das Spielfeld
        jpanel_spielfeld = new SpielbrettCanvas(game);
        jpanel_spielfeld.setPreferredSize(new Dimension(660, 660));
        c.add(jpanel_spielfeld, BorderLayout.WEST);
        
        addButtons(c);

        jframe.setSize(1080, 720);
        //jframe.setResizable(false);//prevent window resizing
        jframe.setVisible(true);

        hinweisHinzufuegen("Du darfst jetzt wuerfeln!");
    }

    private void addButtons(Container c) {
        JPanel jpanel_controlPanel = new JPanel();
        jpanel_controlPanel.setLayout(new GridLayout(10, 1));
        MenuListener menuListener = new MenuListener();

        //add Start button
        JButton jbutton_end = new JButton("End");
        jbutton_end.addActionListener(menuListener);
        jbutton_end.setActionCommand("End");

        jbutton_wuerfeln = new JButton("Wuerfeln");
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

    public void menueErstellen() {
        MenuListener mL = new MenuListener();
        JMenuBar menueleiste = new JMenuBar();
        JMenu menuFile = new JMenu("Datei");
        JMenu menuHelp = new JMenu("Hilfe");

        JMenuItem untermenueDateiOeffnen = new JMenuItem("Oeffnen");
        JMenuItem untermenueDateiNeuesSpiel = new JMenuItem("Neues Spiel");
        JMenuItem untermenueDateiSpielSpeichern = new JMenuItem("Spiel speichern");
        JMenuItem untermenueDateiBeenden = new JMenuItem("Beenden");
        untermenueDateiBeenden.addActionListener(mL);
        untermenueDateiBeenden.setActionCommand("exit");
        menuFile.add(untermenueDateiOeffnen);
        menuFile.add(untermenueDateiNeuesSpiel);
        menuFile.add(untermenueDateiSpielSpeichern);
        menuFile.add(untermenueDateiBeenden);

        menueleiste.add(menuFile);
        menueleiste.add(menuHelp);
        jframe.setJMenuBar(menueleiste);
    }

    /**
     * berechnet die Koordinaten fuer das Spielfeld
     */
    public void spielfeldErstellen() {
        for (int i = 0; i <= 10; i++) {
            for (int s = 0; s <= 10; s++) {
                for (int t = 0; t <= 1; t++) {
                    if (t == 0) {
                        xy[i][s][t] = (i + 1) * 10 + i * 40;
                    }
                    if (t == 1) {
                        xy[i][s][t] = (s + 1) * 10 + s * 40;
                    }
                }
            }
        }
        for (int u = 0; u <= 10; u++) {     //Markieren, welche Felder keine Spielfelder sind...
            for (int v = 0; v <= 10; v++) { //...und deshalb nicht angezeigt werden sollen
                if (u == 0 || u == 1 || u == 9 || u == 10) {
                    if (v == 2 || v == 3 || v == 7 || v == 8) {
                        is[u][v] = true;
                    }
                }
                if (u == 2 || u == 3 || u == 7 || u == 8) {
                    if (v != 4 && v != 5 && v != 6) {
                        is[u][v] = true;
                    }
                }
            }
        }
        is[5][5] = true;

        //jframe.add(new SpielbrettCanvas(game));
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

package mypackage;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import javax.*;


public class Oberflaeche extends JFrame {

    /*Array, das mit den ersten beiden Koordinaten jedes Spielfeld abdeckt
    und mit der dritten Dimension die x und y Koordinate des jeweiligen Feldes anzeigt*/
    public static int[][][] xy = new int[11][11][2]; 
    public static boolean[][] is = new boolean[11][11]; //Array, das angibt, welche Felder von xy kein Spielfeld darstellen
    static Container c;
    static JLabel jlabel_hinweis;

    public static Game game = new Game(4);

    public Oberflaeche() {
        JFrame gui = new JFrame("Mensch �rgere Dich Nicht!");
        c = getContentPane();
        c.setLayout(new BorderLayout());
        c.setBackground(new Color(252, 228, 150));

        //menueErstellen();   //Menue hinzufuegen
        spielfeldErstellen();   //Spielfeld hinzufuegen

        JPanel jpanel_hinweise = new JPanel();
        jpanel_hinweise.setPreferredSize(new Dimension(660, 40));
        jlabel_hinweis = new JLabel("original: ");
        jpanel_hinweise.add(jlabel_hinweis);
        c.add(jpanel_hinweise, BorderLayout.SOUTH);
        
        JPanel jpanel_controlPanel = new JPanel();
        jpanel_controlPanel.setLayout(new GridLayout(10, 1));
        //add Start button
        JButton jbutton_start = new JButton("Start");
        MenuListener bmL = new MenuListener(this);
        jbutton_start.addActionListener(bmL);
        jbutton_start.setActionCommand("Start");
        
        JButton jbutton_wuerfeln = new JButton("Wuerfeln");
        MenuListener cmL = new MenuListener(this);
        jbutton_wuerfeln.addActionListener(cmL);
        jbutton_wuerfeln.setActionCommand("Wuerfeln");
        
        MenuListener dmL = new MenuListener(this);
        
        JButton jbutton_figur1 = new JButton("Figur 1");
        jbutton_figur1.addActionListener(dmL);
        jbutton_figur1.setActionCommand("jbutton_figur1");
        
        JButton jbutton_figur2 = new JButton("Figur 2");
        jbutton_figur2.addActionListener(dmL);
        jbutton_figur2.setActionCommand("jbutton_figur2");
        
        JButton jbutton_figur3 = new JButton("Figur 3");
        jbutton_figur3.addActionListener(dmL);
        jbutton_figur3.setActionCommand("jbutton_figur3");
        
        JButton jbutton_figur4 = new JButton("Figur 4");
        jbutton_figur4.addActionListener(dmL);
        jbutton_figur4.setActionCommand("jbutton_figur4");

        jpanel_controlPanel.add(jbutton_start);
        jpanel_controlPanel.add(jbutton_wuerfeln);
        jpanel_controlPanel.add(jbutton_figur1);
        jpanel_controlPanel.add(jbutton_figur2);
        jpanel_controlPanel.add(jbutton_figur3);
        jpanel_controlPanel.add(jbutton_figur4);

        c.add(jpanel_controlPanel, BorderLayout.EAST);
        
    }

    public void menueErstellen() {
        MenuListener mL = new MenuListener(this);
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
        setJMenuBar(menueleiste);
    }

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

        add(new Kreis());
    }

    public static void hinweisHinzufuegen(String text) {
        jlabel_hinweis.setText("Hinweis: " + text);
    }

}

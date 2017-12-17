package mypackage;

import java.awt.Color;
import java.awt.Container;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Startbildschirm extends JFrame {

    //Container fuer GUI Elemente
    static Container c;

    public static void main(String[] args) {
        Startbildschirm startbildschirm = new Startbildschirm();
        startbildschirm.setSize(650, 450);
        startbildschirm.setLocation(10, 10);
        startbildschirm.setBackground(new Color(252, 228, 92));
        startbildschirm.setVisible(true);
    }

    public Startbildschirm() {
        setTitle("Mensch Ärgere Dich Nicht");
        c = getContentPane();
        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 255, 255));
        panel.setLayout(null);

        BufferedImage pic1 = null;
        File startbild = new File(".resources/images/StartbildschirmHintergrund.png");
        System.out.println(startbild);
        try {
            pic1 = ImageIO.read(getClass().getResource("images/StartbildschirmHintergrund.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JLabel startbildLabel = new JLabel(new ImageIcon(pic1));
        startbildLabel.setBounds(0, 0, 640, 400);

        JButton start = new JButton();
        start.setBounds(320, 170, 50, 50);
        start.setOpaque(false);
        start.setContentAreaFilled(false);
        start.setBorderPainted(false);
        ButtonListener buttonstart = new ButtonListener();
        start.addActionListener(buttonstart);
        start.setActionCommand("Start");

        JButton einstellungen = new JButton();
        einstellungen.setBounds(250, 220, 50, 50);
        einstellungen.setOpaque(false);
        einstellungen.setContentAreaFilled(false);
        einstellungen.setBorderPainted(false);
        ButtonListener buttoneinstellungen = new ButtonListener();
        einstellungen.addActionListener(buttoneinstellungen);
        einstellungen.setActionCommand("Einstellungen");

        JButton hilfe = new JButton();
        hilfe.setBounds(390, 240, 50, 50);
        hilfe.setOpaque(false);
        hilfe.setContentAreaFilled(false);
        hilfe.setBorderPainted(false);
        ButtonListener buttonhilfe = new ButtonListener();
        hilfe.addActionListener(buttonhilfe);
        hilfe.setActionCommand("Hilfe");

        panel.add(startbildLabel);
        panel.add(start);
        panel.add(einstellungen);
        panel.add(hilfe);
        c.add(panel);

    }
}

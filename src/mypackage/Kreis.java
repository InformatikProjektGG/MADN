package mypackage;


import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

public class Kreis extends Canvas {

    public void paint(Graphics g) {
        g.setColor(Color.white);
        for (int i = 0; i <= 10; i++) {
            for (int s = 0; s <= 10; s++) {
                g.setColor(Color.black);
                g.fillOval(Oberflaeche.xy[i][s][0], Oberflaeche.xy[i][s][1], 42, 42);
                g.setColor(Color.white);
                if ((i == 0 || i == 1) && (s == 0 || s == 1)) {
                    g.setColor(Color.red);
                }
                if ((s == 5) && (i == 1 || i == 2 || i == 3 || i == 4)) {
                    g.setColor(Color.red);
                }
                if (i == 0 && s == 4) {
                    g.setColor(new Color(255, 160, 160));
                }
                if ((i == 9 || i == 10) && (s == 9 || s == 10)) {
                    g.setColor(Color.green);
                }
                if ((s == 5) && (i == 6 || i == 7 || i == 8 || i == 9)) {
                    g.setColor(Color.green);
                }
                if (i == 10 && s == 6) {
                    g.setColor(new Color(160, 255, 160));
                }
                if ((i == 9 || i == 10) && (s == 0 || s == 1)) {
                    g.setColor(Color.blue);
                }
                if ((i == 5) && (s == 1 || s == 2 || s == 3 || s == 4)) {
                    g.setColor(Color.blue);
                }
                if (i == 6 && s == 0) {
                    g.setColor(new Color(160, 160, 255));
                }
                if ((s == 9 || s == 10) && (i == 0 || i == 1)) {
                    g.setColor(Color.yellow);
                }
                if ((i == 5) && (s == 6 || s == 7 || s == 8 || s == 9)) {
                    g.setColor(Color.yellow);
                }
                if (i == 4 && s == 10) {
                    g.setColor(new Color(255, 255, 160));
                }
                g.fillOval(Oberflaeche.xy[i][s][0], Oberflaeche.xy[i][s][1], 40, 40);
                g.setColor(Color.white);
            }
        }
        for (int u = 0; u <= 10; u++) {
            for (int v = 0; v <= 10; v++) {
                if (Oberflaeche.is[u][v] == true) {
                    g.clearRect(Oberflaeche.xy[u][v][0], Oberflaeche.xy[u][v][1], 42, 42);
                }
            }
        }
    }
}

package mypackage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class SpielbrettJPanel extends JPanel {

    Game game;
    int theme;

    public SpielbrettJPanel(Game game, int theme) {
        this.game = game;
        this.theme = theme;
    }

    @Override
    public void paintComponent(Graphics g) {
        //clean background
        super.paintComponent(g);
        Dimension dimension = getSize();
        if (theme == 0) {
            //Weihnachtstheme
            Image weihnachten = null;
            try {
                weihnachten = ImageIO.read(getClass().getResource("images/Weihnachten/Weihnachten.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            g.drawImage(weihnachten, 0, 0, dimension.width, dimension.height, null);
        }
        if (theme == 1) {
            //Technologietheme
            Image technologie = null;
            try {
                technologie = ImageIO.read(getClass().getResource("images/Technologie/Technologie.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            g.drawImage(technologie, 0, 0, dimension.width, dimension.height, null);
        }
        if (theme == 2) {
            g.setColor(new Color(252, 228, 92));
            g.fillRect(0, 0, dimension.width, dimension.height);
            g.setColor(Color.BLACK);
        }

        //calculate coordinates for grid layout
        int[][][] gridXY = new int[11][11][2];  //dritte Dimension beinhaltet x und y Koordinaten

        int spielfeldKantenLaenge;
        if (dimension.width * 0.7 > dimension.height) {
            spielfeldKantenLaenge = dimension.height;
        } else {
            spielfeldKantenLaenge = (int) Math.round(dimension.width * 0.7);
        }
        for (int x = 0; x <= 10; x++) {
            for (int y = 0; y <= 10; y++) {
                //for every grid coordinate
                gridXY[x][y][0] = x * (spielfeldKantenLaenge / 11) + 10;
                gridXY[x][y][1] = y * (spielfeldKantenLaenge / 11) + 10;
            }
        }

        g.setColor(Color.white);
        for (int x = 0; x <= 10; x++) {
            for (int y = 0; y <= 10; y++) {
                g.setColor(Color.white);
                boolean malen = false;
                //Spielfelder markieren
                if (x == 4 || x == 5 || x == 6) {
                    g.setColor(Color.white);
                    malen = true;
                }
                if (y == 4 || y == 5 || y == 6) {
                    g.setColor(Color.white);
                    malen = true;
                }
                if ((x == 0 || x == 1) && (y == 0 || y == 1)) {
                    g.setColor(Color.red);
                    malen = true;
                }
                if ((y == 5) && (x == 1 || x == 2 || x == 3 || x == 4)) {
                    g.setColor(Color.red);
                    malen = true;
                }
                if (x == 0 && y == 4) {
                    g.setColor(new Color(255, 160, 160));
                    malen = true;
                }
                if ((x == 9 || x == 10) && (y == 9 || y == 10)) {
                    g.setColor(Color.green);
                    malen = true;
                }
                if ((y == 5) && (x == 6 || x == 7 || x == 8 || x == 9)) {
                    g.setColor(Color.green);
                    malen = true;
                }
                if (x == 10 && y == 6) {
                    g.setColor(new Color(160, 255, 160));
                    malen = true;
                }
                if ((x == 9 || x == 10) && (y == 0 || y == 1)) {
                    g.setColor(Color.blue);
                    malen = true;
                }
                if ((x == 5) && (y == 1 || y == 2 || y == 3 || y == 4)) {
                    g.setColor(Color.blue);
                    malen = true;
                }
                if (x == 6 && y == 0) {
                    g.setColor(new Color(160, 160, 255));
                    malen = true;
                }
                if ((y == 9 || y == 10) && (x == 0 || x == 1)) {
                    g.setColor(Color.yellow);
                    malen = true;
                }
                if ((x == 5) && (y == 6 || y == 7 || y == 8 || y == 9)) {
                    g.setColor(Color.yellow);
                    malen = true;
                }
                if (x == 4 && y == 10) {
                    g.setColor(new Color(255, 255, 160));
                    malen = true;
                }
                if ((x == 4 || x == 6) && (y == 4 || y == 6)) {
                    g.setColor(Color.black);
                    malen = true;
                }
                if (x == 5 && y == 5) {
                    malen = false;
                }

                if (malen) {
                    g.fillOval(gridXY[x][y][0], gridXY[x][y][1], spielfeldKantenLaenge / 13, spielfeldKantenLaenge / 13);
                }

                g.setColor(Color.white);
            }
        }

        //Figuren malen
        for (int player = 0; player < 4; player++) {
            for (int figur = 0; figur < 4; figur++) {
                //für jede Figur
                //Farbe für die Figur wählen
                String figurName = "";
                switch (player) {
                    case 0:
                        //red player
                        g.setColor(new Color(153, 21, 21));
                        figurName = "Rot";
                        break;
                    case 1:
                        //blue player
                        g.setColor(new Color(130, 177, 255));
                        figurName = "Blau";
                        break;
                    case 2:
                        //green player
                        g.setColor(new Color(47, 175, 58));
                        figurName = "Gruen";
                        break;
                    case 3:
                        //yellow player
                        g.setColor(new Color(203, 234, 0));
                        figurName = "Gelb";
                        break;
                }

                int position = game.positions.positions[player][figur];
                int figurX = 0;
                int figurY = 0;
                if (position == 0) {
                    //berechne Koordinaten des linken oberen Feldes
                    if (player == 1) {
                        figurX += 9;
                    }
                    if (player == 2) {
                        figurX += 9;
                        figurY += 9;
                    }
                    if (player == 3) {
                        figurY += 9;
                    }

                    //platziere jede Figur auf einem eigenem Feld
                    if (figur == 1) {
                        figurX += 1;
                    }
                    if (figur == 2) {
                        figurY += 1;
                    }
                    if (figur == 3) {
                        figurX += 1;
                        figurY += 1;
                    }
                } else if (position >= 41 && position <= 44) {
                    //Figur befindet sich in der endzone
                    switch (player) {
                        case 0:
                            //red player
                            figurX = position - 40;
                            figurY = 5;
                            break;
                        case 1:
                            //blue player
                            figurX = 5;
                            figurY = position - 40;
                            break;
                        case 2:
                            //green player
                            figurX = 44 - position + 6;
                            figurY = 5;
                            break;
                        case 3:
                            //yellow player
                            figurX = 5;
                            figurY = 44 - position + 6;
                            break;
                    }
                } else {
                    //find X coordinate
                    if (position >= 1 && position <= 5) {
                        figurX = position - 1;
                        figurY = 4;
                    }

                    if (position >= 6 && position <= 8) {
                        figurX = 4;
                        figurY = 9 - position;
                    }
                    if (position >= 9 && position <= 11) {
                        figurX = position - 5;
                        figurY = 0;
                    }
                    if (position >= 12 && position <= 14) {
                        figurX = 6;
                        figurY = position - 11;
                    }
                    if (position >= 15 && position <= 19) {
                        figurX = position - 9;
                        figurY = 4;
                    }
                    if (position == 20) {
                        figurX = 10;
                        figurY = 5;
                    }
                    if (position >= 21 && position <= 25) {
                        figurX = 25 - position + 6;
                        figurY = 6;
                    }
                    if (position >= 26 && position <= 28) {
                        figurX = 6;
                        figurY = position - 26 + 7;
                    }
                    if (position >= 29 && position <= 31) {
                        figurX = 31 - position + 4;
                        figurY = 10;
                    }
                    if (position >= 32 && position <= 34) {
                        figurX = 4;
                        figurY = 41 - position;
                    }
                    if (position >= 35 && position <= 39) {
                        figurX = 39 - position;
                        figurY = 6;
                    }
                    if (position == 40) {
                        figurX = 0;
                        figurY = 5;
                    }
                }

                //figur zeichnen
                int figurXPixels = gridXY[figurX][figurY][0] + 5;
                int figurYPixels = gridXY[figurX][figurY][1] + 5;
                //g.fillRect(figurXPixels, figurYPixels, dimension.width / 40, dimension.height / 40);

                //draw Figur Zahl
                g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
                g.setColor(Color.BLACK);
                g.drawString(String.valueOf(figur + 1), figurXPixels + 7, figurYPixels + 27);

                //richtigen Ordner f�r Figuren ausw�hlen
                String themeFolder;
                if (theme == 0) {
                    themeFolder = "Weihnachten/";
                } else if (theme == 1) {
                    themeFolder = "Technologie/";
                } else {
                    themeFolder = "Standard/";
                }

                // Figur-icon zeichnen
                Image figurImg = null;
                try {
                    figurImg = ImageIO.read(getClass().getResource("images/" + themeFolder + figurName + String.valueOf(figur + 1) + ".png"));
                    figurImg = figurImg.getScaledInstance(45, 45, 0);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                g.drawImage(figurImg, figurXPixels - 5, figurYPixels - 5, spielfeldKantenLaenge / 13, spielfeldKantenLaenge / 13, null);
            }
        }

        //wuerfelZahl anzeigen, wenn Spieler0 dran ist
        boolean gewuerfelt = game.getBereitsGewuerfelt();
        if ((game.getCurrentPlayer() == 0 && gewuerfelt) || (game.getCurrentPlayer() == 1 && !gewuerfelt)) {
            int wuerfelZahl = game.getWuerfelZahl();
            if (wuerfelZahl >= 1 && wuerfelZahl <= 6) {
                //Wuerfel zeichnen
                BufferedImage img = null;
                try {
                    img = ImageIO.read(getClass().getResource("images/Wuerfel" + wuerfelZahl + ".png"));
                } catch (IOException e) {
                }
                //random x and y coordinate rechst vom Spielfeld
                int wuerfelX = (int) Math.round(dimension.width * 0.7 * (new Random().nextFloat() / 10 + 0.95));
                int wuerfelY = (int) Math.round(gridXY[10][4][1] * (new Random().nextFloat() + 0.5));
                g.drawImage(img, wuerfelX, wuerfelY, spielfeldKantenLaenge / 7, spielfeldKantenLaenge / 7, this);
            }
        }
    }
}

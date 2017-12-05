package mypackage;


import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

public class SpielbrettCanvas extends Canvas {
    
    Positions positions;
    public SpielbrettCanvas(Positions positions){
        this.positions = positions;
    }
    
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
        
        //Figuren malen
        for(int player = 0; player <4; player++){
            for(int figur = 0; figur < 4; figur++){
                //für jede Figur
                //Farbe für die Figur wählen
                switch(player){
                    case 0:
                        //red player
                        g.setColor(new Color(153, 21, 21));
                        break;
                    case 1:
                        //blue player
                        g.setColor(new Color(130, 177, 255));
                        break;
                    case 2:
                        //green player
                        g.setColor(new Color(47, 175, 58));
                        break;
                    case 3:
                        //yellow player
                        g.setColor(new Color(203, 234, 0));
                        break;
                }
                
                int position = positions.positions[player][figur];
                int figurX = 0;
                int figurY = 0;
                if(position == 0){
                    //berechne Koordinaten des linken oberen Feldes
                    if(player == 1) figurX += 9;
                    if(player == 2){
                        figurX += 9;
                        figurY += 9;
                    }
                    if(player == 3) figurY += 9;
                    
                    //platziere jede Figur auf einem eigenem Feld
                    if(figur == 1) figurX += 1;
                    if(figur == 2) figurY += 1;
                    if(figur == 3){
                        figurX += 1;
                        figurY += 1;
                    }
                }else{
                    //find X coordinate
                    if(position >= 1 && position <= 5){
                        figurX = position - 1;
                        figurY = 4;
                    }
                    
                    if(position >= 6 && position <= 8){
                        figurX = 4;
                        figurY = 9 - position;
                    }
                    if(position >= 9 && position <= 11){
                        figurX = position - 5;
                        figurY = 0;
                    }
                    if(position >= 12 && position <= 14){
                        figurX = 6;
                        figurY = position - 11;
                    }
                    if(position >= 15 && position <= 19){
                        figurX = position - 9;
                        figurY = 4;
                    }
                    if(position == 20){
                        figurX = 10;
                        figurY = 5;
                    }
                    if(position >= 21 && position <= 25){
                        figurX = 25 - position + 6;
                        figurY = 6;
                    }
                    if(position >= 26 && position <= 28){
                        figurX = 6;
                        figurY = position - 26 + 6;
                    }
                    if(position >= 29 && position <= 31){
                        figurX = 31 - position + 4;
                        figurY = 10;
                    }
                    if(position >= 32 && position <= 34){
                        figurX = 4;
                        figurY =  41 - position;
                    }
                    if(position >= 35 && position <= 39){
                        figurX = 39 - position;
                        figurY = 6;
                    }
                    if(position == 40){
                        figurX = 0;
                        figurY = 5;
                    }
                }
                
                //figur zeichnen
                g.fillRect(Oberflaeche.xy[figurX][figurY][0] + 5, Oberflaeche.xy[figurX][figurY][1] + 5, 30, 30);
            }
        }
    }
}

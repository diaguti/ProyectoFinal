/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PacmUN;

import java.awt.*;
import javax.swing.ImageIcon;

/**
 *
 * @author Usuario
 */
public class Nivel1 extends NivelA {
    //1 representa un muro al lado izquierdo
    //3 representa un muro al lado derecho
    //5 representa un muro arriba
    //7 representa un muro abajo
    private int[][] matrizInicial ={{2,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,4},
                              {1,0,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,3},
                              {1,9,2,4,0,2,5,4, 0,9,2,5,5,5,5,4,9,3},
                              {1,10,1,3,0,1,0,3,9,0,1,0,2,5,4,3,10,3},
                              {1,9,1,3,0,1,0,3,0,9,1,0,3,0,1,3,9,3},
                              {1,9,1,6,7,8,0,3,9,0,1,0,3,0,1,3,9,3},
                              {1,9,6,7,7,7,7,8,0,9,6,7,8,0,6,8,9,3},
                              {1,9,9,9,3,9,9,9,9,9,9,9,9,9,9,9,9,3},
                              {1,9,9,9,3,9,9,9,9,9,9,9,9,9,9,9,9,3},
                              {6,7,7,0,0,0,0,0,0,0,0,0,0,0,0,7,7,8},
                              {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                              {2,5,5,0,3,0,0,0,0,0,0,0,0,0,0,5,5,4},
                              {1,9,9,9,3,9,9,9,9,9,9,9,9,9,9,9,9,3},
                              {1,9,2,5,5,5,5,4,9,9,2,5,4,9,9,9,9,3},
                              {1,9,1,2,5,4,0,3,9,0,1,0,3,9,9,9,9,3},
                              {1,9,1,3,7,1,0,3,0,9,1,0,3,9,9,9,9,3},
                              {1,9,1,3,7,1,0,3,9,0,1,0,6,5,5,4,9,3},
                              {1,10,6,8,0,6,7,8,0,9,6,7,7,7,7,8,10,3},
                              {1,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,3},
                              {6,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,8}};
    private int[][] level1 = {{2,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,4},
                              {1,0,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,3},
                              {1,9,2,4,0,2,5,4, 0,9,2,5,5,5,5,4,9,3},
                              {1,10,1,3,0,1,0,3,9,0,1,0,2,5,4,3,10,3},
                              {1,9,1,3,0,1,0,3,0,9,1,0,3,0,1,3,9,3},
                              {1,9,1,6,7,8,0,3,9,0,1,0,3,0,1,3,9,3},
                              {1,9,6,7,7,7,7,8,0,9,6,7,8,0,6,8,9,3},
                              {1,9,9,9,3,9,9,9,9,9,9,9,9,9,9,9,9,3},
                              {1,9,9,9,3,9,9,9,9,9,9,9,9,9,9,9,9,3},
                              {6,7,7,0,0,0,0,0,0,0,0,0,0,0,0,7,7,8},
                              {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                              {2,5,5,0,3,0,0,0,0,0,0,0,0,0,0,5,5,4},
                              {1,9,9,9,3,9,9,9,9,9,9,9,9,9,9,9,9,3},
                              {1,9,2,5,5,5,5,4,9,9,2,5,4,9,9,9,9,3},
                              {1,9,1,2,5,4,0,3,9,0,1,0,3,9,9,9,9,3},
                              {1,9,1,3,7,1,0,3,0,9,1,0,3,9,9,9,9,3},
                              {1,9,1,3,7,1,0,3,9,0,1,0,6,5,5,4,9,3},
                              {1,10,6,8,0,6,7,8,0,9,6,7,7,7,7,8,10,3},
                              {1,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,3},
                              {6,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,8}};
    public Nivel1() {
    }

    @Override
    protected Image loadImage(String imageName) {
        ImageIcon ii = new ImageIcon(imageName);
        Image image = ii.getImage();
        return image;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        repaint();
        Image wall = loadImage("level.png");
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 18; j++) {
                if (level1[i][j] == 1) {
                    g.drawImage(wall, j * 20, i * 20, (j * 20) + 20, (i * 20) + 20, 27, 27, 27 + 8, 27 + 8, this);
                }
                if (level1[i][j] == 2) {
                    g.drawImage(wall, j * 20, i * 20, (j * 20) + 20, (i * 20) + 20, 9, 27, 9 + 8, 27 + 8, this);
                }
                if (level1[i][j] == 3) {
                    g.drawImage(wall, j * 20, i * 20, (j * 20) + 20, (i * 20) + 20, 18, 27, 18 + 8, 27 + 8, this);
                }
                if (level1[i][j] == 4) {
                    g.drawImage(wall, j * 20, i * 20, (j * 20) + 20, (i * 20) + 20, 0, 27, 8, 27 + 8, this);
                }
                if (level1[i][j] == 5) {
                    g.drawImage(wall, j * 20, i * 20, (j * 20) + 20, (i * 20) + 20, 99, 27, 99 + 8, 27 + 8, this);
                }
                if (level1[i][j] == 6) {
                    g.drawImage(wall, j * 20, i * 20, (j * 20) + 20, (i * 20) + 20, 45, 27, 45 + 8, 27 + 8, this);
                }
                if (level1[i][j] == 7) {
                    g.drawImage(wall, j * 20, i * 20, (j * 20) + 20, (i * 20) + 20, 108, 27, 108 + 8, 27 + 8, this);
                }
                if (level1[i][j] == 8) {
                    g.drawImage(wall, j * 20, i * 20, (j * 20) + 20, (i * 20) + 20, 36, 27, 36 + 8, 27 + 8, this);
                }
                if (level1[i][j] == 0) {
                    g.drawImage(wall, j * 20, i * 20, (j * 20) + 20, (i * 20) + 20, 108, 18, 108 + 8, 18 + 8, this);
                }
                if (level1[i][j] == 9) {
                    g.drawImage(wall, j * 20, i * 20, (j * 20) + 20, (i * 20) + 20, 117, 18, 117 + 8, 18 + 8, this);
                }
                if (level1[i][j] == 10) {
                    g.drawImage(wall, j * 20, i * 20, (j * 20) + 20, (i * 20) + 20, 135, 18, 135 + 8, 18 + 8, this);
                }
            }
        }
    }

    public int[][] getLevel1() {
        return level1;
    }

    public void setLevel1(int[][] level1) {
        this.level1 = level1;
    }

    public int[][] getMatrizInicial() {
        return matrizInicial;
    }

    public int getPosLevel1(int r, int t) {
        return level1[r][t];
    }
}
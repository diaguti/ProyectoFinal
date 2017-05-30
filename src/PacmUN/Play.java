/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PacmUN;

import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author Usuario
 */
public class Play extends JPanel implements ActionListener, KeyListener {

    private Timer timer;
    private Ghost[] ghost = new Ghost[4];
    private Nivel1 nivel1;
    private Puntaje puntaje;
    private int dx, dy, secuencia, x, y, direccion, puntosTotales, noPaint;
    private boolean sinVidas;
    private int pacmanlives, var = 0;
    private int[][] matrizInicial;
    private Font letra = new Font("Helvetica", Font.BOLD, 14);
    private String nameUser = "";

    private int c = 0, v = 0;

    public Play() throws FileNotFoundException, IOException {
        addKeyListener(new TAdapter());
        setFocusable(true);
        this.timer = new Timer(25, this);
        this.timer.start();
        this.nivel1 = new Nivel1();
        this.ghost[0] = new Ghost(20, 20, 2, 9, 11);
        this.ghost[1] = new Ghost(20, 20, 2, 10, 11);
        this.ghost[2] = new Ghost(20, 20, 2, 8, 11);
        this.ghost[3] = new Ghost(20, 20, 2, 11, 11);
        this.dx = 0;
        this.dy = 0;
        this.direccion = 2;
        this.secuencia = 0;
        this.puntosTotales = 0;
        this.noPaint = 0;
        this.puntaje = new Puntaje();
        this.puntaje.leerPuntaje();
        this.pacmanlives = 3;
        this.sinVidas = false;
        this.matrizInicial = this.nivel1.getMatrizInicial();
    }

    //reinicia los personajes sin afectar lo guardado en el nivel ni el puntaje acumulado
    public void restart() {
        this.ghost[0] = new Ghost(20, 20, 2, 9, 11);
        this.ghost[1] = new Ghost(20, 20, 2, 10, 11);
        this.ghost[2] = new Ghost(20, 20, 2, 8, 11);
        this.ghost[3] = new Ghost(20, 20, 2, 11, 11);
        this.x = 20;
        this.y = 20;
        this.dx = 0;
        this.dy = 0;
        this.direccion = 2;
        this.secuencia = 0;
    }

    public void restartOnlyGhost(int i) {
        switch (i) {
            case 0:
                this.ghost[0] = new Ghost(20, 20, 2, 9, 11);
                break;
            case 1:
                this.ghost[1] = new Ghost(20, 20, 2, 10, 11);
                break;
            case 2:
                this.ghost[2] = new Ghost(20, 20, 2, 8, 11);
                break;
            case 3:
                this.ghost[3] = new Ghost(20, 20, 2, 11, 11);
                break;
            default:
                break;
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        System.out.println("salio sali");
        if (this.sinVidas == false) {
            super.paintComponent(g);
            nivel1.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            setBackground(Color.BLACK);
            g.drawImage(loadImage("Fred.png"), ghost[0].x, ghost[0].y, 20, 20, this);
            g.drawImage(loadImage("Fblue.png"), ghost[1].x, ghost[1].y, 20, 20, this);
            g.drawImage(loadImage("Forange.png"), ghost[2].x, ghost[2].y, 20, 20, this);
            g.drawImage(loadImage("Fpink.png"), ghost[3].x, ghost[3].y, 20, 20, this);
            this.dibujarPuntajes(g2d);
            this.dibujarScore(g2d);
            this.pintarPacman(g);
        } else {
            var++;
            System.out.println(var);
            System.out.println("si reinicia");
            Graphics2D g2d = (Graphics2D) g;
            nivel1.setLevel1(matrizInicial);
            nivel1.paintComponent(g);
            this.LetreroGameOver(g2d);
        }

        g.setColor(Color.yellow);
        for (int i = 0; i < 4; i++) {
            if (ghost[i].isVulnerable()) {
                g.drawOval(ghost[i].x - 1, ghost[i].y - 1, 5, 5);
            }
        }
    }

    public void pintarPacman(Graphics g) {

        Image pac = loadImage("pac.png");
        Image pac1 = loadImage("pacInv.png");
        x += dx;
        y += dy;
        if ((x + 10 < 360 || x + 10 > 0) && y + 10 != 200) {
            colisionParedes();
        }
        if ((x + 10 > 360 || x + 10 < 0) && y + 10 == 200) {
            tunel();
        }
        //Derecha
        if (direccion == 0) {

            g.drawImage(pac, 10 + x, 10 + y, 30 + x, 30 + y, 280, 0 + (secuencia * 40), 320, 40 + (secuencia * 40), this);
        }
        //Izquierda
        if (direccion == 1) {
            g.drawImage(pac, 10 + x, 10 + y, 30 + x, 30 + y, 320, 40 + (secuencia * 40), 280, 0 + (secuencia * 40), this);
        }
        //Abajo                                                           
        if (direccion == 2) {
            g.drawImage(pac1, 10 + x, 10 + y, 30 + x, 30 + y, 0 + (secuencia * 40), 280, 40 + (secuencia * 40), 320, this);
        }

        //Arriba
        if (direccion == 3) {
            g.drawImage(pac1, 10 + x, 10 + y, 30 + x, 30 + y, 40 + (secuencia * 40), 320, 0 + (secuencia * 40), 280, this);
        }
    }

    public Image loadImage(String imageName) {
        ImageIcon ii = new ImageIcon(imageName);
        Image image = ii.getImage();
        return image;
    }

    public void checkCollision() throws IOException {
        int i = 5, j = 5;
        for (int k = 0; k < 4; k++) {
            //up
            if (this.ghost[k].getDirection() == 0) {
                if (this.ghost[k].x % 20 == 0 && this.ghost[k].y % 20 == 0) {
                    if (this.nivel1.getLevel1()[(this.ghost[k].y / 20) - 1][this.ghost[k].x / 20] == 5
                            || this.nivel1.getLevel1()[(this.ghost[k].y / 20) - 1][this.ghost[k].x / 20] == 7) {

                        try {
                            Thread.sleep(25);

                        } catch (InterruptedException ex) {
                            Logger.getLogger(Play.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        if (this.nivel1.getLevel1()[(this.ghost[k].y / 20) - 1][(this.ghost[k].x / 20) + 1] != 0) {
                            i = (int) (Math.random() * 3);
                        }
                        if (this.nivel1.getLevel1()[(this.ghost[k].y / 20) - 1][(this.ghost[k].x / 20) - 1] != 0) {
                            i = (int) (Math.random() * 3);
                        }
                        this.ghost[k].changeDirection(i);
                        checkCollision();

                    }
                }
            }
            //down
            if (this.ghost[k].getDirection() == 1) {
                if (this.ghost[k].x % 20 == 0 && this.ghost[k].y % 20 == 0) {
                    if (this.nivel1.getLevel1()[(this.ghost[k].y / 20) + 1][this.ghost[k].x / 20] == 7
                            || this.nivel1.getLevel1()[(this.ghost[k].y / 20) + 1][this.ghost[k].x / 20] == 5) {

                        try {
                            Thread.sleep(25);

                        } catch (InterruptedException ex) {
                            Logger.getLogger(Play.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        if (this.nivel1.getLevel1()[(this.ghost[k].y / 20) + 1][(this.ghost[k].x / 20) + 1] != 0) {
                            i = (int) (Math.random() * 3);
                        }
                        if (this.nivel1.getLevel1()[(this.ghost[k].y / 20) + 1][(this.ghost[k].x / 20) - 1] != 0) {
                            i = (int) (Math.random() * 3);
                        }
                        this.ghost[k].changeDirection(i);
                        checkCollision();
                    }
                }
            }
            //right
            if (this.ghost[k].getDirection() == 2) {
                if (this.ghost[k].x % 20 == 0 && this.ghost[k].y % 20 == 0) {
                    if (this.nivel1.getLevel1()[this.ghost[k].y / 20][(this.ghost[k].x / 20) + 1] == 3
                            || this.nivel1.getLevel1()[this.ghost[k].y / 20][(this.ghost[k].x / 20) + 1] == 1
                            || this.nivel1.getLevel1()[this.ghost[k].y / 20][(this.ghost[k].x / 20) + 1] == 5) {

                        try {
                            Thread.sleep(25);

                        } catch (InterruptedException ex) {
                            Logger.getLogger(Play.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        if (this.nivel1.getLevel1()[(this.ghost[k].y / 20) - 1][(this.ghost[k].x / 20) + 1] != 0) {
                            i = (int) (Math.random() * 3);
                        }
                        if (this.nivel1.getLevel1()[(this.ghost[k].y / 20) + 1][(this.ghost[k].x / 20) + 1] != 0) {
                            i = (int) (Math.random() * 3);
                        }
                        this.ghost[k].changeDirection(i);
                        checkCollision();
                    }
                }
            }
            //left
            if (this.ghost[k].getDirection() == 3) {
                if (this.ghost[k].x % 20 == 0 && this.ghost[k].y % 20 == 0) {
                    if (this.nivel1.getLevel1()[this.ghost[k].y / 20][(this.ghost[k].x / 20) - 1] == 1
                            || this.nivel1.getLevel1()[this.ghost[k].y / 20][(this.ghost[k].x / 20) - 1] == 3
                            || this.nivel1.getLevel1()[this.ghost[k].y / 20][(this.ghost[k].x / 20) - 1] == 5) {

                        try {
                            Thread.sleep(25);

                        } catch (InterruptedException ex) {
                            Logger.getLogger(Play.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        if (this.nivel1.getLevel1()[(this.ghost[k].y / 20) - 1][(this.ghost[k].x / 20) - 1] != 0) {
                            i = (int) (Math.random() * 3);
                        }
                        if (this.nivel1.getLevel1()[(this.ghost[k].y / 20) + 1][(this.ghost[k].x / 20) - 1] != 0) {
                            i = (int) (Math.random() * 3);
                        }
                        this.ghost[k].changeDirection(i);
                        checkCollision();
                    }
                }
            }
//--------- Colision con los puntos, llamado al metodo para aumentar puntaje y cambio del mapa para que desaparezcan los puntos
            if (x + 10 < 360 && x > 0 && y + 10 != 200) {
                if (this.nivel1.getLevel1()[(this.y + 10) / 20][(this.x + 10) / 20] == 9) {
                    try {
                        Thread.sleep(25);

                    } catch (InterruptedException ex) {
                        Logger.getLogger(Play.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    int[][] aux = this.nivel1.getLevel1();
                    aux[(this.y + 10) / 20][(this.x + 10) / 20] = 0;
                    puntaje(1);
                    nivel1.setMatriz(aux);
                }
                if (this.nivel1.getLevel1()[(this.y + 10) / 20][(this.x + 10) / 20] == 10) {

                    try {
                        Thread.sleep(25);

                    } catch (InterruptedException ex) {
                        Logger.getLogger(Play.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    int[][] aux = this.nivel1.getLevel1();
                    aux[(this.y + 10) / 20][(this.x + 10) / 20] = 0;
                    nivel1.setMatriz(aux);
                    for (int l = 0; l < 4; l++) {
                        ghost[l].setVulnerable(true);
                    }

                }
            }
            //Dying
            if (pacmanrect().intersects(ghost[k].getBounds()) && ghost[k].isVulnerable() == false) {
                try {
                    Thread.sleep(25);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Play.class.getName()).log(Level.SEVERE, null, ex);
                }
                this.muere();
            }
            if (pacmanrect().intersects(ghost[k].getBounds()) && ghost[k].isVulnerable() == true) {
                try {
                    Thread.sleep(25);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Play.class.getName()).log(Level.SEVERE, null, ex);
                }
                puntaje(10);
                restartOnlyGhost(k);
            }
        }
    }

    public void reiniciar() {
        this.sinVidas = false;
        this.puntosTotales = 0;
        this.nameUser = "";
        this.pacmanlives = 3;
        this.restart();
    }

    public void puntaje(int punt) throws IOException {
        puntosTotales += punt;
    }

    public void GameOver() throws IOException {
        int a = 0;
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 18; j++) {
                if (this.nivel1.getLevel1()[i][j] == 9) {
                    a++;
                }
            }
        }
        if (a == 0) {
            while (nameUser.equals("")) {
                nameUser = JOptionPane.showInputDialog(nivel1, "Ingrese su nombre");
                puntaje.escribirPuntaje(puntosTotales, nameUser);
            }

        }
    }

    private void muere() throws IOException {//murio
        if (pacmanlives == 0) {
            this.sinVidas = true;
            while (nameUser.equals("")) {
                nameUser = JOptionPane.showInputDialog(nivel1, "Ingrese su nombre");
            }
            puntaje.escribirPuntaje(puntosTotales, nameUser);

        } else {
            pacmanlives--;
            this.restart();
        }
    }

    private void LetreroGameOver(Graphics2D g2d) {
        String s = "Has Perdido";
        String v = "Presiona (y) si deseas reiniciar el juego";
        Font medium = new Font("Helvetica", Font.BOLD, 30);
        Font small = new Font("Helvetica", Font.BOLD, 15);
        g2d.setColor(Color.RED);
        g2d.setFont(medium);
        g2d.drawString(s, 100, 190);
        g2d.setColor(Color.WHITE);
        g2d.setFont(small);
        g2d.drawString(v, 50, 215);
    }

    private void dibujarScore(Graphics2D g) {
        String s, v;
        g.setFont(letra);
        g.setColor(new Color(96, 128, 255));
        s = "Score: " + this.puntosTotales;
        g.drawString(s, 280, 420);
        v = "Vidas: ";
        g.drawString(v, 10, 420);
        for (int i = 1; i <= this.pacmanlives; i++) {
            g.drawImage(loadImage("vida.png"), i * 20 + 40, 405, 20, 20, null);
        }
    }

    private void dibujarPuntajes(Graphics2D g) {
        ArrayList<Integer> score = puntaje.getScore();
        ArrayList<String> names = puntaje.getNames();
        puntaje.ordenarPuntajes();
        g.setFont(letra);
        g.setColor(Color.MAGENTA);
        String v = "Best Scores";
        g.drawString(v, 375, 15);
        g.setFont(letra);
        g.setColor(new Color(96, 128, 255));
        for (int i = 1; i < score.size(); i++) {
            String s = score.get(i - 1).toString();
            String t = names.get(i - 1);
            g.drawString(s, 375, i * 15 + 20);
            g.drawString(t, 400, i * 15 + 20);
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        //Cuando pacman ha comido todas las galletas
        try {
            GameOver();
        } catch (IOException ex) {
            Logger.getLogger(Play.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Cuenta el tiempo de vulnerabilidad de los fantasmas
        if (ghost[0].isVulnerable() || ghost[1].isVulnerable() || ghost[2].isVulnerable() || ghost[3].isVulnerable()) {
            c++;
            if (c == 40) {
                c = 0;
                v++;
            }
        }
        for (int d = 0; d < 4; d++) {
            if (v == 5) {
                ghost[d].setVulnerable(false);
            }
            if (v == 5 && d == 3) {
                v = 0;
            }
            //mueves los fantasmas
            ghost[d].move();
        }
        try {
            checkCollision();
        } catch (IOException ex) {
            Logger.getLogger(Play.class.getName()).log(Level.SEVERE, null, ex);
        }
        noPaint++;
        if (noPaint == 3) {
            secuencia++;
            noPaint = 0;
            if (this.secuencia == 4) {
                this.secuencia = 0;
            }
        }
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();
            if (sinVidas == false) {
                if (key == KeyEvent.VK_RIGHT) {
                    dx = 2;
                    dy = 0;
                    direccion = 0;
                }

                if (key == KeyEvent.VK_LEFT) {
                    dx = -2;
                    dy = 0;
                    direccion = 1;
                }

                if (key == KeyEvent.VK_DOWN) {
                    dy = 2;
                    dx = 0;
                    direccion = 2;
                }

                if (key == KeyEvent.VK_UP) {
                    dy = -2;
                    dx = 0;
                    direccion = 3;
                }
            } else {
                if (key == 'y' || key == 'Y') {
                    reiniciar();
                }
            }
        }
    }

    public int getPuntosTotales() {
        return puntosTotales;
    }

    public void setPuntosTotales(int puntosTotales) {
        this.puntosTotales = puntosTotales;
    }

    public Rectangle pacmanrect() {
        return new Rectangle(10 + x, 10 + y, 20, 20);
    }

    public void colisionParedes() {
        System.out.println("Inicio");
        for (int r = 0; r < 20; r++) {
            for (int ñ = 0; ñ < 18; ñ++) {
                int detener = 0;
                //Verificar que no se un espacio en negro o una galleta
                int verificar = this.nivel1.getPosLevel1(r, ñ);
                //Cada imagen tiene un caso en especial de rectangulo son 7 ya que el 0 y el 9 no aplican para colision
                if (this.nivel1.getPosLevel1(r, ñ) == 1) {
                    if (pacmanrect().intersects(rectangulosMuro(r, 0, ñ, 0, 10, 20))) {
                        detener = 1;
                        if (direccion == 1) {
                            x += 2;
                        }
                        if (direccion == 0) {
                            x -= 2;
                        }
                    }
                } else if (this.nivel1.getPosLevel1(r, ñ) == 2) {
                    if (pacmanrect().intersects(rectangulosMuro(r, 0, ñ, 0, 10, 10))) {
                        detener = 1;
                        //abajo
                        if (direccion == 2) {
                            x -= 2;
                        }
                        //arriba o izquierda
                        if (direccion == 3 || direccion == 1) {
                            x += 2;
                            y += 2;
                        }
                        //derecha
                        if (direccion == 0) {
                            y -= 2;
                        }
                    }

                } else if (this.nivel1.getPosLevel1(r, ñ) == 3) {
                    if (pacmanrect().intersects(rectangulosMuro(r, 10, ñ, 0, 10, 20))) {
                        detener = 1;
                        //derecha
                        if (direccion == 0) {
                            x -= 2;
                        }
                        //izquierda
                        if (direccion == 1) {
                            x += 2;
                        }
                    }
                } else if (this.nivel1.getPosLevel1(r, ñ) == 4) {
                    if (pacmanrect().intersects(rectangulosMuro(r, 10, ñ, 0, 10, 10))) {
                        detener = 1;
                        //arriba o derecha
                        if (direccion == 3 || direccion == 0) {
                            x -= 2;
                            y += 2;
                        }
                        //abajo               
                        if (direccion == 2) {
                            y -= 2;
                        }
                        //izquierda
                        if (direccion == 1) {
                            x += 2;
                        }
                    }
                } else if (this.nivel1.getPosLevel1(r, ñ) == 5) {
                    if (pacmanrect().intersects(rectangulosMuro(r, 0, ñ, 0, 20, 10))) {
                        detener = 1;
                        //abajo
                        if (direccion == 2) {
                            y -= 2;
                        }
                        //arriba
                        if (direccion == 3) {
                            y += 2;
                        }
                    }
                } else if (this.nivel1.getPosLevel1(r, ñ) == 6) {
                    if (pacmanrect().intersects(rectangulosMuro(r, 0, ñ, 10, 10, 10))) {
                        detener = 1;
                        //abajo o izquierda
                        if (direccion == 2 || direccion == 1) {
                            y -= 2;
                            x += 2;
                        }
                        //derecha
                        if (direccion == 2 || direccion == 1) {
                            x -= 2;
                        }
                        //arriba
                        if (direccion == 2 || direccion == 1) {
                            y += 2;
                        }
                    }
                } else if (this.nivel1.getPosLevel1(r, ñ) == 7) {
                    if (pacmanrect().intersects(rectangulosMuro(r, 0, ñ, 10, 20, 10))) {
                        detener = 1;
                        //abajo
                        if (direccion == 2) {
                            y -= 2;
                        }
                        //arriba
                        if (direccion == 3) {
                            y += 2;
                        }
                    }
                } else if (this.nivel1.getPosLevel1(r, ñ) == 8) {
                    if (pacmanrect().intersects(rectangulosMuro(r, 10, ñ, 10, 10, 10))) {
                        detener = 1;
                        //abajo o derecha
                        if (direccion == 0 || direccion == 2) {
                            x -= 2;
                            y -= 2;
                        }
                        //izquierda
                        if (direccion == 1 || direccion == 2) {
                            x += 2;
                        }
                        //arriba
                        if (direccion == 3 || direccion == 2) {
                            y += 2;
                        }
                    }

                }
                if (verificar != 0 && verificar != 9 && detener == 1) {
                    //Deterner movimiento, pero no la animacion
                    dx = 0;
                    dy = 0;
                }
            }
        }
    }

    public void tunel() {
        if (x < 10 && y + 10 == 200) {
            x = 350;
        }
        if (x + 10 > 360 && y + 10 == 200) {
            x = 0;
        }

    }

    public Rectangle rectangulosMuro(int r, int w, int ñ, int h, int alto, int ancho) {
        return new Rectangle((ñ * 20) + w, (r * 20) + h, alto, ancho);
    }

}

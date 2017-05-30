/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication15;

import java.util.Random;

/**
 *
 * @author Usuario
 */
public class Ghost extends Characters {

    private final int vel;
    private Random random;
    private int direction;
    private boolean vulnerable = false;

    public Ghost(int width, int height, int vel, int posx, int posy) {
        super(width, height, posx, posy);
        this.random = new Random();
        this.direction = random.nextInt(4);
        this.vel = vel;
    }

    public void changeDirection(int n) {
        int l = this.direction;
        while (l == this.direction || n == this.direction) {
            this.direction = random.nextInt(4);
        }
    }

    public void move() {
        //Up
        if (direction == 0) {
            y -= vel;
        }
        //Down
        if (direction == 1) {
            y += vel;
        }
        //Right
        if (direction == 2) {
            x += vel;
        }
        //Left
        if (direction == 3) {
            x -= vel;
        }
    }

    public int getDirection() {
        return direction;
    }

    public boolean isVulnerable() {
        return vulnerable;
    }

    public void setVulnerable(boolean vulnerable) {
        this.vulnerable = vulnerable;
    }
}

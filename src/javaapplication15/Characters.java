/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication15;

import java.awt.*;

/**
 *
 * @author Usuario
 */
public class Characters {

    protected int x, y;
    protected int width, height;
    protected int posx, posy;

    public Characters(int width, int height, int posx, int posy) {
        this.x = posx * 20;
        this.y = posy * 20;
        this.width = width;
        this.height = height;
        this.posx = posx;
        this.posy = posy;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getPosx() {
        return posx;
    }

    public int getPosy() {
        return posy;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width + 5, height + 5);
    }
}

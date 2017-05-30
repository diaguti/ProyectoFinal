/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PacmUN;

import java.awt.*;
import javax.swing.JPanel;

/**
 *
 * @author Usuario
 */
public abstract class NivelA extends JPanel {

    protected int[][] matriz;

    public NivelA() {
    }

    protected abstract Image loadImage(String imageName);

    public void setMatriz(int[][] matriz) {
        this.matriz = matriz;
    }

}

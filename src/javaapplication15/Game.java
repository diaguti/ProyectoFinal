/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication15;

import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JFrame;

/**
 *
 * @author Usuario
 */
public class Game extends JFrame {

    public Game() throws FileNotFoundException, IOException {
        add(new Play());
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        Game frame = new Game();
        frame.setTitle("TestPaintComponent");
        frame.setSize(485, 465);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

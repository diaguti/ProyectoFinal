/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PacmUN;

import java.io.*;
import java.util.*;

/**
 *
 * @author intel
 */
public class Puntaje {

    private final Scanner input;
    private final FileWriter TextOut;
    private ArrayList<String> names = new ArrayList<String>();
    private ArrayList<Integer> score = new ArrayList<Integer>();

    public Puntaje() throws IOException {
        this.input = new Scanner(new File("puntaje.txt"));
        File TextFile = new File("puntaje.txt");
        this.TextOut = new FileWriter(TextFile, true);
    }

    public void escribirPuntaje(int puntaje, String nombre) throws IOException {
        TextOut.write(puntaje + " " + nombre + "\r\n");
        TextOut.close();
    }

    public void leerPuntaje() {
        while (input.hasNext()) {
            if (input.hasNextInt() == false) {
                String name = input.next();
                names.add(name);
            } else if (input.hasNextInt() == true) {
                int sc = input.nextInt();
                score.add(sc);
            }
        }
    }

    public void ordenarPuntajes() {
        for (int i = 0; i < (score.size()) - 1; i++) {
            for (int j = i + 1; j < score.size(); j++) {
                if (score.get(i) < score.get(j)) {
                    int aux = score.get(i);
                    score.set(i, score.get(j));
                    score.set(j, aux);
                    String auxName = names.get(i);
                    names.set(i, names.get(j));
                    names.set(j, auxName);
                }
            }
        }
        for (int i = 0; i < score.size(); i++) {
            System.out.println(score.get(i) + " " + names.get(i));
        }
    }

    public ArrayList<String> getNames() {
        return names;
    }

    public void setNames(ArrayList<String> names) {
        this.names = names;
    }

    public ArrayList<Integer> getScore() {
        return score;
    }

    public void setScore(ArrayList<Integer> score) {
        this.score = score;
    }

}

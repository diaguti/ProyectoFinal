/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication15;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author Diego
 */
public class Cliente {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Socket cliente = new Socket("localhost", 1234);

        ObjectOutputStream out = new ObjectOutputStream(cliente.getOutputStream());

        FileInputStream file = new FileInputStream("puntaje.txt");

        byte[] buf = new byte[4096];

        while (true) {
            int len = file.read(buf);
            if (len == -1) {
                break;
            }
            out.write(buf, 0, len);
        }

    }

}

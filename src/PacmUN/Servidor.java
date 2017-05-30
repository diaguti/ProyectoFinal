/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PacmUN;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Diego
 */
public class Servidor {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        ServerSocket servidor = new ServerSocket(1234);
        Socket sv = servidor.accept();
        ObjectInputStream out = new ObjectInputStream(sv.getInputStream());
        FileOutputStream file = new FileOutputStream("puntaje.txt");
        byte[] buf = new byte[4096];
        while (true) {
            int len = out.read(buf);
            if (len == -1) {
                break;
            }
            file.write(buf, 0, len);
        }
    }
}

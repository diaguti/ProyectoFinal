/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PacmUN;

/**
 *
 * @author Diego
 */
import java.net.*;
import java.io.*;

public class Servidor1 {

    private ServerSocket servidor = null;

    public Servidor1() throws IOException {
        servidor = new ServerSocket(8000);

    }

    public void iniciarServidor() {

        while (true) {
            try {
                Socket cliente = servidor.accept();
                DataInputStream dis = new DataInputStream(cliente.getInputStream());
                String nombreArchivo = dis.readUTF().toString();
                int tam = dis.readInt();
                System.out.println("Recibiendo archivo " + nombreArchivo);
                FileOutputStream fos = new FileOutputStream(nombreArchivo);
                BufferedOutputStream out = new BufferedOutputStream(fos);
                BufferedInputStream in = new BufferedInputStream(cliente.getInputStream());
                byte[] buffer = new byte[tam];
                for (int i = 0; i < buffer.length; i++) {
                    buffer[i] = (byte) in.read();
                }
                out.write(buffer);
                out.flush();
                in.close();
                out.close();
                cliente.close();
                System.out.println("Archivo Recibido " + nombreArchivo);
            } catch (Exception e) {
                System.out.println("Recibir: " + e.toString());
            }
        }
    }

    public static void main(String args[]) throws IOException {
        new Servidor1().iniciarServidor();
    }
}

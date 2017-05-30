/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PacmUN;

import java.net.*;
import java.io.*;

public class Cliente1 {

    private String nombreArchivo = "";

    public Cliente1(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public void enviarArchivo() {

        try {

            Socket socket = new Socket("localhost", 8000);

            File archivo = new File(nombreArchivo);
            int tamañoArchivo = (int) archivo.length();
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

            System.out.println("Enviando Archivo: " + archivo.getName());

            dos.writeUTF(archivo.getName());
            dos.writeInt(tamañoArchivo);

            FileInputStream fis = new FileInputStream(nombreArchivo);
            BufferedInputStream bis = new BufferedInputStream(fis);
            BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());

            byte[] buffer = new byte[tamañoArchivo];

            bis.read(buffer);

            for (int i = 0; i < buffer.length; i++) {
                bos.write(buffer[i]);
            }

            System.out.println("Archivo Enviado: " + archivo.getName());

            bis.close();
            bos.close();
            socket.close();

        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }

    public static void main(String args[]) {
        Cliente1 archivo = new Cliente1("puntaje.txt");
        archivo.enviarArchivo();
    }

}
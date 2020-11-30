/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tda;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 *
 * @author Noelia Intriago
 */
public class Video {
    private String url;
    
    public Video(String url){
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Video{" + "url=" + url + '}';
    }
    
    public static CircularDoublyLinkedList<String> leerArchivo(String nombre){
        CircularDoublyLinkedList<String> videos = new CircularDoublyLinkedList<>();
        try {
            java.util.List<String> lineas = Files.readAllLines(Paths.get(nombre));
            for (String linea : lineas) {
                //Video v = new Video(linea);
                videos.addLast("/resources/videos/"+linea);
            }
        } catch (IOException e) {
            System.err.println("Archivo no encontrado\n"+e);
        }
        return videos;
    }
    
    public static void addVideo(String nombre,String linea){
        FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            fichero = new FileWriter(nombre,true);
            pw = new PrintWriter(fichero);        
            pw.println(linea);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fichero)
                    fichero.close();
            }catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}

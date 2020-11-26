/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package media;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Scanner;
import javafx.application.Application;
import tda.CircularDoublyLinkedList;

/**
 *
 * @author Viviana Vera
 */
public class MediaView {
    
    URL url;
        
    public static CircularDoublyLinkedList leerArchivos(){
        CircularDoublyLinkedList<String> videos = new CircularDoublyLinkedList<>();        
        try{                                    
            Scanner sc = new Scanner(new File("\\resources\\videos.txt"));
            while(sc.hasNext()){
                String linea = sc.nextLine();
                System.out.println(linea);
                videos.addLast(linea);
            }
            sc.close();
            
        }catch(FileNotFoundException e){                        
            e.printStackTrace();            
            System.out.println(e.getMessage());
        }
        return videos;
    }
    //implementar writer   
    
    public static void main(String[] args) throws FileNotFoundException {
        CircularDoublyLinkedList<String> videos = leerArchivos();        
    }
}

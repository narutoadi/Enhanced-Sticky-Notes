/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stickyserver;

import java.io.*;
import java.net.*;
/**
 *
 * @author pc
 */
public class StickyServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try{
            ServerSocket server=new ServerSocket(2244);
            while(true){
                Socket client=server.accept();
                new ClientThread(client);
            }
        }catch(Exception ex){
            
        }
    }
    
}

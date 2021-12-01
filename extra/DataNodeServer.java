/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataNodeServer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author dalton
 */
public class DataNodeServer {
    
    private static Socket dataNodeSocket;
    private static DataInputStream din;
    private static DataOutputStream dop;
    
    public static void main(String[] args) {
        
   
        try{
            System.out.println("Data Node Server Comming online getting connection..");
            dataNodeSocket = new Socket("localHost", 1432);
            din = new DataInputStream(dataNodeSocket.getInputStream());
            dop = new DataOutputStream(dataNodeSocket.getOutputStream());
            System.out.println("going into open state");
            openState();
            
            
            
        }catch(Exception e) {
            e.printStackTrace();
            System.out.println("$ Something went wrong sorry about that $ ");
            System.exit(1);
        }
    }
    
    public static void openState() throws IOException {
        String message = din.readUTF();
        System.out.println(message);
    }
}

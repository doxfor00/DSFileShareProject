import startingofdbfinialcode.UserdataHandler;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dalton
 */
public class MasterServerMain {
    
    
    public static UserdataHandler userInformationHandler;
    private static ArrayList<Thread> dataNodeThreads;
    private static ArrayList<Thread> clientThreadList;
    
    
    public static void main(String[] args){
    
        try{
            
            // Get information for user log in
            MasterServerMain.userInformationHandler = new UserdataHandler();
            dataNodeThreads = new ArrayList<Thread>();
            
            System.out.println("Length of hashMap: "+ userInformationHandler.getUserMap().size());
            
            // Prints the userMap
            printLine();
            printHashMap(userInformationHandler.getUserMap());
            printLine();
            System.out.println("Uploading the server");
            
            
            // 
            ServerSocket ssc = new ServerSocket(1431);
            System.out.println("Server started...");
            
            // Array to maintain threads started for the client
            clientThreadList = new ArrayList<Thread>();
            Thread lastThread;
            Socket socket;
            
            
            //#Testing the node listeer for the backend part of the code#
            Thread backendThread = new Thread(new NodeListenThread());
            backendThread.start();
            // testing
            
            
            
            // Listen for client connection and when a client does connection the assied to a thread
            while(true) {
                
                socket = ssc.accept();
                lastThread = new Thread(new MasterServerClientThread(userInformationHandler , socket));
                clientThreadList.add(lastThread);
                lastThread.start();
                printIfActive();
                
            }
            
            
            
            
        }catch(Exception e) {
            e.printStackTrace();
        }
    
    
    }
    
    
    // for using reason only
    public static void printHashMap(HashMap<String,String> userMap){
        String user, hash;
        int elementCount = 0;
        for(Map.Entry<String , String> entry : userMap.entrySet()) {
            user = entry.getKey();
            hash = entry.getValue();
            System.out.println( "Element Number " + elementCount + ""+" -----> " + user + ","+ hash);
        }
        
    }
    
    public static void printLine() {
        System.out.println("++++++++++++++++++++++++++");
    }
    
    
    // Testing checks in the programming to check if the thread is active 
    
    public static void printIfActive() {
        for(Thread thread: clientThreadList) {
            if(thread != null) {
                System.out.println("Thread is: " + thread.isAlive());
            }
        }
    }
    
    
    public static class NodeListenThread implements Runnable {
        
        private ServerSocket dataNodeSS;
        
        
        public NodeListenThread() {
            try {
                this.dataNodeSS = new ServerSocket(1432);
            }catch(Exception e ){
                e.printStackTrace();
            }
        }
        
        
        public void run() {
            Socket tempSocket;
            Thread lastThread;
            System.out.println("Listening on the backend of the server 1432 port...");
            while(true) {
               
                
                try {
                    
                   tempSocket = dataNodeSS.accept();
                   System.out.println("got connection to a dataNode");
                   lastThread = new Thread(new DataNodeHandlerThread(tempSocket));
                   dataNodeThreads.add(lastThread);
                   lastThread.start();
                   
                }catch(Exception e) {
                   e.printStackTrace();
                }
            }
            
        }
    }
    
        
    
   
}
 
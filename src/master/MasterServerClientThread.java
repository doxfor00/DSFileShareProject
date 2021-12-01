


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import startingofdbfinialcode.UserdataHandler;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dalton
 */
public class MasterServerClientThread implements Runnable{

    
         
        
        // **Checking if the user has be log in**
        private boolean logIn;
        // **Checking if the user has be log in**
        
        
        //Need to share main information with the client connection thread 
        private UserdataHandler userInformationHandler;
        //Need to share main information with the client connection thread
        
        
        // The information for the connection with the client
        private DataInputStream din;
        private DataOutputStream dop;
        private Socket clientSocket;
        
        public MasterServerClientThread(UserdataHandler userInformationHandler, Socket clientSocket) {
            
            // Master server userinformation not the best code things I done but something 
            this.userInformationHandler = userInformationHandler;
            // here
            
            this.clientSocket = clientSocket;
            this.logIn = false;
            
            try{
                
               this.din = new DataInputStream(clientSocket.getInputStream());
               this.dop = new DataOutputStream(clientSocket.getOutputStream());
               
            }catch(Exception e) {
                
            }
            
        }
        
        public void run() {
            /*
            Note: The nature of the thread goes from checking the session in then to a mulp command 
            can execute to the server which is the using the openState to say that it open to different 
            state like inserting a file getting back file on hand logs and other information 
            */
            
            
            String usernameAndPassword = "";
           
             try {
                sessionCheck();
        
                
                
                while(true) {
                    openState();
                }
                
             }catch(Exception e) {
                e.printStackTrace();
             }
                
            
        }
        
        public DataInputStream getDin() {
            return this.din;
        }
        public DataOutputStream getDop() {
            return this.dop;
        }
        public Socket getClientSocket(){
            return this.clientSocket;
        }
        
        public void sessionCheck() {
           
            try{
                
                
                String usernameAndPassword = "";
                String tempUser,tempHash, realHash;
                String[] tempListBreakString;
                System.out.println("Starting session checking");
                
                
                while(!logIn ) {
                    try{
                        usernameAndPassword = din.readUTF();
                        System.out.println(usernameAndPassword);
                        tempListBreakString = usernameAndPassword.split(",");
                       
                        
                        
                        tempUser = tempListBreakString[0];
                        tempHash = tempListBreakString[1];
                        
                        // Line right here is where an error in the code I not sure about the 
                        realHash = userInformationHandler.getUserMap().get(tempUser);
                        if((realHash != null)&&(tempHash.equals(realHash))) {
                            System.out.println("here");
                           
                            // sending 
                            this.dop.writeUTF("***ValidLogIn***");
                            this.logIn = true;
                        }else {
                            System.out.println("here");
                            
                            // if not 
                            this.dop.writeUTF("***NoneValidLogIn***");
                            
                        }
                        
                    }catch(Exception e) {
                        e.printStackTrace();
                    }
                }
                
            }catch(Exception e) {
                e.printStackTrace();
            }
        }
       
        
        // once a session is valid the session comes to 
        // an open state where it looks for request made my the client
        public void openState() throws IOException, Exception {
            
            
            // waiting for the client to make a request to the server
            String clientOpenMessage = din.readUTF();
            // waiting for the client to make a request ot the server
            
            
            
            System.out.println(clientOpenMessage);
            
            // cutting out information not part of the command
            String[] brokenCommand = clientOpenMessage.split("#");
            
            
            // check if the user is wanting to upload a file to the start
            if((brokenCommand.length == 3)&&(brokenCommand[0].equals("***UploadFile"))){
                receiveFile(brokenCommand[1]);
            }
            // check is it a no var command something to the degre
            else if(brokenCommand.length == 1) {
                
            }
            
            else{
                System.out.println("other command");
            }
            
            
            
            
        }
        
    // this is the main method wi get file from the network with the
    public void receiveFile(String fileName ) throws Exception {
        
        int bytes = 0;
        
        fileName = "master\\" + fileName;
        FileOutputStream fileOutputStream = new FileOutputStream(fileName);
        
        
        long size = din.readLong();
        byte[] buffer = new byte[4*1024];
        
        while ((size > 0) && (bytes = din.read(buffer, 0, (int)Math.min(buffer.length, size))) != -1) {
            fileOutputStream.write(buffer, 0 ,bytes);
            size -= bytes;
        }
        fileOutputStream.close();
    }
        
       
}

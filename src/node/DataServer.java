/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package node;
import java.time.*;
import java.time.format.*;
import java.io.*;
import java.net.*;
import javax.swing.filechooser.FileSystemView;

/**
 *
 * @author jimmy
 */
public class DataServer {
    
    public static void main(String[] args) throws IOException{
        
        ServerSocket ss = new ServerSocket(1432);
        String serverName = "Data1";
        int counter = 0;
        System.out.println("Data Server: " + serverName + " is now Running");
        while(true){
            try{
                Action a = new Action();
                Socket dataSocket = ss.accept();

                DataInputStream input = new DataInputStream(dataSocket.getInputStream());
                
                LocalDateTime date = LocalDateTime.now();
                DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                String time = date.format(format);
                String action = input.readUTF();
                String user = input.readUTF();
                String filename = input.readUTF();
                String root = FileSystemView.getFileSystemView().getHomeDirectory().getAbsolutePath();
                DataOutputStream output = new DataOutputStream(new FileOutputStream(root + File.separator + user + File.separator + filename));
                
                
                if(action.equals("list")) {
                    String[] list = a.retrieveList(root, user);
                    DataOutputStream listOut = new DataOutputStream(dataSocket.getOutputStream());
                    for(int i = 0; i < list.length; i++){
                        listOut.writeUTF(list[i]);
                    }
                } else if(action.equals("add")) {
                    try{
                        System.out.println("Checking for user Directory");
                        a.checkDirectory(root + File.separator + user);
                        System.out.println("Backing up file...");
                        a.writeFile(input, output);
                        System.out.println("File: " + filename + " has been backed up successfully.");
                        a.updateLog(root, user, filename,time , root + File.separator + user + File.separator + filename, filename, 0);
                        counter++;
                    } catch(Exception ex){
                        ex.printStackTrace();
                    }
                } else if(action.equals("delete")){
                    a.deleteFile(root, user, filename);
                    System.out.println("File: " + filename + " has been deleted.");
                    counter--;
                } 
                
                
                

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    
    
}



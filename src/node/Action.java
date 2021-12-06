/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package node;

import java.io.*;
import java.nio.file.*;
/**
 *
 * @author jimmy
 */
public class Action {
    
    void deleteFile(String root, String user, String filename) {
        
        Path path = Paths.get(root + File.separator + user + File.separator + filename);
        if(path.toFile().isFile()) {
            path.toFile().delete();
        }
        
    }
    
    String[] retrieveList(String root, String user) {
        Path paths = Paths.get(root + File.separator + user);
        String[] list = paths.toFile().list();
        return list;
    }
    
   void updateLog(String root, String user, String filename, String date, String path, String filesize, int access) throws IOException {
        FileWriter write = new FileWriter(new File(root + File.separator + "files" + File.separator + "system_catalog_management" + File.separator + "datafiles.txt"));
        String line = user + "::" + filename + "::"  + date + "::" + path + "::" + filesize +"::" + access;
        
    }
    
    void writeFile(DataInputStream input, DataOutputStream output) throws IOException{
        
        byte[] b = new byte[8*1024];
        int len;
        while((len = input.read(b))!= -1) {
            output.write(b,0,len);
        }
    }
    
    void sendFile(String filepath, DataOutputStream output) throws IOException{
        DataInputStream input = new DataInputStream(new FileInputStream(filepath));
        byte[] b = new byte[8*1024];
        int len;
        while((len = input.read(b))!= -1) {
            output.write(b, 0, len);
        }
    }
    
    void checkDirectory(String filepath) throws IOException{
        
        if(filepath == null) {
            System.out.println("Invalid file path");
            return;
        } else {
            File directory = new File(filepath);
            if(!directory.exists()) {
                directory.mkdir();
            }
        }
    }
    
}

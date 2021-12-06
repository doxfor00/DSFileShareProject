/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package node;

import java.io.*;
/**
 *
 * @author jimmy
 */
public class DatafilesEdit {
    
    private FileWriter write;
    private final String LOG = "datafiles.txt";
    
    public DatafilesEdit(String user, String file, String date, String path, String[] directories) throws IOException{
        write = new FileWriter(new File(LOG));
		String line = user + "::" + file + "::"  + date + "::" + path + "::";
		for (int i =0; i < directories.length; i ++) {
			line = line + directories[i] + ",";
		}
		write.write(line);
    }
    
    
    
}

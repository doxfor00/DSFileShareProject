package startingofdbfinialcode;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dalton
 */
public class UserdataHandler {
   
    
    private HashMap<String , String> userMap;
    
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        System.out.print("Enter username: ");
        String username = reader.next();
        System.out.print("Enter password: ");
        String passwordText = encryptString(reader.next());
        writeUser(username, passwordText);
    }
    
    
    public UserdataHandler() {
        this.userMap = loadUsersFromFile();
    }
    
    
    public static String encryptString(String valueToEncrypt) {
		StringBuffer output = new StringBuffer();

		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(valueToEncrypt.getBytes(StandardCharsets.UTF_8));


			for (int i = 0; i < hash.length; i++) {
				String hex = Integer.toHexString(0xff & hash[i]);

				if (hex.length() == 1)
					output.append('0');

				output.append(hex);
			}
		} catch(NoSuchAlgorithmException ex) {
			ex.printStackTrace();
		}

		return output.toString();
    }

    public static synchronized void writeUser(String username, String hash) {
        try{
            File userFile = new File("userInfo.txt");
            if(!userFile.exists()){
                userFile.createNewFile();
            }
		// this is the file being writen from and reading from for user handling
            BufferedWriter bw = new BufferedWriter(new FileWriter("accounts.txt", true));
            bw.write(username + ","+hash);
            bw.newLine();
            bw.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void printLine() {
        System.out.println("++++++++++++++++++++++++++");
    }
    public HashMap<String, String> loadUsersFromFile() {
        
        
        HashMap<String , String> userMap = new HashMap<String, String>();
        try{
            
            BufferedReader reader = new BufferedReader(new FileReader("accounts.txt"));
            String line;
            String[] list = new String[2];
            while((line = reader.readLine()) != null) {
                
                list = line.split(",");
                userMap.put(list[0], list[1]);
                
            }
            
            
            userMap.put(list[0], list[1]);
            
            
            reader.close();
            
            
            return userMap;
            
        }catch(Exception e) {
            e.printStackTrace(); 
            return null;
        }
        
        
    }
    
    
    // getter for the userMap 
    public HashMap<String, String> getUserMap() {
        return this.userMap;
    }
}

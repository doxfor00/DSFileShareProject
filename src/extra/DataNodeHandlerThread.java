
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Queue;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dalton
 */
public class DataNodeHandlerThread implements Runnable {
    private Socket nodeConnection;
    private DataInputStream din;
    private DataOutputStream dop;
    
    
    private Queue<NodeCommand> threadTaskPool;
   
    
    
    public DataNodeHandlerThread(Socket nodeConnection) {
        this.nodeConnection = nodeConnection;
        
        try{
            this.din = new DataInputStream(nodeConnection.getInputStream());
            this.dop = new DataOutputStream(nodeConnection.getOutputStream());
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public void setThreadTaskPool(Queue<NodeCommand> threadTaskPool) {
        this.threadTaskPool = threadTaskPool;
    }
    
    public Queue<NodeCommand> getThreadTaskPool() {
        return this.threadTaskPool;
    }
    
    public void run() {
        
        try {
            dop.writeUTF("get the message dam ass");
            
        }catch(Exception e ){
            e.printStackTrace();
        }
        /*
        while(true) {
            if(threadTaskPool.isEmpty()) {
                
            }else {
                
            }
        }
        */
    }
    
}

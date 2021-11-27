
import java.net.Socket;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// probably not going to be used
public class DataNodeConnection {
    
    
    private int maker; 
    private Socket nodeConnection;
    
    
    
    public DataNodeConnection(Socket nodeConnection) {
        this.nodeConnection = nodeConnection;
    }
    public void setNodeConnection(Socket nodeConenction) {
        this.nodeConnection = nodeConnection;
    }
    
    public Socket getNodeConnection() {
        return this.nodeConnection;
    }
    
    public void setMaker(int maker) {
        this.maker = maker;
    }
    
    public int getMaker() {
        return this.maker;
    }
    
    
}

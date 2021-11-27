/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dalton
 */
public class NodeCommand {
    private NodeHanderCommand state;
    private String fileName;
    private String username;
    
    
    public NodeCommand() {
        
    }
    public NodeCommand(NodeHanderCommand state) {
        this.state = state;
    }
    public NodeCommand(NodeHanderCommand state, String fileName) {
        this.state = state;
        this.fileName = fileName;
    }
    
    public NodeCommand(String username, NodeHanderCommand state) {
        this.state = state;
        this.username = username;
    }
    public void setState(NodeHanderCommand state) {
        this.state = state;
    }
    
    public NodeHanderCommand getState() {
        return this.state;
    }
    
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    
    public String getFileName() {
        return this.fileName;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getUsername() {
        return this.username;
    }
}

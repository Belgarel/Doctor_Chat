/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doctor_chat.common.connection;

import java.io.Serializable;

/**
 *
 * @author Home
 */
public class ClientMessage implements Serializable {
    protected String sender;
    
    public ClientMessage() {
        sender = "";
    }
    public ClientMessage(String sender) {
        sender = sender;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
    
    
    
}

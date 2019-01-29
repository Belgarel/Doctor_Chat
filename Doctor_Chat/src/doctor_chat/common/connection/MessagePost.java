/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doctor_chat.common.connection;

import doctor_chat.common.Message;

/**
 *
 * @author Home
 */
public class MessagePost extends ClientMessage {
    
    private Message message;

    public MessagePost(Message message) {
        this.message = message;
    }
    public Message getMessage() {
        return message;
    }
    public void setMessage(Message message) {
        this.message = message;
    }
    
}

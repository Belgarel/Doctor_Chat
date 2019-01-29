/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doctor_chat.common.connection;

import doctor_chat.common.Conversation;
import java.io.Serializable;

/**
 *
 * @author Home
 */
public class ConversationInvite extends ServerMessage implements Serializable {
    private Conversation conversation;
    
    public ConversationInvite(Conversation conversation) {
        super();
        this.conversation = conversation;
    }

    public Conversation getConversation() {
        return conversation;
    }
    public void setConversation(Conversation conversation) {
        this.conversation = conversation;
    }
}

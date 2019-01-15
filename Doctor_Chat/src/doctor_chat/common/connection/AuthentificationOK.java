/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doctor_chat.common.connection;

import doctor_chat.common.Conversation;
import doctor_chat.common.User;
import java.io.Serializable;
import java.util.HashSet;

/**
 *
 * @author Home
 */
public class AuthentificationOK extends ServerMessage implements Serializable {
    
    private User account;
    private HashSet<User> contacts;
    private HashSet<Conversation> conversation;

    public AuthentificationOK(User account, HashSet<User> contacts, HashSet<Conversation> conversation) {
        this.account = account;
        this.contacts = contacts;
        this.conversation = conversation;
    }

    public User getAccount() {
        return account;
    }
    public void setAccount(User account) {
        this.account = account;
    }
    public HashSet<User> getContacts() {
        return contacts;
    }
    public void setContacts(HashSet<User> contacts) {
        this.contacts = contacts;
    }
    public HashSet<Conversation> getConversation() {
        return conversation;
    }
    public void setConversation(HashSet<Conversation> conversation) {
        this.conversation = conversation;
    }
}

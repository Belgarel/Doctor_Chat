/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doctor_chat.common;

import java.awt.Color;
import java.io.Serializable;
import java.util.HashSet;

/**
 *
 * @author Home
 */
public class User implements Serializable{
    private long num;
    private String login;
    private Color color;
    private HashSet<Long> contactIds;
    private HashSet<Conversation> conversations;
    
    public User() {
        this.num = -1;
        this.login = "";
        this.color = Color.BLACK;
        this.contactIds = new HashSet<Long>();
        this.conversations = new HashSet<Conversation>();
    }
    
    public boolean addContact(long userId) {
        return this.contactIds.add(userId);
    }
    public boolean removeContact(long userId) {
        return this.contactIds.remove(userId);
    }
    public boolean isContact(long userId) {
        return this.contactIds.contains(userId);
    }
    public boolean addConversations(Conversation convo) {
        return this.conversations.add(convo);
    }
    public boolean removeConversation(Conversation convo) {
        return this.conversations.remove(convo);
    }
    public boolean isConversation(Conversation convo) {
        return this.conversations.contains(convo);
    }

    public long getNum() {
        return num;
    }
    public void setNum(long num) {
        this.num = num;
    }
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public Color getColor() {
        return color;
    }
    public void setColor(Color color) {
        this.color = color;
    }
    public HashSet<Long> getContacts() {
        return this.contactIds;
    }
    public void setContacts(HashSet<Long> contacts) {
        this.contactIds = contacts;
    }
    
    
}

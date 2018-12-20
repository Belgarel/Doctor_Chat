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
    private String password;
    private Color color;
    private HashSet<Long> contactIds;
    private HashSet<Long> conversationIds;
    
    public User() {
        this.num = -1;
        this.login = "";
        this.password = "";
        this.color = Color.BLACK;
        this.contactIds = new HashSet<Long>();
        this.conversationIds = new HashSet<Long>();
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
    public boolean addConversations(long convId) {
        return this.conversationIds.add(convId);
    }
    public boolean removeConversation(Long convId) {
        return this.conversationIds.remove(convId);
    }
    public boolean isConversation(Long convId) {
        return this.conversationIds.contains(convId);
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
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
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
    public HashSet<Long> getContactIds() {
        return contactIds;
    }
    public void setContactIds(HashSet<Long> contactIds) {
        this.contactIds = contactIds;
    }
    public HashSet<Long> getConversationIds() {
        return conversationIds;
    }
    public void setConversationIds(HashSet<Long> conversationIds) {
        this.conversationIds = conversationIds;
    }
    
    public String toString() {
        return "USER - id : " + num + " ; login : " + login + " ; color : " + color
                + " ; contacts : " + contactIds
                + " ; conversations : " + conversationIds;
    }
}

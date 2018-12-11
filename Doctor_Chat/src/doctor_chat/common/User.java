/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doctor_chat.common;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Home
 */
public class User implements Serializable{
    private long num;
    private String login;
    private String mdp;
    private Color color;
    private HashSet<Integer> contactIds;
    
    public User() {
        this.num = -1;
        this.login = "";
        this.mdp = "";
        this.color = Color.BLACK;
        this.contactIds = new HashSet<Integer>();
    }
    
    public boolean addContact(int userId) {
        return this.contactIds.add(userId);
    }
    public boolean removeContact(int userId) {
        return this.contactIds.remove(userId);
    }
    public boolean isContact(int userId) {
        return this.contactIds.contains(userId);
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
    public String getMdp() {
        return mdp;
    }
    public void setMdp(String mdp) {
        this.mdp = mdp;
    }
    public Color getColor() {
        return color;
    }
    public void setColor(Color color) {
        this.color = color;
    }
    public HashSet<Integer> getContacts() {
        return this.contactIds;
    }
    public void setContacts(HashSet<Integer> contacts) {
        this.contactIds = contacts;
    }
    
    
}

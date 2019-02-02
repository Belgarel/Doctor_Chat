/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doctor_chat.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;

/**
 *
 * @author Home
 */
public class Conversation implements Serializable {
    private long id;
    private HashSet<User> members;
    private HashSet<Message> messages;

    public Conversation() {
        this.members = new HashSet<User>();
        this.messages = new HashSet<Message>();
    }
    public Conversation(HashSet<User> members, HashSet<Message> messages) {
        this.members = members;
        this.messages = messages;
    }
    
    public boolean addMessage(Message message) {
        return this.messages.add(message);
    }
    public boolean removeMessage(Message message) {
        return this.messages.remove(message);
    }
    public boolean addMember(User member) {
        return this.members.add(member);
    }
    public boolean removeMember(User member) {
        return this.members.remove(member);
    }
    public boolean isMember(User member) {
        return this.members.contains(member);
    }
    public boolean isMember(String login) {
        boolean found = false;
        Iterator<User> it = this.members.iterator();
        User current = null;
        while (!found && it.hasNext()) {
            current = it.next();
            found = current.getLogin().equals(login);
        }
        return found;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public HashSet<Message> getMessages() {
        return messages;
    }
    public void setMessages(HashSet<Message> messages) {
        this.messages = messages;
    }
    public HashSet<User> getMembers() {
        return members;
    }
    public void setMembers(HashSet<User> members) {
        this.members = members;
    }
    
    public boolean equals(Object o) {
        if (!(o instanceof Conversation))
            return false;
        Conversation other = (Conversation) o;
        return this.id == other.getId();
    }
    
    public String toString() {
        String membersString = "";
        for (User member : this.members) {
            membersString = membersString.concat("\n|" + member);
        }
        String messagesString = "";
        for (Message message : this.messages) {
            messagesString = messagesString.concat("\n|messId : " + message.getId());
        }
        
        return "CONVERSATION - id : " + this.id + " ; members : " + membersString
                + "\n|messages : " + messagesString;
    }
}

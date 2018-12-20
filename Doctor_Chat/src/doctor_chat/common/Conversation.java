/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doctor_chat.common;

import java.util.ArrayList;

/**
 *
 * @author Home
 */
public class Conversation {
    private long id;
    private ArrayList<User> members;
    private ArrayList<Message> messages;

    public Conversation() {
        this.members = new ArrayList<User>();
        this.messages = new ArrayList<Message>();
    }
    public Conversation(ArrayList<User> members, ArrayList<Message> messages) {
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

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public ArrayList<Message> getMessages() {
        return messages;
    }
    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }
    public ArrayList<User> getMembers() {
        return members;
    }
    public void setMembers(ArrayList<User> members) {
        this.members = members;
    }
    
    public String toString() {
        String membersString = "";
        for (User member : this.members) {
            membersString = membersString.concat("\n|" + member);
        }
        String messagesString = "";
        for (Message message : this.messages) {
            messagesString = messagesString.concat("\n|" + message);
        }
        
        return "CONVERSATION - id : " + this.id + " ; members : " + membersString
                + "\n|messages : " + messagesString;
    }
}

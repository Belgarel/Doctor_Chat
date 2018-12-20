/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doctor_chat.common;

import java.util.Date;

/**
 *
 * @author Home
 */
class Message {
    private User author;
    private Date date;
    private String content;
    private String filePath;
    private long convId;

    public Message(User author, Date date, String content, String filePath, long convId) {
        this.author = author;
        this.date = date;
        this.content = content;
        this.filePath = filePath;
        this.convId = convId;
    }
    
    public User getAuthor() {
        return author;
    }
    public void setAuthor(User author) {
        this.author = author;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getFilePath() {
        return filePath;
    }
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    public long getConvId() {
        return convId;
    }
    public void setConvId(long convId) {
        this.convId = convId;
    }
    
    public String toString() { 
        return "MESSAGE - id conv : " + convId + " ; date : " + date
                + " ; content : \"" + content + "\" ; filePath : " + filePath
                + " ; author : " + author;
    }
    
}

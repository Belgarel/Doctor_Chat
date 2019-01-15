/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doctor_chat.common;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Home
 */
public class Message {

    private long id;
    private Date date;
    private String content;
    private String filePath;
    private User author;
    private Conversation conversation;

    public Message() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
    public String getDateDDMMYYY() {
        String concatDate = "";
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        Integer n = cal.get(Calendar.DAY_OF_MONTH);
        if (n < 10)
            concatDate = concatDate.concat("0");
        concatDate = concatDate.concat(n + "/");
        n = cal.get(Calendar.MONTH) + 1;
        if (n < 10)
            concatDate = concatDate.concat("0");
        concatDate = concatDate.concat(n + "/");
        n = cal.get(Calendar.YEAR);
        concatDate = concatDate.concat(n.toString());
        return concatDate;
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

    public Conversation getConversation() {
        return conversation;
    }

    public void setConversation(Conversation conversation) {
        this.conversation = conversation;
    }

    public String toString() {
        return "MESSAGE - id : " + id + ";  conv : " + conversation + "\n|date : " + date
                + " ; content : \"" + content + "\" ; filePath : " + filePath
                + "\n|author : " + author;
    }

}

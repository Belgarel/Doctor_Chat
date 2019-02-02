/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doctor_chat.server;

import doctor_chat.common.Conversation;
import doctor_chat.common.NotFoundException;
import doctor_chat.common.Message;
import doctor_chat.common.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 *
 * @author Home
 */
public class MessageService {
    private static MessageService instance = null;
    
    public HashSet<Message> findMessagesForConversation(Conversation conversation) {
        HashSet<Message> ret = new HashSet<Message>();
        Statement request = null;
        ResultSet results = null;
        String sql = "select * from drc_message where no_conversation = " + conversation.getId() + "order by DATEPOSTED asc";
        try {
            request = DBConnection.instance().getConnection().createStatement();
            results = request.executeQuery(sql);
            
            boolean next = true;
            while (next)
            {
                try {
                    ret.add(toMessage(results, conversation));
                } catch (NotFoundException ex) {
                    next = false;
                }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (results != null)
                    results.close();
                if (request != null)
                    request.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ret;
    }
    public Message findMessagesbyId(long messId) throws NotFoundException {
        Message ret = null;
        Statement request = null;
        ResultSet results = null;
        String sql = "select * from drc_message where no_message = " + messId;
        try {
            request = DBConnection.instance().getConnection().createStatement();
            results = request.executeQuery(sql);
            
            ret = toMessage(results, null);
            
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (results != null)
                    results.close();
                if (request != null)
                    request.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ret;
    }
    
    public Message toMessage(ResultSet results, Conversation conversation) throws SQLException, NotFoundException {
        if (!results.next())
            throw new NotFoundException();
        
        Message ret = new Message();
        
        //first, retreive the conversation
        long convId = results.getLong("NO_CONVERSATION");
        //if the conversation in parameter does not match the one from the table, go retrieve the correct conversation
        if (conversation == null || conversation.getId() != convId)
            conversation = ConversationService.instance().toConversation(convId);
        
        //next, retrieve the author. First look in the conversation
        long authId = results.getLong("NO_AUTHOR");
        User author = null;
        Iterator<User> itMembers = conversation.getMembers().iterator();
        while (author == null && itMembers.hasNext()) {
            author = itMembers.next();
            if (author.getNum() != authId)
                author = null;
        }
        //if the author was not in the conversation, search in the database
        if (author == null)
            author = UserService.instance().findUser(authId);
        
        //Setting the Message Object (if author and conversation were found)
        ret.setId(results.getLong("NO_MESSAGE"));
        ret.setDate(results.getDate("DATEPOSTED"));
        ret.setContent(results.getNString("MESSAGE"));
        ret.setFilePath(results.getNString("NOM_FICHIER"));
        ret.setConversation(conversation);
        ret.setAuthor(author);
        
        return ret;
    }
    
    public synchronized long createMessage(Message message) {
        Statement select = null;
        Statement update = null;
        //filePath field
        String concatFilePath = "'" + message.getFilePath() + "', '";
        if ("'', '".equals(concatFilePath))
            concatFilePath = "NULL, '";
        //Date field
        String concatDate = message.getDateDDMMYYY();
        //Id field
        String sql = "select max(NO_MESSAGE) from DRC_MESSAGE";
        long maxId = -1;
        try {
            //retreiving the id of the next message
            select = DBConnection.instance().getConnection().createStatement();
            ResultSet rs = select.executeQuery(sql);
            if (rs.next()) //if table is not empty
                maxId = rs.getLong(1) + 1;
            else //if the table is empty
                maxId = 1;
            
            //Creating the message
            sql = "insert into DRC_MESSAGE (NO_MESSAGE, NO_CONVERSATION, NOM_FICHIER, MESSAGE, DATEPOSTED, NO_AUTHOR) values ("
                    + maxId + ", "
                    + message.getConversation().getId() + ", "
                    + concatFilePath
                    + message.getContent() + "', TO_DATE('"
                    + concatDate + "', 'DD/MM/YY'), "
                    + message.getAuthor().getNum() + ")"
                    ;
            update = DBConnection.instance().getConnection().createStatement();
            update.executeUpdate(sql);
            
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (update != null)
                    update.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return maxId;
    }
    public void delete(Message mess) {
        delete(mess.getId());
    }
    public void delete(long messId) {
        Statement update = null;
        String sql = "delete from DRC_MESSAGE where NO_MESSAGE = " + messId;
        try {
            update = DBConnection.instance().getConnection().createStatement();
            update.executeUpdate(sql);
            
        } catch (SQLException ex) {
            System.out.println("MessageService > delete : Exception SQL "
                    + ex.getMessage());
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (update != null)
                    update.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public void deleteByConversationId(long convId) {
        Statement update = null;
        String sql = "delete from DRC_MESSAGE where NO_CONVERSATION = " + convId;
        try {
            update = DBConnection.instance().getConnection().createStatement();
            update.executeUpdate(sql);
            
        } catch (SQLException ex) {
            System.out.println("MessageService > delete : Exception SQL "
                    + ex.getMessage());
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (update != null)
                    update.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private MessageService() {}
    public static MessageService instance() {
        if (instance == null)
        {
            synchronized(MessageService.class) {
                if (instance == null)
                    instance = new MessageService();
            }
        }
        return instance;
    }
}

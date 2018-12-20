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
    
    public Message toMessage(ResultSet results, Conversation conversation) throws SQLException, NotFoundException {
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

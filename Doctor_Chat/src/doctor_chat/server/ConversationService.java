/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doctor_chat.server;

import doctor_chat.common.Conversation;
import doctor_chat.common.NotFoundException;
import doctor_chat.common.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 *
 * @author Home
 */
public class ConversationService {
    private static ConversationService instance = null;
    
    public HashSet<Long> findConversations(User user) {
        return findConversations(user.getNum());
    }
    public HashSet<Long> findConversations(long userId) {
        HashSet<Long> ret =  new HashSet<Long>();
        Statement request = null;
        ResultSet results = null;
        String sql = "select * from drc_participe where no_utilisateur = " + userId;
        try {
            request = DBConnection.instance().getConnection().createStatement();
            results = request.executeQuery(sql);
            
            while (results.next()) {
                ret.add(Long.valueOf(results.getNString("NO_CONVERSATION")));
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
    public Conversation findConversation(long conversationId) throws NotFoundException {
        Conversation ret = new Conversation();
        Statement request = null;
        ResultSet results = null;
        String sql = "select * from drc_conversation where no_conversation = " + conversationId;
        try {
            request = DBConnection.instance().getConnection().createStatement();
            results = request.executeQuery(sql);
            
            if (!results.next())
                throw new NotFoundException();
            ret.setId(Long.valueOf(results.getNString("NO_CONVERSATION")));
            ret.setMembers(findMembers(ret.getId()));
            
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
        
        //TODO: messages & members
        
        return ret;
    }
    public ArrayList<Long> findMemberIds(long conversationId) {
        ArrayList<Long> ret =  new ArrayList<Long>();
        Statement request = null;
        ResultSet results = null;
        String sql = "select * from drc_participe where no_conversation = " + conversationId;
        try {
            request = DBConnection.instance().getConnection().createStatement();
            results = request.executeQuery(sql);
            
            while (results.next()) {
                ret.add(Long.valueOf(results.getNString("NO_UTILISATEUR")));
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
    public ArrayList<User> findMembers(long conversationId) {
        ArrayList<User> ret = new ArrayList<User>();
        ArrayList<Long> userIds = findMemberIds(conversationId);
        for (long uid : userIds)
            try {
                ret.add(UserService.instance().findUser(uid));
            } catch (NotFoundException ex) {
                System.out.println("ConversationService > findUsers : user " + uid + " not found.");
            }
        return ret;
    }
    
    public void createConversation(Conversation conv) {
        throw new NotImplementedException();
    }
    public void deleteConversation(Conversation conv) {
        deleteConversation(conv.getId());
    }
    public void deleteConversation(long conversationId) {
        throw new NotImplementedException();
    }
    
    public void addMemberToConversation(long conversationId, long userId) {
        throw new NotImplementedException();
    }
    public void removeMemberFromConversation(long conversationId, long userId) {
        throw new NotImplementedException();
    }
    public void addMessageToConversation(long conversationId, long messId) {
        throw new NotImplementedException();
    }
    public void removeMessageFromConversation(long conversationId, long messId) {
        throw new NotImplementedException();
    }
    
    
    private ConversationService() {}
    public static ConversationService instance() {
        if (instance == null)
        {
            synchronized(ConversationService.class) {
                if (instance == null)
                    instance = new ConversationService();
            }
        }
        return instance;
    }
}

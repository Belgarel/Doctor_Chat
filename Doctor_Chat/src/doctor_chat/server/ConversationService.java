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
import java.util.HashSet;
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
    
    public HashSet<Conversation> findUsersConversations(User user) {
        return findConversationsFromIds(findUsersConversationIds(user));
    }
    public HashSet<Long> findUsersConversationIds(User user) {
        HashSet<Long> ret =  new HashSet<Long>();
        Statement request = null;
        ResultSet results = null;
        String sql = "select * from drc_participe where no_utilisateur = " + user.getNum();
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
    public HashSet<Conversation> findConversationsFromIds (HashSet<Long> convIds) {
        HashSet<Conversation> ret = new HashSet<Conversation>();
        for (Long convId : convIds) {
            try {
                ret.add(toConversation(convId));
            } catch (NotFoundException ex) {
                System.out.println("User not found: " + ex.getMessage());
            }
        }
        return ret;
    }
    public Conversation toConversation(long conversationId) throws NotFoundException {
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
            ret.setMessages(MessageService.instance().findMessagesForConversation(ret));
            
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
    public HashSet<Long> findMemberIds(long conversationId) {
        HashSet<Long> ret =  new HashSet<Long>();
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
    public HashSet<User> findMembers(long conversationId) {
        HashSet<User> ret = new HashSet<User>();
        HashSet<Long> userIds = findMemberIds(conversationId);
        for (long uid : userIds)
            try {
                ret.add(UserService.instance().findUser(uid));
            } catch (NotFoundException ex) {
                System.out.println("ConversationService > findUsers : user " + uid + " not found.");
            }
        return ret;
    }
    
    public synchronized Conversation createConversation() {
        /*this function is synchronized so that one conversation can be created at the time
          so that the id of the newly created conversation can be retrieved. */
        Statement select;
        Statement update = null;
        String sql = "select max(id) from DRC_CONVERSATION";
        int maxId = -1;
        try {
            //retreiving the id of the next conversation
            select = DBConnection.instance().getConnection().createStatement();
            ResultSet rs = select.executeQuery(sql);
            if (rs.first()) //if table is not empty
                maxId = rs.getInt(1) + 1;
            else //if the table is empty
                maxId = 1;
            
            sql = "insert into DRC_CONVERSATION  values (" + maxId + ")";
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
        
        if (maxId == -1) //in case the function was not executed properly
            return null;
        Conversation ret = new Conversation();
        ret.setId(maxId);
        return ret;
    }
    public void deleteConversation(Conversation conv) {
        deleteConversation(conv.getId());
    }
    public void deleteConversation(long conversationId) {
        throw new NotImplementedException();
        //TODO : delete in the conversation table
        //TODO : delete all participations in the conversation
        //TODO : delete all messages of the conversation
    }
    
    public void addMemberToConversation(long conversationId, long userId) {
        /*there is no need to check that the user is not already in the conversation.
        If they are, an SQL Exception will be thrown/caught,
        resulting in the function doing nothing.
        */
        Statement update = null;
        String sql = "insert into DRC_PARTICIPE (NO_UTILISATEUR, NO_CONVERSATION) values ("
                + userId + ", " + conversationId + ")";
        try {
            update = DBConnection.instance().getConnection().createStatement();
            update.executeUpdate(sql);
            
        } catch (SQLException ex) {
            System.out.print("ConversationService > addMemberToConversation : Exception SQL "
                    + ex.getMessage());
            if (ex.getMessage() != null && ex.getMessage().startsWith("ORA-00001"))
                System.out.println("|The member is already part of the conversation.");
            else
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
    public void removeMemberFromConversation(long conversationId, long userId) {
        /*there is no need to check that the user is already in the conversation.
        If they are, the query (as well as the function) will have no effect.
        */
        Statement update = null;
        String sql = "delete from DRC_PARTICIPE where NO_UTILISATEUR = " + userId +
                " and NO_CONVERSATION = " + conversationId;
        try {
            update = DBConnection.instance().getConnection().createStatement();
            update.executeUpdate(sql);
            
        } catch (SQLException ex) {
            System.out.println("ConversationService > removeMemberFromConversation : Exception SQL "
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

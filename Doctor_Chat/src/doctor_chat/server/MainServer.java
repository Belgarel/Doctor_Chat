/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doctor_chat.server;

/**
 *
 * @author Home
 */
public class MainServer {
    public static void main(String[] args) {
        System.out.println("here\n");
        System.out.println("here\n" + DBConnection.instance().getConnection());
    }    
}

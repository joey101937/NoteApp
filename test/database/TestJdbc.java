/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author Joseph
 */
public class TestJdbc {
    public static void main(String[] args) {
        try{
            String jdbcUrl = "jdbc:mysql://localhost:3306?useSSL=false";
            String user = "joey";
            String pass = JOptionPane.showInputDialog("Enter Password");
            System.out.println("connecting to databse " + jdbcUrl);
            Connection myConn = DriverManager.getConnection(jdbcUrl, user, pass);
            System.out.println("SUCCESS");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 * DBConnect: Quan ly ket noi den CSDL va cac method dung chung cho cac DAO
 */
public class DBConnect {
    public Connection conn = null;
    public DBConnect(){
        this("jdbc:sqlserver://localhost\\SQLEXPRESS:1433;databaseName=SE1704", "sa", "123456");
//        try {
//            //Goi driver
//            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//            //connect
//            conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=SE1704", "sa", "123456");
//            System.out.println("Connected");
//        } catch (ClassNotFoundException ex) {
//            ex.printStackTrace();
//        } catch(SQLException ex){
//            ex.printStackTrace();
//        }
  }
    
    public DBConnect(String url, String username, String pass){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(url, username, pass);
            System.out.println("Connected");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public ResultSet getData(String sql){
        ResultSet rs = null;
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            rs = state.executeQuery(sql);
            
        } catch (SQLException ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        new DBConnect();
    }
}

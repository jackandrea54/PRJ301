/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Admin;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.DBConnect;

/**
 *
 * @author ADMIN
 */
public class DAOAdmin extends DBConnect {

    public int AddAdmin(Admin admin) {
        int n = 0;
        String sql = "USE [SE1704]\n"
                + "INSERT INTO [admin]\n"
                + "       VALUES\n"
                + "           ('" + admin.getAdmin() + "',\n"
                + "           '" + admin.getPassword() + "')";

        Statement state;
        try {
            state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int addAdminByPre(Admin admin) {
        int n = 0;
        String sql = "USE [SE1704]\n"
                + "INSERT INTO [admin]\n"
                + "       VALUES(?,?)";
                
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            //set parameter
            pre.setString(1, admin.getAdmin());
            pre.setString(2, admin.getPassword());
            //run
            n = pre.executeUpdate();
//            pre.setDataType(index?,value);
//            dataType is dataType of field
//            index of ? start 1
        } catch (SQLException ex) {
            Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int update(Admin admin){
        int n = 0;
        String sql = "USE [SE1704]\n"
                + "UPDATE [admin]\n"
                + "      SET [password] = ?"
                + "      WHERE  [admin] = ?";
                
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            //set parameter
            pre.setString(1, admin.getPassword());
            pre.setString(2, admin.getAdmin());
            //run
            n = pre.executeUpdate();
//            pre.setDataType(index?,value);
//            dataType is dataType of field
//            index of ? start 1
        } catch (SQLException ex) {
            Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public Vector<Admin> getAllAdmin() {
        Vector<Admin> vector = new Vector<>();
        ResultSet rs = this.getData("select * from Admin");
        try {
            while (rs.next()) {
                String admin = rs.getString("admin");
//                String id = rs.getNString(1);
                String password = rs.getString("password");
                Admin ad = new Admin(admin,password);
                vector.add(ad);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }

        return vector;
    }
    
    public Vector<Admin> getAdmin(String sql) {
        Vector<Admin> vector = new Vector<>();
        ResultSet rs = this.getData(sql);
        try {
            while (rs.next()) {
                String admin = rs.getString("admin");
//                String id = rs.getNString(1);
                String password = rs.getString("password");
                Admin ad = new Admin(admin,password);
                vector.add(ad);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }

        return vector;
    }
    
    public int removeAdmin(String admin) {
        int n = 0;
        String sql = "Delete from admin where admin ='" + admin + "'";
        try {
                Statement state = conn.createStatement();
                n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }
    
    
    public static void main(String[] args) {
        DAOAdmin dao = new DAOAdmin();
        int n = dao.update(new Admin("jackandrea", "jackandrea1708"));
        if (n > 0) {
            System.out.println("updated");
        }
    }

}

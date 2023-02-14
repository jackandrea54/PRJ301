/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Category;
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
public class DAOCategory extends DBConnect {

    public int AddCategory(Category cate) {
        int n = 0;
        String sql = "USE [SE1704]\n"
                + "INSERT INTO [Category]\n"
                + "           ([cateName]\n"
                + "           ,[status])\n"
                + "     VALUES\n"
                + "           ('" + cate.getCateName() + "'\n"
                + "           ," + cate.getStatus() + ")";

        try {
            //Tao lenh
            Statement state = conn.createStatement();
            //Chay lenh va nhan ket qua
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int addCategoryByPre(Category cate) {
        int n = 0;
        String sql = "USE [SE1704]\n"
                + "INSERT INTO [Category]\n"
                + "           ([cateName]\n"
                + "           ,[status])\n"
                + "     VALUES\n"
                + "           (?,?)";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);

            pre.setString(1, cate.getCateName());
            pre.setInt(2, cate.getStatus());

            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int update(Category cate) {
        int n = 0;

        String sql = "USE [SE1704]\n"
                + "UPDATE [Category]\n"
                + "   SET [cateName] = ?\n"
                + "      ,[status] = ?\n"
                + " WHERE cateID = ?";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);

            pre.setString(1, cate.getCateName());
            pre.setInt(2, cate.getStatus());
            pre.setInt(3, cate.getCateID());
            
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }
    
    public Vector<Category> getAllCategory() {
        Vector<Category> vector = new Vector<>();
        ResultSet rs = this.getData("select * from Category");
        try {
            while (rs.next()) {
                int cateID = rs.getInt(1);
                String cateName = rs.getString(2);
                int status = rs.getInt(3);
                Category cate = new Category(cateID, cateName, status);
                vector.add(cate);
//                System.out.println(cate);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
        }

        return vector;
    }
    
    public Vector<Category> getCategory(String sql) {
        Vector<Category> vector = new Vector<>();
        ResultSet rs = this.getData(sql);
        try {
            while (rs.next()) {
                int cateID = rs.getInt(1);
                String cateName = rs.getString(2);
                int status = rs.getInt(3);
                Category cate = new Category(cateID, cateName, status);
                vector.add(cate);
//                System.out.println(cate);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
        }

        return vector;
    }
    
    public int removeCategory(String cateID) {
        int n = 0;
        String sql = "Delete from Category where cateID ='" + cateID + "'";
        try {
            //Note: cateID --1---n--> Product --> Khong xoa duoc
            ResultSet rs = this.getData("Select * from Product where cateID = '" + cateID + "'");
            if (rs.next()) {
                // co ton tai Product
                // thong bao ma khong lam gi ca
                n = -1; // khong xoa dc vi constrain (tu dinh nghia sau)
            } else {
                Statement state = conn.createStatement();
                n = state.executeUpdate(sql);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOBill.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public static void main(String[] args) {
        DAOCategory dao = new DAOCategory();
        int n = dao.update(new Category(2,"Food & Beverage", 0));
        if (n > 0) {
            System.out.println("updated");
        }
    }
}

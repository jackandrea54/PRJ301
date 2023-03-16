/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Product;
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
public class DAOProduct extends DBConnect {

    public int AddProduct(Product product) {
        int n = 0;
        String sql = "USE [SE1704]\n"
                + "\n"
                + "INSERT INTO [Product]\n"
                + "           ([pid]\n"
                + "           ,[pname]\n"
                + "           ,[quantity]\n"
                + "           ,[price]\n"
                + "           ,[image]\n"
                + "           ,[description]\n"
                + "           ,[status]\n"
                + "           ,[cateID])\n"
                + "     VALUES\n"
                + "           ('" + product.getPid() + "'\n"
                + "           ,'" + product.getPname() + "'\n"
                + "           ,'" + product.getQuantity() + "'\n"
                + "           ,'" + product.getPrice() + "'\n"
                + "           ,'" + product.getImage() + "'\n"
                + "           ,'" + product.getDescription() + "'\n"
                + "           ,'" + product.getStatus() + "'\n"
                + "           ,'" + product.getCateID() + "')";

        Statement state;

        try {
            state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int addProductByPre(Product product) {
        int n = 0;
        String sql = "USE [SE1704]\n"
                + "\n"
                + "INSERT INTO [Product]\n"
                + "           ([pid]\n"
                + "           ,[pname]\n"
                + "           ,[quantity]\n"
                + "           ,[price]\n"
                + "           ,[image]\n"
                + "           ,[description]\n"
                + "           ,[status]\n"
                + "           ,[cateID])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, product.getPid());
            pre.setString(2, product.getPname());
            pre.setDouble(3, product.getQuantity());
            pre.setDouble(4, product.getPrice());
            pre.setString(5, product.getImage());
            pre.setString(6, product.getDescription());
            pre.setInt(7, product.getStatus());
            pre.setInt(8, product.getCateID());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public int update(Product product) {
        int n = 0;
        String sql = "USE [SE1704]\n"
                + "UPDATE [Product]\n"
                + "   SET [pname] = ?\n"
                + "      ,[quantity] = ?\n"
                + "      ,[price] = ?\n"
                + "      ,[image] = ?\n"
                + "      ,[description] = ?\n"
                + "      ,[status] = ?\n"
                + "      ,[cateID] = ?\n"
                + " WHERE [pid] = ?";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);

            pre.setString(1, product.getPname());
            pre.setDouble(2, product.getQuantity());
            pre.setDouble(3, product.getPrice());
            pre.setString(4, product.getImage());
            pre.setString(5, product.getDescription());
            pre.setInt(6, product.getStatus());
            pre.setInt(7, product.getCateID());
            pre.setString(8, product.getPid());

            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public Vector<Product> getAllProduct() {
        Vector<Product> vector = new Vector<>();
        ResultSet rs = this.getData("select * from Product");
        try {
            while (rs.next()) {
                String pid = rs.getString(1);
                String pname = rs.getString(2);
                int quantity = rs.getInt(3);
                double price = rs.getDouble(4);
                String image = rs.getString(5);
                String description = rs.getString(6);
                int status = rs.getInt(7);
                int cateID = rs.getInt(8);
                Product prod = new Product(pid, pname, quantity, price, image, description, status, cateID);
                vector.add(prod);
//                System.out.println(cate);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DAOCategory.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        return vector;
    }
    
    public Vector<Product> getProduct(String sql) {
        Vector<Product> vector = new Vector<>();
        ResultSet rs = this.getData(sql);
        try {
            while (rs.next()) {
                String pid = rs.getString(1);
                String pname = rs.getString(2);
                int quantity = rs.getInt(3);
                double price = rs.getDouble(4);
                String image = rs.getString(5);
                String description = rs.getString(6);
                int status = rs.getInt(7);
                int cateID = rs.getInt(8);
                Product prod = new Product(pid, pname, quantity, price, image, description, status, cateID);
                vector.add(prod);
//                System.out.println(cate);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DAOCategory.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        return vector;
    }

    public int removeProduct(String pid) {
        int n = 0;
        String sql = "Delete from Product where pid ='" + pid + "'";
        try {
            //Note: Pid --1---n--> BillDetail --> Khong xoa duoc
            ResultSet rs = this.getData("Select * from BillDetail where pid = '" + pid + "'");
            if (rs.next()) {
                // co ton tai billdetail
                // thong bao ma khong lam gi ca
                n = -1; // khong xoa dc vi constrain (tu dinh nghia sau)
            } else {
                Statement state = conn.createStatement();
                n = state.executeUpdate(sql);

            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCategory.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public static void main(String[] args) {
        DAOProduct dao = new DAOProduct();
        System.out.println(dao.getAllProduct());
//        int n = dao.update(new Product("P02", "Nike", 15, 90000,
//                "https://shopgiayreplica.com/wp-content/uploads/2021/04/Jordan-1-Retro-High-Twist-Panda.jpeg",
//                "Very nice shoes", 0, 1));
//        if (n > 0) {
//            System.out.println("UPdated");
//        }
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author ADMIN
 */
import entity.Customer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.DBConnect;

public class DAOCustomer extends DBConnect {

    public int AddCustomer(Customer cus) {
        int n = 0;
        String sql = "USE [SE1704]\n"
                + "INSERT INTO [Customer]\n"
                + "           ([cname]\n"
                + "           ,[username]\n"
                + "           ,[password]\n"
                + "           ,[address]\n"
                + "           ,[phone]\n"
                + "           ,[status])\n"
                + "     VALUES ('" + cus.getCname() + "','"
                + cus.getUsername() + "','" + cus.getPassword() + "','"
                + cus.getAddress() + "','" + cus.getPhone() + "'," + cus.getStatus() + ")";

        try {
            //Tao lenh
            Statement state = conn.createStatement();
            //Chay lenh va nhan ket qua
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
        return n;
    }

    public int addCustomerByPre(Customer cus) {
        int n = 0;
        String sql = "USE [SE1704]\n"
                + "INSERT INTO [Customer]\n"
                + "           ([cname]\n"
                + "           ,[username]\n"
                + "           ,[password]\n"
                + "           ,[address]\n"
                + "           ,[phone]\n"
                + "           ,[status])\n"
                + "     VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            //set parameter
            pre.setString(1, cus.getCname());
            pre.setString(2, cus.getUsername());
            pre.setString(3, cus.getPassword());
            pre.setString(4, cus.getAddress());
            pre.setString(5, cus.getPhone());
            pre.setInt(6, cus.getStatus());
            //run
            n = pre.executeUpdate();
//            pre.setDataType(index?,value);
//            dataType is dataType of field
//            index of ? start 1
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int updateCustomer(Customer cus) {
        int n = 0;
        String sql = "USE [SE1704]\n"
                + "UPDATE [Customer]\n"
                + "   SET [cname] = ?\n"
                + "      ,[username] = ?\n"
                + "      ,[password] = ?\n"
                + "      ,[address] = ?\n"
                + "      ,[phone] = ?\n"
                + "      ,[status] = ?\n"
                + " WHERE [cid] = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            //set parameter
            pre.setString(1, cus.getCname());
            pre.setString(2, cus.getUsername());
            pre.setString(3, cus.getPassword());
            pre.setString(4, cus.getAddress());
            pre.setString(5, cus.getPhone());
            pre.setInt(6, cus.getStatus());
            pre.setString(7, cus.getCid());
            //run
            n = pre.executeUpdate();
//            pre.setDataType(index?,value);
//            dataType is dataType of field
//            index of ? start 1
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public boolean login(String user, String pass) {
        //Khong duoc dung cong chuoi trong login: doc them ve sql injection
//        sql = "select * from Customer where username = '"+user+"' and "
//                + " password = '"+pass+"'";
        
        String sql = "select * from Customer where username = ? and "
                + " password = ? and status = 1";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, user);
            pre.setString(2, pass);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    //Nen dung Vector thay vi resultSet vi Vector pass by value con resultSet pass by reference
    //Vector co synchronise, khong dung ArrayList
    public Vector<Customer> getAllCustomer() {
        Vector<Customer> vector = new Vector<>();
        ResultSet rs = this.getData("select * from Customer");
        try {
            while (rs.next()) {
                String id = rs.getString("cid");
//                String id = rs.getNString(1);
                String name = rs.getString("cname");
//                String name = rs.getNString(2);
                String userName = rs.getString(3);
                String pass = rs.getString(4);
                String address = rs.getString(5);
                String phone = rs.getString(6);
                int status = rs.getInt("status");
                Customer cus = new Customer(id, name, userName, pass, address, phone, status);
                vector.add(cus);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }

        return vector;
    }
    
    public Vector<Customer> getCustomer(String sql) {
        Vector<Customer> vector = new Vector<>();
        ResultSet rs = this.getData(sql);
        try {
            while (rs.next()) {
                String id = rs.getString("cid");
//                String id = rs.getNString(1);
                String name = rs.getString("cname");
//                String name = rs.getNString(2);
                String userName = rs.getString(3);
                String pass = rs.getString(4);
                String address = rs.getString(5);
                String phone = rs.getString(6);
                int status = rs.getInt("status");
                Customer cus = new Customer(id, name, userName, pass, address, phone, status);
                vector.add(cus);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }

        return vector;
    }

    public void displayAll() {
        String sql = "select * from Customer";
        try {
//            Statement state = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, 
//                                                   ResultSet.CONCUR_READ_ONLY); //Nen truyen tham so mac dinh
            //Default: TYPE_FORWARD_Only: con tro chi di xuong
            // CONCUR_READ_ONLY: Ket qua chi doc, khong sua
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            //TYPE_SCROLL_SENSITIVE: Scroll: Con tro 2 chieu
            // Sensitive: threadSafe
            //UPDATABLE: Cho phep sua
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                /* dataType varName = rs.getDateType (fieldName|index);
                index start at 1*/
                String id = rs.getString("cid");
//                String id = rs.getNString(1);
                String name = rs.getString("cname");
//                String name = rs.getNString(2);
                String userName = rs.getString(3);
                String pass = rs.getString(4);
                String address = rs.getString(5);
                String phone = rs.getString(6);
                int status = rs.getInt("status");
                Customer cus = new Customer(id, name, userName, pass, address, phone, status);
                System.out.println(cus);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int removeCustomer(String id) {
        int n = 0;
        String sql = "Delete from Customer where cid ='" + id + "'";
        try {
            //Note: Customer --1---n--> Bill --> Khong xoa duoc
            // Neu cid ton tai tren Bill (foreign key constrain)
            ResultSet rs = this.getData("Select * from Bill where cid = '" + id + "'");
            if (rs.next()) {
                // co ton tai bill
                // thong bao ma khong lam gi ca
                n = -1; // khong xoa dc vi constrain (tu dinh nghia sau)
                //servlet call update status of customer
            } else {
                Statement state = conn.createStatement();
                n = state.executeUpdate(sql);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public static void main(String[] args) {
        DAOCustomer dao = new DAOCustomer();
        int n = dao.AddCustomer(new Customer("John Nghia", "nghia1708", "nghia1708", "HN",
                "0352963942", 1));
        if (n > 0) {
            System.out.println("Inserted");
        }

        dao.displayAll();
    }

    
}

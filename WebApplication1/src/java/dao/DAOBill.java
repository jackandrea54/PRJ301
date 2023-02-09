/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Bill;
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
public class DAOBill extends DBConnect {

    public int AddBill(Bill bill) {
        int n = 0;
        String sql = "USE [SE1704]\n"
                + "INSERT INTO [Bill]\n"
                + "           ([bid]\n"
                + "           ,[recAddress]\n"
                + "           ,[recPhone]\n"
                + "           ,[note]\n"
                + "           ,[totalMoney]\n"
                + "           ,[status]\n"
                + "           ,[cid])\n"
                + "     VALUES\n"
                + "           ('" + bill.getBid() + "'\n"
                + "           ,'" + bill.getRecAddress() + "'\n"
                + "           ,'" + bill.getRecPhone() + "'\n"
                + "           ,'" + bill.getNote() + "'\n"
                + "           ,'" + bill.getTotalMoney() + "'\n"
                + "           ,'" + bill.getStatus() + "'\n"
                + "           ,'" + bill.getCid() + "')";

        Statement state;
        try {
            state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOBill.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int addBillByPre(Bill bill) {
        int n = 0;
        String sql = "USE [SE1704]\n"
                + "INSERT INTO [Bill]\n"
                + "           ([bid]\n"
                + "           ,[recAddress]\n"
                + "           ,[recPhone]\n"
                + "           ,[note]\n"
                + "           ,[totalMoney]\n"
                + "           ,[status]\n"
                + "           ,[cid])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?,?)";

        try {
            PreparedStatement pre;
            pre = conn.prepareStatement(sql);
            pre.setString(1, bill.getBid());
            pre.setString(2, bill.getRecAddress());
            pre.setString(3, bill.getRecPhone());
            pre.setString(4, bill.getNote());
            pre.setDouble(5, bill.getTotalMoney());
            pre.setInt(6, bill.getStatus());
            pre.setString(7, bill.getCid());

            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOBill.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public int update(Bill bill) {
        int n = 0;
        String sql = "USE [SE1704]\n"
                + "UPDATE [Bill]\n"
                + "   SET [dateCreate] = ?\n"
                + "      ,[recAddress] = ?\n"
                + "      ,[recPhone] = ?\n"
                + "      ,[note] = ?\n"
                + "      ,[totalMoney] = ?\n"
                + "      ,[status] = ?\n"
                + "      ,[cid] = ?\n"
                + " WHERE [bid] = ?";

        try {
            PreparedStatement pre;
            pre = conn.prepareStatement(sql);
            
            pre.setString(1, bill.getDateCreate());
            pre.setString(2, bill.getRecAddress());
            pre.setString(3, bill.getRecPhone());
            pre.setString(4, bill.getNote());
            pre.setDouble(5, bill.getTotalMoney());
            pre.setInt(6, bill.getStatus());
            pre.setString(7, bill.getCid());
            pre.setString(8, bill.getBid());
            
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOBill.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return n;
    }

    public Vector<Bill> getAllBill() {
        Vector<Bill> vector = new Vector<>();
        ResultSet rs = this.getData("select * from Bill");
        try {
            while (rs.next()) {
                String bid = rs.getString(1);
                String dateCreate = rs.getString(2);
                String recAddress = rs.getString(3);
                String recPhone = rs.getString(4);
                String note = rs.getString(5);
                double totalMoney = rs.getDouble(6);
                int status = rs.getInt(7);
                String cid = rs.getString(8);
                Bill bill = new Bill(bid, dateCreate ,recAddress, recPhone, note, totalMoney, status, cid);
                vector.add(bill);
                System.out.println(bill);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOBill.class.getName()).log(Level.SEVERE, null, ex);
        }

        return vector;
    }
    
    public int removeBill(String bid) {
        int n = 0;
        String sql = "Delete from Bill where bid ='" + bid + "'";
        try {
            //Note: Bill --1---n--> BillDetail --> Khong xoa duoc
            ResultSet rs = this.getData("Select * from BillDetail where bid = '" + bid + "'");
            if (rs.next()) {
                // co ton tai billdetail
                // thong bao ma khong lam gi ca
                n = -1; // khong xoa dc vi constrain (tu dinh nghia sau)
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
        DAOBill dao = new DAOBill();
        int n = dao.update(new Bill("B02", "2020-10-29", "SG", "0352963942", "Very poor", 102131213, 0, "C02"));
        if (n > 0) {
            System.out.println("updated");
        }
        dao.getAllBill();
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.BillDetail;
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
public class DAOBillDetail extends DBConnect {

    public int AddBillDetail(BillDetail billDe) {
        int n = 0;
        String sql = "USE [SE1704]\n"
                + "INSERT INTO [BillDetail]\n"
                + "           ([bid]\n"
                + "           ,[pid]\n"
                + "           ,[buyQuantity]\n"
                + "           ,[buyPrice]\n"
                + "           ,[subtotal])\n"
                + "     VALUES\n"
                + "           ('" + billDe.getBid() + "'\n"
                + "           ,'" + billDe.getPid() + "'\n"
                + "           ," + billDe.getBuyQuantity() + "\n"
                + "           ," + billDe.getBuyPrice() + "\n"
                + "           ," + billDe.getSubtotal() + ")";
        Statement state;
        try {
            state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOBill.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;

    }

    public int addBillDetailByPre(BillDetail billDe) {
        int n = 0;
        String sql = "USE [SE1704]\n"
                + "INSERT INTO [BillDetail]\n"
                + "           ([bid]\n"
                + "           ,[pid]\n"
                + "           ,[buyQuantity]\n"
                + "           ,[buyPrice]\n"
                + "           ,[subtotal])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, billDe.getBid());
            pre.setString(2, billDe.getPid());
            pre.setDouble(3, billDe.getBuyQuantity());
            pre.setDouble(4, billDe.getBuyPrice());
            pre.setDouble(5, billDe.getSubtotal());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOBillDetail.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public int update(BillDetail billDe) {
        int n = 0;
        String sql = "USE [SE1704]\n"
                + "UPDATE [dbo].[BillDetail]\n"
                + "   SET [buyQuantity] = ?\n"
                + "      ,[buyPrice] = ?\n"
                + "      ,[subtotal] = ?\n"
                + " WHERE [bid] = ? and [pid] = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setDouble(1, billDe.getBuyQuantity());
            pre.setDouble(2, billDe.getBuyPrice());
            pre.setDouble(3, billDe.getSubtotal());
            pre.setString(4, billDe.getBid());
            pre.setString(5, billDe.getPid());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOBillDetail.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }
    
    public Vector<BillDetail> getAllBillDetail() {
        Vector<BillDetail> vector = new Vector<>();
        ResultSet rs = this.getData("select * from BillDetail");
        try {
            while (rs.next()) {
                String bid = rs.getString(1);
                String pid = rs.getString(2);
                int buyQuantity = rs.getInt(3);
                double buyPrice = rs.getDouble(4);
                double subTotal = rs.getDouble(5);
                BillDetail billDetail = new BillDetail(bid, pid, buyQuantity, buyPrice, subTotal);
                vector.add(billDetail);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOBillDetail.class.getName()).log(Level.SEVERE, null, ex);
        }

        return vector;
    }

    public int removeBillDetail(String pid) {
        int n = 0;
        String sql = "Delete from BillDetail where pid ='" + pid + "'";
        try {
                Statement state = conn.createStatement();
                n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }
    
    public static void main(String[] args) {
        DAOBillDetail dao = new DAOBillDetail();
        int n = dao.update(new BillDetail("B01", "P02", 1, 120000, 120000));
        if (n > 0) {
            System.out.println("updated");
        }
    }

}

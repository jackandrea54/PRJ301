/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import DBtoTable.DBContext;
import Models.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author LENOVO
 */
public class DAO {

    private ArrayList<Product> productsRecent;
    private ArrayList<Account> accounts;
    private ArrayList<Product> productsBestBuy;
    private ArrayList<Color> colors;
    private ArrayList<Collection> collections;
    private ArrayList<Category> categories;
    private ArrayList<Product> products;
    private ArrayList<Product> productFilter;
    private ArrayList<Integer> productBuyQuantity;
    private ArrayList<Size> sizes;
    private ArrayList<Status> stats;
    String testSQL;
    private String status = "ok";

    public DAO() {

    }

    public ArrayList<Status> getStats() {
        return stats;
    }

    public void setStats(ArrayList<Status> stats) {
        this.stats = stats;
    }

    public ArrayList<Size> getSizes() {
        return sizes;
    }

    public void setSizes(ArrayList<Size> sizes) {
        this.sizes = sizes;
    }

    public ArrayList<Integer> getProductBuyQuantity() {
        return productBuyQuantity;
    }

    public void setProductBuyQuantity(ArrayList<Integer> productBuyQuantity) {
        this.productBuyQuantity = productBuyQuantity;
    }

    public ArrayList<Product> getProductFilter() {
        return productFilter;
    }

    public void setProductFilter(ArrayList<Product> productFilter) {
        this.productFilter = productFilter;
    }

    public ArrayList<Color> getColors() {
        return colors;
    }

    public void setColors(ArrayList<Color> colors) {
        this.colors = colors;
    }

    public ArrayList<Collection> getCollections() {
        return collections;
    }

    public void setCollections(ArrayList<Collection> collections) {
        this.collections = collections;
    }

    public ArrayList<Product> getProductsRecent() {
        return productsRecent;
    }

    public void setProductsRecent(ArrayList<Product> productsRecent) {
        this.productsRecent = productsRecent;
    }

    public ArrayList<Product> getProductsBestBuy() {
        return productsBestBuy;
    }

    public void setProductsBestBuy(ArrayList<Product> productsBestBuy) {
        this.productsBestBuy = productsBestBuy;
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(ArrayList<Account> accounts) {
        this.accounts = accounts;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public String getPassword(String username) {
        String sql = "SELECT password FROM [Account_HE176764] WHERE username = '" + username + "'";
        try {
            Connection con = new DBContext().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String password = rs.getString(1);
                return password;
            }
            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {
            status = "Error " + e.getMessage();
        }
        return null;
    }

    public void insertAccount(String fullname, String username, String email, String phoneNumber, String password, int roleId) {
        String sql = "INSERT INTO [Account_HE176764] (fullname, username, email, phone_number, password, role_id) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            Connection con = new DBContext().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, fullname);
            ps.setString(2, username);
            ps.setString(3, email);
            ps.setString(4, phoneNumber);
            ps.setString(5, password);
            ps.setInt(6, roleId);
            ps.execute();
            ps.close();
        } catch (Exception e) {
            status = "Error at insertAccount " + e.getMessage();
        }
    }

    public int loadAccountIdByUsername(String username) {
        int id = 0;
        String sql = "SELECT id FROM [Account_HE176764] WHERE username "
                + "LIKE ?";
        try {
            Connection con = new DBContext().getConnection();

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1);
            }
            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {
            status = "Error " + e.getMessage();
        }
        return id;
    }

    public Account loadAccountById(int id) {
        Account acc = new Account();
        String sql = "SELECT * FROM [Account_HE176764] WHERE id = ? ";
        try {
            Connection con = new DBContext().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int accId = rs.getInt(1);
                String fullname = rs.getString(2);
                String username = rs.getString(3);
                String pass = rs.getString(4);
                String email = rs.getString(5);
                String phoneNum = rs.getString(6);
                int role_id = rs.getInt(7);
                String create = rs.getString(8);
                String update = rs.getString(9);
                acc = new Account(accId, fullname, username, pass, email, phoneNum, role_id, create, update);
            }
            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {
            status = "Error " + e.getMessage();
        }
        return acc;
    }

    public void loadProductDateDesc() {
        productsRecent = new ArrayList<>();
        productBuyQuantity = new ArrayList<>();
        String sql = "WITH counts AS (SELECT name, COUNT(name) as quan FROM "
                + "Order_Details_HE176764, [Product_HE176764] "
                + "WHERE Order_Details_HE176764.product_id = [Product_HE176764].id GROUP BY name), "
                + "reps AS (SELECT MAX(id) as rep_id, name from [Product_HE176764] GROUP BY name), "
                + "rep_pro AS (SELECT rep_id, reps.name, price, discount, thumbnail, created_at, status "
                + "FROM [Product_HE176764], reps WHERE rep_id = [Product_HE176764].id)"
                + "SELECT rep_id, rep_pro.name, price, discount, thumbnail, created_at, "
                + "COALESCE(quan, 0) as quantity, status FROM rep_pro "
                + "LEFT JOIN "
                + "counts\n"
                + "ON rep_pro.name = counts.name\n"
                + "ORDER BY created_at DESC";
        try {
            Connection con = new DBContext().getConnection();

            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                int price = rs.getInt(3);
                int discount = rs.getInt(4);
                String thumbnail = rs.getString(5);
                String createdAt = rs.getString(6);
                int quantity = rs.getInt(7);
                int status = rs.getInt(8);
                productsRecent.add(new Product(id, name, price, 0, 0, 0, 0, discount, thumbnail, "", status, createdAt));
                productBuyQuantity.add(quantity);
            }
            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {
            status = "Error " + e.getMessage();

        }
    }

    public void loadProductBuyDesc() {
        productsBestBuy = new ArrayList<>();
        String sql = "	WITH counts AS (SELECT name, COUNT(name) as quan FROM Order_Details_HE176764, [Product_HE176764] WHERE Order_Details_HE176764.product_id = [Product_HE176764].id GROUP BY name),"
                + "	nameId AS (SELECT MAX(id) as id, counts.name, counts.quan from [Product_HE176764], counts WHERE counts.name = [Product_HE176764].name GROUP BY counts.name, counts.quan )"
                + "	SELECT nameId.id, nameId.name, price, discount, thumbnail from [Product_HE176764], nameId WHERE nameId.id = [Product_HE176764].id ORDER BY nameId.quan DESC";

        try {
            Connection con = new DBContext().getConnection();

            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                int price = rs.getInt(3);
                int discount = rs.getInt(4);
                String thumbnail = rs.getString(5);
                productsBestBuy.add(new Product(id, name, price, 0, 0, 0, 0, discount, thumbnail, "", 0, ""));

            }
            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {
            status = "Error " + e.getMessage();

        }
    }

    public void loadColor() {
        colors = new ArrayList<>();
        String sql = "	SELECT * FROM [Color_HE176764]";
        try {
            Connection con = new DBContext().getConnection();

            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String colorcode = rs.getString(3);
                colors.add(new Color(id, name, colorcode));

            }
            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {
            status = "Error " + e.getMessage();
        }
    }

    public void loadSize() {
        sizes = new ArrayList<>();
        String sql = "select * from Size_HE176764";
        try {
            Connection con = new DBContext().getConnection();

            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                sizes.add(new Size(id, name));
            }
            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {
            status = "Error " + e.getMessage();
        }
    }

    public void loadCollection() {
        collections = new ArrayList<>();
        String sql = "SELECT * FROM [Collection_HE176764]";
        try {
            Connection con = new DBContext().getConnection();

            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String description = rs.getString(3);
                String thumbnail = rs.getString(4);
                collections.add(new Collection(id, name, description, thumbnail));
            }
            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {
            status = "Error " + e.getMessage();
        }
    }

    public void loadCategory() {
        categories = new ArrayList<>();
        String sql = "select * from Category_HE176764";
        try {
            Connection con = new DBContext().getConnection();

            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                categories.add(new Category(id, name));
            }
            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {
            status = "Error " + e.getMessage();
        }
    }

    public void loadAllProducts() {
        productFilter = new ArrayList<>();
        String sql = "WITH selectRepId AS (SELECT MAX(id) AS represent_id, name "
                + "FROM [Product_HE176764] GROUP BY name) "
                + "SELECT represent_id, selectRepId.name, price, category_id, "
                + "collection_id, color_id, discount, thumbnail, status "
                + "FROM selectRepId, [Product_HE176764] "
                + "WHERE [Product_HE176764].id = represent_id";
        try {
            Connection con = new DBContext().getConnection();

            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                int price = rs.getInt(3);
                int categoryId = rs.getInt(4);
                int collectionId = rs.getInt(5);
                int colorId = rs.getInt(6);
                int discount = rs.getInt(7);
                String thumbnail = rs.getString(8);
                int status = rs.getInt(9);
                productFilter.add(new Product(id, name, price, categoryId, collectionId, colorId, 0, discount, thumbnail, "", status, ""));
            }
            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {
            status = "Error " + e.getMessage();
        }
    }

    public void loadProductWithFilter(ArrayList<Integer> category, ArrayList<Integer> collection, ArrayList<Integer> color, int priceStart, int priceEnd) {
        productFilter = new ArrayList<>();
        productBuyQuantity = new ArrayList<>();
        //check if user choose price
        String priceSQL = "";
        if (priceStart != -1 && priceEnd == -1) {
            priceEnd = 100000000;
            priceSQL = "(price BETWEEN " + priceStart + " AND " + priceEnd + ")";
        } else if (priceStart == -1 && priceEnd != -1) {
            priceStart = 0;
            priceSQL = "(price BETWEEN " + priceStart + " AND " + priceEnd + ")";
        } else if (priceStart != -1 && priceEnd != -1) {
            priceSQL = "(price BETWEEN " + priceStart + " AND " + priceEnd + ")";
        }

        String categorySQL = "";

        //check if user choose category
        if (!category.isEmpty()) {
            for (int i = 0; i < category.size() - 1; i++) {
                categorySQL += "category_id = " + category.get(i) + " OR ";
            }
            categorySQL += "category_id = " + category.get(category.size() - 1);
        }
        //add AND if user input price or category
        if (!priceSQL.isEmpty() && !category.isEmpty()) {
            categorySQL = "(" + categorySQL + ")";
            categorySQL = " AND " + categorySQL;
        }

        String collectionSQL = "";

        //check if user choose collection
        if (!collection.isEmpty()) {
            for (int i = 0; i < collection.size() - 1; i++) {
                collectionSQL += "collection_id = " + collection.get(i) + " OR ";
            }
            collectionSQL += "collection_id = " + collection.get(collection.size() - 1);
        }
        //add AND if user input price or category and collection
        if ((!priceSQL.isEmpty() || !category.isEmpty()) && !collection.isEmpty()) {
            collectionSQL = "(" + collectionSQL + ")";
            collectionSQL = " AND " + collectionSQL;
        }

        String colorSQL = "";

        //check if user choose color
        if (!color.isEmpty()) {
            for (int i = 0; i < color.size() - 1; i++) {
                colorSQL += "color_id = " + color.get(i) + " OR ";
            }
            colorSQL += "color_id = " + color.get(color.size() - 1);
        }
        //add AND if user input price or category or collection and color
        if ((!priceSQL.isEmpty() || !category.isEmpty() || !collection.isEmpty()) && !color.isEmpty()) {
            colorSQL = "(" + colorSQL + ")";
            colorSQL = " AND " + colorSQL;
        }

        String sql = "WITH counts AS (SELECT name, COUNT(name) as quan "
                + "FROM Order_Details_HE176764, [Product_HE176764] "
                + "WHERE Order_Details_HE176764.product_id = [Product_HE176764].id "
                + "GROUP BY name),\n"
                + "reps AS (SELECT MAX(id) as rep_id, name from [Product_HE176764]"
                + " WHERE " + priceSQL + " " + categorySQL + collectionSQL + colorSQL
                + " GROUP BY name),\n"
                + "rep_pro AS (SELECT rep_id, reps.name, price, discount, thumbnail, "
                + "category_id, collection_id, color_id, "
                + "created_at, status FROM [Product_HE176764], reps "
                + "WHERE rep_id = [Product_HE176764].id)\n"
                + "SELECT rep_id, rep_pro.name, price, category_id, collection_id, "
                + "color_id, discount, thumbnail, status, "
                + "COALESCE(quan, 0) as quantity FROM rep_pro \n"
                + "LEFT JOIN counts ON rep_pro.name = counts.name";
//        testSQL = sql;
        try {
            Connection con = new DBContext().getConnection();

            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                int price = rs.getInt(3);
                int categoryId = rs.getInt(4);
                int collectionId = rs.getInt(5);
                int colorId = rs.getInt(6);
                int discount = rs.getInt(7);
                String thumbnail = rs.getString(8);
                int status = rs.getInt(9);
                int quantity = rs.getInt(10);
                productFilter.add(new Product(id, name, price, categoryId, collectionId, colorId, 0, discount, thumbnail, "", status, ""));
                productBuyQuantity.add(quantity);

            }
            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {
            status = "Error " + e.getMessage();
        }
    }

    public void searchProductByName(String textSearch) {
        productFilter = new ArrayList<>();
        productBuyQuantity = new ArrayList<>();
        String sql = "WITH counts AS (SELECT name, COUNT(name) as quan "
                + "FROM Order_Details_HE176764, [Product_HE176764] "
                + "WHERE Order_Details_HE176764.product_id "
                + "= [Product_HE176764].id GROUP BY name),\n"
                + "reps AS (SELECT MAX(id) as rep_id, name from [Product_HE176764] "
                + "GROUP BY name),\n"
                + "rep_pro AS (SELECT rep_id, reps.name, price, category_id, "
                + "collection_id, color_id, discount, thumbnail, "
                + "status FROM [Product_HE176764], reps WHERE rep_id = [Product_HE176764].id)\n"
                + "SELECT rep_id, rep_pro.name, price, category_id, collection_id, "
                + "color_id, discount, thumbnail, "
                + "COALESCE(quan, 0) as quantity, status FROM rep_pro \n"
                + "LEFT JOIN counts ON rep_pro.name = counts.name "
                + "WHERE (status = 1 OR status = 2) AND rep_pro.name LIKE ?";
        try {
            Connection con = new DBContext().getConnection();

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + textSearch + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                int price = rs.getInt(3);
                int categoryId = rs.getInt(4);
                int collectionId = rs.getInt(5);
                int colorId = rs.getInt(6);
                int discount = rs.getInt(7);
                String thumbnail = rs.getString(8);
                int quantity = rs.getInt(9);
                int status = rs.getInt(10);
                productFilter.add(new Product(id, name, price, categoryId, collectionId, colorId, 0, discount, thumbnail, "", status, ""));
                productBuyQuantity.add(quantity);
            }
            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {
            status = "Error " + e.getMessage();
        }
    }

    public void loadColorByProductName(String productName) {
        colors = new ArrayList<>();
        String sql = "	SELECT * FROM [Color_HE176764] WHERE id in "
                + "(SELECT DISTINCT color_id FROM [Product_HE176764] "
                + "WHERE name like N'" + productName + "')";
        try {
            Connection con = new DBContext().getConnection();

            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String colorcode = rs.getString(3);
                colors.add(new Color(id, name, colorcode));
            }
            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {
            status = "Error " + e.getMessage();
        }
    }

    public void loadProductByName(String productName) {
        products = new ArrayList<>();
        String sql = "SELECT * FROM [Product_HE176764] "
                + "WHERE name like '%" + productName + "%'";
        try {
            Connection con = new DBContext().getConnection();

            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                int price = rs.getInt(3);
                int categoryId = rs.getInt(4);
                int collectionId = rs.getInt(5);
                int colorId = rs.getInt(6);
                int sizeId = rs.getInt(7);
                int discount = rs.getInt(8);
                String thumbnail = rs.getString(9);
                String description = rs.getString(10);
                int status = rs.getInt(11);
                String created_at = rs.getString(12);
                products.add(new Product(id, name, price, categoryId, collectionId, colorId, sizeId, discount, thumbnail, description, status, created_at));
            }
            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {
            status = "Error " + e.getMessage();
        }
    }

    public int loadProductIdByAttributes(String productName, int colorId, int sizeId) {
        int id = 0;
        String sql = "SELECT id FROM [Product_HE176764] WHERE "
                + "name LIKE N'" + productName + "' "
                + "AND color_id = ? AND size_id = ?";
        try {
            Connection con = new DBContext().getConnection();

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, colorId);
            ps.setInt(2, sizeId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1);
            }
            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
            status = "Error " + e.getMessage();
        }
        return id;
    }

    public Product loadProductById(int productId) {
        Product pro = new Product();
        String sql = " SELECT * FROM [Product_HE176764] where id = ?";
        try {
            Connection con = new DBContext().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, productId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                int price = rs.getInt(3);
                int categoryId = rs.getInt(4);
                int collectionId = rs.getInt(5);
                int colorId = rs.getInt(6);
                int sizeId = rs.getInt(7);
                int discount = rs.getInt(8);
                String thumbnail = rs.getString(9);
                String description = rs.getString(10);
                int status = rs.getInt(11);
                String created_at = rs.getString(12);
                pro = new Product(id, name, price, categoryId, collectionId, colorId, sizeId, discount, thumbnail, description, status, created_at);
            }
            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
            status = "Error " + e.getMessage();
        }
        return pro;
    }

    public void loadStats() {
        stats = new ArrayList<>();
        String sql = "select * from Status_HE176764";
        try {
            Connection con = new DBContext().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                stats.add(new Status(id, name));
            }
            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {
            status = "Error " + e.getMessage();
        }
    }

    public void loadProductsAll() {
        products = new ArrayList<>();
        String sql = "SELECT * FROM [Product_HE176764]";
        try {
            Connection con = new DBContext().getConnection();

            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                int price = rs.getInt(3);
                int categoryId = rs.getInt(4);
                int collectionId = rs.getInt(5);
                int colorId = rs.getInt(6);
                int sizeId = rs.getInt(7);
                int discount = rs.getInt(8);
                String thumbnail = rs.getString(9);
                String description = rs.getString(10);
                int status = rs.getInt(11);
                String createAt = rs.getString(12);
                products.add(new Product(id, name, price, categoryId, collectionId, colorId, sizeId, discount, thumbnail, description, status, createAt));
            }
            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {
            status = "Error " + e.getMessage();
        }
    }

    public boolean checkUsernameSame(String name) {
        boolean hasUser = false;
        String sql = " SELECT username FROM [Account_HE176764] where username like ?";
        try {
            Connection con = new DBContext().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String username = rs.getString(1);
                if (name != null) {
                    hasUser = true;
                }
            }
            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
            status = "Error " + e.getMessage();
        }
        return hasUser;
    }

    public int loadNumberOfProducts() {
        int total = 0;
        String sql = "SELECT COUNT(*) AS total FROM [Product_HE176764];";
        try {
            Connection con = new DBContext().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                total = rs.getInt(1);
            }
            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
            status = "Error " + e.getMessage();
        }
        return total;
    }

    public void insertProduct(String name, int price, int category, int collection, int color, int size, int discount, String thumbnail, int stat, String description) {
        String sql = "insert into Product_HE176764 (name, price, category_id, "
                + "collection_id, color_id, size_id, discount, thumbnail, "
                + "status, description) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            Connection con = new DBContext().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setInt(2, price);
            ps.setInt(3, category);
            ps.setInt(4, collection);
            ps.setInt(5, color);
            ps.setInt(6, size);
            ps.setInt(7, discount);
            ps.setString(8, thumbnail);
            ps.setInt(9, stat);
            ps.setString(10, description);
            ps.execute();
            ps.close();
            con.close();
        } catch (Exception e) {
            status = "Error at insert " + e.getMessage();
        }

    }

    public void updateProduct(int id, String name, int price, int category, int collection, int color, int size, int discount, String thumbnail, int stat, String description) {
        String sql = "UPDATE Product_HE176764 set name = ?, price = ?, category_id = ?, "
                + "collection_id = ?, color_id = ?, size_id = ?, discount = ?, "
                + "thumbnail = ?, [status] = ?, [description] = ? "
                + "WHERE id = ?";
        try {
            Connection con = new DBContext().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setInt(2, price);
            ps.setInt(3, category);
            ps.setInt(4, collection);
            ps.setInt(5, color);
            ps.setInt(6, size);
            ps.setInt(7, discount);
            ps.setString(8, thumbnail);
            ps.setInt(9, stat);
            ps.setString(10, description);
            ps.setInt(11, id);
            ps.execute();
            ps.close();
            con.close();
        } catch (Exception e) {
            status = "Error at insert " + e.getMessage();
        }
    }

    public void deleteProduct(int id) {
        String sql = "delete from Product_HE176764 where id = ?";
        try {
            Connection con = new DBContext().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            ps.close();
            con.close();
        } catch (Exception e) {
            status = "Error at delete " + e.getMessage();
        }
    }

    public int insertOrder(int accId, String fullname, String email, String phoneNumber, String address, String note) {
        int orderId = 0;
        String insertSql = "INSERT INTO Orders_HE176764 "
                + "(account_id, fullname, email, phone_number, address, note)\n"
                + "VALUES (?, ?, ?, ?, ?, ?);";
        String selectSql = "SELECT MAX(id) as id FROM Orders_HE176764;";
        try {
            Connection con = new DBContext().getConnection();
            //insert
            PreparedStatement ps = con.prepareStatement(insertSql);
            ps.setInt(1, accId);
            ps.setString(2, fullname);
            ps.setString(3, email);
            ps.setString(4, phoneNumber);
            ps.setString(5, address);
            ps.setString(6, note);
            ps.execute();
            ps.close();

            //get id
            PreparedStatement selectPs = con.prepareStatement(selectSql);
            ResultSet rs = selectPs.executeQuery();
            while (rs.next()) {
                orderId = rs.getInt(1);
            }
            rs.close();
            selectPs.close();

            con.close();
        } catch (Exception e) {
            status = "Error " + e.getMessage();
        }
        return orderId;
    }

    public void insertOrderDetail(int orderId, int productId, int quantity) {
        String sql = "insert into Order_Details_HE176764 (order_id, product_id, "
                + "quantity) values (?, ?, ?)";
        try {
            Connection con = new DBContext().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, orderId);
            ps.setInt(2, productId);
            ps.setInt(3, quantity);
            ps.execute();
            ps.close();
            con.close();
        } catch (Exception e) {
            status = "Error " + e.getMessage();
        }
    }

    public int loadRoleAccountByUsername(String username) {
        int role = 0;
        String sql = "SELECT role_id FROM [Account_HE176764] "
                + "WHERE username LIKE ?";
        try {
            Connection con = new DBContext().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                role = rs.getInt(1);
            }
            rs.close();
            ps.close();
            con.close();
            testSQL = sql;
        } catch (Exception e) {
            status = "Error " + e.getMessage();
        }
        return role;
    }

    public static void main(String[] args) {
        DAO d = new DAO();
        ArrayList<Integer> cat = new ArrayList<>();
        cat.add(3);
        ArrayList<Integer> col = new ArrayList<>();
//        col.add(1);
//        col.add(2);
        ArrayList<Integer> color = new ArrayList<>();
        color.add(1);
//        d.loadColorByProduct    
//        d.loadSize();
//        d.searchProductByName("con");
//        d.loadProductWithFilter(cat, col, color, -1, -1);
        System.out.println(d.testSQL);
        System.out.println(d.status);
//        for (int i = 0; i < d.getProductFilter().size(); i++) {
//            System.out.print(d.getProductFilter().get(i).getName() + " ");
//            System.out.println(d.productBuyQuantity.get(i));
//        }
//        d.loadProductDateDesc();
//        d.loadProductsAll();
//        d.loadStats();
//        for (int i = 0; i < d.stats.size(); i++) {
//            System.out.println(d.stats.get(i).getName());
//        }
//        System.out.println(d.insertOrder(2, "Lê hồ đức phú", "hhhhhhhhhh", "999999999999999999", "Lê hồ đức phú", "Lê hồ đức phú"));
//        d.insertOrderDetail(1, 3, 3);
System.out.println(d.loadRoleAccountByUsername("raspberry719"));
    }

}

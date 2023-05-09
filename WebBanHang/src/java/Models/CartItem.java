/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author LENOVO
 */
public class CartItem
{
    private int productId;
    private String name;
    private int pricePerItem;
    private int quantity;

    public CartItem() {
    }

    public CartItem(int productId, String name, int pricePerItem, int quantity) {
        this.productId = productId;
        this.name = name;
        this.pricePerItem = pricePerItem;
        this.quantity = quantity;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPricePerItem() {
        return pricePerItem;
    }

    public void setPricePerItem(int pricePerItem) {
        this.pricePerItem = pricePerItem;
    }
    
    public int getItemTotalPrice(){
        int total = pricePerItem * quantity;
        return total;
    }
    
    
    
    
}
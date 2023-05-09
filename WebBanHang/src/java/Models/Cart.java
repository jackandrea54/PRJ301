/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author LENOVO
 */
public class Cart {
    private int accountId;
    private Map<Integer, CartItem> items;

    public Cart() {
    }

    public Cart(int accountId) {
        this.accountId = accountId;
        items = new HashMap<Integer, CartItem>();
    }

    public void addItem(int itemId, String name, int pricePerItem,int quantity) {
        if (items.containsKey(itemId)) {
            CartItem current = items.get(itemId);
            int currentQuantity = current.getQuantity();
            current.setQuantity(currentQuantity+quantity);
            items.replace(itemId, current);
        } else {
            items.put(itemId, new CartItem(itemId, name, pricePerItem, quantity));
        }
    }

    public void removeItem(int itemId) {
        items.remove(itemId);
    }

    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public int getTotalPrice() {
        int totalPrice = 0;
        for (Map.Entry<Integer, CartItem> entry : items.entrySet()) {
            int itemId = entry.getKey();
            int itemTotalPrice = entry.getValue().getItemTotalPrice();
            totalPrice += itemTotalPrice;
        }
        return totalPrice;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    @Override
    public String toString() {
        return super.toString(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
    
    
    
}


package com.restaurant.model;

/**
 * Represents an individual item within an order
 * 
 * This class models a single menu item that is part of an order.
 */
public class OrderItem {
    private int itemId;
    private int orderId;
    private String itemName;
    private int quantity;
    private double price;
    private String notes;

    /**
     * Default constructor
     */
    public OrderItem() {
        this.quantity = 1;
    }

    /**
     * Constructor with parameters
     * 
     * @param orderId ID of the order this item belongs to
     * @param itemName Name of the menu item
     * @param quantity Quantity ordered
     * @param price Price per unit
     */
    public OrderItem(int orderId, String itemName, int quantity, double price) {
        this.orderId = orderId;
        this.itemName = itemName;
        this.quantity = quantity;
        this.price = price;
    }

    /**
     * Calculates the total price for this item (price * quantity)
     * 
     * @return Total price for this item
     */
    public double getTotalPrice() {
        return price * quantity;
    }

    // Getters and Setters
    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return String.format("%s x%d - $%.2f", itemName, quantity, getTotalPrice());
    }
}


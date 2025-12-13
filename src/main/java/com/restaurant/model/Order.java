package com.restaurant.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a restaurant order
 * 
 * This class models an order placed by customers, including order details,
 * status, priority, and timing information used for scheduling algorithms.
 */
public class Order {
    private int orderId;
    private int tableId;
    private Integer staffId;
    private String orderNumber;
    private OrderStatus status;
    private int priority;  // 1 = Highest priority, 10 = Lowest priority
    private int estimatedTime;  // Estimated preparation time in minutes
    private Integer actualTime;  // Actual preparation time in minutes
    private double totalAmount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<OrderItem> items;

    /**
     * Enumeration for order status
     */
    public enum OrderStatus {
        PENDING,    // Order just placed, waiting to be processed
        PREPARING,  // Order is being prepared
        READY,      // Order is ready to be served
        SERVED,     // Order has been served to customers
        CANCELLED   // Order was cancelled
    }

    /**
     * Default constructor
     */
    public Order() {
        this.items = new ArrayList<>();
        this.priority = 5;  // Default priority
        this.status = OrderStatus.PENDING;
    }

    /**
     * Constructor with essential parameters
     * 
     * @param tableId ID of the table placing the order
     * @param orderNumber Unique order number
     * @param priority Priority level (1-10)
     * @param estimatedTime Estimated preparation time in minutes
     */
    public Order(int tableId, String orderNumber, int priority, int estimatedTime) {
        this();
        this.tableId = tableId;
        this.orderNumber = orderNumber;
        this.priority = priority;
        this.estimatedTime = estimatedTime;
    }

    /**
     * Adds an item to the order
     * 
     * @param item Order item to add
     */
    public void addItem(OrderItem item) {
        if (items == null) {
            items = new ArrayList<>();
        }
        items.add(item);
        totalAmount += item.getPrice() * item.getQuantity();
    }

    // Getters and Setters
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(int estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public Integer getActualTime() {
        return actualTime;
    }

    public void setActualTime(Integer actualTime) {
        this.actualTime = actualTime;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return String.format("Order %s (Table: %d, Priority: %d, Time: %d min, Amount: $%.2f)", 
            orderNumber, tableId, priority, estimatedTime, totalAmount);
    }
}


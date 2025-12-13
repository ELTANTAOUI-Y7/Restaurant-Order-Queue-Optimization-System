package com.restaurant.model;

/**
 * Represents a restaurant table
 * 
 * This class models a physical table in the restaurant with properties
 * such as table number, capacity, and current status.
 */
public class Table {
    private int tableId;
    private int tableNumber;
    private int capacity;
    private TableStatus status;

    /**
     * Enumeration for table status
     */
    public enum TableStatus {
        AVAILABLE,   // Table is free and ready for customers
        OCCUPIED,   // Table currently has customers
        RESERVED    // Table is reserved for future use
    }

    /**
     * Default constructor
     */
    public Table() {
    }

    /**
     * Constructor with parameters
     * 
     * @param tableId Unique identifier for the table
     * @param tableNumber Display number of the table
     * @param capacity Maximum number of customers the table can accommodate
     * @param status Current status of the table
     */
    public Table(int tableId, int tableNumber, int capacity, TableStatus status) {
        this.tableId = tableId;
        this.tableNumber = tableNumber;
        this.capacity = capacity;
        this.status = status;
    }

    // Getters and Setters
    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public TableStatus getStatus() {
        return status;
    }

    public void setStatus(TableStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format("Table #%d (Capacity: %d, Status: %s)", 
            tableNumber, capacity, status);
    }
}


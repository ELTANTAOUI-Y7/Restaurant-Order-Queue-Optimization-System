package com.restaurant.model;

/**
 * Represents a staff member (waiter, chef, manager)
 * 
 * This class models restaurant staff with their role and availability status.
 */
public class Staff {
    private int staffId;
    private String name;
    private StaffRole role;
    private StaffStatus status;

    /**
     * Enumeration for staff roles
     */
    public enum StaffRole {
        WAITER,   // Serves customers and takes orders
        CHEF,     // Prepares food
        MANAGER   // Oversees operations
    }

    /**
     * Enumeration for staff status
     */
    public enum StaffStatus {
        AVAILABLE,  // Staff member is free and ready to work
        BUSY,       // Staff member is currently handling tasks
        OFF_DUTY    // Staff member is not working
    }

    /**
     * Default constructor
     */
    public Staff() {
    }

    /**
     * Constructor with parameters
     * 
     * @param staffId Unique identifier for the staff member
     * @param name Name of the staff member
     * @param role Role of the staff member (WAITER, CHEF, MANAGER)
     * @param status Current availability status
     */
    public Staff(int staffId, String name, StaffRole role, StaffStatus status) {
        this.staffId = staffId;
        this.name = name;
        this.role = role;
        this.status = status;
    }

    // Getters and Setters
    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StaffRole getRole() {
        return role;
    }

    public void setRole(StaffRole role) {
        this.role = role;
    }

    public StaffStatus getStatus() {
        return status;
    }

    public void setStatus(StaffStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format("%s (%s) - %s", name, role, status);
    }
}


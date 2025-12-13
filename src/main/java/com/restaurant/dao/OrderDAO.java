package com.restaurant.dao;

import com.restaurant.database.DatabaseConnection;
import com.restaurant.model.Order;
import com.restaurant.model.OrderItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object for Order operations
 * 
 * This class handles all database operations related to orders,
 * including CRUD operations and querying orders by status.
 */
public class OrderDAO {
    private DatabaseConnection dbConnection;

    /**
     * Constructor
     */
    public OrderDAO() {
        this.dbConnection = DatabaseConnection.getInstance();
    }

    /**
     * Retrieves all pending orders from the database
     * 
     * @return List of pending orders
     */
    public List<Order> getAllPendingOrders() {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders WHERE status = 'PENDING' ORDER BY created_at ASC";

        try (Connection conn = dbConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("Querying pending orders...");
            int count = 0;
            while (rs.next()) {
                count++;
                try {
                    Order order = mapResultSetToOrder(rs);
                    System.out.println("Loading order: " + order.getOrderNumber() + " (ID: " + order.getOrderId() + ")");
                    order.setItems(getOrderItems(order.getOrderId(), conn));
                    orders.add(order);
                    System.out.println("Successfully loaded order: " + order.getOrderNumber());
                } catch (Exception e) {
                    System.err.println("Error processing order ID " + rs.getInt("order_id") + ": " + e.getMessage());
                    e.printStackTrace();
                    // Continue processing other orders even if one fails
                }
            }
            System.out.println("Total orders found: " + count + ", successfully loaded: " + orders.size());
        } catch (SQLException e) {
            System.err.println("Error retrieving pending orders: " + e.getMessage());
            e.printStackTrace();
        }

        return orders;
    }

    /**
     * Retrieves all orders from the database
     * 
     * @return List of all orders
     */
    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders ORDER BY created_at DESC";

        try (Connection conn = dbConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                try {
                    Order order = mapResultSetToOrder(rs);
                    order.setItems(getOrderItems(order.getOrderId(), conn));
                    orders.add(order);
                } catch (Exception e) {
                    System.err.println("Error processing order ID " + rs.getInt("order_id") + ": " + e.getMessage());
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving orders: " + e.getMessage());
            e.printStackTrace();
        }

        return orders;
    }

    /**
     * Retrieves order items for a specific order
     * 
     * @param orderId ID of the order
     * @param conn Database connection to reuse (to avoid connection issues)
     * @return List of order items
     */
    private List<OrderItem> getOrderItems(int orderId, Connection conn) {
        List<OrderItem> items = new ArrayList<>();
        String sql = "SELECT * FROM order_items WHERE order_id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, orderId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                OrderItem item = new OrderItem();
                item.setItemId(rs.getInt("item_id"));
                item.setOrderId(rs.getInt("order_id"));
                item.setItemName(rs.getString("item_name"));
                item.setQuantity(rs.getInt("quantity"));
                item.setPrice(rs.getDouble("price"));
                item.setNotes(rs.getString("notes"));
                items.add(item);
            }
            rs.close();
        } catch (SQLException e) {
            System.err.println("Error retrieving order items for order " + orderId + ": " + e.getMessage());
            e.printStackTrace();
        }

        return items;
    }

    /**
     * Maps a ResultSet row to an Order object
     * 
     * @param rs ResultSet containing order data
     * @return Order object
     * @throws SQLException if database error occurs
     */
    private Order mapResultSetToOrder(ResultSet rs) throws SQLException {
        Order order = new Order();
        order.setOrderId(rs.getInt("order_id"));
        order.setTableId(rs.getInt("table_id"));
        
        int staffId = rs.getInt("staff_id");
        if (!rs.wasNull()) {
            order.setStaffId(staffId);
        }
        
        order.setOrderNumber(rs.getString("order_number"));
        order.setStatus(Order.OrderStatus.valueOf(rs.getString("status")));
        order.setPriority(rs.getInt("priority"));
        order.setEstimatedTime(rs.getInt("estimated_time"));
        
        int actualTime = rs.getInt("actual_time");
        if (!rs.wasNull()) {
            order.setActualTime(actualTime);
        }
        
        order.setTotalAmount(rs.getDouble("total_amount"));
        
        Timestamp createdAt = rs.getTimestamp("created_at");
        if (createdAt != null) {
            order.setCreatedAt(createdAt.toLocalDateTime());
        }
        
        Timestamp updatedAt = rs.getTimestamp("updated_at");
        if (updatedAt != null) {
            order.setUpdatedAt(updatedAt.toLocalDateTime());
        }

        return order;
    }

    /**
     * Updates the status of an order
     * 
     * @param orderId ID of the order to update
     * @param status New status
     * @return true if update successful, false otherwise
     */
    public boolean updateOrderStatus(int orderId, Order.OrderStatus status) {
        String sql = "UPDATE orders SET status = ? WHERE order_id = ?";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, status.name());
            pstmt.setInt(2, orderId);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error updating order status: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}


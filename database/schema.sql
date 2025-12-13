-- =====================================================
-- Restaurant Order & Queue Optimization System
-- Database Schema
-- =====================================================

-- Create database
CREATE DATABASE IF NOT EXISTS restaurant_db;
USE restaurant_db;

-- =====================================================
-- Tables Table
-- Stores restaurant table information
-- =====================================================
CREATE TABLE IF NOT EXISTS tables (
    table_id INT PRIMARY KEY AUTO_INCREMENT,
    table_number INT NOT NULL UNIQUE,
    capacity INT NOT NULL,
    status ENUM('AVAILABLE', 'OCCUPIED', 'RESERVED') DEFAULT 'AVAILABLE',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    INDEX idx_status (status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- =====================================================
-- Staff Table
-- Stores staff/waiter information
-- =====================================================
CREATE TABLE IF NOT EXISTS staff (
    staff_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    role ENUM('WAITER', 'CHEF', 'MANAGER') DEFAULT 'WAITER',
    status ENUM('AVAILABLE', 'BUSY', 'OFF_DUTY') DEFAULT 'AVAILABLE',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    INDEX idx_status (status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- =====================================================
-- Orders Table
-- Stores order information
-- =====================================================
CREATE TABLE IF NOT EXISTS orders (
    order_id INT PRIMARY KEY AUTO_INCREMENT,
    table_id INT NOT NULL,
    staff_id INT,
    order_number VARCHAR(20) NOT NULL UNIQUE,
    status ENUM('PENDING', 'PREPARING', 'READY', 'SERVED', 'CANCELLED') DEFAULT 'PENDING',
    priority INT DEFAULT 5 COMMENT '1=Highest, 10=Lowest',
    estimated_time INT NOT NULL COMMENT 'Estimated preparation time in minutes',
    actual_time INT COMMENT 'Actual preparation time in minutes',
    total_amount DECIMAL(10, 2) DEFAULT 0.00,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (table_id) REFERENCES tables(table_id) ON DELETE CASCADE,
    FOREIGN KEY (staff_id) REFERENCES staff(staff_id) ON DELETE SET NULL,
    INDEX idx_status (status),
    INDEX idx_priority (priority),
    INDEX idx_created_at (created_at)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- =====================================================
-- Order Items Table
-- Stores individual items in each order
-- =====================================================
CREATE TABLE IF NOT EXISTS order_items (
    item_id INT PRIMARY KEY AUTO_INCREMENT,
    order_id INT NOT NULL,
    item_name VARCHAR(200) NOT NULL,
    quantity INT NOT NULL DEFAULT 1,
    price DECIMAL(10, 2) NOT NULL,
    notes TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (order_id) REFERENCES orders(order_id) ON DELETE CASCADE,
    INDEX idx_order_id (order_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- =====================================================
-- Sample Data
-- =====================================================

-- Insert sample tables
INSERT INTO tables (table_number, capacity, status) VALUES
(1, 4, 'AVAILABLE'),
(2, 2, 'OCCUPIED'),
(3, 6, 'AVAILABLE'),
(4, 4, 'OCCUPIED'),
(5, 8, 'AVAILABLE');

-- Insert sample staff
INSERT INTO staff (name, role, status) VALUES
('John Smith', 'WAITER', 'AVAILABLE'),
('Sarah Johnson', 'WAITER', 'AVAILABLE'),
('Mike Davis', 'CHEF', 'AVAILABLE'),
('Emily Brown', 'WAITER', 'BUSY'),
('David Wilson', 'MANAGER', 'AVAILABLE');

-- Insert sample orders
INSERT INTO orders (table_id, staff_id, order_number, status, priority, estimated_time, total_amount) VALUES
(1, 1, 'ORD-001', 'PENDING', 3, 15, 45.50),
(2, 2, 'ORD-002', 'PENDING', 5, 20, 78.00),
(3, 1, 'ORD-003', 'PENDING', 2, 10, 32.25),
(4, 4, 'ORD-004', 'PENDING', 7, 25, 95.75),
(5, 2, 'ORD-005', 'PENDING', 4, 18, 62.00);

-- Insert sample order items
INSERT INTO order_items (order_id, item_name, quantity, price) VALUES
(1, 'Caesar Salad', 2, 12.50),
(1, 'Grilled Chicken', 1, 20.50),
(2, 'Pasta Carbonara', 2, 24.00),
(2, 'Garlic Bread', 1, 6.00),
(2, 'Tiramisu', 1, 8.00),
(3, 'Soup of the Day', 1, 8.25),
(3, 'Burger', 1, 15.00),
(3, 'Fries', 1, 9.00),
(4, 'Steak', 2, 35.00),
(4, 'Wine', 1, 25.75),
(5, 'Pizza Margherita', 1, 18.00),
(5, 'Pizza Pepperoni', 1, 20.00),
(5, 'Soft Drinks', 2, 12.00);


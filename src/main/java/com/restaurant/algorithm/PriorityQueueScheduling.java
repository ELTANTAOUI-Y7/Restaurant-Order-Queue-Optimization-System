package com.restaurant.algorithm;

import com.restaurant.model.Order;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Priority Queue Scheduling Algorithm
 * 
 * This algorithm schedules orders based on their priority level.
 * Orders with lower priority numbers (1 = highest) are processed first.
 * If two orders have the same priority, they are ordered by creation time (FIFO).
 * 
 * Algorithm: Orders are sorted by priority (ascending), then by creation time.
 * 
 * Advantages:
 * - Ensures high-priority orders are handled first
 * - Useful for VIP customers or urgent orders
 * - Fair for orders with same priority
 * 
 * Disadvantages:
 * - Low-priority orders may wait longer
 * - Doesn't consider processing time
 */
public class PriorityQueueScheduling implements SchedulingAlgorithm {

    @Override
    public List<Order> schedule(List<Order> orders) {
        // Create a copy to avoid modifying the original list
        List<Order> scheduledOrders = new ArrayList<>(orders);
        
        // Sort by priority (ascending - lower number = higher priority)
        // If priorities are equal, sort by creation time (FIFO)
        scheduledOrders.sort(Comparator
            .comparingInt(Order::getPriority)
            .thenComparing(order -> order.getCreatedAt() != null ? order.getCreatedAt() : java.time.LocalDateTime.MIN));
        
        return scheduledOrders;
    }

    @Override
    public String getAlgorithmName() {
        return "Priority Queue Scheduling";
    }

    @Override
    public String getDescription() {
        return "Orders are processed based on priority level (1 = highest priority, 10 = lowest). " +
               "High-priority orders are always served before low-priority ones. " +
               "Orders with the same priority are processed in first-come-first-served order.";
    }
}


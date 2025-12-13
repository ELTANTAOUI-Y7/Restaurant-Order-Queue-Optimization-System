package com.restaurant.algorithm;

import com.restaurant.model.Order;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Shortest Processing Time (SPT) Scheduling Algorithm
 * 
 * This algorithm schedules orders based on their estimated processing time,
 * prioritizing orders that take the least time to complete. This minimizes
 * average waiting time and improves overall throughput.
 * 
 * Algorithm: Orders are sorted by estimated_time in ascending order.
 * 
 * Advantages:
 * - Minimizes average waiting time
 * - Maximizes throughput
 * - Simple and efficient
 * 
 * Disadvantages:
 * - May starve longer orders
 * - Doesn't consider priority
 */
public class ShortestProcessingTime implements SchedulingAlgorithm {

    @Override
    public List<Order> schedule(List<Order> orders) {
        // Create a copy to avoid modifying the original list
        List<Order> scheduledOrders = new ArrayList<>(orders);
        
        // Sort by estimated time (shortest first)
        scheduledOrders.sort(Comparator.comparingInt(Order::getEstimatedTime));
        
        return scheduledOrders;
    }

    @Override
    public String getAlgorithmName() {
        return "Shortest Processing Time (SPT)";
    }

    @Override
    public String getDescription() {
        return "Orders are processed based on shortest estimated time first. " +
               "This minimizes average waiting time and maximizes throughput. " +
               "Orders with shorter preparation times are served before longer ones.";
    }
}


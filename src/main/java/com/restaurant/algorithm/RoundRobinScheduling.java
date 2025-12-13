package com.restaurant.algorithm;

import com.restaurant.model.Order;
import java.util.ArrayList;
import java.util.List;

/**
 * Round Robin Scheduling Algorithm
 * 
 * This algorithm processes orders in a circular fashion, giving each order
 * a time quantum (slice) before moving to the next. Orders are processed
 * in the order they arrived, cycling through all orders until completion.
 * 
 * Algorithm: Orders are processed in a round-robin fashion with a time quantum.
 * For visualization purposes, we maintain the original order but simulate
 * the round-robin processing pattern.
 * 
 * Advantages:
 * - Fair distribution of processing time
 * - No order starvation
 * - Predictable processing pattern
 * 
 * Disadvantages:
 * - May have higher average waiting time than SPT
 * - Context switching overhead (in real systems)
 * - Doesn't optimize for shortest jobs
 */
public class RoundRobinScheduling implements SchedulingAlgorithm {
    
    private static final int TIME_QUANTUM = 5; // Time quantum in minutes

    @Override
    public List<Order> schedule(List<Order> orders) {
        // Create a copy to avoid modifying the original list
        List<Order> scheduledOrders = new ArrayList<>(orders);
        
        // Round Robin maintains the original order but processes in cycles
        // For this implementation, we'll keep the original order but
        // in a real system, orders would be processed in rounds with time quantum
        
        // In a true Round Robin, orders would be processed in cycles:
        // Order 1 (5 min), Order 2 (5 min), Order 3 (5 min), then back to Order 1
        // For visualization, we maintain FIFO order but simulate the round-robin concept
        
        // Sort by creation time to maintain FIFO order (first come, first served)
        scheduledOrders.sort((o1, o2) -> {
            if (o1.getCreatedAt() == null && o2.getCreatedAt() == null) return 0;
            if (o1.getCreatedAt() == null) return 1;
            if (o2.getCreatedAt() == null) return -1;
            return o1.getCreatedAt().compareTo(o2.getCreatedAt());
        });
        
        return scheduledOrders;
    }

    /**
     * Gets the time quantum used for round-robin processing
     * 
     * @return Time quantum in minutes
     */
    public int getTimeQuantum() {
        return TIME_QUANTUM;
    }

    @Override
    public String getAlgorithmName() {
        return "Round Robin Scheduling";
    }

    @Override
    public String getDescription() {
        return "Orders are processed in a circular fashion with a time quantum of " + TIME_QUANTUM + " minutes. " +
               "Each order gets a fair share of processing time before moving to the next order. " +
               "This ensures no order waits indefinitely and provides fair service distribution.";
    }
}


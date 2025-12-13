package com.restaurant.algorithm;

import com.restaurant.model.Order;
import java.util.List;

/**
 * Interface for scheduling algorithms
 * 
 * All scheduling algorithms must implement this interface to provide
 * a consistent way to optimize order processing.
 */
public interface SchedulingAlgorithm {
    /**
     * Schedules orders using the specific algorithm
     * 
     * @param orders List of orders to schedule
     * @return List of orders in optimized processing order
     */
    List<Order> schedule(List<Order> orders);

    /**
     * Gets the name of the algorithm
     * 
     * @return Algorithm name
     */
    String getAlgorithmName();

    /**
     * Gets a description of how the algorithm works
     * 
     * @return Algorithm description
     */
    String getDescription();
}


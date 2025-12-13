# Project Architecture Documentation

## Overview

The Restaurant Order & Queue Optimization System follows a **layered architecture** pattern, separating concerns into distinct layers that communicate through well-defined interfaces.

## Architecture Layers

### 1. Presentation Layer (UI)
**Location**: `src/main/java/com/restaurant/ui/` and `src/main/resources/ui/`

**Components**:
- `RestaurantController.java`: Main UI controller handling user interactions
- `restaurant.fxml`: JavaFX layout definition

**Responsibilities**:
- Display orders in tables
- Handle user input (algorithm selection, refresh)
- Visualize optimization results
- Show statistics and status information

**Design Pattern**: MVC (Model-View-Controller)

---

### 2. Business Logic Layer (Algorithms)
**Location**: `src/main/java/com/restaurant/algorithm/`

**Components**:
- `SchedulingAlgorithm.java`: Interface defining algorithm contract
- `ShortestProcessingTime.java`: SPT algorithm implementation
- `PriorityQueueScheduling.java`: Priority-based scheduling
- `RoundRobinScheduling.java`: Round-robin algorithm

**Responsibilities**:
- Implement scheduling algorithms
- Optimize order processing sequences
- Provide algorithm descriptions

**Design Pattern**: Strategy Pattern (interchangeable algorithms)

---

### 3. Data Access Layer (DAO)
**Location**: `src/main/java/com/restaurant/dao/`

**Components**:
- `OrderDAO.java`: Data access operations for orders

**Responsibilities**:
- Retrieve orders from database
- Map database records to domain objects
- Update order status
- Handle database queries

**Design Pattern**: Data Access Object (DAO) Pattern

---

### 4. Model Layer (Domain Objects)
**Location**: `src/main/java/com/restaurant/model/`

**Components**:
- `Order.java`: Order entity with status, priority, timing
- `OrderItem.java`: Individual items in an order
- `Table.java`: Restaurant table information
- `Staff.java`: Staff member information

**Responsibilities**:
- Represent business entities
- Encapsulate business logic
- Provide data validation

**Design Pattern**: Domain Model Pattern

---

### 5. Database Layer
**Location**: `src/main/java/com/restaurant/database/` and `database/`

**Components**:
- `DatabaseConnection.java`: Connection manager (Singleton)
- `schema.sql`: Database schema and sample data

**Responsibilities**:
- Manage database connections
- Provide connection pooling (basic)
- Define database structure

**Design Pattern**: Singleton Pattern (for connection management)

---

## Data Flow

```
User Action (UI)
    ↓
RestaurantController
    ↓
SchedulingAlgorithm (selected)
    ↓
OrderDAO
    ↓
DatabaseConnection
    ↓
MySQL Database
    ↓
Results flow back up through layers
    ↓
UI displays optimized results
```

## Key Design Patterns Used

### 1. Singleton Pattern
- **Used in**: `DatabaseConnection`
- **Purpose**: Ensure only one database connection instance exists
- **Benefits**: Resource management, consistent connection handling

### 2. Strategy Pattern
- **Used in**: Scheduling algorithms
- **Purpose**: Allow runtime selection of different algorithms
- **Benefits**: Extensibility, easy to add new algorithms

### 3. DAO Pattern
- **Used in**: `OrderDAO`
- **Purpose**: Separate database operations from business logic
- **Benefits**: Maintainability, testability, database independence

### 4. MVC Pattern
- **Used in**: UI layer
- **Purpose**: Separate UI concerns (View, Controller, Model)
- **Benefits**: Code organization, easier maintenance

## Class Relationships

```
RestaurantController
    ├── uses → SchedulingAlgorithm (interface)
    │           ├── ShortestProcessingTime
    │           ├── PriorityQueueScheduling
    │           └── RoundRobinScheduling
    │
    └── uses → OrderDAO
                └── uses → DatabaseConnection
                            └── connects to → MySQL Database

OrderDAO
    └── returns → Order
                    ├── contains → List<OrderItem>
                    ├── references → Table
                    └── references → Staff
```

## Algorithm Selection Flow

1. User selects algorithm from ComboBox
2. Controller updates description label
3. User clicks "Apply Algorithm"
4. Controller retrieves pending orders from DAO
5. Controller applies selected algorithm to orders
6. Optimized order list is displayed in right table
7. Statistics are calculated and displayed

## Database Schema Relationships

```
tables (1) ────< (many) orders
staff (1) ────< (many) orders
orders (1) ────< (many) order_items
```

## Extension Points

### Adding New Algorithms
1. Create class implementing `SchedulingAlgorithm`
2. Implement required methods
3. Add to `RestaurantController.initializeAlgorithms()`

### Adding New Entities
1. Create model class in `model/` package
2. Create corresponding DAO class
3. Add database table in `schema.sql`
4. Update UI if needed

### Adding New Features
- **Order Status Updates**: Extend `OrderDAO.updateOrderStatus()`
- **New Statistics**: Add methods to `RestaurantController`
- **Additional Filters**: Extend `OrderDAO` with new query methods

## Security Considerations

- Database credentials are hardcoded (should use properties file in production)
- SQL injection prevention through PreparedStatements
- Input validation should be added for user inputs

## Performance Considerations

- Database connection is reused (Singleton pattern)
- Orders are loaded on-demand (not all at once)
- Algorithm operations work on copies (non-destructive)
- Table views use ObservableList for efficient updates

## Testing Recommendations

1. **Unit Tests**: Test each algorithm independently
2. **Integration Tests**: Test DAO with test database
3. **UI Tests**: Test controller with mock data
4. **Algorithm Tests**: Verify correct ordering for each algorithm

## Future Enhancements

1. **Configuration File**: Externalize database settings
2. **Logging**: Add proper logging framework
3. **Error Handling**: More robust error handling and recovery
4. **Real-time Updates**: WebSocket for live order updates
5. **Multi-threading**: Parallel order processing
6. **Caching**: Cache frequently accessed data
7. **Export**: Export order reports to PDF/Excel


# Restaurant Order & Queue Optimization System

A comprehensive Java application for managing restaurant orders and optimizing their processing using various scheduling algorithms. This university project demonstrates the implementation of different queue scheduling algorithms in a real-world restaurant management context.

## 📋 Table of Contents

- [Features](#features)
- [Project Architecture](#project-architecture)
- [Technologies Used](#technologies-used)
- [Database Schema](#database-schema)
- [Scheduling Algorithms](#scheduling-algorithms)
- [Setup Instructions](#setup-instructions)
- [Usage Guide](#usage-guide)
- [Project Structure](#project-structure)
- [Key Components](#key-components)

## ✨ Features

- **Order Management**: Create, view, and manage restaurant orders
- **Multiple Scheduling Algorithms**: 
  - Shortest Processing Time (SPT)
  - Priority Queue Scheduling
  - Round Robin Scheduling
- **Real-time Visualization**: Compare original and optimized order queues
- **Database Integration**: MySQL database for persistent data storage
- **Modern JavaFX UI**: Intuitive graphical interface for order management
- **Statistics Dashboard**: View order statistics and optimization metrics

## 🏗️ Project Architecture

The project follows a clean, layered architecture:

```
┌─────────────────────────────────────┐
│         JavaFX UI Layer             │
│    (RestaurantController, FXML)     │
└──────────────┬──────────────────────┘
               │
┌──────────────▼──────────────────────┐
│      Business Logic Layer           │
│   (Scheduling Algorithms)           │
└──────────────┬──────────────────────┘
               │
┌──────────────▼──────────────────────┐
│      Data Access Layer              │
│        (DAO Classes)                │
└──────────────┬──────────────────────┘
               │
┌──────────────▼──────────────────────┐
│      Database Layer                 │
│    (MySQL Database)                 │
└─────────────────────────────────────┘
```

### Architecture Layers:

1. **Presentation Layer**: JavaFX UI components and controllers
2. **Business Logic Layer**: Scheduling algorithms and order processing logic
3. **Data Access Layer**: DAO classes for database operations
4. **Model Layer**: Domain objects (Order, Table, Staff, OrderItem)
5. **Database Layer**: MySQL database with relational schema

## 🛠️ Technologies Used

- **Java 11**: Core programming language
- **JavaFX 17**: User interface framework
- **MySQL 8.0**: Relational database management system
- **Maven**: Dependency management and build tool
- **JDBC**: Database connectivity

## 🗄️ Database Schema

The database consists of four main tables:

### Tables Table
- `table_id` (Primary Key)
- `table_number` (Unique)
- `capacity`
- `status` (AVAILABLE, OCCUPIED, RESERVED)

### Staff Table
- `staff_id` (Primary Key)
- `name`
- `role` (WAITER, CHEF, MANAGER)
- `status` (AVAILABLE, BUSY, OFF_DUTY)

### Orders Table
- `order_id` (Primary Key)
- `table_id` (Foreign Key)
- `staff_id` (Foreign Key)
- `order_number` (Unique)
- `status` (PENDING, PREPARING, READY, SERVED, CANCELLED)
- `priority` (1-10, where 1 is highest)
- `estimated_time` (minutes)
- `actual_time` (minutes)
- `total_amount`

### Order Items Table
- `item_id` (Primary Key)
- `order_id` (Foreign Key)
- `item_name`
- `quantity`
- `price`
- `notes`

## 📊 Scheduling Algorithms

### 1. Shortest Processing Time (SPT)

**How it works**: Orders are sorted by their estimated processing time in ascending order. Orders with shorter preparation times are processed first.

**Advantages**:
- Minimizes average waiting time
- Maximizes throughput
- Simple and efficient implementation

**Disadvantages**:
- May cause longer orders to wait indefinitely (starvation)
- Doesn't consider order priority

**Use Case**: Best for maximizing overall efficiency when all orders have similar importance.

### 2. Priority Queue Scheduling

**How it works**: Orders are sorted by priority level (1 = highest, 10 = lowest). Orders with the same priority are processed in first-come-first-served order.

**Advantages**:
- Ensures high-priority orders are handled first
- Useful for VIP customers or urgent orders
- Fair processing for orders with same priority

**Disadvantages**:
- Low-priority orders may wait significantly longer
- Doesn't consider processing time efficiency

**Use Case**: Best when order importance varies (e.g., VIP customers, time-sensitive orders).

### 3. Round Robin Scheduling

**How it works**: Orders are processed in a circular fashion with a time quantum. Each order gets a fair share of processing time before moving to the next.

**Advantages**:
- Fair distribution of processing time
- No order starvation
- Predictable processing pattern

**Disadvantages**:
- May have higher average waiting time than SPT
- Doesn't optimize for shortest jobs

**Use Case**: Best for ensuring fairness when all orders should receive equal attention.

## 🚀 Setup Instructions

### Prerequisites

1. **Java Development Kit (JDK) 11 or higher**
   - Download from [Oracle](https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html) or [OpenJDK](https://adoptium.net/)
   - Verify installation: `java -version`

2. **MySQL Server 8.0 or higher**
   - Download from [MySQL Official Site](https://dev.mysql.com/downloads/mysql/)
   - Install and start MySQL service
   - Note your MySQL root password

3. **Maven 3.6 or higher**
   - Download from [Apache Maven](https://maven.apache.org/download.cgi)
   - Add to PATH environment variable
   - Verify installation: `mvn -version`

### Step 1: Clone or Download the Project

Extract the project to your desired location.

### Step 2: Set Up MySQL Database

1. Open MySQL Command Line Client or MySQL Workbench
2. Run the schema file to create the database and tables:
   ```sql
   source database/schema.sql
   ```
   Or manually execute the SQL commands from `database/schema.sql`

3. Verify the database was created:
   ```sql
   USE restaurant_db;
   SHOW TABLES;
   ```

### Step 3: Configure Database Connection

1. Open `src/main/java/com/restaurant/database/DatabaseConnection.java`
2. Update the database credentials:
   ```java
   private static final String DB_URL = "jdbc:mysql://localhost:3306/restaurant_db";
   private static final String DB_USER = "root";
   private static final String DB_PASSWORD = "your_mysql_password";
   ```

### Step 4: Build the Project

Open a terminal in the project root directory and run:

```bash
mvn clean compile
```

This will download all dependencies and compile the project.

### Step 5: Run the Application

**Option 1: Using Maven**
```bash
mvn javafx:run
```

**Option 2: Using Java directly**
```bash
mvn clean package
java --module-path <path-to-javafx> --add-modules javafx.controls,javafx.fxml -cp target/classes com.restaurant.Main
```

**Option 3: Using an IDE (Recommended)**
1. Open the project in IntelliJ IDEA or Eclipse
2. Configure JavaFX SDK if needed
3. Run the `Main.java` class

## 📖 Usage Guide

### Starting the Application

1. Ensure MySQL is running
2. Launch the application
3. The main window will display:
   - **Top Panel**: Algorithm selection and controls
   - **Left Panel**: Original orders (unsorted)
   - **Right Panel**: Optimized order queue
   - **Bottom Panel**: Statistics and status

### Using the System

1. **Load Orders**:
   - Click "Refresh Orders" to load pending orders from the database
   - Original orders will appear in the left table

2. **Select Algorithm**:
   - Choose a scheduling algorithm from the dropdown:
     - Shortest Processing Time (SPT)
     - Priority Queue Scheduling
     - Round Robin Scheduling
   - Read the algorithm description below the dropdown

3. **Apply Algorithm**:
   - Click "Apply Algorithm" to optimize the order queue
   - The optimized order sequence will appear in the right table
   - Compare the original and optimized orders

4. **View Statistics**:
   - Check the bottom panel for:
     - Total number of orders
     - Total and average processing time
     - Total order amount
     - Cumulative waiting time (for optimized queue)

### Understanding the Results

- **Order #**: Unique order identifier
- **Table**: Table number placing the order
- **Priority**: Order priority (1 = highest, 10 = lowest)
- **Est. Time**: Estimated preparation time in minutes
- **Amount**: Total order value
- **Status**: Current order status

## 📁 Project Structure

```
restaurant-order-system/
│
├── database/
│   └── schema.sql                 # Database schema and sample data
│
├── src/
│   └── main/
│       ├── java/
│       │   └── com/
│       │       └── restaurant/
│       │           ├── Main.java                          # Application entry point
│       │           │
│       │           ├── model/                             # Domain models
│       │           │   ├── Order.java
│       │           │   ├── OrderItem.java
│       │           │   ├── Table.java
│       │           │   └── Staff.java
│       │           │
│       │           ├── database/                          # Database layer
│       │           │   └── DatabaseConnection.java
│       │           │
│       │           ├── dao/                              # Data Access Objects
│       │           │   └── OrderDAO.java
│       │           │
│       │           ├── algorithm/                         # Scheduling algorithms
│       │           │   ├── SchedulingAlgorithm.java      # Interface
│       │           │   ├── ShortestProcessingTime.java
│       │           │   ├── PriorityQueueScheduling.java
│       │           │   └── RoundRobinScheduling.java
│       │           │
│       │           └── ui/                                # UI components
│       │               └── RestaurantController.java
│       │
│       └── resources/
│           └── ui/
│               └── restaurant.fxml                        # JavaFX UI layout
│
├── pom.xml                         # Maven configuration
└── README.md                       # This file
```

## 🔑 Key Components

### Model Classes

- **Order**: Represents a restaurant order with status, priority, timing, and items
- **OrderItem**: Individual items within an order
- **Table**: Restaurant table information
- **Staff**: Staff member information

### Algorithm Classes

All algorithms implement the `SchedulingAlgorithm` interface:

- **ShortestProcessingTime**: Sorts by estimated time (ascending)
- **PriorityQueueScheduling**: Sorts by priority (ascending), then creation time
- **RoundRobinScheduling**: Processes orders in circular fashion

### UI Components

- **RestaurantController**: Main UI controller handling user interactions
- **restaurant.fxml**: JavaFX layout definition
- **Main**: Application entry point

### Database Components

- **DatabaseConnection**: Singleton connection manager
- **OrderDAO**: Data access operations for orders

## 📝 Notes

- The application loads only **PENDING** orders by default
- To test with different data, modify the sample data in `database/schema.sql`
- Database connection settings can be modified in `DatabaseConnection.java`
- All algorithms work on a copy of the original orders list (non-destructive)

## 🐛 Troubleshooting

### Database Connection Issues

- Verify MySQL is running: `mysql --version`
- Check database credentials in `DatabaseConnection.java`
- Ensure database `restaurant_db` exists
- Verify user has proper permissions

### JavaFX Not Loading

- Ensure JavaFX dependencies are downloaded (Maven should handle this)
- For Java 11+, you may need to add JavaFX modules manually
- Check that `restaurant.fxml` is in `src/main/resources/ui/`

### Compilation Errors

- Verify JDK 11+ is installed and configured
- Run `mvn clean install` to rebuild
- Check that all dependencies are downloaded

## 📚 Academic Context

This project demonstrates:

1. **Object-Oriented Design**: Clean class structure with proper encapsulation
2. **Design Patterns**: Singleton (DatabaseConnection), Strategy (Scheduling Algorithms)
3. **Database Design**: Normalized relational schema with foreign keys
4. **Algorithm Implementation**: Multiple scheduling algorithms with trade-offs
5. **UI Development**: Modern JavaFX application with MVC pattern
6. **Software Architecture**: Layered architecture with separation of concerns

## 👨‍💻 Development

### Adding New Algorithms

1. Create a new class implementing `SchedulingAlgorithm`
2. Implement `schedule()`, `getAlgorithmName()`, and `getDescription()` methods
3. Add the algorithm to the list in `RestaurantController.initializeAlgorithms()`

### Extending Functionality

- Add new order statuses in `Order.OrderStatus` enum
- Extend database schema for additional features
- Add new UI components in `restaurant.fxml`

## 📄 License

This is a university project for educational purposes.

## 👥 Author

University Project - Restaurant Order & Queue Optimization System

---

**Happy Coding! 🍽️**


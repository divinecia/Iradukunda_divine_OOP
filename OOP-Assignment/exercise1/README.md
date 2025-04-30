# Exercise 1: Advanced Stock Management System

This project implements an Advanced Stock Management System in Java, designed to manage product stock, track inventory, and generate reports. It fulfills the requirements of the OOP assignment for Exercise 1, with all diagnostics resolved, including an unclosed string literal in `ElectronicsItem.java`.

## Project Structure

- `StockItem.java`: Abstract class defining the blueprint for stock items.
- `ElectronicsItem.java`: Concrete class for electronics with warranty and discount features.
- `ClothingItem.java`: Concrete class for clothing with size and color management.
- `GroceryItem.java`: Concrete class for groceries with expiration date tracking.
- `FurnitureItem.java`: Concrete class for furniture with weight and packaging validation.
- `PerishableItem.java`: Concrete class for perishable items with shelf life management.
- `Product.java`: Encapsulated class for product details.
- `Supplier.java`: Encapsulated class for supplier contact details.
- `Warehouse.java`: Encapsulated class for inventory tracking.
- `StockManagementSystem.java`: Main class demonstrating functionality.
- `Dockerfile`: Docker configuration for the application.

## Requirements

- **Java**: JDK 17 or later
- **Docker**: For containerization
- **Git**: For version control
- **VS Code**: Recommended IDE with Java Extension Pack

## Setup Instructions

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/divinecia/OOP.git
   cd OOP-Assignment/exercise1
   ```

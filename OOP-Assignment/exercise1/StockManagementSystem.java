import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StockManagementSystem {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            List<StockItem> inventory = new ArrayList<>();

            // Create Supplier
            System.out.println("Enter Supplier Details:");
            System.out.print("Supplier ID: ");
            String supplierId = scanner.nextLine();
            System.out.print("Company Name: ");
            String companyName = scanner.nextLine();
            System.out.print("Contact Person: ");
            String contactPerson = scanner.nextLine();
            System.out.print("Phone (10 digits): ");
            String phone = scanner.nextLine();
            System.out.print("Email: ");
            String email = scanner.nextLine();

            Supplier supplier;
            try {
                supplier = new Supplier(supplierId, companyName, contactPerson, phone, email);
                System.out.println("Supplier created successfully.");
            } catch (IllegalArgumentException e) {
                System.out.println("Error creating supplier: " + e.getMessage());
                return;
            }

            // Create Warehouse
            System.out.println("\nEnter Warehouse Details:");
            System.out.print("Warehouse ID: ");
            String warehouseId = scanner.nextLine();
            System.out.print("Location: ");
            String location = scanner.nextLine();
            System.out.print("Capacity: ");
            int capacity = scanner.nextInt();
            System.out.print("Manager Name: ");
            scanner.nextLine(); // Clear buffer
            String managerName = scanner.nextLine();

            Warehouse warehouse;
            try {
                warehouse = new Warehouse(warehouseId, location, capacity, managerName);
                System.out.println("Warehouse created successfully.");
            } catch (IllegalArgumentException e) {
                System.out.println("Error creating warehouse: " + e.getMessage());
                return;
            }

            // Create ElectronicsItem
            System.out.println("\nEnter Electronics Item Details:");
            System.out.print("Item ID: ");
            String elecId = scanner.nextLine();
            System.out.print("Item Name: ");
            String elecName = scanner.nextLine();
            System.out.print("Quantity in Stock: ");
            int elecQuantity = scanner.nextInt();
            System.out.print("Price per Unit: ");
            double elecPrice = scanner.nextDouble();
            System.out.print("Warranty Period (months): ");
            int warranty = scanner.nextInt();
            scanner.nextLine(); // Clear buffer

            ElectronicsItem electronics = new ElectronicsItem(elecId, elecName, elecQuantity, elecPrice, supplier.getCompanyName(), warranty);
            if (electronics.validateStock()) {
                inventory.add(electronics);
                warehouse.addStock(elecId, elecQuantity);
                electronics.applyDiscount(10.0); // Example discount
            }

            // Create ClothingItem
            System.out.println("\nEnter Clothing Item Details:");
            System.out.print("Item ID: ");
            String clothId = scanner.nextLine();
            System.out.print("Item Name: ");
            String clothName = scanner.nextLine();
            System.out.print("Quantity in Stock: ");
            int clothQuantity = scanner.nextInt();
            System.out.print("Price per Unit: ");
            double clothPrice = scanner.nextDouble();
            System.out.print("Color: ");
            scanner.nextLine(); // Clear buffer
            String color = scanner.nextLine();

            ClothingItem clothing = new ClothingItem(clothId, clothName, clothQuantity, clothPrice, supplier.getCompanyName(), color);
            clothing.addSizeStock("M", clothQuantity / 2);
            clothing.addSizeStock("L", clothQuantity / 2);
            if (clothing.validateStock()) {
                inventory.add(clothing);
                warehouse.addStock(clothId, clothQuantity);
                clothing.applyDiscount(15.0);
            }

            // Create GroceryItem
            System.out.println("\nEnter Grocery Item Details:");
            System.out.print("Item ID: ");
            String groceryId = scanner.nextLine();
            System.out.print("Item Name: ");
            String groceryName = scanner.nextLine();
            System.out.print("Quantity in Stock: ");
            int groceryQuantity = scanner.nextInt();
            System.out.print("Price per Unit: ");
            double groceryPrice = scanner.nextDouble();
            System.out.print("Expiration Date (YYYY-MM-DD): ");
            scanner.nextLine(); // Clear buffer
            String expDateStr = scanner.nextLine();
            LocalDate expDate = LocalDate.parse(expDateStr);

            GroceryItem grocery = new GroceryItem(groceryId, groceryName, groceryQuantity, groceryPrice, supplier.getCompanyName(), expDate);
            if (grocery.validateStock()) {
                inventory.add(grocery);
                warehouse.addStock(groceryId, groceryQuantity);
            }

            // Create FurnitureItem
            System.out.println("\nEnter Furniture Item Details:");
            System.out.print("Item ID: ");
            String furnId = scanner.nextLine();
            System.out.print("Item Name: ");
            String furnName = scanner.nextLine();
            System.out.print("Quantity in Stock: ");
            int furnQuantity = scanner.nextInt();
            System.out.print("Price per Unit: ");
            double furnPrice = scanner.nextDouble();
            System.out.print("Weight (kg): ");
            double weight = scanner.nextDouble();
            scanner.nextLine(); // Clear buffer

            FurnitureItem furniture = new FurnitureItem(furnId, furnName, furnQuantity, furnPrice, supplier.getCompanyName(), weight);
            furniture.packageForDelivery();
            if (furniture.validateStock()) {
                inventory.add(furniture);
                warehouse.addStock(furnId, furnQuantity);
            }

            // Create PerishableItem
            System.out.println("\nEnter Perishable Item Details:");
            System.out.print("Item ID: ");
            String perishId = scanner.nextLine();
            System.out.print("Item Name: ");
            String perishName = scanner.nextLine();
            System.out.print("Quantity in Stock: ");
            int perishQuantity = scanner.nextInt();
            System.out.print("Price per Unit: ");
            double perishPrice = scanner.nextDouble();
            System.out.print("Expiration Date (YYYY-MM-DD): ");
            scanner.nextLine(); // Clear buffer
            String perishExpDateStr = scanner.nextLine();
            LocalDate perishExpDate = LocalDate.parse(perishExpDateStr);

            PerishableItem perishable = new PerishableItem(perishId, perishName, perishQuantity, perishPrice, supplier.getCompanyName(), perishExpDate);
            if (perishable.validateStock()) {
                inventory.add(perishable);
                warehouse.addStock(perishId, perishQuantity);
            }

            // Generate Comprehensive Inventory Report
            System.out.println("\n=== Comprehensive Inventory Report ===");
            double totalStockValue = 0.0;
            for (StockItem item : inventory) {
                System.out.println(item.generateStockReport());
                System.out.println();
                totalStockValue += item.calculateStockValue();
            }
            System.out.printf("Total Stock Value: $%.2f\n", totalStockValue);

            // Warehouse Report
            System.out.println("\n=== Warehouse Inventory Report ===");
            System.out.println(warehouse.generateInventoryReport());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
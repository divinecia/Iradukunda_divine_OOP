import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class GroceryItem extends StockItem {
    private final LocalDate expirationDate;
    private final double discountPercentage;

    public GroceryItem(String itemId, String itemName, int quantityInStock, double pricePerUnit, String supplier, LocalDate expirationDate) {
        super(itemId, itemName, quantityInStock, pricePerUnit, "Groceries", supplier);
        this.expirationDate = expirationDate;
        this.discountPercentage = checkExpirationForDiscount();
    }

    private double checkExpirationForDiscount() {
        long daysUntilExpiration = ChronoUnit.DAYS.between(LocalDate.now(), expirationDate);
        if (daysUntilExpiration <= 7) {
            System.out.println("Item nearing expiration. Applying 20% discount.");
            return 20.0;
        }
        return 0.0;
    }

    @Override
    void updateStock(int quantity) {
        if (quantity >= 0 || (quantity < 0 && quantityInStock + quantity >= 0)) {
            quantityInStock += quantity;
            System.out.println("Stock updated. New quantity: " + quantityInStock);
        } else {
            System.out.println("Error: Insufficient stock for reduction.");
        }
    }

    @Override
    double calculateStockValue() {
        double discountedPrice = pricePerUnit * (1 - discountPercentage / 100);
        return quantityInStock * discountedPrice;
    }

    @Override
    String generateStockReport() {
        return String.format(
                "Grocery Item Report:\nID: %s\nName: %s\nQuantity: %d\nPrice per Unit: $%.2f\nDiscount: %.1f%%\nStock Value: $%.2f\nCategory: %s\nSupplier: %s\nExpiration Date: %s",
                itemId, itemName, quantityInStock, pricePerUnit, discountPercentage, calculateStockValue(), category, supplier, expirationDate);
    }

    @Override
    boolean validateStock() {
        if (quantityInStock <= 0) {
            System.out.println("Error: Stock quantity must be above zero for sale.");
            return false;
        }
        if (pricePerUnit <= 0) {
            System.out.println("Error: Price per unit must be above zero.");
            return false;
        }
        if (discountPercentage > 50) {
            System.out.println("Error: Discount cannot exceed 50%.");
            return false;
        }
        if (expirationDate.isBefore(LocalDate.now())) {
            System.out.println("Error: Item is expired.");
            return false;
        }
        return true;
    }
}
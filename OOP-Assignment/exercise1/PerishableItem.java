import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class PerishableItem extends StockItem {
    private final LocalDate expirationDate;
    private static final long MAX_SHELF_LIFE_DAYS = 30;

    public PerishableItem(String itemId, String itemName, int quantityInStock, double pricePerUnit, String supplier, LocalDate expirationDate) {
        super(itemId, itemName, quantityInStock, pricePerUnit, "Perishable", supplier);
        this.expirationDate = expirationDate;
        checkForDisposal();
    }

    private void checkForDisposal() {
        if (expirationDate.isBefore(LocalDate.now())) {
            System.out.println("Alert: " + itemName + " is expired and needs disposal.");
        }
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
        return quantityInStock * pricePerUnit;
    }

    @Override
    String generateStockReport() {
        String status = expirationDate.isBefore(LocalDate.now()) ? "Expired" : "Valid";
        return String.format(
                "Perishable Item Report:\nID: %s\nName: %s\nQuantity: %d\nPrice per Unit: $%.2f\nStock Value: $%.2f\nCategory: %s\nSupplier: %s\nExpiration Date: %s\nStatus: %s",
                itemId, itemName, quantityInStock, pricePerUnit, calculateStockValue(), category, supplier, expirationDate, status);
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
        long shelfLife = ChronoUnit.DAYS.between(LocalDate.now(), expirationDate);
        if (shelfLife > MAX_SHELF_LIFE_DAYS) {
            System.out.println("Error: Shelf life exceeds maximum for perishable items.");
            return false;
        }
        return true;
    }
}
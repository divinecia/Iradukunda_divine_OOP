import java.util.HashMap;
import java.util.Map;

public class ClothingItem extends StockItem {
    private final Map<String, Integer> sizeStock;
    private final String color;
    private double discountPercentage;

    public ClothingItem(String itemId, String itemName, int quantityInStock, double pricePerUnit, String supplier, String color) {
        super(itemId, itemName, quantityInStock, pricePerUnit, "Clothing", supplier);
        this.sizeStock = new HashMap<>();
        this.color = color;
        this.discountPercentage = 0.0;
    }

    public void addSizeStock(String size, int quantity) {
        sizeStock.put(size, sizeStock.getOrDefault(size, 0) + quantity);
        quantityInStock = sizeStock.values().stream().mapToInt(Integer::intValue).sum();
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
        StringBuilder report = new StringBuilder(String.format(
                "Clothing Item Report:\nID: %s\nName: %s\nQuantity: %d\nPrice per Unit: $%.2f\nDiscount: %.1f%%\nStock Value: $%.2f\nCategory: %s\nSupplier: %s\nColor: %s\nSizes:\n",
                itemId, itemName, quantityInStock, pricePerUnit, discountPercentage, calculateStockValue(), category, supplier, color));
        for (Map.Entry<String, Integer> entry : sizeStock.entrySet()) {
            report.append(String.format("- %s: %d\n", entry.getKey(), entry.getValue()));
        }
        return report.toString();
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
        if (sizeStock.isEmpty()) {
            System.out.println("Error: At least one size must be available.");
            return false;
        }
        return true;
    }

    public void applyDiscount(double discount) {
        if (discount >= 0 && discount <= 50) {
            this.discountPercentage = discount;
            System.out.println("Discount of " + discount + "% applied.");
        } else {
            System.out.println("Error: Discount must be between 0% and 50%.");
        }
    }
}
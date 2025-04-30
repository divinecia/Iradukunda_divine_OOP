public class ElectronicsItem extends StockItem {
    private final int warrantyPeriod; // in months
    private double discountPercentage;

    public ElectronicsItem(String itemId, String itemName, int quantityInStock, double pricePerUnit, String supplier, int warrantyPeriod) {
        super(itemId, itemName, quantityInStock, pricePerUnit, "Electronics", supplier);
        this.warrantyPeriod = warrantyPeriod;
        this.discountPercentage = 0.0;
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
        return String.format("Electronics Item Report:\nID: %s\nName: %s\nQuantity: %d\nPrice per Unit: $%.2f\nDiscount: %.1f%%\nStock Value: $%.2f\nCategory: %s\nSupplier: %s\nWarranty: %d months",
                itemId, itemName, quantityInStock, pricePerUnit, discountPercentage, calculateStockValue(), category, supplier, warrantyPeriod);
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
        if (warrantyPeriod < 0 || warrantyPeriod > 60) {
            System.out.println("Error: Warranty period must be between 0 and 60 months.");
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
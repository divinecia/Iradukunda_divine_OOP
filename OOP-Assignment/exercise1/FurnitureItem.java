public class FurnitureItem extends StockItem {
    private final double weight; // in kilograms
    private boolean isPackaged;

    public FurnitureItem(String itemId, String itemName, int quantityInStock, double pricePerUnit, String supplier, double weight) {
        super(itemId, itemName, quantityInStock, pricePerUnit, "Furniture", supplier);
        this.weight = weight;
        this.isPackaged = false;
    }

    public void packageForDelivery() {
        isPackaged = true;
        System.out.println("Furniture item packaged for delivery.");
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
        return String.format(
                "Furniture Item Report:\nID: %s\nName: %s\nQuantity: %d\nPrice per Unit: $%.2f\nStock Value: $%.2f\nCategory: %s\nSupplier: %s\nWeight: %.2f kg\nPackaged: %s",
                itemId, itemName, quantityInStock, pricePerUnit, calculateStockValue(), category, supplier, weight, isPackaged ? "Yes" : "No");
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
        if (weight <= 0) {
            System.out.println("Error: Weight must be positive.");
            return false;
        }
        if (!isPackaged) {
            System.out.println("Error: Furniture must be packaged for delivery.");
            return false;
        }
        return true;
    }
}
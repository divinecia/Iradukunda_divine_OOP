
public abstract class StockItem {
    protected String itemId;
    protected String itemName;
    protected int quantityInStock;
    protected double pricePerUnit;
    protected String category;
    protected String supplier;

    public StockItem(String itemId, String itemName, int quantityInStock, double pricePerUnit, String category, String supplier) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.quantityInStock = quantityInStock;
        this.pricePerUnit = pricePerUnit;
        this.category = category;
        this.supplier = supplier;
    }

    // Abstract methods
    @SuppressWarnings("unused")
    abstract void updateStock(int quantity);
    abstract double calculateStockValue();
    abstract String generateStockReport();
    @SuppressWarnings("unused")
    abstract boolean validateStock();

    // Getters
    public String getItemId() { return itemId; }
    public String getItemName() { return itemName; }
    public int getQuantityInStock() { return quantityInStock; }
    public double getPricePerUnit() { return pricePerUnit; }
    public String getCategory() { return category; }
    public String getSupplier() { return supplier; }
}
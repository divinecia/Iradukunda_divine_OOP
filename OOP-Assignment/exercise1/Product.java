public class Product {
    private final String productId;
    private String productName;
    private String brand;
    private final String supplier;
    private int stockQuantity;

    public Product(String productId, String productName, String brand, String supplier, int stockQuantity) {
        this.productId = productId;
        if (validateProductName(productName)) {
            this.productName = productName;
        } else {
            throw new IllegalArgumentException("Invalid product name.");
        }
        if (validateBrand(brand)) {
            this.brand = brand;
        } else {
            throw new IllegalArgumentException("Invalid brand.");
        }
        this.supplier = supplier;
        if (stockQuantity >= 0) {
            this.stockQuantity = stockQuantity;
        } else {
            throw new IllegalArgumentException("Stock quantity cannot be negative.");
        }
    }

    private boolean validateProductName(String name) {
        return name != null && !name.trim().isEmpty();
    }

    private boolean validateBrand(String brand) {
        return brand != null && !brand.trim().isEmpty();
    }

    public String getProductId() { return productId; }
    public String getProductName() { return productName; }
    public String getBrand() { return brand; }
    public String getSupplier() { return supplier; }
    public int getStockQuantity() { return stockQuantity; }

    public void setStockQuantity(int stockQuantity) {
        if (stockQuantity >= 0) {
            this.stockQuantity = stockQuantity;
        } else {
            throw new IllegalArgumentException("Stock quantity cannot be negative.");
        }
    }
}
public class ClothingItem extends ShoppingItem {
    private final String size;
    private final double seasonalDiscount;

    public ClothingItem(String itemId, String itemName, String itemDescription, double price, int stockAvailable, String size, double seasonalDiscount) {
        super(itemId, itemName, itemDescription, price, stockAvailable);
        this.size = size;
        this.seasonalDiscount = seasonalDiscount;
    }

    @Override
    public void updateStock(int quantity) {
        if (quantity <= stockAvailable) {
            stockAvailable -= quantity;
            System.out.println("Stock updated: " + stockAvailable + " remaining for " + itemName);
        } else {
            System.out.println("Insufficient stock for " + itemName);
        }
    }

    @Override
    public void addToCart(Customer customer) {
        if (validateItem()) {
            customer.getCart().addItem(this);
            System.out.println(itemName + " (Size: " + size + ") added to cart for " + customer.getCustomerName());
        } else {
            System.out.println("Cannot add " + itemName + " to cart: invalid item.");
        }
    }

    @Override
    public void generateInvoice(Customer customer) {
        double discountedPrice = price * (1 - seasonalDiscount);
        System.out.println("Invoice for " + customer.getCustomerName() + ":");
        System.out.println("Item: " + itemName + ", Size: " + size + ", Price: $" + discountedPrice);
    }

    @Override
    public boolean validateItem() {
        return stockAvailable > 0 && !size.isEmpty() && price > 0;
    }
}
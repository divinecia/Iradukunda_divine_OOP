public class AccessoriesItem extends ShoppingItem {
    private final String variety;
    private final double rating;

    public AccessoriesItem(String itemId, String itemName, String itemDescription, double price, int stockAvailable, String variety, double rating) {
        super(itemId, itemName, itemDescription, price, stockAvailable);
        this.variety = variety;
        this.rating = rating;
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
            System.out.println(itemName + " added to cart for " + customer.getCustomerName());
        } else {
            System.out.println("Cannot add " + itemName + " to cart: invalid item.");
        }
    }

    @Override
    public void generateInvoice(Customer customer) {
        System.out.println("Invoice for " + customer.getCustomerName() + ":");
        System.out.println("Item: " + itemName + ", Price: $" + price);
        System.out.println("Variety: " + variety + ", Rating: " + rating);
    }

    @Override
    public boolean validateItem() {
        return stockAvailable > 0 && price > 0 && !variety.isEmpty();
    }
}
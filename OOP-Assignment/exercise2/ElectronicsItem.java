public class ElectronicsItem extends ShoppingItem {
    private final String warrantyDetails;
    private final boolean isRegistered;

    public ElectronicsItem(String itemId, String itemName, String itemDescription, double price, int stockAvailable, String warrantyDetails) {
        super(itemId, itemName, itemDescription, price, stockAvailable);
        this.warrantyDetails = warrantyDetails;
        this.isRegistered = false;
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
            if (isRegistered) {
                System.out.println("Product is registered under warranty.");
            }
        } else {
            System.out.println("Cannot add " + itemName + " to cart: invalid item.");
        }
    }

    @Override
    public void generateInvoice(Customer customer) {
        System.out.println("Invoice for " + customer.getCustomerName() + ":");
        System.out.println("Item: " + itemName + ", Price: $" + price);
        System.out.println("Warranty: " + warrantyDetails);
    }

    @Override
    public boolean validateItem() {
        return stockAvailable > 0 && price > 0;
    }
}
import java.time.LocalDate;

public class GroceriesItem extends ShoppingItem {
    private final LocalDate expirationDate;
    private final double bulkDiscount;

    public GroceriesItem(String itemId, String itemName, String itemDescription, double price, int stockAvailable, LocalDate expirationDate, double bulkDiscount) {
        super(itemId, itemName, itemDescription, price, stockAvailable);
        this.expirationDate = expirationDate;
        this.bulkDiscount = bulkDiscount;
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
        double discountedPrice = price * (1 - bulkDiscount);
        System.out.println("Invoice for " + customer.getCustomerName() + ":");
        System.out.println("Item: " + itemName + ", Price: $" + discountedPrice);
        System.out.println("Expiration Date: " + expirationDate);
    }

    @Override
    public boolean validateItem() {
        return stockAvailable > 0 && price > 0 && expirationDate.isAfter(LocalDate.now());
    }
}
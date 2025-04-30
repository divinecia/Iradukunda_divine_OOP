import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private final String cartId;
    private final List<ShoppingItem> cartItems;
    private double totalPrice;
    private final Customer customer;

    public ShoppingCart(String cartId, Customer customer) {
        this.cartId = cartId;
        this.customer = customer;
        this.cartItems = new ArrayList<>();
        this.totalPrice = 0.0;
    }

    public void addItem(ShoppingItem item) {
        if (item.getStockAvailable() > 0) {
            cartItems.add(item);
            totalPrice += item.getPrice();
            item.updateStock(1);
            System.out.println(item.getItemName() + " added to cart for " + customer.getCustomerName());
        } else {
            System.out.println("Item " + item.getItemName() + " is out of stock.");
        }
    }

    public void removeItem(ShoppingItem item) {
        if (cartItems.remove(item)) {
            totalPrice -= item.getPrice();
            System.out.println(item.getItemName() + " removed from cart for " + customer.getCustomerName());
        }
    }

    public boolean validateCart() {
        return !cartItems.isEmpty() && totalPrice > 0;
    }

    // Getters
    public String getCartId() { return cartId; }
    public double getTotalPrice() { return totalPrice; }
    public List<ShoppingItem> getCartItems() { return cartItems; }
}
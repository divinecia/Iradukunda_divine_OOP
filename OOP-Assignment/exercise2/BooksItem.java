public class BooksItem extends ShoppingItem {
    private final String isbn;
    private final String edition;

    public BooksItem(String itemId, String itemName, String itemDescription, double price, int stockAvailable, String isbn, String edition) {
        super(itemId, itemName, itemDescription, price, stockAvailable);
        this.isbn = isbn;
        this.edition = edition;
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
        System.out.println("ISBN: " + isbn + ", Edition: " + edition);
    }

    @Override
    public boolean validateItem() {
        return stockAvailable > 0 && price > 0 && !isbn.isEmpty();
    }
}
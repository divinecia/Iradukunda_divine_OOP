public class Customer {
    private final String customerId;
    private final String customerName;
    private final String email;
    private final String address;
    private final String phone;
    private final ShoppingCart cart;

    public Customer(String customerId, String customerName, String email, String address, String phone) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.cart = new ShoppingCart(customerId + "_CART", this);
    }

    public boolean validateCustomer() {
        return !customerId.isEmpty() && !customerName.isEmpty() && email.contains("@") && !address.isEmpty() && phone.length() >= 10;
    }

    // Getters
    public String getCustomerId() { return customerId; }
    public String getCustomerName() { return customerName; }
    public String getEmail() { return email; }
    public String getAddress() { return address; }
    public String getPhone() { return phone; }
    public ShoppingCart getCart() { return cart; }
}
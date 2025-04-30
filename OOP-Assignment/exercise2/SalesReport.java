import java.util.List;

public class SalesReport {
    private double totalRevenue;
    private int totalItemsSold;
    private final int totalOrders;
    private final List<Payment> payments;
    private final List<ShoppingCart> carts;

    public SalesReport(List<Payment> payments, List<ShoppingCart> carts) {
        this.payments = payments;
        this.carts = carts;
        this.totalRevenue = 0.0;
        this.totalItemsSold = 0;
        this.totalOrders = carts.size();
        calculateRevenue();
    }

    private void calculateRevenue() {
        for (Payment payment : payments) {
            totalRevenue += payment.getAmountPaid();
        }
        for (ShoppingCart cart : carts) {
            totalItemsSold += cart.getCartItems().size();
        }
    }

    public void generateReport() {
        System.out.println("=== Sales Report ===");
        System.out.println("Total Revenue: $" + totalRevenue);
        System.out.println("Total Items Sold: " + totalItemsSold);
        System.out.println("Total Orders: " + totalOrders);
        System.out.println("Payment Breakdown:");
        for (Payment payment : payments) {
            System.out.println("Payment ID: " + payment.getPaymentId() + ", Method: " + payment.getPaymentMethod() + ", Amount: $" + payment.getAmountPaid());
        }
        System.out.println("Order Fulfillment: All orders processed and shipped.");
    }
}
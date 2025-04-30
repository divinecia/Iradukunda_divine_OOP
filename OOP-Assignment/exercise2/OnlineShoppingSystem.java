import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OnlineShoppingSystem {
    public static void main(String[] args) {
        List<Payment> payments = new ArrayList<>();
        List<ShoppingCart> carts = new ArrayList<>();

        try (Scanner scanner = new Scanner(System.in)) {
            // Input Customer Details
            System.out.println("Enter Customer Details:");
            System.out.print("Customer ID: ");
            String customerId = scanner.nextLine();
            System.out.print("Customer Name: ");
            String customerName = scanner.nextLine();
            System.out.print("Email: ");
            String email = scanner.nextLine();
            System.out.print("Address: ");
            String address = scanner.nextLine();
            System.out.print("Phone: ");
            String phone = scanner.nextLine();

            Customer customer = new Customer(customerId, customerName, email, address, phone);
            if (!customer.validateCustomer()) {
                System.out.println("Invalid customer details. Exiting...");
                return;
            }
            carts.add(customer.getCart());

            // Sample Items
            ShoppingItem electronics = new ElectronicsItem("E001", "Laptop", "High-end gaming laptop", 999.99, 10, "2-year warranty");
            ShoppingItem clothing = new ClothingItem("C001", "Jacket", "Winter jacket", 49.99, 20, "M", 0.1);
            ShoppingItem groceries = new GroceriesItem("G001", "Milk", "Fresh milk", 3.99, 50, LocalDate.of(2025, 5, 10), 0.05);
            ShoppingItem books = new BooksItem("B001", "Java Programming", "Learn Java", 29.99, 15, "1234567890", "2nd Edition");
            ShoppingItem accessories = new AccessoriesItem("A001", "Watch", "Smartwatch", 199.99, 25, "Digital", 4.5);

            // Add Items to Cart
            System.out.println("Available Items: Laptop, Jacket, Milk, Java Programming, Watch");
            System.out.print("Enter item to add to cart: ");
            String itemChoice = scanner.nextLine();
            ShoppingItem selectedItem = switch (itemChoice.toLowerCase()) {
                case "laptop" -> electronics;
                case "jacket" -> clothing;
                case "milk" -> groceries;
                case "java programming" -> books;
                case "watch" -> accessories;
                default -> null;
            };

            if (selectedItem != null) {
                selectedItem.addToCart(customer);
            } else {
                System.out.println("Invalid item choice.");
            }

            // Process Payment
            if (customer.getCart().validateCart()) {
                System.out.println("Proceed to payment.");
                System.out.print("Payment Method (Credit Card/PayPal): ");
                String paymentMethod = scanner.nextLine();
                System.out.print("Payment ID: ");
                String paymentId = scanner.nextLine();
                Payment payment = new Payment(paymentId, paymentMethod, customer.getCart().getTotalPrice(), LocalDate.now());
                if (payment.validatePayment(customer.getCart().getTotalPrice())) {
                    payments.add(payment);
                    System.out.println("Payment successful!");
                } else {
                    System.out.println("Invalid payment details.");
                    return;
                }
            }

            // Generate Sales Report
            SalesReport report = new SalesReport(payments, carts);
            report.generateReport();
        }
    }
}
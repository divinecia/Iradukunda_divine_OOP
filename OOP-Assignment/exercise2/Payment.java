import java.time.LocalDate;

public class Payment {
    private final String paymentId;
    private final String paymentMethod;
    private final double amountPaid;
    private final LocalDate transactionDate;

    public Payment(String paymentId, String paymentMethod, double amountPaid, LocalDate transactionDate) {
        this.paymentId = paymentId;
        this.paymentMethod = paymentMethod;
        this.amountPaid = amountPaid;
        this.transactionDate = transactionDate;
    }

    public boolean validatePayment(double totalPrice) {
        return amountPaid == totalPrice && (paymentMethod.equals("Credit Card") || paymentMethod.equals("PayPal"));
    }

    // Getters
    public String getPaymentId() { return paymentId; }
    public String getPaymentMethod() { return paymentMethod; }
    public double getAmountPaid() { return amountPaid; }
    public LocalDate getTransactionDate() { return transactionDate; }
}
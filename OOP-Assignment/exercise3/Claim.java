import java.time.LocalDate;

public class Claim {
    private final String claimId;
    private final double claimAmount;
    private final LocalDate claimDate;
    private String claimStatus;

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public Claim(String claimId, double claimAmount, LocalDate claimDate) {
        this.claimId = claimId;
        this.claimAmount = claimAmount;
        this.claimDate = claimDate;
        this.claimStatus = "Pending";
        if (!validateClaim()) {
            throw new IllegalArgumentException("Invalid claim details.");
        }
    }

    public boolean validateClaim() {
        return claimId != null && !claimId.isEmpty() &&
               claimAmount > 0 &&
               claimDate != null && !claimDate.isAfter(LocalDate.now());
    }

    public boolean validateClaimAmount(double coverageAmount) {
        return claimAmount <= coverageAmount;
    }

    public void approveClaim() {
        this.claimStatus = "Approved";
    }

    public void rejectClaim() {
        this.claimStatus = "Rejected";
    }

    // Getters
    public String getClaimId() { return claimId; }
    public double getClaimAmount() { return claimAmount; }
    public LocalDate getClaimDate() { return claimDate; }
    public String getClaimStatus() { return claimStatus; }
}
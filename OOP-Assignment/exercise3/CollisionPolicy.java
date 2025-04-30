import java.time.LocalDate;

public class CollisionPolicy extends InsurancePolicy {
    private final boolean isSafeDriver;

    public CollisionPolicy(String policyId, Vehicle vehicle, Person policyHolder, double coverageAmount,
                          LocalDate policyStartDate, LocalDate policyEndDate, boolean isSafeDriver) {
        super(policyId, vehicle, policyHolder, coverageAmount, policyStartDate, policyEndDate);
        this.isSafeDriver = isSafeDriver;
        initializePremium();
    }

    @Override
    public double calculatePremium() {
        double basePremium = coverageAmount * 0.04;
        return basePremium * (isSafeDriver ? 0.8 : 1.0);
    }

    @Override
    public boolean processClaim(double claimAmount) {
        Claim claim = new Claim("CLM" + policyId, claimAmount, LocalDate.now());
        if (claim.validateClaim() && claim.validateClaimAmount(coverageAmount) && vehicle.validateVehicle()) {
            claim.approveClaim();
            System.out.println("Collision claim approved for " + claimAmount);
            return true;
        } else {
            claim.rejectClaim();
            System.out.println("Collision claim rejected: Invalid or vehicle safety check failed.");
            return false;
        }
    }

    @Override
    public String generatePolicyReport() {
        return """
            Collision Policy Report:
            Policy ID: %s
            Vehicle: %s %s (%d)
            Policyholder: %s
            Coverage: $%.2f
            Premium: $%.2f
            Safe Driver: %s
            Period: %s to %s
            """.formatted(policyId, vehicle.getVehicleMake(), vehicle.getVehicleModel(), vehicle.getVehicleYear(),
                          policyHolder.getFullName(), coverageAmount, premiumAmount,
                          isSafeDriver ? "Yes" : "No", policyStartDate, policyEndDate);
    }

    @Override
    public boolean validatePolicy() {
        return vehicle.validateVehicle() &&
               policyHolder.validatePerson() &&
               coverageAmount > 0 &&
               policyStartDate.isBefore(policyEndDate) &&
               vehicle.getVehicleYear() >= 1995;
    }
}
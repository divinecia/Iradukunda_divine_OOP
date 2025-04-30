import java.time.LocalDate;

public class ThirdPartyPolicy extends InsurancePolicy {
    private final boolean additionalCoverage;

    public ThirdPartyPolicy(String policyId, Vehicle vehicle, Person policyHolder, double coverageAmount,
                           LocalDate policyStartDate, LocalDate policyEndDate, boolean additionalCoverage) {
        super(policyId, vehicle, policyHolder, coverageAmount, policyStartDate, policyEndDate);
        this.additionalCoverage = additionalCoverage;
        initializePremium();
    }

    @Override
    public double calculatePremium() {
        double basePremium = coverageAmount * 0.03;
        double engineFactor = vehicle.getVehicleType().equalsIgnoreCase("Car") ? 1.0 : 1.3;
        return basePremium * engineFactor * (additionalCoverage ? 1.2 : 1.0);
    }

    @Override
    public boolean processClaim(double claimAmount) {
        Claim claim = new Claim("CLM" + policyId, claimAmount, LocalDate.now());
        if (claim.validateClaim() && claim.validateClaimAmount(coverageAmount)) {
            claim.approveClaim();
            System.out.println("Third-party claim approved for " + claimAmount);
            return true;
        } else {
            claim.rejectClaim();
            System.out.println("Third-party claim rejected: Invalid or exceeds coverage.");
            return false;
        }
    }

    @Override
    public String generatePolicyReport() {
        return """
            Third-Party Policy Report:
            Policy ID: %s
            Vehicle: %s %s (%d)
            Policyholder: %s
            Coverage: $%.2f
            Premium: $%.2f
            Additional Coverage: %s
            Period: %s to %s
            """.formatted(policyId, vehicle.getVehicleMake(), vehicle.getVehicleModel(), vehicle.getVehicleYear(),
                          policyHolder.getFullName(), coverageAmount, premiumAmount,
                          additionalCoverage ? "Yes" : "No", policyStartDate, policyEndDate);
    }

    @Override
    public boolean validatePolicy() {
        return vehicle.validateVehicle() &&
               policyHolder.validatePerson() &&
               coverageAmount > 0 &&
               policyStartDate.isBefore(policyEndDate);
    }
}
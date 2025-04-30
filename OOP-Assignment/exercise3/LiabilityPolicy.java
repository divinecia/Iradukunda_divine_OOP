import java.time.LocalDate;

public class LiabilityPolicy extends InsurancePolicy {
    private final boolean hasMedicalCheckup;
    private final boolean extendedCoverage;

    public LiabilityPolicy(String policyId, Vehicle vehicle, Person policyHolder, double coverageAmount,
                          LocalDate policyStartDate, LocalDate policyEndDate, boolean hasMedicalCheckup, boolean extendedCoverage) {
        super(policyId, vehicle, policyHolder, coverageAmount, policyStartDate, policyEndDate);
        this.hasMedicalCheckup = hasMedicalCheckup;
        this.extendedCoverage = extendedCoverage;
        initializePremium();
    }

    @Override
    public double calculatePremium() {
        double basePremium = coverageAmount * 0.02;
        return basePremium * (extendedCoverage ? 1.3 : 1.0);
    }

    @Override
    public boolean processClaim(double claimAmount) {
        Claim claim = new Claim("CLM" + policyId, claimAmount, LocalDate.now());
        if (claim.validateClaim() && claim.validateClaimAmount(coverageAmount) && hasMedicalCheckup) {
            claim.approveClaim();
            System.out.println("Liability claim approved for " + claimAmount);
            return true;
        } else {
            claim.rejectClaim();
            System.out.println("Liability claim rejected: Invalid, exceeds coverage, or no medical checkup.");
            return false;
        }
    }

    @Override
    public String generatePolicyReport() {
        return """
            Liability Policy Report:
            Policy ID: %s
            Vehicle: %s %s (%d)
            Policyholder: %s
            Coverage: $%.2f
            Premium: $%.2f
            Medical Checkup: %s
            Extended Coverage: %s
            Period: %s to %s
            """.formatted(policyId, vehicle.getVehicleMake(), vehicle.getVehicleModel(), vehicle.getVehicleYear(),
                          policyHolder.getFullName(), coverageAmount, premiumAmount,
                          hasMedicalCheckup ? "Yes" : "No", extendedCoverage ? "Yes" : "No",
                          policyStartDate, policyEndDate);
    }

    @Override
    public boolean validatePolicy() {
        return vehicle.validateVehicle() &&
               policyHolder.validatePerson() &&
               coverageAmount > 0 &&
               policyStartDate.isBefore(policyEndDate) &&
               hasMedicalCheckup;
    }
}
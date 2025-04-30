import java.time.LocalDate;
import java.time.Year;

public class ComprehensivePolicy extends InsurancePolicy {
    public ComprehensivePolicy(String policyId, Vehicle vehicle, Person policyHolder, double coverageAmount,
                              LocalDate policyStartDate, LocalDate policyEndDate) {
        super(policyId, vehicle, policyHolder, coverageAmount, policyStartDate, policyEndDate);
        initializePremium();
    }

    @Override
    public double calculatePremium() {
        int vehicleAge = Year.now().getValue() - vehicle.getVehicleYear();
        double basePremium = coverageAmount * 0.05; // 5% of coverage
        double ageFactor = vehicleAge <= 5 ? 1.0 : (vehicleAge <= 10 ? 1.2 : 1.5);
        return basePremium * ageFactor;
    }

    @Override
    public boolean processClaim(double claimAmount) {
        Claim claim = new Claim("CLM" + policyId, claimAmount, LocalDate.now());
        if (claim.validateClaim() && claim.validateClaimAmount(coverageAmount)) {
            claim.approveClaim();
            System.out.println("Claim approved for " + claimAmount);
            return true;
        } else {
            claim.rejectClaim();
            System.out.println("Claim rejected: Invalid claim or exceeds coverage.");
            return false;
        }
    }

    @Override
    public String generatePolicyReport() {
        return """
            Comprehensive Policy Report:
            Policy ID: %s
            Vehicle: %s %s (%d)
            Policyholder: %s
            Coverage: $%.2f
            Premium: $%.2f
            Period: %s to %s
            """.formatted(policyId, vehicle.getVehicleMake(), vehicle.getVehicleModel(), vehicle.getVehicleYear(),
                          policyHolder.getFullName(), coverageAmount, premiumAmount, policyStartDate, policyEndDate);
    }

    @Override
    public boolean validatePolicy() {
        return vehicle.validateVehicle() &&
               policyHolder.validatePerson() &&
               coverageAmount > 0 &&
               policyStartDate.isBefore(policyEndDate) &&
               vehicle.getVehicleYear() >= 2000;
    }
}
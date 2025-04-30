import java.time.LocalDate;

public class RoadsideAssistancePolicy extends InsurancePolicy {
    private final boolean isVehicleRegistered;

    public RoadsideAssistancePolicy(String policyId, Vehicle vehicle, Person policyHolder, double coverageAmount,
                                   LocalDate policyStartDate, LocalDate policyEndDate, boolean isVehicleRegistered) {
        super(policyId, vehicle, policyHolder, coverageAmount, policyStartDate, policyEndDate);
        this.isVehicleRegistered = isVehicleRegistered;
        initializePremium();
    }

    @Override
    public double calculatePremium() {
        double basePremium = coverageAmount * 0.01;
        return basePremium * (vehicle.getVehicleType().equalsIgnoreCase("Commercial") ? 1.5 : 1.0);
    }

    @Override
    public boolean processClaim(double claimAmount) {
        Claim claim = new Claim("CLM" + policyId, claimAmount, LocalDate.now());
        if (claim.validateClaim() && claim.validateClaimAmount(coverageAmount) && isVehicleRegistered) {
            claim.approveClaim();
            System.out.println("Roadside assistance claim approved for " + claimAmount);
            return true;
        } else {
            claim.rejectClaim();
            System.out.println("Roadside assistance claim rejected: Invalid, exceeds coverage, or vehicle not registered.");
            return false;
        }
    }

    @Override
    public String generatePolicyReport() {
        return """
            Roadside Assistance Policy Report:
            Policy ID: %s
            Vehicle: %s %s (%d)
            Policyholder: %s
            Coverage: $%.2f
            Premium: $%.2f
            Vehicle Registered: %s
            Period: %s to %s
            """.formatted(policyId, vehicle.getVehicleMake(), vehicle.getVehicleModel(), vehicle.getVehicleYear(),
                          policyHolder.getFullName(), coverageAmount, premiumAmount,
                          isVehicleRegistered ? "Yes" : "No", policyStartDate, policyEndDate);
    }

    @Override
    public boolean validatePolicy() {
        return vehicle.validateVehicle() &&
               policyHolder.validatePerson() &&
               coverageAmount > 0 &&
               policyStartDate.isBefore(policyEndDate) &&
               isVehicleRegistered;
    }
}
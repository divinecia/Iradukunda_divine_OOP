import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MotorVehicleInsuranceSystem {
    public static void main(String[] args) {
        List<InsurancePolicy> policies = new ArrayList<>();
        List<Claim> claims = new ArrayList<>();
        double totalPremiums = 0.0;
        int totalClaimsProcessed = 0;

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("\nMotor Vehicle Insurance System");
                System.out.println("1. Add Policy");
                System.out.println("2. Process Claim");
                System.out.println("3. Generate Report");
                System.out.println("4. Exit");
                System.out.print("Choose an option: ");
                String input = scanner.nextLine();
                int choice;
                try {
                    choice = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid option. Please enter a number.");
                    continue;
                }

                switch (choice) {
                    case 1 -> {
                        // Input Person Details
                        System.out.print("Person ID: ");
                        String personId = scanner.nextLine();
                        System.out.print("Full Name: ");
                        String fullName = scanner.nextLine();
                        System.out.print("Date of Birth (YYYY-MM-DD): ");
                        String dobInput = scanner.nextLine();
                        LocalDate dob;
                        try {
                            dob = LocalDate.parse(dobInput);
                        } catch (Exception e) {
                            System.out.println("Invalid date format.");
                            continue;
                        }
                        System.out.print("Email: ");
                        String email = scanner.nextLine();
                        System.out.print("Phone: ");
                        String phone = scanner.nextLine();
                        Person person;
                        try {
                            person = new Person(personId, fullName, dob, email, phone);
                        } catch (IllegalArgumentException e) {
                            System.out.println("Invalid person details: " + e.getMessage());
                            continue;
                        }

                        // Input Vehicle Details
                        System.out.print("Vehicle ID: ");
                        String vehicleId = scanner.nextLine();
                        System.out.print("Vehicle Make: ");
                        String vehicleMake = scanner.nextLine();
                        System.out.print("Vehicle Model: ");
                        String vehicleModel = scanner.nextLine();
                        System.out.print("Vehicle Year: ");
                        String yearInput = scanner.nextLine();
                        int vehicleYear;
                        try {
                            vehicleYear = Integer.parseInt(yearInput);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid vehicle year.");
                            continue;
                        }
                        System.out.print("Vehicle Type (Car/Truck/Commercial): ");
                        String vehicleType = scanner.nextLine();
                        Vehicle vehicle;
                        try {
                            vehicle = new Vehicle(vehicleId, vehicleMake, vehicleModel, vehicleYear, vehicleType);
                        } catch (IllegalArgumentException e) {
                            System.out.println("Invalid vehicle details: " + e.getMessage());
                            continue;
                        }

                        // Input Policy Details
                        System.out.print("Policy ID: ");
                        String policyId = scanner.nextLine();
                        System.out.print("Coverage Amount: ");
                        String coverageInput = scanner.nextLine();
                        double coverageAmount;
                        try {
                            coverageAmount = Double.parseDouble(coverageInput);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid coverage amount.");
                            continue;
                        }
                        System.out.print("Policy Start Date (YYYY-MM-DD): ");
                        String startDateInput = scanner.nextLine();
                        LocalDate startDate;
                        try {
                            startDate = LocalDate.parse(startDateInput);
                        } catch (Exception e) {
                            System.out.println("Invalid start date.");
                            continue;
                        }
                        System.out.print("Policy End Date (YYYY-MM-DD): ");
                        String endDateInput = scanner.nextLine();
                        LocalDate endDate;
                        try {
                            endDate = LocalDate.parse(endDateInput);
                        } catch (Exception e) {
                            System.out.println("Invalid end date.");
                            continue;
                        }

                        System.out.println("Policy Type: 1-Comprehensive, 2-ThirdParty, 3-Collision, 4-Liability, 5-RoadsideAssistance");
                        String policyTypeInput = scanner.nextLine();
                        int policyType;
                        try {
                            policyType = Integer.parseInt(policyTypeInput);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid policy type.");
                            continue;
                        }

                        InsurancePolicy policy = switch (policyType) {
                            case 1 -> {
                                ComprehensivePolicy p = new ComprehensivePolicy(policyId, vehicle, person, coverageAmount, startDate, endDate);
                                yield p;
                            }
                            case 2 -> {
                                System.out.print("Additional Coverage (yes/no): ");
                                boolean additionalCoverage = scanner.nextLine().equalsIgnoreCase("yes");
                                ThirdPartyPolicy p = new ThirdPartyPolicy(policyId, vehicle, person, coverageAmount, startDate, endDate, additionalCoverage);
                                yield p;
                            }
                            case 3 -> {
                                System.out.print("Safe Driver (yes/no): ");
                                boolean isSafeDriver = scanner.nextLine().equalsIgnoreCase("yes");
                                CollisionPolicy p = new CollisionPolicy(policyId, vehicle, person, coverageAmount, startDate, endDate, isSafeDriver);
                                yield p;
                            }
                            case 4 -> {
                                System.out.print("Medical Checkup (yes/no): ");
                                boolean hasMedicalCheckup = scanner.nextLine().equalsIgnoreCase("yes");
                                System.out.print("Extended Coverage (yes/no): ");
                                boolean extendedCoverage = scanner.nextLine().equalsIgnoreCase("yes");
                                LiabilityPolicy p = new LiabilityPolicy(policyId, vehicle, person, coverageAmount, startDate, endDate, hasMedicalCheckup, extendedCoverage);
                                yield p;
                            }
                            case 5 -> {
                                System.out.print("Vehicle Registered (yes/no): ");
                                boolean isVehicleRegistered = scanner.nextLine().equalsIgnoreCase("yes");
                                RoadsideAssistancePolicy p = new RoadsideAssistancePolicy(policyId, vehicle, person, coverageAmount, startDate, endDate, isVehicleRegistered);
                                yield p;
                            }
                            default -> null;
                        };

                        if (policy != null && policy.validatePolicy()) {
                            policies.add(policy);
                            totalPremiums += policy.getPremiumAmount();
                            System.out.println("Policy added successfully.");
                        } else {
                            System.out.println("Invalid policy details or type.");
                        }
                    }
                    case 2 -> {
                        System.out.print("Policy ID for Claim: ");
                        String policyId = scanner.nextLine();
                        InsurancePolicy policy = policies.stream()
                                .filter(p -> p.getPolicyId().equals(policyId))
                                .findFirst()
                                .orElse(null);

                        if (policy == null) {
                            System.out.println("Policy not found.");
                            continue;
                        }

                        System.out.print("Claim ID: ");
                        String claimId = scanner.nextLine();
                        System.out.print("Claim Amount: ");
                        String claimAmountInput = scanner.nextLine();
                        double claimAmount;
                        try {
                            claimAmount = Double.parseDouble(claimAmountInput);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid claim amount.");
                            continue;
                        }

                        Claim claim = new Claim(claimId, claimAmount, LocalDate.now());
                        if (policy.processClaim(claimAmount)) {
                            claims.add(claim);
                            totalClaimsProcessed++;
                            System.out.println("Claim processed successfully.");
                        } else {
                            System.out.println("Claim processing failed.");
                        }
                    }
                    case 3 -> {
                        System.out.println("\n=== Insurance System Report ===");
                        System.out.println("Total Premiums Collected: $" + totalPremiums);
                        System.out.println("Total Claims Processed: " + totalClaimsProcessed);
                        System.out.println("Coverage Breakdown by Policy Type:");
                        long comprehensiveCount = policies.stream().filter(p -> p instanceof ComprehensivePolicy).count();
                        long thirdPartyCount = policies.stream().filter(p -> p instanceof ThirdPartyPolicy).count();
                        long collisionCount = policies.stream().filter(p -> p instanceof CollisionPolicy).count();
                        long liabilityCount = policies.stream().filter(p -> p instanceof LiabilityPolicy).count();
                        long roadsideCount = policies.stream().filter(p -> p instanceof RoadsideAssistancePolicy).count();
                        System.out.println("Comprehensive Policies: " + comprehensiveCount);
                        System.out.println("Third-Party Policies: " + thirdPartyCount);
                        System.out.println("Collision Policies: " + collisionCount);
                        System.out.println("Liability Policies: " + liabilityCount);
                        System.out.println("Roadside Assistance Policies: " + roadsideCount);
                        System.out.println("\nPolicy Details:");
                        for (InsurancePolicy policy : policies) {
                            System.out.println(policy.generatePolicyReport());
                            System.out.println("------------------------");
                        }
                        System.out.println("Claim Details:");
                        for (Claim claim : claims) {
                            System.out.println("Claim ID: " + claim.getClaimId() + ", Amount: $" + claim.getClaimAmount() +
                                               ", Date: " + claim.getClaimDate() + ", Status: " + claim.getClaimStatus());
                        }
                    }
                    case 4 -> {
                        System.out.println("Exiting system.");
                        return;
                    }
                    default -> System.out.println("Invalid option.");
                }
            }
        }
    }
}
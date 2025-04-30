public class Vehicle {
    private final String vehicleId;
    private final String vehicleMake;
    private final String vehicleModel;
    private final int vehicleYear;
    private final String vehicleType;

    public Vehicle(String vehicleId, String vehicleMake, String vehicleModel, int vehicleYear, String vehicleType) {
        this.vehicleId = vehicleId;
        this.vehicleMake = vehicleMake;
        this.vehicleModel = vehicleModel;
        this.vehicleYear = vehicleYear;
        this.vehicleType = vehicleType;
        validate();
    }

    private void validate() {
        if (!validateVehicle()) {
            throw new IllegalArgumentException("Invalid vehicle details.");
        }
    }

    public boolean validateVehicle() {
        return vehicleId != null && !vehicleId.isEmpty() &&
               vehicleMake != null && !vehicleMake.isEmpty() &&
               vehicleModel != null && !vehicleModel.isEmpty() &&
               vehicleYear >= 1980 && vehicleYear <= java.time.Year.now().getValue() &&
               vehicleType != null && !vehicleType.isEmpty();
    }

    // Getters
    public String getVehicleId() { return vehicleId; }
    public String getVehicleMake() { return vehicleMake; }
    public String getVehicleModel() { return vehicleModel; }
    public int getVehicleYear() { return vehicleYear; }
    public String getVehicleType() { return vehicleType; }
}
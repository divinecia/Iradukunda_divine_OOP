import java.util.HashMap;
import java.util.Map;

public class Warehouse {
    private final String warehouseId;
    private String location;
    private int capacity;
    private String managerName;
    private final Map<String, Integer> inventory;

    public Warehouse(String warehouseId, String location, int capacity, String managerName) {
        this.warehouseId = warehouseId;
        if (validateLocation(location)) {
            this.location = location;
        } else {
            throw new IllegalArgumentException("Invalid location.");
        }
        if (capacity > 0) {
            this.capacity = capacity;
        } else {
            throw new IllegalArgumentException("Capacity must be positive.");
        }
        if (validateManagerName(managerName)) {
            this.managerName = managerName;
        } else {
            throw new IllegalArgumentException("Invalid manager name.");
        }
        this.inventory = new HashMap<>();
    }

    private boolean validateLocation(String location) {
        return location != null && !location.trim().isEmpty();
    }

    private boolean validateManagerName(String name) {
        return name != null && !name.trim().isEmpty();
    }

    public void addStock(String itemId, int quantity) {
        int currentTotal = inventory.values().stream().mapToInt(Integer::intValue).sum();
        if (currentTotal + quantity <= capacity) {
            inventory.put(itemId, inventory.getOrDefault(itemId, 0) + quantity);
            System.out.println("Added " + quantity + " units of item " + itemId + " to warehouse.");
        } else {
            System.out.println("Error: Warehouse capacity exceeded.");
        }
    }

    public void removeStock(String itemId, int quantity) {
        if (inventory.containsKey(itemId) && inventory.get(itemId) >= quantity) {
            inventory.put(itemId, inventory.get(itemId) - quantity);
            if (inventory.get(itemId) == 0) {
                inventory.remove(itemId);
            }
            System.out.println("Removed " + quantity + " units of item " + itemId + " from warehouse.");
        } else {
            System.out.println("Error: Insufficient stock in warehouse.");
        }
    }

    public String generateInventoryReport() {
        StringBuilder report = new StringBuilder(String.format(
                "Warehouse Report:\nID: %s\nLocation: %s\nCapacity: %d\nManager: %s\nInventory:\n",
                warehouseId, location, capacity, managerName));
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            report.append(String.format("- Item ID: %s, Quantity: %d\n", entry.getKey(), entry.getValue()));
        }
        return report.toString();
    }

    public String getWarehouseId() { return warehouseId; }
    public String getLocation() { return location; }
    public int getCapacity() { return capacity; }
    public String getManagerName() { return managerName; }
}
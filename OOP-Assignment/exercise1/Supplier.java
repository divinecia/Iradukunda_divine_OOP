public class Supplier {
    private final String supplierId;
    private String companyName;
    private String contactPerson;
    private String phone;
    private String email;

    public Supplier(String supplierId, String companyName, String contactPerson, String phone, String email) {
        this.supplierId = supplierId;
        if (validateCompanyName(companyName)) {
            this.companyName = companyName;
        } else {
            throw new IllegalArgumentException("Invalid company name.");
        }
        if (validateContactPerson(contactPerson)) {
            this.contactPerson = contactPerson;
        } else {
            throw new IllegalArgumentException("Invalid contact person.");
        }
        if (validatePhone(phone)) {
            this.phone = phone;
        } else {
            throw new IllegalArgumentException("Invalid phone number.");
        }
        if (validateEmail(email)) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Invalid email.");
        }
    }

    private boolean validateCompanyName(String name) {
        return name != null && !name.trim().isEmpty();
    }

    private boolean validateContactPerson(String person) {
        return person != null && !person.trim().isEmpty();
    }

    private boolean validatePhone(String phone) {
        return phone != null && phone.matches("\\d{10}");
    }

    private boolean validateEmail(String email) {
        return email != null && email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    }

    public String getSupplierId() { return supplierId; }
    public String getCompanyName() { return companyName; }
    public String getContactPerson() { return contactPerson; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }
}
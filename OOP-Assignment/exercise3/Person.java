import java.time.LocalDate;
import java.time.Period;

public class Person {
    private final String personId;
    private final String fullName;
    private final LocalDate dob;
    private final String email;
    private final String phone;

    public Person(String personId, String fullName, LocalDate dob, String email, String phone) {
        this.personId = personId;
        this.fullName = fullName;
        this.dob = dob;
        this.email = email;
        this.phone = phone;
        validate();
    }

    private void validate() {
        if (!validatePerson()) {
            throw new IllegalArgumentException("Invalid person details.");
        }
    }

    public boolean validatePerson() {
        return personId != null && !personId.isEmpty() &&
               fullName != null && !fullName.isEmpty() &&
               dob != null && Period.between(dob, LocalDate.now()).getYears() >= 18 &&
               email != null && email.contains("@") &&
               phone != null && phone.length() >= 10;
    }

    // Getters
    public String getPersonId() { return personId; }
    public String getFullName() { return fullName; }
    public LocalDate getDob() { return dob; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
}
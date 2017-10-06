public class Contact {
    private String firstName;
    private String lastName;
    private String phoneNumber;

    public Contact(String name, String lastName, String phoneNumber) {
        this.firstName = name;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public String getLastName(){ return this.lastName; }

    public String getFullName() { return String.format("%s %s", this.firstName, this.lastName);}
}

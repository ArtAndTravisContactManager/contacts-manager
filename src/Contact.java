public class Contact {
    private String name;
    private String lastName;
    private String phoneNumber;

    public Contact(String name, String lastname, String phoneNumber) {
        this.name = name;
        this.lastName = lastname;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getLastName(){ return this.lastName; }

}

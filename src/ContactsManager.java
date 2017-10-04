import util.FileHandler;

import java.util.ArrayList;
import java.util.List;

public class ContactsManager {
    public static void main(String[] args) {
        // Show all contacts
        // Add a new contact
        // Search contacts by name
        // Delete existing contact
        ArrayList<Contact> contacts = new ArrayList<>();
        FileHandler contactsFile = new FileHandler("./resources", "contacts.txt");
        List<String> contactsFileContents = contactsFile.readFromFile();
        contactsFile.getFilePath();

        for (String line : contactsFileContents) {
            String[] parts = line.split(",");
            String name = parts[0];
            String phoneNumber = parts[1];
//            String name = line.split(",")[0];
//            String phoneNumber = line.split(",")[1];
            Contact currentContact = new Contact(name, phoneNumber);
            contacts.add(currentContact);

        }
        displayContacts(contacts);
    }

    public static void displayContacts(ArrayList<Contact> contacts) {
        System.out.println("Name | Phone Number ");
        System.out.println("---------------------------------");
        for (Contact contact: contacts){
            System.out.println(contact.getName() + " | " + contact.getPhoneNumber());
        }
    }
}



import util.FileHandler;
import util.Input;

import java.util.ArrayList;
import java.util.List;

public class ContactsManager {
       public static Input console = new Input();

    public static void main(String[] args) {
        // Show all contacts
        // Add a new contact
        // Search contacts by name
        // Delete existing contact
        ArrayList<Contact> contacts = new ArrayList<>();
        FileHandler contactsFile = new FileHandler("./resources", "contacts.txt");
        List<String> contactsFileContents = contactsFile.readFromFile();
        int userChoice;

        contactsFile.getFilePath();

        for (String line : contactsFileContents) {
            String[] parts = line.split(",");
            String name = parts[0];
            String phoneNumber = parts[1];
            Contact currentContact = new Contact(name, phoneNumber);
            contacts.add(currentContact);
        }
    // ***Possible refactoring per #2 in menu***
        displayMenu();
        userChoice = console.getInt(1, 5, "Enter an option (1, 2, 3, 4, or 5): ");
        System.out.println();

        switch (userChoice) {
            case 1:
                displayContacts(contacts);
                break;
            case 2:
                addNewContact(contacts);
                displayContacts(contacts);
                break;


        }
    }

    private static void addNewContact(ArrayList<Contact> contacts) {
        String name = console.getString("Please Enter contact's name:");
        String phoneNumber = console.getString("Please enter contact's phone number:");
        contacts.add(new Contact(name, phoneNumber));
    }

    public static void displayContacts(ArrayList<Contact> contacts) {
        System.out.println("Name | Phone Number ");
        System.out.println("---------------------------------");
        for (Contact contact: contacts){
            System.out.println(contact.getName() + " | " + contact.getPhoneNumber());
        }
    }

    public static void displayMenu() {
        System.out.println("Welcome to your Contacts Manager!\n");
        System.out.println("1. View contacts.");
        System.out.println("2. Add a new contact.");
        System.out.println("3. Search a contact by name.");
        System.out.println("4. Delete an existing contact.");
        System.out.println("5. Exit.");
    }
}



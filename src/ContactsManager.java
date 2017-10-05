import util.FileHandler;
import util.Input;

import java.util.ArrayList;
import java.util.List;

public class ContactsManager {
       public static Input console = new Input();

    public static void main(String[] args) {
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
        do {
            displayMenu();
            userChoice = console.getInt(1, 5, "Enter an option (1, 2, 3, 4, or 5):\n ");
            System.out.println("You entered: " + userChoice);
            System.out.println();


            switch (userChoice) {
                // View contacts
                case 1:
                    displayContacts(contacts);
                    System.out.println();

                    break;
                // Add a new contact
                case 2:
                    addNewContact(contacts);
                    displayContacts(contacts);
                    System.out.println();
                    break;
                // Search a contact by name
                case 3:
                    String searchName = console.getString("What is the name of the contact you are looking for?: ");
                    int index = searchContact(contacts, searchName);
                    if (index >= 0) {
                        System.out.printf("Name: %s\nPhone Number: %s\n",
                                contacts.get(index).getName(), contacts.get(index).getPhoneNumber());
                    } else {
                        System.out.printf("User '%s' was not found!\n", searchName);
                        System.out.println();
                    }
                    break;
                // Delete an existing contact
                case 4:
                    break;
                // Exit
                case 5:
                    break;
            }
        } while(console.yesNo("Would you like another option? (y/n)"));

    }

    public static int searchContact(ArrayList<Contact> contacts, String searchName) {
        for(int i = 0; i < contacts.size(); i++) {
            if (contacts.get(i).getName().equals(searchName)) {
                return i;
            }
        }
        return -1;
    }

    public static void addNewContact(ArrayList<Contact> contacts) {
        String name = console.getString("Please Enter contact's name:\n");
        String phoneNumber = console.getString("Please enter contact's phone number:\n");
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
        System.out.println();

    }
}



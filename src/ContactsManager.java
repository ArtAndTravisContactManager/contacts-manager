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
            userChoice = console.getInt(1, 5, "Enter an option (1, 2, 3, 4, or 5):\n" );

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
                    String searchName = console.getString("What is the name of the contact you are looking for?:\n ");
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
                    displayContacts(contacts);
                    System.out.println();
                    String contactToDelete = console.getString("Enter the contact's name to delete:\n ");
                    if (console.yesNo("Are you sure you want to delete " + contactToDelete + " ?\n")) {
                        if (deleteContact(contacts, contactToDelete)) {
                            System.out.println("Contact deleted successfully.");
                        } else {
                            System.out.println("Unable to delete contact by that name.");
                        }
                    }
                    break;
                // Exit
                case 5:
                    System.out.println("Saving contacts... Thank you for using the contacts menu.");
                    saveContacts(contacts, contactsFile);
                    System.exit(0);
                    break;
            }
        } while(console.yesNo("Would you like another option? (y/n)\n"));
        System.out.println("Goodbye");
            saveContacts(contacts, contactsFile);

    }

    public static String[] phoneNumberFormat (String phoneNumber) {
        String areaCode = phoneNumber.substring (0,3);
        String primaryNumber = phoneNumber.substring(3,6);
        String lastPartOfNumber = phoneNumber.substring(6);
        return new String[]{areaCode, primaryNumber, lastPartOfNumber};
    }

    private static void saveContacts(ArrayList<Contact> contacts, FileHandler contactsFile) {
        List<String> contentsToWrite = new ArrayList<>();

        for (Contact contactToWrite : contacts) {
            contentsToWrite.add(String.format("%s,%s", contactToWrite.getName(), contactToWrite.getPhoneNumber()));
        }
        contactsFile.writeToFile(contentsToWrite);
    }

    public static boolean deleteContact(ArrayList<Contact> contacts, String contactToDelete) {
        int index = searchContact(contacts, contactToDelete);

        if (index < 0) {
            return false;
        }

        contacts.remove(index);
        return true;
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
        String[] phoneNumberList = phoneNumberFormat(phoneNumber);
        phoneNumber = ("(" + phoneNumberList[0] + ")" + phoneNumberList[1] + " - " + phoneNumberList[2]);
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



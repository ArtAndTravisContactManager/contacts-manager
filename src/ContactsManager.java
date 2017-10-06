import com.sun.deploy.util.StringUtils;
import util.FileHandler;
import util.Input;

import java.util.ArrayList;
import java.util.Collections;
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
            String lastName = parts[1];
            String phoneNumber = parts[2];
            Contact currentContact = new Contact(name, lastName, phoneNumber);
            contacts.add(currentContact);
        }
        System.out.println("Welcome to your Contacts Manager!\n");
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

                    searchName = filterContacts(contacts, searchName);
                    if (searchName.length() > 0) {
                        int index = searchContact(contacts, searchName);
                        System.out.printf("Name: %s\nPhone Number: %s\n",
                                contacts.get(index).getFullName(), contacts.get(index).getPhoneNumber());
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

    public static String filterContacts(ArrayList<Contact> contacts, String filterName) {
        ArrayList<String> searchResults = new ArrayList<>();

        for(Contact currentContact : contacts) {
            if (currentContact.getFullName().toLowerCase().contains(filterName.toLowerCase())) {
                searchResults.add(currentContact.getFullName());
            }
        }

        switch(searchResults.size()) {
            case 0:
                System.out.println("No contact could be located by that name.");
                return "";
            case 1:
                return searchResults.get(0);
            default:
                System.out.println("We found the following contacts:");
                for (int i = 0; i < searchResults.size(); i++) {
                    System.out.printf("%d.) %s\n", i + 1, searchResults.get(i));
                }

                int userChoice = console.getInt(1, searchResults.size(), "\nWhich contact would you like?: ") - 1;
                return searchResults.get(userChoice);
        }
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
            contentsToWrite.add(String.format("%s,%s,%s",
                    contactToWrite.getFirstName(), contactToWrite.getLastName() ,contactToWrite.getPhoneNumber()));
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
            if (contacts.get(i).getFullName().equals(searchName)) {
                return i;
            }
        }
        return -1;
    }

    public static void addNewContact(ArrayList<Contact> contacts) {
        String name = console.getString("Please Enter contact's first name:\n");
        String lname = console.getString("Please Enter contact's last name:\n");
        String phoneNumber = console.getString("Please enter contact's phone number:\n");
        String[] phoneNumberList = phoneNumberFormat(phoneNumber);
        phoneNumber = ("(" + phoneNumberList[0] + ")" + phoneNumberList[1] + " - " + phoneNumberList[2]);
        System.out.println(name);
        System.out.println(lname);
        System.out.println(phoneNumber);
        contacts.add(new Contact(name, lname, phoneNumber));
    }

    public static void displayContacts(ArrayList<Contact> contacts) {
        System.out.printf("%-30s | %14s  |\n", "Name" ,"Phone Number");
        System.out.println(String.join("", Collections.nCopies(51, "-")));
        for (Contact contact: contacts){
            //System.out.println(contact.getFirstName() + " " + contact.getLastName() + " | " + contact.getPhoneNumber());
            System.out.printf("%-30s | %15s |\n", contact.getFullName(), contact.getPhoneNumber());
        }
    }

    public static void displayMenu() {
        System.out.println("1. View contacts.");
        System.out.println("2. Add a new contact.");
        System.out.println("3. Search a contact by name.");
        System.out.println("4. Delete an existing contact.");
        System.out.println("5. Exit.");
        System.out.println();
    }
}



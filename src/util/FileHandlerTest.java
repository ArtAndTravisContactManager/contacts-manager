package util;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class FileHandlerTest {

    public static void main(String[] args) {
        FileHandler fh = new FileHandler("./resources", "contacts.txt");
        fh.createFile();

//        System.out.println(fh.getFilePath());

        List<String> contacts = new ArrayList<>();
        contacts.add("Art Valdez,(210)555-5678");
        contacts.add("Travis Payne,(361)444-4567");
        contacts.add("Michael Jordan(512)333-3456");

        List<String>FileContents =  fh.readFromFile();
        for(String line: FileContents) {
            System.out.println(line);
        }

        fh.writeToFile(contacts);

//        Files.write(Paths, contacts, StandardOpenOption.APPEND);
    }
}

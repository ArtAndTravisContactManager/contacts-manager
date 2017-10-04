package util;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class FileHandlerTest {

    public static void main(String[] args) {
        FileHandler fh = new FileHandler("./resources", "test.txt");
        fh.createFile();

//        System.out.println(fh.getFilePath());

        List<String> contacts = new ArrayList<>();
        contacts.add("Art");
        contacts.add("Travis");
        contacts.add("Michael");

        List<String>FileContents =  fh.readFromFile();
        for(String line: FileContents) {
            System.out.println(line);
        }

//        fh.writeToFile(contacts);

//        Files.write(Paths, contacts, StandardOpenOption.APPEND);
    }
}

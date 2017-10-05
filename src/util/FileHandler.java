package util;

//import java.nio.file;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import contactsManager.Contact;



public class FileHandler {
    private Path directory;
    private Path filename;

    public FileHandler(String directory, String filename) {
        this.directory = Paths.get(directory);
        this.filename = Paths.get(directory, filename);
        createFile();
    }



    protected void createFile (){
        createDirectory();

        try {
            if (Files.notExists(filename)) {
                Files.createFile(filename);
            }
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    protected void createDirectory() {
        try {
            if(Files.notExists(directory)) {
                Files.createDirectory(directory);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public String getFilePath() {
        return filename.toString();
    }

    public boolean writeToFile(List<String> contacts){
        try {
            Files.write(this.filename, contacts);
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public List<String> readFromFile(){
        try {
            return Files.readAllLines(filename);
        } catch (IOException e) {
            return null;
        }
    }
}



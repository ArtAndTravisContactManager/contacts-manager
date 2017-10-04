package util;

//import java.nio.file;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileHandler {
    private Path directory;
    private Path filename;

    public FileHandler(String directory, String filename) {
        this.directory = Paths.get(directory);
        this.filename = Paths.get(directory, filename);
    }

    public void createFile (){
        try {
            if (Files.notExists(filename)) {
                Files.createFile(filename);
            }
        } catch (IOException e){
            System.out.println(e.getMessage());
        }

    }
}



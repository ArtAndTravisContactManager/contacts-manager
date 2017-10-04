package util;

public class FileHandlerTest {

    public static void main(String[] args) {
        FileHandler fh = new FileHandler("./src/util/test", "test.txt");
        fh.createFile();

        System.out.println(fh.getFilePath());
    }
}

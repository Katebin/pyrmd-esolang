package Systems.IO;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileHandler {
    // handles files ONLY IN JAVA, read / write
    // sole purpose is to ease my suffering
    private File file;
    private Scanner scanner;

    public FileHandler(String filePath) {
        try {
            this.file = new File(filePath);
            this.scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public String read() {
        // read and return the file contents
        String fileContents = "";

        while(scanner.hasNextLine()) {
            fileContents += scanner.nextLine();
        }

        return fileContents;
    }
}

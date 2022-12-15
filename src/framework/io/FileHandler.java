package framework.io;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileHandler {
    // handles files, read / write
    // sole purpose is to ease suffering
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
            fileContents += "\n"; // add back new lines
        }

        return fileContents;
    }
}

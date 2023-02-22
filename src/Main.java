import systems.io.Console;
import systems.io.FileHandler;

public class Main {
    public static void main(String[] args) {
        FileHandler file = new FileHandler("src\\HelloWorld.pyrmd");
        Console console = new Console();

        console.println("testy");
        console.println(console.input());
    }
}
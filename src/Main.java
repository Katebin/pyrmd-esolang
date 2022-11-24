import Systems.IO.Console;
import Systems.IO.FileHandler;
import Systems.Interpreter.Lexer;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        FileHandler file = new FileHandler("src\\HelloWorld.pyrmd");
        Console console = new Console();
        Lexer lexer = new Lexer();

        ArrayList<String> tokens = lexer.smartSplit(file.read());

        for(int i = 0; i < tokens.size(); i++) {
            console.println(tokens.get(i).toString());
        }
    }
}
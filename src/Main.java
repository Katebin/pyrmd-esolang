import Systems.IO.Console;
import Systems.IO.FileHandler;
import Systems.Interpreter.Lexer;
import Systems.Interpreter.Token;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        FileHandler file = new FileHandler("src\\HelloWorld.pyrmd");
        Console console = new Console();
        Lexer lexer = new Lexer();

        // debugging
        ArrayList<Token> tokens = lexer.tokenize(lexer.smartSplit(file.read()));// grab tokens
        for(Token lineToken : tokens) {
            console.println(lineToken.toString()); // print one per line
        }
    }
}
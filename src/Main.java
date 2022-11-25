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

        //ArrayList<Token> debugTokens = lexer.tokenize(lexer.smartSplit(file.read())); // grab tokens
        ArrayList<String> tokens = lexer.smartSplit(file.read());

        for(String lineToken : tokens) {
            console.println(lineToken.toString()); // print one per line
        }
    }
}
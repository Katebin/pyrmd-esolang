import Systems.IO.Console;
import Systems.IO.FileHandler;
import Systems.Interpreter.Lexer;
import Systems.Interpreter.Parser;
import Systems.Interpreter.Token;

public class Main {
    public static void main(String[] args) {
        FileHandler file = new FileHandler("src\\HelloWorld.pyrmd");
        Console console = new Console();
        Lexer lexer = new Lexer();
        Parser parser = new Parser();

        // debugging
        Token[] tokens = lexer.lex(file.read());// grab tokens
        for(Token lineToken : tokens) {
            console.println(lineToken.toString()); // print one per line
        }

        console.println("Grammatical Status: " + Boolean.toString(parser.isExpression(tokens)));
    }
}
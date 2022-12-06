import Systems.IO.Console;
import Systems.IO.FileHandler;
import Systems.Interpreter.Lexer;
import Systems.Interpreter.Tokens.ValueToken;

public class Main {
    public static void main(String[] args) {
        FileHandler file = new FileHandler("src\\HelloWorld.pyrmd");
        Console console = new Console();
        Lexer lexer = new Lexer();

        // debugging
        ValueToken[] tokens = lexer.lex(file.read());// grab tokens
        for(ValueToken lineToken : tokens) {
            console.println(lineToken.type.toString()); // print one per line
        }

        //console.println("Grammatical Status: " + Boolean.toString(parser.isExpression(tokens)));
    }
}
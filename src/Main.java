import development.parser.Lexer;
import framework.io.Console;
import framework.io.FileHandler;

public class Main {
    // temporary, only used for development
    public static void main(String[] args) {
        Console console = new Console();
        Lexer lexer = new Lexer();
        FileHandler fileHandler = new FileHandler("src/HelloWorld.txt");

        lexer.smartSplit(fileHandler.read());

        for(String lexeme : lexer.lexemes) {
            console.println(lexeme.toString());
        }

        for(int i = 0; i < 3; i++) {
            console.println(console.input());
        }
    }
}

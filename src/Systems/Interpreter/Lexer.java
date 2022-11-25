package Systems.Interpreter;
import java.util.ArrayList;

public class Lexer {
    // I am a lexer, I speak for the lexemes
    private char[] rawSeparators = new char[] {' ', '\n', '\t', ',', '.', '{', '}', '(', ')', '[', ']',';', '\'', '"'};
    public Lexer() {}

    private boolean isDigit(char character) {
        // check if char is a digit, no import
        switch(character) {
            case '0':
                return true;
            case '1':
                return true;
            case '2':
                return true;
            case '3':
                return true;
            case '4':
                return true;
            case '5':
                return true;
            case '6':
                return true;
            case '7':
                return true;
            case '8':
                return true;
            case '9':
                return true;
            default:
                return false;
        }
    }

    private boolean isNumber(String lexeme) {
        // check if a string is a number
        int checks = 0;
        for(int charNum = 0; charNum < lexeme.length(); charNum++) {
            if(isDigit(lexeme.charAt(charNum)) == true) {
                checks += 1;
            }
        }

        if(checks == lexeme.length()) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isSeparator(char sourceCharacter) {
        // check if a character matches a separator
        for(char separator : rawSeparators) {
            if(sourceCharacter == separator) {
                return true;
            }
        }

        return false;
    }

    public ArrayList<String> smartSplit(String source) {
        // split the source code into potential lexemes
        ArrayList<String> lexemes = new ArrayList<String>(); // stores source
        String charCache = ""; // stores characters
        byte currentState = 0; // for states
        char currentCharacter;

        for(int charNum = 0; charNum < source.length(); charNum++) { // fix int limit later
            // scan each char and create a potential array of lexemes
            currentCharacter = source.charAt(charNum);

            switch(currentState) {
                case 0: // default state
                    if (isSeparator(currentCharacter) == true) {
                        if (charCache.length() > 0) { // avoid sad appends
                            lexemes.add(charCache);
                            lexemes.add(String.valueOf(currentCharacter));
                            charCache = ""; // reset cache
                        } else {
                            lexemes.add(String.valueOf(currentCharacter));
                        }
                    } else if(currentCharacter == '@') {
                        // switch to handle comments, ignores tokens
                        currentState = 1;

                    } else {
                        charCache += currentCharacter;
                    }

                case 1: // comment state
                    if(currentCharacter == '\n') {
                        currentState = 0; // switch back to default
                    } else {
                        continue; // skip chars
                    }
            }
        }

        if(charCache.length() > 0) { // handle the final index
            lexemes.add(charCache);
        }

        return lexemes;
    }

    public ArrayList<Token> tokenize (ArrayList<String> lexemes) {
        ArrayList<Token> tokens = new ArrayList<Token>();

        // scan and tokenize lexemes
        for(String suspect : lexemes) {
            //System.out.println(suspect);
            switch(suspect) {
                // separators
                case " ":
                    break; // do not tokenize spaces
                case "\n":
                    tokens.add(Token.NEW_LINE);
                    break;
                case "\t":
                    tokens.add(Token.INDENT);
                    break;

                // base types
                case "int":
                    tokens.add(Token.INT);
                    break;
                case "void":
                    tokens.add(Token.VOID);
                    break;

                // operators
                case "->":
                    tokens.add(Token.DEFINE);
                    break;
                case "=":
                    tokens.add(Token.EQUALS);
                    break;
                case "!=":
                    tokens.add(Token.NOT_EQUALS);
                    break;
                case "+":
                    tokens.add(Token.PLUS);
                    break;
                case "-":
                    tokens.add(Token.MINUS);
                    break;
                case "*":
                    tokens.add(Token.TIMES);
                    break;
                case "/":
                    tokens.add(Token.DIVIDE);
                    break;

                // string tools
                case "\"":
                    tokens.add(Token.DOUBLE_QUOTE);
                    break;
                case "'":
                    tokens.add(Token.SINGLE_QUOTE);
                    break;

                // symbols
                case ";":
                    tokens.add(Token.SEMICOLON);
                    break;
                case "(":
                    tokens.add(Token.L_PAREN);
                    break;
                case ")":
                    tokens.add(Token.R_PAREN);
                    break;
                case "[":
                    tokens.add(Token.L_BRACK);
                    break;
                case "]":
                    tokens.add(Token.R_BRACK);
                    break;
                case "{":
                    tokens.add(Token.L_CURL);
                    break;
                case "}":
                    tokens.add(Token.R_CURL);
                    break;

                // handle identifiers and numbers
                default:
                    if(isNumber(suspect) == true) {
                        tokens.add(Token.NUMBER);
                    } else {
                        tokens.add(Token.IDENTIFIER);
                    }

                    break;
            }
        }

        return tokens;
    }
}

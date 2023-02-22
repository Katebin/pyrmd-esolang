package Systems.Interpreter;
import Systems.Interpreter.Tokens.Token;
import Systems.Interpreter.Tokens.ValueToken;
import java.util.ArrayList;

public class Lexer {
    // I am a lexer, I speak for the lexemes
    private final char[] rawSeparators = new char[] {' ', '\n', '\t', ',', '.', '{', '}', '(', ')', '[', ']',';', '\''};
    public Lexer() {}

    private boolean isNumber(String lexeme) {
        // check if a string is a number
        int checks = 0;
        for(int charNum = 0; charNum < lexeme.length(); charNum++) {
            if(Character.isDigit(lexeme.charAt(charNum)) == true) {
                checks += 1;
            }
        }

        if(checks == lexeme.length()) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isAlphaNumeric(String lexeme) {
        // check if the provided string is alphanumeric, needed for identifiers
        int checks = 0;
        for(int charNum = 0; charNum < lexeme.length(); charNum++) {
            if(Character.isDigit(lexeme.charAt(charNum)) | Character.isAlphabetic(lexeme.charAt(charNum))) {
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

    private ArrayList<String> smartSplit(String source) {
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
                        break;

                    } else if(currentCharacter == '@') {
                        // switch to handle comments, ignores tokens
                        currentState = 1;
                        break;

                    } else if(currentCharacter == '"') {
                        // switch to handle comments
                        if (charCache.length() > 0) { // avoid sad appends
                            lexemes.add(charCache);
                        }

                        currentState = 2;
                        charCache = "\""; // reset cache
                        break;

                    } else {
                        charCache += currentCharacter;
                        break;
                    }

                case 1: // comment state
                    if(currentCharacter == '@') {
                        currentState = 0; // switch back to default
                    }
                    break;

                case 2: // string state
                    if(currentCharacter == '"') {
                        lexemes.add(charCache + currentCharacter);
                        charCache = "";
                        currentState = 0;

                    } else {
                        charCache += currentCharacter;
                    }
                    break;
            }
        }

        if(charCache.length() > 0) { // handle the final index
            lexemes.add(charCache);
        }

        return lexemes;
    }

    private ArrayList<ValueToken> tokenize (ArrayList<String> lexemes) {
        // tokenize the lexemes for further processing
        ArrayList<ValueToken> tokens = new ArrayList<ValueToken>();

        for(String lexeme : lexemes) {
            switch(lexeme) {
                // types
                case "int":
                case "void":
                    tokens.add(new ValueToken(lexeme, Token.LITERAL));
                    break;

                // math operators
                case "+":
                case "-":
                case "*":
                case "/":
                    tokens.add(new ValueToken(lexeme, Token.MATH_OP));
                    break;

                // logic operators
                case "=":
                case "!=":
                    tokens.add(new ValueToken(lexeme, Token.LOGIC_OP));
                    break;

                // keywords
                case "return":
                    tokens.add(new ValueToken(lexeme, Token.RETURN));
                    break;

                // symbols
                case ";":
                    tokens.add(new ValueToken(lexeme, Token.SEMICOLON));
                    break;
                case ",":
                    tokens.add(new ValueToken(lexeme, Token.COMMA));
                    break;
                case "->":
                    tokens.add(new ValueToken(lexeme, Token.DEFINE_OP));
                    break;
                case "(":
                    tokens.add(new ValueToken(lexeme, Token.L_PAREN));
                    break;
                case ")":
                    tokens.add(new ValueToken(lexeme, Token.R_PAREN));
                    break;
                case "{":
                    tokens.add(new ValueToken(lexeme, Token.L_CURL));
                    break;
                case "}":
                    tokens.add(new ValueToken(lexeme, Token.R_CURL));
                    break;
                case "[":
                    tokens.add(new ValueToken(lexeme, Token.L_BRACK));
                    break;
                case "]":
                    tokens.add(new ValueToken(lexeme, Token.R_BRACK));
                    break;

                // specials
                default:
                    if(isNumber(lexeme) == true) {
                        tokens.add(new ValueToken(lexeme, Token.NUMBER));
                    } else if(lexeme.charAt(0) == '"' & lexeme.charAt(lexeme.length() - 1) == '"') { // should not go out of range as length is always > 0
                        tokens.add(new ValueToken(lexeme, Token.STRING));
                    } else if(isAlphaNumeric(lexeme) == true){
                        tokens.add(new ValueToken(lexeme, Token.IDENTIFIER));
                    } else { // handle unknown tokens
                        // throw error
                    }

                    break;
            }
        }
        return tokens;
    }

    public ValueToken[] lex(String source) {
        // convert the final array lists to an immutable array
        ArrayList<ValueToken> oldTokens = tokenize(smartSplit(source));
        ValueToken[] tokens = oldTokens.toArray(new ValueToken[oldTokens.size()]);

        return tokens;
    }
}
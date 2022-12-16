package development.parser;
import java.util.ArrayList;

public class Lexer {
    // I am the lexer, I speak for lexemes.
    public ArrayList<String> lexemes = new ArrayList<String>();
    private final char[] rawSeparators = new char[] {' ', '\n', '\t', ',', '.', '{', '}', '(', ')', '[', ']',';', '\''};

    public Lexer() {
        this.lexemes = lexemes;
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

    public void smartSplit(String source) {
        // split the source code into potential lexemes
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
                            this.lexemes.add(charCache);
                            this.lexemes.add(String.valueOf(currentCharacter));
                            charCache = ""; // reset cache
                        } else {
                            this.lexemes.add(String.valueOf(currentCharacter));
                        }
                        break;

                    } else if(currentCharacter == '@') {
                        // switch to handle comments, ignores tokens
                        currentState = 1;
                        break;

                    } else if(currentCharacter == '"') {
                        // switch to handle comments
                        if (charCache.length() > 0) { // avoid sad appends
                            this.lexemes.add(charCache);
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
                        this.lexemes.add(charCache + currentCharacter);
                        charCache = "";
                        currentState = 0;

                    } else {
                        charCache += currentCharacter;
                    }
                    break;
            }
        }

        if(charCache.length() > 0) { // handle the final index
            this.lexemes.add(charCache);
        }
    }
}

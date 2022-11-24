package Systems.Interpreter;

import java.util.ArrayList;

public class Lexer {
    // I am a lexer, I speak for the lexemes
    private char[] rawSeparators = new char[] {' ', '\n', '\t', ',', '.', '{', '}', '(', ')', '[', ']',';', '\'', '"'};
    public Lexer() {}

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
        ArrayList<String> potentialLexemes = new ArrayList<String>(); // stores source
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
                            potentialLexemes.add(charCache);
                            potentialLexemes.add(String.valueOf(currentCharacter));
                            charCache = ""; // reset cache
                        } else {
                            potentialLexemes.add(String.valueOf(currentCharacter));
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
            potentialLexemes.add(charCache);
        }

        return potentialLexemes;
    }
}

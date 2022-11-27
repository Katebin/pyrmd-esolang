package Systems.Interpreter;

public class Parser {
    // I am the parser, I ensure code is grammatically correct
    public Parser() {}

    public boolean isExpression(Token[] section) {
        // check if the provided tokens are an expression
        byte currentState = 0;
        int parenDepth = 0;

        for(int i = 0; i < section.length; i++) {
            switch(currentState) { // state shifter
                case 0: // default case
                    if(section[i] == Token.NUMBER | section[i] == Token.IDENTIFIER | section[i] == Token.STRING) {
                        currentState = 1; // seek operator

                    } else if(section[i] == Token.L_PAREN) {
                        currentState = 0; // seek value
                        parenDepth += 1;

                    } else if(section[i] == Token.R_PAREN) {
                        currentState = 1;
                        parenDepth -= 1;

                    } else {
                        return false; // does not match, not valid expression
                    }

                    break;
                case 1: // operator case
                    if(section[i] == Token.OPERATOR | section[i] == Token.COMMA) {
                        currentState = 0; // seek value

                    } else if(section[i] == Token.L_PAREN) {
                        currentState = 0; // seek value
                        parenDepth += 1;

                    } else if(section[i] == Token.R_PAREN) {
                        currentState = 1; // seek operator
                        parenDepth -= 1;

                    } else {
                        return false; // does not match, not valid expression
                    }

                    break;
            }
        }

        if(parenDepth == 0) { // ensure all parens are closed
            return true;

        } else { // handle when parens are closed (add error stuff later)
            return false;
        }
    }
}

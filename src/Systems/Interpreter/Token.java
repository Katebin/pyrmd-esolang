package Systems.Interpreter;

public enum Token {
    // types
    INT(),
    VOID(),
    NUMBER(),
    IDENTIFIER(),

    // operators
    DEFINE(),
    EQUALS(),
    NOT_EQUALS(),
    PLUS(),
    MINUS(),
    TIMES(),
    DIVIDE(),

    // String tools
    DOUBLE_QUOTE(),
    SINGLE_QUOTE(),

    // symbols
    SEMICOLON(),
    L_PAREN(),
    R_PAREN(),
    L_BRACK(),
    R_BRACK(),
    L_CURL(),
    R_CURL(),

    // Specials
    INDENT(),
    NEW_LINE(),
}

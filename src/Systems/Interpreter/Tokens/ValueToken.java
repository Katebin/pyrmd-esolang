package Systems.Interpreter.Tokens;

public class ValueToken {
    // store the value of tokens and their type
    public final String value;
    public final Token type;

    public ValueToken(String value, Token type) {
        this.value = value;
        this.type = type;
    }
}

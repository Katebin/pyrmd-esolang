package framework.errors;

public class LexingError {
    private String highlight;
    private int lineNum;

    public LexingError(String highlight, int lineNum) {
        this.lineNum = lineNum;
        this.highlight = highlight;
    }

    public void performAction() {

    }
}

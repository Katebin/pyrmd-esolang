package framework.errors;

public abstract class Error {
    private String highlight;
    private int lineNum;

    public Error(String highlight, int lineNum) {
        this.lineNum = lineNum;
        this.highlight = highlight;
    }

    public abstract void performAction();
}

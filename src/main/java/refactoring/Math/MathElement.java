package refactoring.Math;

public abstract class MathElement {
    protected int indexStart;
    protected int indexEnd;
    protected String chars;

    public int getIndexStart() {
        return indexStart;
    }

    public int getIndexEnd() {
        return indexEnd;
    }

    public MathElement(String chars, int indexStart, int indexEnd) {
        this.chars = chars;
        this.indexStart = indexStart;
        this.indexEnd = indexEnd;
    }

    public abstract double calculate();

    public String getChars() { return chars;
    }

    public abstract boolean isEmpty();

    public abstract MathElement getInstance(int indexStart, int indexEnd );

    public abstract String toString();
}

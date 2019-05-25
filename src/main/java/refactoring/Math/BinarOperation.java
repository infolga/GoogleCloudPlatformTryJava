package refactoring.Math;

public abstract class BinarOperation extends Operation {
    protected MathElement A;
    protected MathElement B;

    public BinarOperation(String chars, int indexStart, int indexEnd) {
        super(chars, indexStart, indexEnd);
    }

    @Override
    abstract public double calculate();

    public void setA(MathElement A) {
        this.A = A;
    }

    public void setB(MathElement B) {
        this.B = B;
    }

    @Override
    public boolean isEmpty() {
        return A == null || B == null;
    }

    @Override
    public String toString() {
        if (A == null || B == null) {
            return chars;
        } else {
            return "(" + A.toString() + ")" + chars + "(" + B.toString() + ")";
        }
    }
}

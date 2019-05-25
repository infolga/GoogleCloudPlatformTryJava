package refactoring.Math;


public abstract class UnarOperation extends Operation {

    protected MathElement A;

    public UnarOperation(String chars, int indexStart, int indexEnd) {
        super(chars, indexStart, indexEnd);
    }

    @Override
    abstract public double calculate();

    public void setA(MathElement A) {
        this.A = A;
    }

    @Override
    public boolean isEmpty() {
        return A == null;
    }

    @Override
    public String toString() {
        if (A == null) {
            return chars;
        } else {
            return chars + "(" + A.toString() + ")";
        }
    }
}

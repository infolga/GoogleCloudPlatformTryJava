package refactoring.Math;

public abstract class UnarFunction extends Function {
    protected MathElement A;

    public UnarFunction(String chars, int indexStart, int indexEnd) {
        super(chars, indexStart, indexEnd);
    }


    @Override
    public double calculate() {
        return 0;
    }

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

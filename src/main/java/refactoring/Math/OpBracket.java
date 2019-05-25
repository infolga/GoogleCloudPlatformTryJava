package refactoring.Math;


public class OpBracket extends MathElement {


    public OpBracket(int indexStart, int indexEnd) {
        super("(", indexStart, indexEnd);
    }

    @Override
    public double calculate() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public MathElement getInstance(int indexStart, int indexEnd) {
        return new OpBracket(indexStart,indexEnd);
    }

    @Override
    public String toString() {
        return null;
    }

}

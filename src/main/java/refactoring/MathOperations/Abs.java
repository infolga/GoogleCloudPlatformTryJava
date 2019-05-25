package refactoring.MathOperations;

import refactoring.Math.MathElement;
import refactoring.Math.UnarFunction;

public class Abs extends UnarFunction {

    public Abs(int indexStart, int indexEnd) {
        super("abs", indexStart, indexEnd);
    }

    @Override
    public double calculate() {
        return Math.abs(A.calculate());
    }

    @Override
    public MathElement getInstance(int indexStart, int indexEnd) {
        return new Abs(indexStart,indexEnd);
    }

}

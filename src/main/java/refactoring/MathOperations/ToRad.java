package refactoring.MathOperations;

import refactoring.Math.MathElement;
import refactoring.Math.UnarFunction;

public class ToRad extends UnarFunction {

    public ToRad(int indexStart, int indexEnd) {
        super("rad", indexStart, indexEnd);
    }

    @Override
    public double calculate() {
        return Math.toRadians(A.calculate());
    }

    public MathElement getInstance(int indexStart, int indexEnd) {
        return new ToRad(indexStart,indexEnd);
    }

}

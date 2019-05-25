package refactoring.MathOperations;


import refactoring.Math.MathElement;
import refactoring.Math.UnarFunction;

public class ToDeg extends UnarFunction {

    public ToDeg(int indexStart, int indexEnd) {
        super("deg", indexStart, indexEnd);
    }

    @Override
    public double calculate() {
        return Math.toDegrees(A.calculate());
    }

    public MathElement getInstance(int indexStart, int indexEnd) {
        return new ToDeg(indexStart,indexEnd);
    }

}

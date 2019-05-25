package refactoring.MathOperations;
import refactoring.Math.MathElement;
import refactoring.Math.UnarFunction;

public class Sqrt extends UnarFunction {

     public Sqrt(int indexStart, int indexEnd) {
        super("sqrt", indexStart, indexEnd);
    }

    @Override
    public double calculate() {
        return Math.sqrt(A.calculate());
    }

    public MathElement getInstance(int indexStart, int indexEnd) {
        return new Sqrt(indexStart,indexEnd);
    }
}
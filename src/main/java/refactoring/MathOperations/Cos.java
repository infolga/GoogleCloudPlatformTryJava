package refactoring.MathOperations;


import refactoring.Math.MathElement;
import refactoring.Math.UnarFunction;

public class Cos extends UnarFunction {

    public Cos(int indexStart, int indexEnd) {
        super("cos", indexStart, indexEnd);
    }

    @Override
    public double calculate() {
        return Math.cos(A.calculate());
    }

    @Override
    public MathElement getInstance(int indexStart, int indexEnd) {
        return new Cos(indexStart,indexEnd);
    }

}

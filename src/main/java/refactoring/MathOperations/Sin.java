package refactoring.MathOperations;


import refactoring.Math.MathElement;
import refactoring.Math.UnarFunction;

public class Sin extends UnarFunction {

     public Sin(int indexStart, int indexEnd) {
        super("sin",indexStart,indexEnd);
    }

    @Override
    public double calculate() {
        return Math.sin(A.calculate());
    }

    public MathElement getInstance(int indexStart, int indexEnd) {
        return new Sin(indexStart,indexEnd);
    }
}

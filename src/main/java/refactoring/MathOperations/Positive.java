package refactoring.MathOperations;


import refactoring.Math.MathElement;
import refactoring.Math.UnarOperation;

public class Positive extends UnarOperation {

    public Positive(int indexStart, int indexEnd) {
        super("+", indexStart, indexEnd);
        prioritet = Pr4;
    }

    @Override
    public double calculate() {
        return A.calculate();
    }

    @Override
    public MathElement getInstance(int indexStart, int indexEnd) {
        return new Positive(indexStart,indexEnd);
    }

}

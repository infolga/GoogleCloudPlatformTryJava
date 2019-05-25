package refactoring.MathOperations;


import refactoring.Math.BinarOperation;
import refactoring.Math.MathElement;

public class Sum extends BinarOperation {

    public Sum(int indexStart, int indexEnd) {
        super("+", indexStart, indexEnd);
        prioritet = Pr1;
    }

    @Override
    public double calculate() {
        return A.calculate() + B.calculate();
    }

    public MathElement getInstance(int indexStart, int indexEnd) {
        return new Sum(indexStart,indexEnd);
    }
}

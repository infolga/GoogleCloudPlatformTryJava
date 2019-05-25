package refactoring.MathOperations;


import refactoring.Math.BinarOperation;
import refactoring.Math.MathElement;

public class Pow extends BinarOperation {

    public Pow(int indexStart, int IndexEnd) {
        super("^", indexStart, IndexEnd);
        prioritet = Pr3;
    }

    @Override
    public double calculate() {
        return Math.pow(A.calculate(), B.calculate());
    }

    public MathElement getInstance(int indexStart, int indexEnd) {
        return new Pow(indexStart,indexEnd);
    }
}

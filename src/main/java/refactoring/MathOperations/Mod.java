package refactoring.MathOperations;


import refactoring.Math.BinarOperation;
import refactoring.Math.MathElement;

public class Mod extends BinarOperation {

    public Mod(int indexStart, int indexEnd) {
        super("%", indexStart, indexEnd);
        prioritet = Pr2;
    }

    @Override
    public double calculate() {
        return A.calculate() % B.calculate();
    }

    @Override
    public MathElement getInstance(int indexStart, int indexEnd) {
        return new Mod(indexStart,indexEnd);
    }

}

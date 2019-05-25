package refactoring.Math;



public abstract class Function extends MathElement {

    public Function(String chars, int indexStart, int indexEnd) {
        super(chars, indexStart, indexEnd);
    }

    @Override
    abstract public double calculate();
}

package refactoring.Math;


public class Number extends MathElement {

    private double A;

    public Number(int val, int indexStart, int indexEnd) {
        super(Integer.toString(val), indexStart, indexEnd);
        this.A = val;
    }

    public Number(double val, int indexStart, int indexEnd) {
        super(Double.toString(val), indexStart, indexEnd);
        this.A = val;
    }

    public Number(String group, int indexStart, int indexEnd) {
        super(group, indexStart, indexEnd);
        this.A = Double.valueOf(group);
    }

    @Override
    public double calculate() {
        return A;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public MathElement getInstance(int indexStart, int indexEnd) {
        return null;
    }

    @Override
    public String toString() {
        return Double.toString(A);
    }


}

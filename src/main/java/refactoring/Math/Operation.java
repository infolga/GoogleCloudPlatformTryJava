package refactoring.Math;



public abstract class Operation extends MathElement {
	public final static int Pr1 = 1;
	public final static int Pr2 = 2;
	public final static int Pr3 = 3;
	public final static int Pr4 = 4;
	public final static int Pr5 = 5;
	public final static int Pr6 = 6;
	
	protected int prioritet;

	public Operation(String chars, int indexStart, int indexEnd) {
		super(chars, indexStart, indexEnd);
	}

	@Override
	abstract public double calculate();

	public int compareTo(Operation A) {
		return this.prioritet - A.prioritet;
	}

}

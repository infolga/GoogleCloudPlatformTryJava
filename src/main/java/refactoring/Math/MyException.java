package refactoring.Math;

public class MyException extends Exception {

	public String message;
	public String str;
	public int startIndex;
	public int endIndex;



	public MyException(String message, String str, int startIndex, int endIndex) {
		this.str = str;
		this.message = message;
		this.startIndex = startIndex;
		this.endIndex = endIndex;
	}

	public String print() {
		StringBuilder string =  new StringBuilder("\n");
		string.append(message);
		string.append("\n");
		string.append(str);
	    string.append("\n");
		for (int i = 0; i < startIndex; i++) {
			string.append(" ");
		}
		for (int i = 0; i < endIndex - startIndex; i++) {
			string.append("^");
		}
		return string.toString();
	}
}

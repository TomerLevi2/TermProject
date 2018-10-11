import java.lang.String;

public class Validator implements Acceptable{
	private double makeNotStatic;
	private String makeNonStatic;
	private int makeNoStatic;
	

	public void setNotStatic(double d) {
		makeNotStatic = d;
	}
	
	public double getNotStatic() {
		return makeNotStatic;
	}
	
	public void setNonStatic(String s) {
		makeNonStatic = s;
	}
	
	public String getNonStatic() {
		return makeNonStatic;
	}
	
	public void setNoStatic(int i) {
		makeNoStatic = i;
	}
	
	public int getNoStatic() {
		return makeNoStatic;
	}
	
	public boolean isNonEmptyString(String s) {
		if(s.equals(null) || s.equals("")) {
			return false;
		}
		else {
			return true;
		}
	}
	public boolean isPositiveInput(double d) {
		if(d > 0.0) {
			return true;
		}
		else {
			return false;
		}
	}
	public boolean isPositive(int i) {
		if(i > 0.0) {
			return true;
		}
		else {
			return false;
		}
	}
}

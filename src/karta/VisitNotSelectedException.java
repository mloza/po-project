package karta;

public class VisitNotSelectedException extends Exception {
	public static final int ERROR_CODE = 12345;
	
	public VisitNotSelectedException() { super("Nie zaznaczono wizyty"); }
	public VisitNotSelectedException(String msg) { super(msg); }
}

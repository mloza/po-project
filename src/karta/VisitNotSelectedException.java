package karta;

public class VisitNotSelectedException extends Exception {
	public VisitNotSelectedException() { super("Nie zaznaczono wizyty"); }
	public VisitNotSelectedException(String msg) { super(msg); }
}

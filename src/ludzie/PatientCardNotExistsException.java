package ludzie;

public class PatientCardNotExistsException extends Exception {
	public PatientCardNotExistsException() {}
	public PatientCardNotExistsException(String msg) { super(msg); }
}

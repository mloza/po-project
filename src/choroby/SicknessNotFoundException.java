package choroby;

public class SicknessNotFoundException extends Exception {
	public SicknessNotFoundException() {
		super("Szukana choroba nie została znaleziona");
	}
}

package people;

/*
 * w sumie zrobilem enum zeby dorota sie cieszyl ze jest, sluzy tylko zabezpieczeniu do tworzenia wlasciwego konta
 */
public enum Occupation {
	ADMINISTRATOR, DOCTOR, NURSE, SUPERCIEC, PATIENT;
}

enum Type{
	LOGIN, PASSWORD;
}

/*
 * a tu zebym pamietal ze pracownicy maja dzialac w tej metodzie
 */
interface accW{
	public void run();
}
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

interface accW{
	public void run();
}
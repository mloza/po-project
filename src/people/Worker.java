package people;

public abstract class Worker extends Person implements accW{
	private Occupation type;
	Worker(String surname, String name, Occupation type){
		super(surname, name);
		this.type = type;
	}
	public Occupation getType() {
		return type;
	}
	private void setType(Occupation type) {
		this.type = type;
	}
}

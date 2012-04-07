package ludzie;


public abstract class Worker extends Person{
	private Occupation type;
	
	Worker(Occupation type, String name, String surname){
		super(name, surname);
		this.setType(type);
	}

	public Occupation getType() {
		return type;
	}

	public void setType(Occupation type) {
		this.type = type;
	}

	public String toString(){
		return "Typ pracownika: "+type.getNazwa()+"\nNazwisko: "+name+"\nImie: "+surname;
	}
	
	

}

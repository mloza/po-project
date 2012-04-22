package gabinet;

import java.util.ArrayList;

public class Gabinet {
String typGabinetu;
String typOswietlenia;
ArrayList<Wyposazenie> jakieWyposazenie = new ArrayList<Wyposazenie>();


Gabinet(String typGabinetu, String typOswietlenia, WyposazeniePodstawowe jakiePodstawowe, WyposazenieDodatkowe jakieDodatkowe){
	this.typGabinetu = typGabinetu;
    this.typOswietlenia = typOswietlenia;
}

}
interface Wyposazenie {
	public void zwiekszIlosc();
	public void zmniejszIlosc();
}
enum WyposazenieDodatkowe implements Wyposazenie {
	APARAT_EEG("Aparat_EEG", 1), APARAT_KTG("Aparat_KTG", 1), AUTOKLAW("Autoklaw", 1), BIOPTRON("Bioptron", 1), CPAP("Cpap", 1), HOLTER("Holter", 1), CYKLOERGOMETR("Cykloergometr", 1),  KRIOKOMORA("Kriokomora", 1),  LAMPA_SZCZELINOWA("Lampa_Szczelinowa", 1); 
	String nazwa;
	int ilosc;
	WyposazenieDodatkowe(String nazwa, int ilosc){
		this.nazwa = nazwa;
		this.ilosc = ilosc;
	}
	@Override
	public void zwiekszIlosc() {
		ilosc++;
		
	}
	@Override
	public void zmniejszIlosc() {
		ilosc--;
		
	}
}
enum WyposazeniePodstawowe implements Wyposazenie {
	STOL("Stol", 1), STETOSKOP("Stetoskop", 1), KRZESLO("Krzeslo", 1), SZAFKA("Szafka", 1);
	String nazwa;
	int ilosc;
	WyposazeniePodstawowe(String nazwa, int ilosc){
		this.nazwa = nazwa;
		this.ilosc = ilosc;
	}
	@Override
	public void zwiekszIlosc() {
		ilosc++;
		
	}
	@Override
	public void zmniejszIlosc() {
		ilosc--;
		
	}
}
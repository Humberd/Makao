package konsola;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class OknoDialogowe {
	private final static char o = '\u00AF';
	private final static String[] okno= {" ________________________________________",
										 "|                                        |",
										 "|                                        |",
										 "|   __________________________________   |",
										 "|                                        |",
										 "|                                        |",
										 "|                                        |",
										 " "+o+o+o+o+o+o+o+o+o+o+o+o+o+o+o+o+o+o+o+o+o+o+o+o+o+o+o+o+o+o+o+o+o+o+o+o+o+o+o+o};
	
	/**
	 * Zmienna przechowuj¹ca predefiniowane podpisy okien, oraz ich guziki
	 */
	private static LinkedHashMap<String,String[]> predefiniowaneOkna = new LinkedHashMap<String,String[]>();
	
	//w tym bloku definiujê jak maj¹ wygl¹daæ wszystkie okna, coœ jakby spis treœci
	static {
		predefiniowaneOkna.put("Witaj w grze Makao", new String[] {"KONTYNUUJ"});
		predefiniowaneOkna.put("Wybierz Kolor", new String[] {String.valueOf(Karty.karoSymbol),
															  String.valueOf(Karty.kierSymbol),
															  String.valueOf(Karty.pikSymbol),
															  String.valueOf(Karty.treflSymbol),
															  "Nic"});
		predefiniowaneOkna.put("¯¹daj", new String[] {"6",
													  "7",
													  "8",
													  "9",
													  "10",
													  "!Q",
													  "!K",
													  "Nic"});
		predefiniowaneOkna.put("Akcja", new String[] {"BIORÊ",
													  "REZYGNUJÊ",
													  "TRACÊ"});
	}

	/**
	 * Zmienna przechowuj¹ca aktualnie wyœwietlane okno razem z napisem, oraz
	 * guzikami
	 */
	private static String[] aktualneOkno;

	private OknoDialogowe() {

	}

	/**
	 * @return String[] - zwraca tekstow¹ reprezentacjê okienka informacyjnego
	 */
	public static final String[] oknoPowitalne() {
		//nie mo¿na pobraæ pary kluczy tylko po indeksie z LinkedHashMap, dlatego trzeba zrobiæ 
		//listê ArrayList, z której mo¿na wzi¹æ szukany string, b¹dŸ tablicê stringów, po indeksie
		dodajWiadomosc(new ArrayList<String>(predefiniowaneOkna.keySet()).get(0));
		dodajGuziki(new ArrayList<String[]>(predefiniowaneOkna.values()).get(0));
		return aktualneOkno;
	}

	/**
	 * @param wiadomosc  - wiadomoœæ, która ma zostaæ wypisana w treœci wiadomoœci;
	 */
	private static final void dodajWiadomosc(String wiadomosc) {
		// tworze kopie okna, do którego bêdê wrzucaæ napis
		aktualneOkno = okno.clone();

		int dlugoscWiadomosci = wiadomosc.length();
		int dlugoscOkna = okno[2].length();
		// obliczam, w którym miejscu w linijce ma zacz¹æ wypisywaæ napis tak,
		// aby zawsze by³ po œrodku
		int miejsceRozpoczeciaPisania = (dlugoscOkna / 2) - (dlugoscWiadomosci / 2);

		// wypisujê napis do okna
		aktualneOkno[2] = okno[2].substring(0, miejsceRozpoczeciaPisania) + wiadomosc
				+ okno[2].substring(miejsceRozpoczeciaPisania + dlugoscWiadomosci);
	}

	private static final void dodajGuziki(String[] guziki) {

	}

	public static final String[] getAktualneOkno() {
		return aktualneOkno;
	}
}

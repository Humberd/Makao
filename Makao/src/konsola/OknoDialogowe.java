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
	 * Zmienna przechowuj�ca predefiniowane podpisy okien, oraz ich guziki
	 */
	private static LinkedHashMap<String,String[]> predefiniowaneOkna = new LinkedHashMap<String,String[]>();
	
	//w tym bloku definiuj� jak maj� wygl�da� wszystkie okna, co� jakby spis tre�ci
	static {
		predefiniowaneOkna.put("Witaj w grze Makao", new String[] {"KONTYNUUJ"});
		predefiniowaneOkna.put("Wybierz Kolor", new String[] {String.valueOf(Karty.karoSymbol),
															  String.valueOf(Karty.kierSymbol),
															  String.valueOf(Karty.pikSymbol),
															  String.valueOf(Karty.treflSymbol),
															  "Nic"});
		predefiniowaneOkna.put("��daj", new String[] {"6",
													  "7",
													  "8",
													  "9",
													  "10",
													  "!Q",
													  "!K",
													  "Nic"});
		predefiniowaneOkna.put("Akcja", new String[] {"BIOR�",
													  "REZYGNUJ�",
													  "TRAC�"});
	}

	/**
	 * Zmienna przechowuj�ca aktualnie wy�wietlane okno razem z napisem, oraz
	 * guzikami
	 */
	private static String[] aktualneOkno;

	private OknoDialogowe() {

	}

	/**
	 * @return String[] - zwraca tekstow� reprezentacj� okienka informacyjnego
	 */
	public static final String[] oknoPowitalne() {
		//nie mo�na pobra� pary kluczy tylko po indeksie z LinkedHashMap, dlatego trzeba zrobi� 
		//list� ArrayList, z kt�rej mo�na wzi�� szukany string, b�d� tablic� string�w, po indeksie
		dodajWiadomosc(new ArrayList<String>(predefiniowaneOkna.keySet()).get(0));
		dodajGuziki(new ArrayList<String[]>(predefiniowaneOkna.values()).get(0));
		return aktualneOkno;
	}

	/**
	 * @param wiadomosc  - wiadomo��, kt�ra ma zosta� wypisana w tre�ci wiadomo�ci;
	 */
	private static final void dodajWiadomosc(String wiadomosc) {
		// tworze kopie okna, do kt�rego b�d� wrzuca� napis
		aktualneOkno = okno.clone();

		int dlugoscWiadomosci = wiadomosc.length();
		int dlugoscOkna = okno[2].length();
		// obliczam, w kt�rym miejscu w linijce ma zacz�� wypisywa� napis tak,
		// aby zawsze by� po �rodku
		int miejsceRozpoczeciaPisania = (dlugoscOkna / 2) - (dlugoscWiadomosci / 2);

		// wypisuj� napis do okna
		aktualneOkno[2] = okno[2].substring(0, miejsceRozpoczeciaPisania) + wiadomosc
				+ okno[2].substring(miejsceRozpoczeciaPisania + dlugoscWiadomosci);
	}

	private static final void dodajGuziki(String[] guziki) {

	}

	public static final String[] getAktualneOkno() {
		return aktualneOkno;
	}
}

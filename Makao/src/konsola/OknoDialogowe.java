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
		predefiniowaneOkna.put("Wybierz Kolor", new String[] {String.valueOf(Karty.kierSymbol),
															  String.valueOf(Karty.karoSymbol),
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
	/**
	 * Przechowuje numer zaznaczonego guzik
	 * -1 oznacza, �e nie nie jest zaznaczone
	 * 0...* oznacza aktualnie zaznaczony guzik
	 */
	private static int zaznaczonyGuzikWAktualnymOknie = -1;

	private OknoDialogowe() {

	}

	/**
	 * @param numerGuzikaDoPodswietlenia - numer guzika, kt�ry ma pod�wietli�
	 * @return String[] - zwraca tekstow� reprezentacj� okienka informacyjnego
	 */

	public static final String[] oknoPowitalne() {
		// tworze kopie okna, do kt�rego b�d� wrzuca� napis
		aktualneOkno = okno.clone();
		//czyszcze zaznaczenie, jesli wywoluje okno
		zaznaczonyGuzikWAktualnymOknie = -1;
		//nie mo�na pobra� pary kluczy tylko po indeksie z LinkedHashMap, dlatego trzeba zrobi� 
		//list� ArrayList, z kt�rej mo�na wzi�� szukany string, b�d� tablic� string�w, po indeksie
		dodajWiadomosc(new ArrayList<String>(predefiniowaneOkna.keySet()).get(2));
		dodajGuziki(new ArrayList<String[]>(predefiniowaneOkna.values()).get(2));
		return aktualneOkno;
	}

	/**
	 * @param wiadomosc  - wiadomo��, kt�ra ma zosta� wypisana w tre�ci wiadomo�ci;
	 */
	private static final void dodajWiadomosc(String wiadomosc) {
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
		//licz� sobie jak d�ugi ma by� napis, �eby m�c �adnie dopasowa� guziki do okna
		int dlugoscGuzikow = 0;
		
		for (int i = 0; i < guziki.length; i++) {
			dlugoscGuzikow += guziki[i].length();
		}
		
		int dlugoscOkna = okno[5].length();
		int odlegloscPomiedzyGuzikami = dlugoscOkna/(guziki.length+1);
		aktualneOkno[5] = okno[5].substring(0, odlegloscPomiedzyGuzikami-(guziki[0].length()/2)) + guziki[0];
		for (int i = 1; i < guziki.length; i++) {
			aktualneOkno[5] += okno[5].substring((odlegloscPomiedzyGuzikami*i)-(guziki[i-1].length()/2), (odlegloscPomiedzyGuzikami*(i+1))-(guziki[i].length()/2)) + guziki[i];
		}
		//////////////////////////////////////////TODOOOOOOOOOOOOOOOOOOOOOO
//		aktualneOkno[5] += okno[5].substring(odlegloscPomiedzyGuzikami*guziki.length+(guziki[guziki.length-2].length()/2));
	}

	public static final String[] getAktualneOkno() {
		return aktualneOkno;
	}
}

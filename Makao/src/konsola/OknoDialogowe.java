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
		predefiniowaneOkna.put("Wybierz Kolor", new String[] {String.valueOf(Karty.kierSymbol),
															  String.valueOf(Karty.karoSymbol),
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
													  "TARCÊ"});
	}

	/**
	 * Zmienna przechowuj¹ca aktualnie wyœwietlane okno razem z napisem, oraz
	 * guzikami
	 */
	private static String[] aktualneOkno;
	
	/**
	 * Zmienna zawiera na pierwszej pozycji w ktorym miejscu znajduje sie guzik w oknie, a na drugim dlugosc tego guzika
	 */
	private static int[][] pozycjaGuzikowWAktualnymOknie;
	/**
	 * Przechowuje numer zaznaczonego guzik
	 * -1 oznacza, ¿e nie nie jest zaznaczone
	 * 0...* oznacza aktualnie zaznaczony guzik
	 */
	private static int zaznaczonyGuzikWAktualnymOknie = -1;

	/**
	 * 0-okno powitalne
	 * 1-¿¹daj
	 * 2-wybierz kolor
	 * 3-akcja
	 */
	private static int idOknaDialogowego = -1;
	
	private OknoDialogowe() {

	}
	/**
	 * @param numerGuzikaDoPodswietlenia - numer guzika, który ma podœwietliæ
	 * @return String[] - zwraca tekstow¹ reprezentacjê okienka informacyjnego
	 */
	public static final String[] oknoPowitalne() {
		idOknaDialogowego = 0;
		// tworze kopie okna, do którego bêdê wrzucaæ napis
		aktualneOkno = okno.clone();
		//czyszcze zaznaczenie, jesli wywoluje okno
		zaznaczonyGuzikWAktualnymOknie = -1;
		//nie mo¿na pobraæ pary kluczy tylko po indeksie z LinkedHashMap, dlatego trzeba zrobiæ 
		//listê ArrayList, z której mo¿na wzi¹æ szukany string, b¹dŸ tablicê stringów, po indeksie
		dodajWiadomosc(new ArrayList<String>(predefiniowaneOkna.keySet()).get(0));
		dodajGuziki(new ArrayList<String[]>(predefiniowaneOkna.values()).get(0));
		return aktualneOkno;
	}
	
	public static final String[] wybierzKolor() {
		idOknaDialogowego = 1;
		aktualneOkno = okno.clone();
		zaznaczonyGuzikWAktualnymOknie = -1;
		dodajWiadomosc(new ArrayList<String>(predefiniowaneOkna.keySet()).get(1));
		dodajGuziki(new ArrayList<String[]>(predefiniowaneOkna.values()).get(1));
		return aktualneOkno;
	}
	
	public static final String[] zadaj() {
		idOknaDialogowego = 2;
		aktualneOkno = okno.clone();
		zaznaczonyGuzikWAktualnymOknie = -1;
		dodajWiadomosc(new ArrayList<String>(predefiniowaneOkna.keySet()).get(2));
		dodajGuziki(new ArrayList<String[]>(predefiniowaneOkna.values()).get(2));
		return aktualneOkno;
	}
	
	public static final String[] akcja() {
		idOknaDialogowego = 3;
		aktualneOkno = okno.clone();
		zaznaczonyGuzikWAktualnymOknie = -1;
		dodajWiadomosc(new ArrayList<String>(predefiniowaneOkna.keySet()).get(3));
		dodajGuziki(new ArrayList<String[]>(predefiniowaneOkna.values()).get(3));
		return aktualneOkno;
	}

	/**
	 * @param wiadomosc  - wiadomoœæ, która ma zostaæ wypisana w treœci wiadomoœci;
	 */
	private static final void dodajWiadomosc(String wiadomosc) {
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
		pozycjaGuzikowWAktualnymOknie = new int[guziki.length][2];
		//liczê sobie jak d³ugi ma byæ napis, ¿eby móc ³adnie dopasowaæ guziki do okna
		int liczbaZnakow = 0;
		
		for (int i = 0; i < guziki.length; i++) {
			liczbaZnakow += guziki[i].length();
		}
		int liczbaGuzikow = guziki.length;
		int dlugoscStringa = okno[5].length()-2;
		//wzor na to, jak dluga ma byc spacja pomiedzy wierszami
		double dlugoscSpacjiDouble = (double)(dlugoscStringa-liczbaZnakow)/(double)(liczbaGuzikow+1);
		//parsuje wynik na inta
		int dlugoscSpacjiInt = (int) dlugoscSpacjiDouble;
//		System.out.println(dlugoscSpacjiDouble);
//		System.out.println(dlugoscSpacjiInt);
		//obliczbam liczbe po przecinku spacjiDouble, aby wiedziec w ilu miejscach wstawic o 1 wiecej znak spacji
		double roznicaSpacjiDoubleInt = dlugoscSpacjiDouble-dlugoscSpacjiInt;
//		System.out.println(roznicaSpacjiDoubleInt);
		//licze w ilu miejscach mam wstawic o 1 wiecej spacji
		int wIluMiejscachWstawicWiecejSpacji = (int) (roznicaSpacjiDoubleInt*(liczbaGuzikow+1));
//		System.out.println(wIluMiejscachWstawicWiecejSpacji);
		aktualneOkno[5] = okno[5].substring(0, (wIluMiejscachWstawicWiecejSpacji>0)?dlugoscSpacjiInt+2:(dlugoscSpacjiInt+1))+guziki[0];
		pozycjaGuzikowWAktualnymOknie[0][0] = (wIluMiejscachWstawicWiecejSpacji>0)?dlugoscSpacjiInt+2:(dlugoscSpacjiInt+1);
		pozycjaGuzikowWAktualnymOknie[0][1] = guziki[0].length();
		int sumaDlugosciNapisowDodanychGuzikow = guziki[0].length();
		for (int i = 1; i < guziki.length; i++) {
//			System.out.println();
			aktualneOkno[5] += okno[5].substring((wIluMiejscachWstawicWiecejSpacji*1)+(i*dlugoscSpacjiInt)+(sumaDlugosciNapisowDodanychGuzikow)
												,(wIluMiejscachWstawicWiecejSpacji>i)?(wIluMiejscachWstawicWiecejSpacji*1)+(i*dlugoscSpacjiInt)+(sumaDlugosciNapisowDodanychGuzikow)+(dlugoscSpacjiInt+1):(wIluMiejscachWstawicWiecejSpacji*1)+(i*dlugoscSpacjiInt)+(sumaDlugosciNapisowDodanychGuzikow)+(dlugoscSpacjiInt))
							+guziki[i];
			pozycjaGuzikowWAktualnymOknie[i][0] = (wIluMiejscachWstawicWiecejSpacji>i)?(wIluMiejscachWstawicWiecejSpacji*1)+(i*dlugoscSpacjiInt)+(sumaDlugosciNapisowDodanychGuzikow)+(dlugoscSpacjiInt+1):(wIluMiejscachWstawicWiecejSpacji*1)+(i*dlugoscSpacjiInt)+(sumaDlugosciNapisowDodanychGuzikow)+(dlugoscSpacjiInt)+1;
			pozycjaGuzikowWAktualnymOknie[i][1] = guziki[i].length();
			sumaDlugosciNapisowDodanychGuzikow+=guziki[i].length();
		}
		aktualneOkno[5] += okno[5].substring((wIluMiejscachWstawicWiecejSpacji*1)+(liczbaGuzikow*dlugoscSpacjiInt)+(sumaDlugosciNapisowDodanychGuzikow)+1);
	}
	
	/**
	 * Metoda ta graficznie(tekstowo) zaznacza dany guzik
	 * @param numerGuzika - numer guzika do zaznaczenia w danym oknie dialogowym
	 * @return
	 */
	public static final String[] zaznaczGuzik(int numerGuzika) {
		//jesli numer guzika jest wiekszy, niz ich faktycznie jest guzikow
		if (numerGuzika >= pozycjaGuzikowWAktualnymOknie.length) {
			return aktualneOkno;
		} 
		//jesli numer guzika jest -1, to oznacza, ze nie ma zadnego zaznaczenia
		else if (numerGuzika == -1) {
			aktualneOkno[6] = okno[6];
			zaznaczonyGuzikWAktualnymOknie = -1;
			return aktualneOkno;
		}
		aktualneOkno[6] = okno[6].substring(0,pozycjaGuzikowWAktualnymOknie[numerGuzika][0]);
		
		for (int i=0; i< pozycjaGuzikowWAktualnymOknie[numerGuzika][1]; i++) {
			aktualneOkno[6] += o;
		}
		aktualneOkno[6] += okno[6].substring(pozycjaGuzikowWAktualnymOknie[numerGuzika][0]+pozycjaGuzikowWAktualnymOknie[numerGuzika][1]);
		zaznaczonyGuzikWAktualnymOknie = numerGuzika;
		
		return aktualneOkno;
	}

	public static final String[] getAktualneOkno() {
		return aktualneOkno;
	}

	public static int[][] getPozycjaGuzikowWAktualnymOknie() {
		return pozycjaGuzikowWAktualnymOknie;
	}

	public static int getZaznaczonyGuzikWAktualnymOknie() {
		return zaznaczonyGuzikWAktualnymOknie;
	}
	public static int getIdOknaDialogowego() {
		return idOknaDialogowego;
	}
	public static void setIdOknaDialogowego(int idOknaDialogowego) {
		OknoDialogowe.idOknaDialogowego = idOknaDialogowego;
	}
}

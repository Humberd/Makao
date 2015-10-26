package konsola;

public class Karty {
	private final static char overScore = '\u00AF'; //"\u0305" - zapasowy w razie czego
	private final static String[] karta = {" _______",
										   "|       |",
							               "|       |",
							               "|       |",
							               "|       |",
							               "|       |",
							               " "+overScore+overScore+overScore+overScore+overScore+overScore+overScore};
	
	public final static char kierSymbol = '\u2665';
	public final static char karoSymbol = '\u2666';
	public final static char pikSymbol = '\u2660';
	public final static char treflSymbol = '\u2663';
	
	
	private Karty() {
		
	}

	/**
	 * @param wartosc - tekstowa wartoœæ, jak bêdzie wypisana na karcie
	 * @param kolor - tekstowy kolor, jaki bêdzie wypisany na karcie
	 * @return string[] - zwraca tablicê stringów, który przedstawia graficzne odwzorowanie karty
	 */
	public final static String[] getKarta(String wartosc, String kolor) {
		//robie klon karty, zeby moc ja odpowiednio edytowac
		String[] kartaKopia = karta.clone();
		
		//dodaje wartosc karty w lewym gornym rogu
		kartaKopia[1] = karta[1].substring(0, 1) + wartosc;
		
		if (wartosc.equals("10")) {
			kartaKopia[1]+= karta[1].substring(3);
			//dodaje wartosc karty w prawym dolnym rogu
			kartaKopia[karta.length-2] = karta[karta.length-2].substring(0, karta[karta.length-2].length()-3);
		} else {
			kartaKopia[1]+= karta[1].substring(2);
			//dodaje wartosc karty w prawym dolnym rogu
			kartaKopia[karta.length-2] = karta[karta.length-2].substring(0, karta[karta.length-2].length()-2);
		}
		kartaKopia[karta.length-2] += wartosc + karta[karta.length-2].substring(karta[karta.length-2].length()-1);
		//dodaje symbol koloru przy wartoœci
		char symbol = '0';
		switch (kolor) {
			case "Kier": symbol = kierSymbol; break;
			case "Karo": symbol = karoSymbol; break;
			case "Pik": symbol = pikSymbol; break;
			case "Trefl": symbol = treflSymbol; break;
			default: symbol = kierSymbol; break;
		}
		kartaKopia[2] = karta[2].substring(0, 1) + symbol + karta[2].substring(2);
		
		//oraz dodaje symbol koloru na œrodku karty
		kartaKopia[3] = karta[3].substring(0, 4) + symbol + karta[3].substring(5);
		
		//dodaje symbol koloru w prawym dolnym rogu
		kartaKopia[karta.length-3] = karta[karta.length-3].substring(0, karta[karta.length-3].length()-2) + symbol +karta[karta.length-3].substring(karta[karta.length-3].length()-1);
		return kartaKopia;
	}
	
	public String[] getKarta() {
		return karta;
	}
}

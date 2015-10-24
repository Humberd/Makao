package konsola;

import javax.swing.text.AbstractDocument.LeafElement;

public class Karty {
	private final char overScore = '\u00AF'; //"\u0305" - zapasowy w razie czego
	private String[] karta = {" _______",
							  "|       |",
							  "|       |",
							  "|       |",
							  "|       |",
							  "|       |",
							  " "+overScore+overScore+overScore+overScore+overScore+overScore+overScore};
	
	private final char kierSymbol = '\u2665';
	private final char karoSymbol = '\u2666';
	private final char pikSymbol = '\u2660';
	private final char treflSymbol = '\u2663';
	
	
	public Karty() {
		
	}

	/**
	 * @param wartosc - tekstowa wartoœæ, jak bêdzie wypisana na karcie
	 * @param kolor - tekstowy kolor, jaki bêdzie wypisany na karcie
	 * @return string[] - zwraca tablicê stringów, który przedstawia graficzne odwzorowanie karty
	 */
	public String[] getKarta(String wartosc, String kolor) {
		//robie klon karty, zeby moc ja odpowiednio edytowac
		String[] kartaKopia = karta.clone();
		
		//dodaje wartosc karty w lewym gornym rogu
		kartaKopia[1] = karta[1].substring(0, 1) + wartosc;
		
		if (wartosc == "10") {
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
			case "Kier": symbol = this.kierSymbol; break;
			case "Karo": symbol = this.karoSymbol; break;
			case "Pik": symbol = this.pikSymbol; break;
			case "Trefl": symbol = this.treflSymbol; break;
			default: symbol = this.kierSymbol; break;
		}
		kartaKopia[2] = karta[2].substring(0, 1) + symbol + karta[2].substring(2);
		
		//oraz dodaje symbol koloru na œrodku karty
		kartaKopia[3] = karta[3].substring(0, 4) + symbol + karta[3].substring(5);
		
		//dodaje symbol koloru w prawym dolnym rogu
		kartaKopia[karta.length-3] = karta[karta.length-3].substring(0, karta[karta.length-3].length()-2) + symbol +karta[karta.length-3].substring(karta[karta.length-3].length()-1);
		return kartaKopia;
	}
}

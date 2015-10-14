package karty;

import exceptions.CardException;

/**
 * @author Maciej Sawicki
 *
 */
public abstract class Karta {
	
	/**
	 * Wartosci od 2 do 14, gdzie 2, to "2", a 14, to "As".
	 */
	private final int wartosc;
	/**
	 * Znak przedstawiaj�cy warto�� karty: A,K,Q,J,10,...,2
	 */
	private final String znak;
	/**
	 * Napis zawieraj�cy tekstow� reprezentacj� koloru: Dw�jka, Tr�jka,..., Kr�l, As.
	 */
	private final String textowaWartosc;
	/**
	 * Napis zawieraj�cy tekstow� reprezentacj� koloru: Pik, Trefl, Karo, Kier.
	 */
	private final String kolor;
	
	//obrazek
	
	/**
	 * Konstruktor tworz�cy kart� w zale�no�ci od podanych parametr�w;
	 * @param wartosc - warto�� od 2 do 14 reprezentuj�ca warto�� karty
	 * @param kolor - kolor reprezentuj�cy warto�� karty
	 * @throws CardException
	 */
	public Karta(int wartosc, String kolor) throws CardException {	
		//sprawdzam poprawno�� warto�ci nowej karty, w razie b��du wyrzuca b��d
		if (wartosc >=2 && wartosc <=14) {
			this.wartosc = wartosc;
			if (wartosc == 11) this.znak = "J";
			else if (wartosc == 12) this.znak = "Q";
			else if (wartosc == 13) this.znak = "K";
			else if (wartosc == 14) this.znak = "A";
			else this.znak = (String) (wartosc + "");
		} else {
			throw new CardException(1);
		}
		
		//sprawdzam poprawno�� koloru nowej karty, w razie b��du wyrzuca b��d
		if (kolor== "Pik" || kolor== "Trefl" || kolor == "Karo" || kolor== "Kier") {
			this.kolor = kolor;
		} else {
			throw new CardException(3);
		}
		
		//dodaj� s�own� reprezentacj� karty
		String[] tablicaWartosciTextowych = {"Dw�jka","Tr�jka","Czw�rka","Pi�tka","Sz�stka","Si�demka","�semka","Dziewi�tka","Dziesi�tka","Walet","Dama","Kr�l","As"};
			
		this.textowaWartosc = tablicaWartosciTextowych[wartosc-2];
	}
	public Karta() throws CardException {
		throw new CardException(2);
	}
	
	public String toString() {
		return znak +" "+ kolor;
	}
	
	public int getWartosc() {
		return wartosc;
	}

	public String getZnak() {
		return znak;
	}

	public String getKolor() {
		return kolor;
	}
	
	public String getTextowaWartosc() {
		return textowaWartosc;
	}
	
}

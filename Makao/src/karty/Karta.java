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
	 * Znak przedstawiaj¹cy wartoœæ karty: A,K,Q,J,10,...,2
	 */
	private final String znak;
	/**
	 * Napis zawieraj¹cy tekstow¹ reprezentacjê koloru: Dwójka, Trójka,..., Król, As.
	 */
	private final String textowaWartosc;
	/**
	 * Napis zawieraj¹cy tekstow¹ reprezentacjê koloru: Pik, Trefl, Karo, Kier.
	 */
	private final String kolor;
	
	//obrazek
	
	/**
	 * Konstruktor tworz¹cy kartê w zale¿noœci od podanych parametrów;
	 * @param wartosc - wartoœæ od 2 do 14 reprezentuj¹ca wartoœæ karty
	 * @param kolor - kolor reprezentuj¹cy wartoœæ karty
	 * @throws CardException
	 */
	public Karta(int wartosc, String kolor) throws CardException {	
		//sprawdzam poprawnoœæ wartoœci nowej karty, w razie b³êdu wyrzuca b³¹d
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
		
		//sprawdzam poprawnoœæ koloru nowej karty, w razie b³êdu wyrzuca b³¹d
		if (kolor== "Pik" || kolor== "Trefl" || kolor == "Karo" || kolor== "Kier") {
			this.kolor = kolor;
		} else {
			throw new CardException(3);
		}
		
		//dodajê s³own¹ reprezentacjê karty
		String[] tablicaWartosciTextowych = {"Dwójka","Trójka","Czwórka","Pi¹tka","Szóstka","Siódemka","Ósemka","Dziewi¹tka","Dziesi¹tka","Walet","Dama","Król","As"};
			
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

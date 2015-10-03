package karty;

import java.util.HashMap;
import java.util.Map;

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
	private final char znak;
	/**
	 * Napis zawieraj¹cy tekstow¹ reprezentacjê koloru: Dwójka, Trójka,..., Król, As.
	 */
	private final String textowaWartosc;
	/**
	 * Napis zawieraj¹cy tekstow¹ reprezentacjê koloru: Pik, Trefl, Karo, Kier.
	 */
	private final String kolor;
	
	//obrazek
	
	public Karta(int wartosc, String kolor) throws CardException {
		if (wartosc >=2 && wartosc <=14) {
			this.wartosc = wartosc;
			if (wartosc == 11) this.znak = 'J';
			else if (wartosc == 12) this.znak = 'Q';
			else if (wartosc == 13) this.znak = 'K';
			else if (wartosc == 14) this.znak = 'A';
			else this.znak = (char) (wartosc+'0');
		} else {
			throw new CardException(1);
		}
		if (kolor.toLowerCase() == "pik" || kolor.toLowerCase() == "trefl" || kolor.toLowerCase() == "karo" || kolor.toLowerCase() == "kier") {
			this.kolor = kolor;
		} else {
			System.out.println(kolor.toLowerCase() == "karo");
			throw new CardException(3);
		}
		String[] tablicaWartosciTextowych = {"Dwójka","Trójka","Czwórka","Pi¹tka","Szóstka","Siódemka","Ósemka","Dziewi¹tka","Dziesi¹tka","Walet","Dama","Król","As"};
			
		this.textowaWartosc = tablicaWartosciTextowych[wartosc-2];
	}
	public Karta() throws CardException {
		throw new CardException(2);
	}
	
	public String toString() {
		return textowaWartosc +" "+ kolor;
	}
	
	public int getWartosc() {
		return wartosc;
	}

	public char getZnak() {
		return znak;
	}

	public String getKolor() {
		return kolor;
	}
	
	public String getTextowaWartosc() {
		return textowaWartosc;
	}
	
}

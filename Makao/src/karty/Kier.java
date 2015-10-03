package karty;

import exceptions.CardException;

public class Kier extends Karta{
	private static String kolor = "kier";
	
	public Kier(int wartosc) throws CardException {
		super(wartosc,kolor);
	}	
	public Kier () throws CardException {
		super();
	}
}

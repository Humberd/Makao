package karty;

import exceptions.CardException;

public class Karo extends  Karta{
	private static String kolor = "Karo";
	
	public Karo (int wartosc) throws CardException {
		super(wartosc,kolor);
	}
	public Karo () throws CardException {
		super();
	}
}

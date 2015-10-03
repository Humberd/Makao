package karty;

import exceptions.CardException;

public class Karo extends  Karta{
	private static String kolor = "trefl";
	
	public Karo (int wartosc) throws CardException {
		super(wartosc,kolor);
	}
	public Karo () throws CardException {
		super();
	}
}

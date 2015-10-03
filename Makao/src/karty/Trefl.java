package karty;

import exceptions.CardException;

public class Trefl extends Karta{
	private static String kolor = "trefl";
	
	public Trefl (int wartosc) throws CardException {
		super(wartosc,kolor);
	}
	public Trefl () throws CardException {
		super();
	}
}

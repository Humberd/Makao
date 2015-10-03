package karty;

import exceptions.CardException;

public class Trefl extends Karta{
	private static String kolor = "Trefl";
	
	public Trefl (int wartosc) throws CardException {
		super(wartosc,kolor);
	}
	public Trefl () throws CardException {
		super();
	}
}

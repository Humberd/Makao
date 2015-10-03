package karty;

import exceptions.CardException;

public class Pik extends Karta{
	private static String kolor = "Pik";
	
	public Pik(int wartosc) throws CardException {
		super(wartosc, kolor);
	}
	
	public Pik () throws CardException {
		super();
	}
}

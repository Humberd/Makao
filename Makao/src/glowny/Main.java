package glowny;

import exceptions.CardException;
import karty.*;

public class Main {

	public static void main(String[] args) throws CardException {
		try {
			Karta karta = new Pik(12);
			System.out.println(karta.getKolor()+",  "+karta.getZnak()+", "+karta.getWartosc());
		} catch (CardException e) {
			
		}

	}

}

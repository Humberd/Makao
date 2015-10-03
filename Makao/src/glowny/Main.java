package glowny;

import exceptions.CardException;

public class Main {

	public static void main(String[] args) throws CardException {
		try {
			Karta karta = new Pik(2);
			System.out.println(karta.getKolor());
		} catch (CardException e) {
			
		}
		System.out.println("test");

	}

}

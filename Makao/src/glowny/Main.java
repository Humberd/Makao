package glowny;

import exceptions.CardException;
import karty.*;

public class Main {

	public static void main(String[] args) throws CardException {
		try {
			Karta karta = new Karo(13);
			System.out.println(karta);
//			karta = new Kier(2);
//			System.out.println(karta);
		} catch (CardException e) {
			
		}

	}

}

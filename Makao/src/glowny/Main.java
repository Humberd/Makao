package glowny;

import exceptions.CardException;
import karty.*;

public class Main {

	public static void main(String[] args) throws CardException {
		try {
			Talia talia = new Talia();
			talia.printTalia();
		} catch (CardException e) {
			
		}

	}

}

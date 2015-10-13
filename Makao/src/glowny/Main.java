package glowny;

import exceptions.CardException;
import gracze.Czlowiek;
import gracze.Gracz;
import karty.*;
import rozgrywka.Gra;

public class Main {

	public static void main(String[] args) throws CardException {
//		try {
//			Talia talia = new Talia();
//			talia.printTalia();
//			talia.tasuj();
//			talia.printTalia();
//		} catch (CardException e) {
//			
//		}
		Gra gra = new Gra();
		Gracz gracz = new Czlowiek();
		Gracz gracz2 = new Czlowiek();
		Gracz gracz3 = new Czlowiek();
		Talia talia = new Talia();
		
		for (int i =0 ;i<12;i++) {
			talia.pushUzyteKarty(talia.popTalia());
		}
		talia.przelozUzyteKartyDoTalii();
		talia.printTalia();
		System.out.println("------");
		talia.printUzyteKarty();;
		System.out.println("///////////");
	}

}

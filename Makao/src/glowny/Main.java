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
		System.out.println(gra.dodajGraczaDoGry(gracz));
		System.out.println(gra.usunGraczaZGry(gracz));
		System.out.println(gra.usunGraczaZGry(gracz));

		

	}

}

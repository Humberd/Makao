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
		
		gra.rozpocznijGre();
		gra.dodajGraczaDoGry(gracz);
		gra.rozpocznijGre();
		gra.dodajGraczaDoGry(gracz);
		gra.rozpocznijGre();
		gra.dodajGraczaDoGry(gracz2);
		System.out.println(gra.getStanGry());
		gra.rozpocznijGre();
		System.out.println(gra.getStanGry());
		gra.usunGraczaZGry(gracz3);
		System.out.println(gra.getStanGry());
		
	}

}

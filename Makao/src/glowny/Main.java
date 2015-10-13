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
		
		System.out.println(gra.dodajGraczaDoGry(gracz));
		System.out.println(gra.dodajGraczaDoGry(gracz2));
		gra.rozpocznijGre();
		System.out.println(gracz2.wykonajRuch(new Karta[10]));
//		System.out.println(gracz2.wykonajRuch(new Karta[10]));
	}

}

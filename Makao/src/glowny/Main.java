package glowny;

import java.io.Console;
import java.io.PrintWriter;

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
//		System.out.println(gra.dodajGraczaDoGry(gracz));
//		System.out.println(gra.dodajGraczaDoGry(gracz2));
//		System.out.println(gra.usunGraczaZGry(gracz));
//		System.out.println(gra.usunGraczaZGry(gracz2));
		System.out.println("przed");
		Console konsola = System.console();
		PrintWriter out = konsola.writer();
		
		out.println("printwriter");
		System.out.println("po");
	}

}

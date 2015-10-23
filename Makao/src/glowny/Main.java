package glowny;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
		Gracz sawik = new Czlowiek();
		gra.dodajGraczaDoGry(sawik);
		gra.rozpocznijGre();
		gra.rozdajKarty();
		String coRobic = "";
		Scanner sc = new Scanner(System.in);
		
		while (!coRobic.equals("s")) {
			System.out.println("Talia: "+gra.talia.printTalia());
			System.out.println("U¿yte: >>"+gra.talia.printUzyteKarty());
			System.out.println("Rêka: "+sawik.getReka().toString());
			coRobic = sc.nextLine();
			
			try {
				if (coRobic.equals("p")) {
					sawik.pobierzKarte();
				} else if (coRobic.equals("s")){
					continue;
				} else {
					int index = Integer.parseInt(coRobic);
					if (index >= sawik.getReka().size()) {
						throw new Exception();
					}
					sawik.pushWybraneKarty(sawik.getReka().remove(index));
					sawik.wykonajRuch();
				}
			} catch (Exception e) {
				System.out.println("Input error.");
			} 

		}

		
		
	}

}

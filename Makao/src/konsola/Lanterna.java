package konsola;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;

import javax.swing.WindowConstants;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.gui.GUIScreen;
import com.googlecode.lanterna.gui.Window;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.ScreenCharacterStyle;
import com.googlecode.lanterna.screen.ScreenWriter;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.Terminal.ResizeListener;
import com.googlecode.lanterna.terminal.TerminalSize;
import com.googlecode.lanterna.terminal.swing.SwingTerminal;

import exceptions.CardException;
import karty.Karta;
import karty.Pik;
import rozgrywka.Gra;

public class Lanterna {
	Screen screen;
	Gra gra;
	gracze.Gracz gracz;
	SwingTerminal swing;
	ScreenWriter sw;
	private final int HEIGHT = 40;
	private final int WIDTH = 120;
	
	private String[] oknoDialogowe;
	/**
	 * true-gracz focusuje okno dialogowe
	 * false-gracz focusuje swoje karty
	 */
	private boolean czyJestFocusNaOknoDialogowe = true;
	private boolean czyJestOknoPowitalne = false;
	
	String sameSpacjeDoCzyszczenia = "";
	
	
	public Lanterna() {
		
	}
	
	/**
	 * Konstruktor przyjmuje 2 argumenty
	 * @param gra - rozgrywa, ktora ma pokazywac
	 * @param gracz - uzytkownika - gracza, ktory bedzie gral
	 */
	public Lanterna(Gra gra, gracze.Gracz gracz) {
		for (int j = 0; j < WIDTH; j++) {
			sameSpacjeDoCzyszczenia += " ";
		}
		this.gra = gra;
		this.gracz= gracz;
		this.swing = TerminalFacade.createSwingTerminal(WIDTH, HEIGHT);
		this.screen = new Screen(this.swing);
		this.sw = new ScreenWriter(this.screen);
		this.screen.startScreen();
		this.swing.getJFrame().setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.swing.getJFrame().setResizable(false);
		oknoPowitalne();
		this.swing.getJFrame().addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
					case KeyEvent.VK_RIGHT:
						if (oknoDialogowe != null) {
							if (czyJestFocusNaOknoDialogowe == true) {
//								System.out.println((OknoDialogowe.getZaznaczonyGuzikWAktualnymOknie()+1)%OknoDialogowe.getPozycjaGuzikowWAktualnymOknie().length);
								OknoDialogowe.zaznaczGuzik((OknoDialogowe.getZaznaczonyGuzikWAktualnymOknie()+1)%OknoDialogowe.getPozycjaGuzikowWAktualnymOknie().length);
								odswiezCaleOkno();
							}
						}
						break;
					case KeyEvent.VK_LEFT:
						if (oknoDialogowe != null) {
							if (czyJestFocusNaOknoDialogowe == true) {
//								System.out.println((OknoDialogowe.getZaznaczonyGuzikWAktualnymOknie()-1)%OknoDialogowe.getPozycjaGuzikowWAktualnymOknie().length);
								OknoDialogowe.zaznaczGuzik(((OknoDialogowe.getZaznaczonyGuzikWAktualnymOknie()-1)%OknoDialogowe.getPozycjaGuzikowWAktualnymOknie().length < 0)?OknoDialogowe.getPozycjaGuzikowWAktualnymOknie().length-1:(OknoDialogowe.getZaznaczonyGuzikWAktualnymOknie()-1)%OknoDialogowe.getPozycjaGuzikowWAktualnymOknie().length);
								odswiezCaleOkno();
							}
						}
						break;
					case KeyEvent.VK_SPACE:
						if (czyJestFocusNaOknoDialogowe == true) {
							if (OknoDialogowe.getZaznaczonyGuzikWAktualnymOknie() != -1) {
								if (czyJestOknoPowitalne) {
									czyJestOknoPowitalne = false;
									czyJestFocusNaOknoDialogowe = false;
									screen.clear();
									odswiezCaleOkno();
								}
								//TODO
								//tu beda robione akcje typu zadaj, biore karte, itp
							}
						}
						//jesli gracz focusuje swoje karty
						else {
							
						}
						break;
				}
			}
		});

//		String[] temp = Karty.getKarta("A","Kier");
////		temp = OknoDialogowe.oknoPowitalne();
//		for (int i = 0; i < temp.length; i++) {
//			sw.drawString(5, 2+i, temp[i]);
//		}
//		screen.refresh();	
	}
	private void oknoPowitalne() {
		OknoDialogowe.oknoPowitalne();
		czyJestOknoPowitalne = false; // zmienic na true jesli ma sie na poczatku wyswietlic okno powitalne
		odswiezCaleOkno();
	}
	
	/**
	 * Caly layout strony
	 */
	public void odswiezCaleOkno() {
		oknoDialogowe = OknoDialogowe.getAktualneOkno();
		//jesli flaga czyJestOknoPowitalne jest ustawiona na true, to wyswietla tylko okno powitalne
		if (czyJestOknoPowitalne == true) {
			for (int i = 0; i < oknoDialogowe.length; i++) {
				sw.drawString(WIDTH/2 - oknoDialogowe[0].length()/2, HEIGHT/2 -(oknoDialogowe.length/2) + i, oknoDialogowe[i]);
			}
		} 
		//jesli flaga jest ustawiona na false, to wyswietla cala gre
		else if (czyJestOknoPowitalne == false) {
			odswiezKartyGracza();
		}
		screen.refresh();
	}
	
	public void odswiezKartyGracza() {
		//ile wierszy zajmuje graficznie karta
		int wysokoscSchematuKarty =  Karty.getKarta("2", "Pik").length;
		// nazwa...
		int oIleWyzejPokazacWybranaKarte = 3;
		// na ile wierszy ma byc pole gracza
		int wysokoscPolaKartGracza = oIleWyzejPokazacWybranaKarte + wysokoscSchematuKarty;
		// jak szeroko wyswietlac karte w srodku reki gracza
		int dlugoscSchowanejKarty = 4;
		// jak szeroko jest wogole ma byc wyswietlona ostatnia karta
		int dlugoscOstatniejKarty = Karty.getKarta("2", "Pik")[1].length();
		//suma wszystkich kart do wypisania, aby mozna bylo latwo sie dowiedziec gdzie zaczac wypisywac, aby bylo ladnie wysrodkowane
		int dlugoscKart = dlugoscSchowanejKarty*(gracz.getReka().size()-1)+dlugoscOstatniejKarty;
		//obliczam miejsce, z ktorego ma zaczac wypisywanie tak, aby bylo ladnie wysrodkowane
		int miejsceRozpoczeciaRysowaniaKart = WIDTH/2 - dlugoscKart/2;
		
		//czyszcze cale pole gracza
		for (int i = 0; i < wysokoscPolaKartGracza; i++) {
			sw.drawString(0, HEIGHT-wysokoscPolaKartGracza + i, sameSpacjeDoCzyszczenia);
		}
		
		for (int i = 0; i < gracz.getReka().size(); i++) {
			Karta rysowanaKarta = gracz.getReka().get(i);
			Karta wybranaKarta = gracz.getWybranaKarta();
			String[] schematRysowanejKarty = Karty.getKarta(rysowanaKarta.getZnak(), rysowanaKarta.getKolor());
			//jesli aktualna karta w rece, ktora mam narysowac jest tez wybrana karta, to rysuje ja wyzej niz inne karty
			if (rysowanaKarta.toString() == ((wybranaKarta != null)?wybranaKarta.toString():"0")) {
				for (int j = 0; j < wysokoscSchematuKarty; j++) {
					sw.drawString(miejsceRozpoczeciaRysowaniaKart+ (i*dlugoscSchowanejKarty), HEIGHT-wysokoscPolaKartGracza+j, schematRysowanejKarty[j]);
				}
			} else {
				for (int j = 0; j < wysokoscSchematuKarty; j++) {
					sw.drawString(miejsceRozpoczeciaRysowaniaKart+ (i*dlugoscSchowanejKarty), HEIGHT-wysokoscSchematuKarty+j, schematRysowanejKarty[j]);
				}
			}
		}
		
		screen.refresh();
	}
	
	public void odswiezKartyPrzeciwnikow() {
		for (int i = 0; i < gra.getGracze().length; i++) {
			if (gra.getGracze()[i] == gracz) {
				//rysuje karty dla kazdego z przeciwnikow osobno, bez zadnej petli
				if (gra.getGracze()[(i+1)%gra.getGracze().length] != null) {
					
				}
				if (gra.getGracze()[(i+2)%gra.getGracze().length] != null) {
					
				}
				if (gra.getGracze()[(i+3)%gra.getGracze().length] != null) {
					
				}
			}
		}
	}
	
	public void odswiezStosKart() {
		
	}
	
	public void odswiezCzasGracza() {
		
	}
	
	public void odswiezNickiGraczy() {
		
	}
	
//	public String generujPustyString() {
//		
//	}
}
 
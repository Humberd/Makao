package konsola;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.WindowConstants;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.ScreenWriter;
import com.googlecode.lanterna.terminal.swing.SwingTerminal;

import exceptions.ArbiterException;
import karty.Karta;
import rozgrywka.Gra;

public class Lanterna {
	Screen screen;
	Gra gra;
	gracze.Gracz gracz;
	SwingTerminal swing;
	ScreenWriter sw;
	private final int HEIGHT = 40;
	private final int WIDTH = 120;
	
	/**
	 * true-gracz focusuje okno dialogowe
	 * false-gracz nie focusuje okna dialogowego
	 */
	private boolean czyJestFocusNaOknoDialogowe = false;
	
	/**
	 * true - gracz focusuje swoje karty
	 * false - gracz nie focusuje swoich kart
	 */
	private boolean czyJestFocusNaSwojeKarty = false;
	private boolean czyJestOknoPowitalne = false;
	
	private boolean czyJestOknoDialogowe = false;
	
	private String sameSpacjeDoCzyszczenia = "";
	
	private boolean czyGraczRzucilWszystkieKarty = false;
	
	private boolean czyGraczRzucilCoNajmniejJednaKarte = false;
	
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
		this.swing.getJFrame().setTitle("Makao");
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
					case KeyEvent.VK_UP:
						if (czyJestOknoPowitalne == false) {
	//						System.out.println("______PRZED_____");
	//						System.out.println("CzyJestOknoDialogowe: " +czyJestOknoDialogowe);
	//						System.out.println("Focus Karty: "+czyJestFocusNaSwojeKarty);
	//						System.out.println("Focus Okno: "+czyJestFocusNaOknoDialogowe);
	//						System.out.println("_______PO_______");
							//jesli nie ma okna dialogowego, oraz nie mam focusa na swoje karty
							if (czyJestFocusNaSwojeKarty == false && czyJestOknoDialogowe == true && czyJestFocusNaOknoDialogowe == false) {
								czyJestFocusNaSwojeKarty = true;
								czyJestFocusNaOknoDialogowe = false;
								//jesli gracz nie ma wybranej karty, to wybieraj karte nr 1
								if (gracz.getIndeksWybranejKartyWRece() == -1) {
									gracz.setIndeksWybranejKartyWRece(0);
								}
								odswiezKartyGracza();
							} 
							//jesli nie ma okna dialogowego i jest focus na swoje karty
							else if (czyJestOknoDialogowe == false && czyJestFocusNaSwojeKarty == true) {
								//nic nie robie
							}
							//jesli jest okno dialogowe i jest focus na okno dialogowe
							else if (czyJestOknoDialogowe == true && czyJestFocusNaOknoDialogowe== true) {
								//nic nie robie
							}
							//jesli jest okno dialogowe i jest focus na swoje karty
							else if (czyJestOknoDialogowe == true && czyJestFocusNaSwojeKarty == true) {
								czyJestFocusNaSwojeKarty = false;
								czyJestFocusNaOknoDialogowe = true;
								odswiezKartyGracza();
							}
	//						System.out.println("CzyJestOknoDialogowe: " +czyJestOknoDialogowe);
	//						System.out.println("Focus Karty: "+czyJestFocusNaSwojeKarty);
	//						System.out.println("Focus Okno: "+czyJestFocusNaOknoDialogowe);
						}
						break;
					case KeyEvent.VK_DOWN:
						if (czyJestOknoPowitalne == false) {
	//						System.out.println("______PRZED_____");
	//						System.out.println("CzyJestOknoDialogowe: " +czyJestOknoDialogowe);
	//						System.out.println("Focus Karty: "+czyJestFocusNaSwojeKarty);
	//						System.out.println("Focus Okno: "+czyJestFocusNaOknoDialogowe);
	//						System.out.println("_______PO_______");
							//jesli nie ma okna dialogowego, oraz nie mam focusa na swoje karty
							if (czyJestFocusNaSwojeKarty == false && czyJestOknoDialogowe == false) {
								//nic nie robie
							} 
							//jesli nie ma okna dialogowego i jest focus na swoje karty
							else if (czyJestOknoDialogowe == false && czyJestFocusNaSwojeKarty == true) {
								//nic nie robie
							}
							//jesli jest okno dialogowe i jest focus na okno dialogowe
							else if (czyJestOknoDialogowe == true && czyJestFocusNaOknoDialogowe== true) {
								czyJestFocusNaSwojeKarty = true;
								czyJestFocusNaOknoDialogowe = false;
								odswiezKartyGracza();
							}
							//jesli jest okno dialogowe i jest focus na swoje karty
							else if (czyJestOknoDialogowe == true && czyJestFocusNaSwojeKarty == true) {
								//nic nie robie
							}
	//						System.out.println("CzyJestOknoDialogowe: " +czyJestOknoDialogowe);
	//						System.out.println("Focus Karty: "+czyJestFocusNaSwojeKarty);
	//						System.out.println("Focus Okno: "+czyJestFocusNaOknoDialogowe);
						}
						break;
					case KeyEvent.VK_RIGHT:
						if (czyJestOknoPowitalne == false) {
							if (czyJestFocusNaOknoDialogowe == true) {
//								System.out.println((OknoDialogowe.getZaznaczonyGuzikWAktualnymOknie()+1)%OknoDialogowe.getPozycjaGuzikowWAktualnymOknie().length);
								OknoDialogowe.zaznaczGuzik((OknoDialogowe.getZaznaczonyGuzikWAktualnymOknie()+1)%OknoDialogowe.getPozycjaGuzikowWAktualnymOknie().length);
								odswiezOknoDialogowe();
							}
							if (czyJestFocusNaSwojeKarty == true) {
								gracz.setIndeksWybranejKartyWRece((gracz.getIndeksWybranejKartyWRece()+1)%gracz.getReka().size());
								odswiezKartyGracza();
							}
						} 
						break;
					case KeyEvent.VK_LEFT:
						if (czyJestOknoPowitalne == false) {
							if (czyJestFocusNaOknoDialogowe == true) {
	//								System.out.println((OknoDialogowe.getZaznaczonyGuzikWAktualnymOknie()-1)%OknoDialogowe.getPozycjaGuzikowWAktualnymOknie().length);
								OknoDialogowe.zaznaczGuzik(((OknoDialogowe.getZaznaczonyGuzikWAktualnymOknie()-1)%OknoDialogowe.getPozycjaGuzikowWAktualnymOknie().length < 0)?OknoDialogowe.getPozycjaGuzikowWAktualnymOknie().length-1:(OknoDialogowe.getZaznaczonyGuzikWAktualnymOknie()-1)%OknoDialogowe.getPozycjaGuzikowWAktualnymOknie().length);
								odswiezOknoDialogowe();
							}
							if (czyJestFocusNaSwojeKarty == true) {
								gracz.setIndeksWybranejKartyWRece(((gracz.getIndeksWybranejKartyWRece()-1)%gracz.getReka().size() <0)?(gracz.getReka().size()-1):((gracz.getIndeksWybranejKartyWRece()-1)%gracz.getReka().size()));
								odswiezKartyGracza();
							}
						}
						break;
					case KeyEvent.VK_SPACE:
						if (czyJestFocusNaOknoDialogowe == true) {
							//jest to okno powitalne
							if (OknoDialogowe.getIdOknaDialogowego() == 0){
								czyJestOknoPowitalne = false;
								screen.clear();
//								czyJestOknoDialogowe = true;
								czyJestFocusNaOknoDialogowe = false;
								czyJestFocusNaSwojeKarty = false;
								czyGraczRzucilWszystkieKarty = false;
//								OknoDialogowe.zadaj();
								OknoDialogowe.akcja();
								odswiezCaleOkno();
							} 
							//jesli jest  okno zmiany koloru
							else if (OknoDialogowe.getIdOknaDialogowego() == 1) {
								boolean czyZaakceptowano = false;
								switch (OknoDialogowe.getZaznaczonyGuzikWAktualnymOknie()) {
									//kier
									case 0: czyZaakceptowano = gracz.zmienKolor("Kier");break;
									//karo
									case 1: czyZaakceptowano = gracz.zmienKolor("Karo");break;
									//pik
									case 2: czyZaakceptowano = gracz.zmienKolor("Pik");break;
									//trefl
									case 3: czyZaakceptowano = gracz.zmienKolor("Trefl");break;
									//nic
									default: czyZaakceptowano = gracz.zmienKolor(null);
								}
								if (czyZaakceptowano == true) {
									//tutaj co robie jestli zaakceptowano ruch
								}
							}
							//jesli jest okno ¿¹dania
							else if (OknoDialogowe.getIdOknaDialogowego() == 2) {
								boolean czyZaakceptowano = false;
								switch (OknoDialogowe.getZaznaczonyGuzikWAktualnymOknie()) {
									//6
									case 0: czyZaakceptowano = gracz.zadaj(6);break;
									//7
									case 1: czyZaakceptowano = gracz.zadaj(7);break;
									//8
									case 2: czyZaakceptowano = gracz.zadaj(8);break;
									//9
									case 3: czyZaakceptowano = gracz.zadaj(9);break;
									//10
									case 4: czyZaakceptowano = gracz.zadaj(10);break;
									//!Q
									case 5: czyZaakceptowano = gracz.zadaj(12);break;
									//!K
									case 6: czyZaakceptowano = gracz.zadaj(13);break;
									//nic
									default: czyZaakceptowano = gracz.zadaj(-1);
								}
								if (czyZaakceptowano == true) {
									//tutaj co robie jestli zaakceptowano ruch
								}
							}
							//jesli jest okno akcji
							else if (OknoDialogowe.getIdOknaDialogowego() == 3) {
								switch (OknoDialogowe.getZaznaczonyGuzikWAktualnymOknie()) {
									//BIORÊ
									case 0: 
										if (czyGraczRzucilCoNajmniejJednaKarte == false && czyGraczRzucilWszystkieKarty == false) {
											if (gra.getIleKartDoPobrania() != 0) {
												for (int i = 0; i < gra.getIleKartDoPobrania(); i++) {
													gracz.pobierzKarte();
												}
												gra.setIleKartDoPobrania(0);
											} else {
												gracz.pobierzKarte();
											}
											
											czyGraczRzucilWszystkieKarty = true;
											czyJestFocusNaOknoDialogowe = false;
											czyJestFocusNaSwojeKarty = true;
											czyJestOknoDialogowe = false;
											odswiezKartyGracza();
											odswiezOknoDialogowe();
											gra.przekazRuchNastepnemuGraczowi();
										}
										break;
									//REZYGNUJÊ
									case 1:
										if (czyGraczRzucilCoNajmniejJednaKarte == true) {
											czyGraczRzucilWszystkieKarty = true;
											czyJestFocusNaOknoDialogowe = false;
											czyJestFocusNaSwojeKarty = true;
											czyJestOknoDialogowe = false;
										}
										if (gra.getCzyMozeZadac()) {
											OknoDialogowe.zadaj();
											czyJestFocusNaOknoDialogowe = true;
											czyJestFocusNaSwojeKarty = false;
											czyJestOknoDialogowe = true;
											gra.przekazRuchNastepnemuGraczowi();
										} else if (gra.getCzyMozeZmienicKolor()) {
											OknoDialogowe.wybierzKolor();
											czyJestFocusNaOknoDialogowe = true;
											czyJestFocusNaSwojeKarty = false;
											czyJestOknoDialogowe = true;
											gra.przekazRuchNastepnemuGraczowi();
										}
										odswiezOknoDialogowe();
										break;
									//TRACÊ
									case 2:
										if (gra.getIleKolejekTrzebaStac() > 0) {
											gracz.setIleStojeKolejek(gra.getIleKolejekTrzebaStac());
											gra.setIleKolejekTrzebaStac(0);
											czyGraczRzucilWszystkieKarty = true;
											czyJestFocusNaOknoDialogowe = false;
											czyJestFocusNaSwojeKarty = true;
											czyJestOknoDialogowe = false;
											odswiezOknoDialogowe();
										}
										break;
									default:
								}
							}
						}
						//jesli gracz focusuje swoje karty
						else if (czyJestFocusNaSwojeKarty == true){
							if (czyGraczRzucilWszystkieKarty == false) {
								boolean czyZaakceptowano = false;
								try {
									czyZaakceptowano =  gracz.rzucKarte();
								} catch (ArbiterException e1) {
									System.out.println("Jakis blad przy rzuceniu karty");
								}
								if (czyZaakceptowano == true) {
									//tutaj co robie jesli zaakcpetowano ruch
									czyGraczRzucilCoNajmniejJednaKarte = true;
									odswiezStosKart();
									if (gracz.getReka().size() == 0) {
										koniecGry();
										gra.setStanGry(false);
										//CO MA ROBIC PO KONCU GRY
									} else {
										gracz.setIndeksWybranejKartyWRece((gracz.getIndeksWybranejKartyWRece() == gracz.getReka().size())?(gracz.getIndeksWybranejKartyWRece()-1):(gracz.getIndeksWybranejKartyWRece()));
										odswiezKartyGracza();
									}
								} else {
									System.out.println("wtf");
								}
							}
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
		OknoDialogowe.zaznaczGuzik(0);
		czyJestOknoDialogowe = true;
		czyJestFocusNaOknoDialogowe = true;
		czyJestOknoPowitalne = true; // zmienic na true jesli ma sie na poczatku wyswietlic okno powitalne
//		OknoDialogowe.akcja();
//		czyJestOknoDialogowe = true;
		odswiezCaleOkno();
	}
	
	/**
	 * Caly layout strony
	 */
	public void odswiezCaleOkno() {
		String [] oknoDialogowe = OknoDialogowe.getAktualneOkno();
		//jesli flaga czyJestOknoPowitalne jest ustawiona na true, to wyswietla tylko okno powitalne
		if (czyJestOknoPowitalne == true) {
			for (int i = 0; i < oknoDialogowe.length; i++) {
				sw.drawString(WIDTH/2 - oknoDialogowe[0].length()/2, HEIGHT/2 -(oknoDialogowe.length/2) + i, oknoDialogowe[i]);
			}
		} 
		//jesli flaga jest ustawiona na false, to wyswietla cala gre
		else if (czyJestOknoPowitalne == false) {
			odswiezKartyGracza();
			odswiezKartyPrzeciwnikow();
			odswiezStosKart();
			odswiezNickiGraczy();
			odswiezCzyjRuch();
			odswiezStanRuchu();
			odswiezOknoDialogowe();
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
		int dlugoscSchowanejKarty = (gracz.getReka().size() >=10 )?2:4;
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
			Karta wybranaKarta = (gracz.getIndeksWybranejKartyWRece() != -1)?gracz.getReka().get(gracz.getIndeksWybranejKartyWRece()): null;
			String[] schematRysowanejKarty = Karty.getKarta(rysowanaKarta.getZnak(), rysowanaKarta.getKolor());
			//jesli aktualna karta w rece, ktora mam narysowac jest tez wybrana karta, to rysuje ja wyzej niz inne karty
//			System.out.println(rysowanaKarta.toString()+"-"+((wybranaKarta != null)?wybranaKarta.toString():"0"));
			if (rysowanaKarta.toString().equals(((wybranaKarta != null)?wybranaKarta.toString():"0"))) {
				
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
					rysujBocznegoPrzeciwnika(0, gra.getGracze()[(i+1)%gra.getGracze().length]);	
				}
				if (gra.getGracze()[(i+2)%gra.getGracze().length] != null) {
					rysujGornegoPrzeciwnika(gra.getGracze()[(i+2)%gra.getGracze().length]);
				}
				if (gra.getGracze()[(i+3)%gra.getGracze().length] != null) {
					rysujBocznegoPrzeciwnika(WIDTH-9, gra.getGracze()[(i+3)%gra.getGracze().length]);
				}
				break;
			}
		}
		screen.refresh();
	}
	
	private void rysujBocznegoPrzeciwnika(int x, gracze.Gracz gracz) {
		int rozmiarReki = gracz.getReka().size();
		int wysokoscSchematuKarty =  Karty.getKarta("2", "Pik").length;
		int dlugoscPolaKartGracza = Karty.getKarta("2", "Pik")[1].length();
		int wysokoscSchowanejKarty = (gracz.getReka().size() >=10)?1:2;
		int wysokoscOstatniejKarty = wysokoscSchematuKarty;
		int wysokoscKart = (rozmiarReki-1)*wysokoscSchowanejKarty+wysokoscOstatniejKarty;
		int miejsceRozpoczeciaRysowaniaKart = HEIGHT/2 - wysokoscKart/2;
		
		String[] schematRysowanejKarty = Karty.getKartarewers();
		//czyszczenie pola gracza
		for (int i = 0; i < HEIGHT; i++) {
			sw.drawString(x, i, "         ");
		}
		
		for (int i = 0; i < rozmiarReki; i++) {
			for (int j = 0; j < schematRysowanejKarty.length; j++) {
				sw.drawString(x, miejsceRozpoczeciaRysowaniaKart+(i*wysokoscSchowanejKarty)+j, (i!=0 && j==0)?"|".concat(schematRysowanejKarty[j].substring(1)):schematRysowanejKarty[j]);
			}
		}
	}
	
	private void rysujGornegoPrzeciwnika(gracze.Gracz gracz) {
		int rozmiarReki = gracz.getReka().size();
		int wysokoscSchematuKarty =  Karty.getKarta("2", "Pik").length;
		int wysokoscPolaKartGracza = wysokoscSchematuKarty;
		int dlugoscSchowanejKarty = (gracz.getReka().size() >=10)?2:4;
		int dlugoscOstatniejKarty = Karty.getKarta("2", "Pik")[1].length();
		int dlugoscKart = dlugoscSchowanejKarty*(gracz.getReka().size()-1)+dlugoscOstatniejKarty;
		int miejsceRozpoczeciaRysowaniaKart = WIDTH/2 - dlugoscKart/2;
		
		String[] schematRysowanejKarty = Karty.getKartarewers();
		//czyszcze cale pole gracza
		for (int i = 0; i < wysokoscPolaKartGracza; i++) {
			sw.drawString(0, i, sameSpacjeDoCzyszczenia);
		}
		
		for (int i = 0; i < rozmiarReki; i++) {
			for (int j = 0; j < schematRysowanejKarty.length; j++) {
				sw.drawString(miejsceRozpoczeciaRysowaniaKart+ (i*dlugoscSchowanejKarty), j, schematRysowanejKarty[j]);
			}
		}
	}
	
	public void odswiezStosKart() {
		int wysokoscSchematuKarty =  Karty.getKarta("2", "Pik").length;
		int wysokoscPolaKartGracza = wysokoscSchematuKarty;
		int dlugoscSchowanejKarty = 6;
		int dlugoscOstatniejKarty = Karty.getKarta("2", "Pik")[1].length();
		int maksymalnaLiczbaWyswietlonychKart = 5;
		int ileMaBycWyswietlonychKart = (gra.getTalia().getUzyteKarty().size() > maksymalnaLiczbaWyswietlonychKart)? maksymalnaLiczbaWyswietlonychKart : gra.getTalia().getUzyteKarty().size();
		int dlugoscKart = dlugoscSchowanejKarty*(ileMaBycWyswietlonychKart-1)+dlugoscOstatniejKarty;
//		int maksymalnaDlugoscKart =dlugoscSchowanejKarty*(maksymalnaLiczbaWyswietlonychKart-1)+dlugoscOstatniejKarty;
		int miejsceRozpoczeciaRysowaniaKartX = WIDTH/2 - dlugoscKart/2;
		int miejsceRozpoczeciaRysowaniaKartY = HEIGHT/2- wysokoscPolaKartGracza/2 -5;
		
		//nie musze czyscic, bo kart moze byc tylko wiecej lub tyle samo, lecz nigdy mniej
		
		for (int i = ileMaBycWyswietlonychKart, counter = 0; i > 0; i--, counter++) {
			String wartosc = gra.getTalia().getUzyteKarty().get(gra.getTalia().getUzyteKarty().size()-i).getZnak();
			String kolor = gra.getTalia().getUzyteKarty().get(gra.getTalia().getUzyteKarty().size()-i).getKolor();
			String[] schematRysowanejKarty = Karty.getKarta(wartosc, kolor);
			for (int j = 0; j < schematRysowanejKarty.length; j++) {
				sw.drawString(miejsceRozpoczeciaRysowaniaKartX+ (counter*dlugoscSchowanejKarty), miejsceRozpoczeciaRysowaniaKartY+j, schematRysowanejKarty[j]);
			}
		}
		screen.refresh();
	}
	
	public void odswiezCzasGracza() {
		
	}
	
	public void odswiezNickiGraczy() {
		for (int i = 0; i < gra.getGracze().length; i++) {
			if (gra.getGracze()[i] == gracz) {
				//rysuje swoj nick
				sw.drawString(WIDTH/2 - gracz.getNickname().length()/2, HEIGHT-11, gracz.getNickname());
				//rysuje nicki dla kazdego z przeciwnikow osobno, bez zadnej petli
				if (gra.getGracze()[(i+1)%gra.getGracze().length] != null) {
					sw.drawString(10, HEIGHT/2, (gra.getGracze()[(i+1)%gra.getGracze().length].getNickname().length() >15)?gra.getGracze()[(i+1)%gra.getGracze().length].getNickname().substring(0, 14)+".":gra.getGracze()[(i+1)%gra.getGracze().length].getNickname());
				}
				if (gra.getGracze()[(i+2)%gra.getGracze().length] != null) {
					sw.drawString(WIDTH/2 - (gra.getGracze()[(i+2)%gra.getGracze().length].getNickname().length()/2), 7, gra.getGracze()[(i+2)%gra.getGracze().length].getNickname());
				}
				if (gra.getGracze()[(i+3)%gra.getGracze().length] != null) {
					String nick = (gra.getGracze()[(i+3)%gra.getGracze().length].getNickname().length() >15)?gra.getGracze()[(i+3)%gra.getGracze().length].getNickname().substring(0, 14)+".":gra.getGracze()[(i+3)%gra.getGracze().length].getNickname();
					sw.drawString(WIDTH-10-nick.length(), HEIGHT/2, nick);
				}
				break;
			}
		}
		screen.refresh();
	}
	
	public void odswiezCzyjRuch() {
		for (int i = 0; i < gra.getGracze().length; i++) {
			if (gra.getGracze()[i] == gracz) {
				//czyszcze wszystkie strzalki
				sw.drawString(WIDTH/2, HEIGHT-12, " ");//dol
				sw.drawString(9+14+1, HEIGHT/2-2, " ");//lewo
				sw.drawString(WIDTH/2, 8, " ");//gora
				sw.drawString(WIDTH-(9+14+2), HEIGHT/2-2, " ");//prawo
				if ((i)%gra.getGracze().length == gra.getAktualnyRuch()) {
					sw.drawString(WIDTH/2, HEIGHT-12, "\u25BC");//dol
				} else if ((i+1)%gra.getGracze().length == gra.getAktualnyRuch()) {
					sw.drawString(9+14+1, HEIGHT/2-2, "\u25BC");//lewo
				} else if ((i+2)%gra.getGracze().length == gra.getAktualnyRuch()) {
					sw.drawString(WIDTH/2, 8, "\u25B2");//gora
				} else if ((i+3)%gra.getGracze().length == gra.getAktualnyRuch()) {
					sw.drawString(WIDTH-(9+14+2), HEIGHT/2-2, "\u25BC");//prawo
				}
				break;
			}
		}
		screen.refresh();
	}
	
	/**
	 * Wyswietla, to trzeba zadac, jaki zmienic kolor, albo ile trzeba siorbaæ
	 */
	public void odswiezStanRuchu() {
		int pozycjaStanuRuchu = 10;
		String napis = "                    ";
		sw.drawString(WIDTH/2 - napis.length()/2, pozycjaStanuRuchu,napis);
		if (gra.getZadanie() !=0) {
			napis = "¯¹danie: "+ gra.getZadanie();
		} else if (gra.getZmianaKoloru() != null) {
			napis = "Zmiana koloru: "+ gra.getZmianaKoloru();
		} else if (gra.getIleKartDoPobrania() != 0) {
			napis = "Suma do pobrania: "+ gra.getIleKartDoPobrania();
		}
		sw.drawString(WIDTH/2 - napis.length()/2, pozycjaStanuRuchu,napis);
		screen.refresh();
	}
	
	public void odswiezOknoDialogowe() {
		String napis = "                                          ";
		int wysokoscOknaDialogowego = 8;
		int szerokoscOknaDialogowego = 42;
		for (int i = 0; i < wysokoscOknaDialogowego; i++) {
			sw.drawString(WIDTH/2 - szerokoscOknaDialogowego/2, HEIGHT/2 +i, napis);
		}
		String[] aktualneOkno = OknoDialogowe.getAktualneOkno();

		if (czyGraczRzucilWszystkieKarty == false) {
			for (int i = 0; i < wysokoscOknaDialogowego; i++) {
				sw.drawString(WIDTH/2 - szerokoscOknaDialogowego/2, HEIGHT/2 +i, aktualneOkno[i]);
			}	
			czyJestOknoDialogowe = true;
		} else if (gra.getCzyMozeZadac() == true) {
			for (int i = 0; i < wysokoscOknaDialogowego; i++) {
				sw.drawString(WIDTH/2 - szerokoscOknaDialogowego/2, HEIGHT/2 +i, aktualneOkno[i]);
			}	
			czyJestOknoDialogowe = true;
		} else if (gra.getCzyMozeZmienicKolor() == true) {
			for (int i = 0; i < wysokoscOknaDialogowego; i++) {
				sw.drawString(WIDTH/2 - szerokoscOknaDialogowego/2, HEIGHT/2 +i, aktualneOkno[i]);
			}
			czyJestOknoDialogowe = true;
		}
//		String[] aktualneOkno = OknoDialogowe.getAktualneOkno();
//		for (int i = 0; i < wysokoscOknaDialogowego; i++) {
//			sw.drawString(WIDTH/2 - szerokoscOknaDialogowego/2, HEIGHT/2 +i, aktualneOkno[i]);
//		}
//		czyJestOknoDialogowe = true;
		
		screen.refresh();
	}
	
	public void koniecGry() {
		
	}
	
	public void kolejGracza() {
		czyGraczRzucilWszystkieKarty = false;
		czyGraczRzucilCoNajmniejJednaKarte = false;
		czyJestFocusNaOknoDialogowe = false;
		czyJestFocusNaSwojeKarty = true;
		czyJestOknoDialogowe = true;
		OknoDialogowe.akcja();
		odswiezOknoDialogowe();
		odswiezKartyGracza();
		odswiezStosKart();
		odswiezKartyPrzeciwnikow();
		odswiezStanRuchu();
		odswiezCzyjRuch();
	}
	
	public void koniecRuchuGracza() {
		int indexGracza = 0;
		for (int i = 0; i < gra.getGracze().length; i++) {
			if (gra.getGracze()[i] == gracz) {
				indexGracza = i;
				break;
			}
		}
		while (gra.getAktualnyRuch() != indexGracza) {
			try {
				Thread.sleep(1000);
				odswiezKartyPrzeciwnikow();
				odswiezStanRuchu();
				odswiezStosKart();
			} catch (InterruptedException e) {
				System.out.println("Jakis blad w spaniu");
			}
		}
		kolejGracza();
	}
//	public String generujPustyString() {
//		
//	}
}
 
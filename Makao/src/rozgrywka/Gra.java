package rozgrywka;

import java.util.List;

import exceptions.ArbiterException;
import exceptions.CardException;
import gracze.Czlowiek;
import gracze.Gracz;
import karty.Karta;
import karty.Talia;

public class Gra {
	/**
	 * Zmienna przechowuj¹ca maksymaln¹ liczê graczy przy stole
	 */
	private int rozmiarStolu = 4;
	private int wymaganaLiczbaGraczyPrzyStole = 2;
	/**
	 * Tablica przechowuj¹ca aktualnych graczy w grze.
	 */
	private Gracz[] gracze = new Gracz[rozmiarStolu];
	/**
	 * Talia sk³adaj¹ca siê z 52 kart, którymi bêdzie grana gra.
	 */
	private Talia talia;
	/**
	 * Arbiter gry, który zna zasady rozgrywki
	 */
	private Arbiter arbiter = new Arbiter(this);
	/**
	 * Zmienna przechowuj¹ca indeks gracza w tablicy "gracze", który aktualnie ma wykonaæ ruch
	 */
	private int aktualnyRuch = 0;
	/**
	 * true - gra sie zaczela
	 * false - gra sie nie zaczela
	 */
	private boolean stanGry = false;
	/**
	 * Zmienna przechowujaca ¿¹dan¹ wartoœæ, jesli jest 0, tzn, ¿e nikt nic nie ¿¹da
	 */
	private int zadanie = 0; //0
	/**
	 * Zmienna zawieraj¹ca liczbê osób, aby sprawdziæ, czy minê³a kolejka ¿¹dania
	 */
	private int zadanieKolejka = 0;
	/**
	 * Zmienna przechowujaca ¿¹dany kolor, jesli jest null, tzn, ¿e nikt nic nie ¿¹da
	 */
	private String zmianaKoloru = null;
	/**
	 * Czy gracz, który ma aktualnie kolejkê mo¿e ¿¹daæ kartê
	 */
	private boolean czyMozeZadac = false; //false
	/**
	 * Czy gracz, który ma aktualnie kolejkê mo¿e zmieniæ kolor
	 */
	private boolean czyMozeZmienicKolor = false; //false
	
	private int ileKartDoPobrania = 0;
	
	private int ileKolejekTrzebaStac = 0;
	
	public Gra() throws CardException {
		this.talia = new Talia();
	}
	
	/**
	 * Metoda sprawdza, czy jest wolne miejsce przy stole. Jeœli jest - dodaje gracza do sto³u.
	 * @param gracz - obiekt klasy Gracz
	 * @return true - pomyœlnie dodano gracza do gry
	 * @return false - nie dodano gracza do gry
	 */
	public boolean dodajGraczaDoGry(Gracz gracz) {
		if (!this.sprawdzCzyGraczJestPrzyStole(gracz)) {
			for (int i =0; i<this.gracze.length; i++) {
				if (this.gracze[i] == null) {
					//dodaje gracza do listy graczy przy stole
					this.gracze[i] = gracz;
					//dodaje aktualna gre w polu gracza
					gracz.setGra(this);
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * @param gracz
	 * @return true - gracz jest przy stole
	 * @return false - gracz nie jest przy stole
	 */
	public boolean sprawdzCzyGraczJestPrzyStole(Gracz gracz) {
		for (int i=0; i<this.gracze.length;i++){
			if (this.gracze[i] == gracz) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * @return 0-4,... - zwraca liczbê wolnych miejsc przy stole
	 */
	public int getWolneMiejscaPrzyStole() {
		int counter = 0;
		for (int i =0 ; i<this.gracze.length; i++) {
			if (this.gracze[i] == null)
				counter++;
		}
		return counter;
	}
	
	/**
	 * Metoda sprawdza, czy gracz podany w parametrze znajduje siê przy stole. Jeœli tak - usuwa gracza ze sto³u
	 * @param gracz - obiekt klasy Gracz
	 * @return true - pomyœlnie usuniêto gracza z gry
	 * @return false - nie usuniêto gracza z gry
	 */
	public boolean usunGraczaZGry(Gracz gracz) {
		for (int i=0; i< this.gracze.length; i++) {
			if (this.gracze[i] == gracz) {
				//usuwa gre z pola gracza
				gracz.setGra(null);
				//usuwa gracza z listy graczy przy stole
				this.gracze[i] = null;
				//jeœli jest kolej gracza na ruch, to przekazuje ruch kolejnemu graczowi
				if (this.aktualnyRuch == i) {
					this.przekazRuchNastepnemuGraczowi();
				}
				//czy jest mniej niz dwoch graczy przy stole, to konczy gre
				if ((this.rozmiarStolu - this.getWolneMiejscaPrzyStole()) < wymaganaLiczbaGraczyPrzyStole) {
					this.stanGry = false;
				}
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Metoda przekazuj¹ca ruch nastêpnemu graczowi. Jeœli nie ma graczy przy stole ustala wartosæ this.aktualnyRuch na 0
	 */
	public void przekazRuchNastepnemuGraczowi() {
		//jesli ktos cos ¿¹da
		if (zadanie != 0) {
			//jesli minela pelna kolejka
			if (++zadanieKolejka == rozmiarStolu +1) {
				zadanie = 0;
			}
		}
		
		this.czyMozeZadac = false;
		this.czyMozeZmienicKolor = false;
		if (this.rozmiarStolu == this.getWolneMiejscaPrzyStole()) {
			this.aktualnyRuch = 0;
			return;
		}
		do {
			this.aktualnyRuch++;
		} while (this.gracze[this.aktualnyRuch] == null);
	}
	
	/**
	 * Rozpoczyna gre, jesli jest min. 2 graczy przy stole, tasuje karty.
	 */
	public void rozpocznijGre () {
		//jeœli jest conajmniej 2 graczy przy stole
		if ((this.rozmiarStolu - this.getWolneMiejscaPrzyStole()) < wymaganaLiczbaGraczyPrzyStole) {
			System.out.println("Gra nie wystartowala");
			return;
		}
		
		this.talia.tasuj();
		//gra sie rozpoczela \/
		this.stanGry = true;
		System.out.println("Gra wystartowala");
	}
	public int getIleKolejekTrzebaStac() {
		return ileKolejekTrzebaStac;
	}

	public void setIleKolejekTrzebaStac(int ileKolejekTrzebaStac) {
		this.ileKolejekTrzebaStac = ileKolejekTrzebaStac;
	}

	public Talia getTalia() {
		return talia;
	}

	public Gracz[] getGracze() {
		return gracze;
	}

	public boolean getStanGry() {
		return this.stanGry;
	}
	
	public void setStanGry(boolean stanGry) {
		this.stanGry = stanGry;
	}

	public int getAktualnyRuch() {
		return this.aktualnyRuch;
	}
	
	public int getZadanie() {
		return zadanie;
	}

	public void setZadanie(int zadanie) {
		this.zadanie = zadanie;
	}

	public int getZadanieKolejka() {
		return zadanieKolejka;
	}

	public void setZadanieKolejka(int zadanieKolejka) {
		this.zadanieKolejka = zadanieKolejka;
	}

	public String getZmianaKoloru() {
		return zmianaKoloru;
	}

	public void setZmianaKoloru(String zmianaKoloru) {
		this.zmianaKoloru = zmianaKoloru;
	}
	
	public boolean getCzyMozeZadac() {
		return czyMozeZadac;
	}

	public void setCzyMozeZadac(boolean czyMozeZadac) {
		this.czyMozeZadac = czyMozeZadac;
	}

	public boolean getCzyMozeZmienicKolor() {
		return czyMozeZmienicKolor;
	}

	public void setCzyMozeZmienicKolor(boolean czyMozeZmienicKolor) {
		this.czyMozeZmienicKolor = czyMozeZmienicKolor;
	}
	
	/**
	 * @param gracz - instancja gracza, ktora bedzie metoda identyfikacji
	 * @param kolor - instancja klasy reprezentuj¹ca zmieniany kolor
	 * @return true - poprawnie zmieniono kolor
	 * @return false - nie mozna zmienic koloru
	 */
	public boolean zmienKolor(Gracz gracz, String kolor) {
		//jesli gra sie nie rozpoczela, to to nie pozwalam wykonaæ ruchu
		if (!this.stanGry) {
			return false;
		}
		//sprawdzam czy gracz jest przy stole
		for (int i =0; i<this.rozmiarStolu; i++) {
			if (gracze[i] == gracz) {
				//jesli jest przy stole to sprawdzam, czy jest jego ruch
				if (i == this.aktualnyRuch) {
					//jesli jest jego ruch, to sprawdzam, czy moze zmienic kolor
					if (this.czyMozeZmienicKolor) {
						if (kolor != null) {
							//jesli moze zmienic kolor, to zmienia
							this.zmianaKoloru = kolor;
						}
						return true;
					}
				} else {
					return false;
				}
			}
		}
		return false;
	}
	
	/**
	 * @param gracz - instancja gracza, ktora bedzie metoda identyfikacji
	 * @param wartosc - zadana wartosc
	 * @return true - zaakceptowano ¿¹danie
	 * @return false - odrzucono ¿¹danie
	 */
	public boolean zadaj (Gracz gracz, int wartosc) {
		//jesli gra sie nie rozpoczela, to to nie pozwalam wykonaæ ruchu
		if (!this.stanGry) {
			return false;
		}
		//sprawdzam czy gracz jest przy stole
		for (int i = 0; i<this.rozmiarStolu; i++) {
			if (gracze[i] == gracz) {
				//jesli jest przy stole to sprawdzam, czy jest jego ruch
				if (i == this.aktualnyRuch) {
					//jesli to jest jego ruch, to sprawdzam, czy mo¿na ¿¹daæ
					if (this.czyMozeZadac) {
						if (wartosc != -1) {
							//jesli mozna ¿¹daæ, to zmienia ¿¹danie
							this.zadanie = wartosc;
							//oraz zaczyna kolejke ¿¹dania od nowa
							this.zadanieKolejka = 0;
						}
						return true;
					}
				} else {
					//jesli nie jest ruch gracza to nie pozwalam na ruch
					return false;
				}
			}
		}
		return false;
	}
	/**
	 * @param gracz - instancja gracza, ktora bedzie metoda identyfikacji
	 * @param karty - tablica kart rzuconych przez gracza
	 * @return true - zaakceptowano ruch
	 * @return false - odrzucono ruch
	 * @throws ArbiterException 
	 */
	public boolean rzucKarte(Gracz gracz, Karta karta) throws ArbiterException {
		//jesli gra sie nie rozpoczela, to to nie pozwalam wykonaæ ruchu
		if (!this.stanGry) {
			return false;
		}
		//jesli metoda  dosta³a pust¹ listê kart
		if (karta == null) {
			return false;
		}
		//sprawdzam czy gracz jest przy stole
		for (int i = 0; i<this.rozmiarStolu; i++) {
			if (gracze[i] == gracz) {
				//jesli jest przy stole to sprawdzam, czy jest jego ruch
				if (i == this.aktualnyRuch) {
					// jesli arbiter powie, ¿e ruch jest dozwolony
					if (arbiter.czyRuchJestDozwolony(karta)) {
						// wrzucam karte na stos uzytych kart
						this.talia.pushUzyteKarty(gracze[i].getReka().remove(gracze[i].getIndeksWybranejKartyWRece()));					
						//jesli ta karta byla J(Jopkiem, Waletem)
						if (karta.getZnak().equals("J")) {
							zmianaKoloru = null;
							czyMozeZadac = true;
							//resetuje kolejke
							zadanieKolejka = 0;
						} 
						//albo byla A(Asem)
						else if (karta.getZnak().equals("A")) {
							czyMozeZmienicKolor = true;
						}
						else if (karta.getWartosc() == 2 || karta.getWartosc() == 3) {
							ileKartDoPobrania += karta.getWartosc();
						}
						else if (karta.getWartosc() == 13) {
							if (karta.getKolor().equals("Pik") || karta.getKolor().equals("Kier")) {
								ileKartDoPobrania  += 5;
							}
						}
						else if (karta.getWartosc() == 4) {
							ileKolejekTrzebaStac += 1;
						}
						//dama bita
						else if (karta.getWartosc() == 12 &&(karta.getKolor().equals("Kier")||karta.getKolor().equals("Pik"))) {
							//jesli karta pod dama jest jopkiem i jest aktualne zadanie
							if (talia.peekUzyteKarty().getWartosc() == 11 && zadanie !=0) {
								zadanie = 0;
								zadanieKolejka = 0;
							} 
							//
							else {
								ileKartDoPobrania = 0;
								ileKolejekTrzebaStac = 0;
								zmianaKoloru = null;
							}
						}
						//jesli spelnilem zmiane koloru
						else if (karta.getKolor() != null) {
							if (karta.getKolor().equals(zmianaKoloru)) {
								zmianaKoloru = null;
							}
						}
						return true;
					}
//					System.out.println("TO jest jego aktualny ruch");
//					this.przekazRuchNastepnemuGraczowi(); //ARBITER - to musi wyrzucac arbiter
					return false;
				} else {
					System.out.println("Nie twoj ruch");
					//jesli nie jest ruch gracza to nie pozwalam na ruch
					return false;
				}
			}
		}
		return false;
	}
	/**
	 * @param gracz - instancja gracza do identyfikacji
	 * @return zwraca graczowi kartê z talii
	 */
	public Karta pobierzKarte(Gracz gracz) {
		return this.talia.popTalia();
	}
	/**
	 * Rozdaje karty wszystkim graczom przy stole
	 */
	public void rozdajKarty() {
		//liczba kart do rozdania kazdemu z graczy na poczatku rundy
		int liczbaKart = 5;
		for (int i =0; i<this.rozmiarStolu; i++) {
			//jezeli miejsce przy stole jest zajete przez gracza
			if (this.gracze[i] != null) {
				//rozdaje 5 kart graczowi
				for (int j = 0; j<liczbaKart; j++) {
					this.gracze[i].dajKarteDoReki(this.talia.popTalia());
				}
			}
		}
		this.talia.pushUzyteKarty(this.talia.popTalia());
	}

	public int getIleKartDoPobrania() {
		return ileKartDoPobrania;
	}

	public void setIleKartDoPobrania(int ileKartDoPobrania) {
		this.ileKartDoPobrania = ileKartDoPobrania;
	}
}

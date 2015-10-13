package rozgrywka;

import exceptions.CardException;
import gracze.Czlowiek;
import gracze.Gracz;
import karty.Talia;

public class Gra {
	/**
	 * Zmienna przechowuj¹ca maksymaln¹ liczê graczy przy stole
	 */
	private int rozmiarStolu = 4;
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
	private Arbiter arbiter = new Arbiter();
	/**
	 * Zmienna przechowuj¹ca indeks gracza w tablicy "gracze", który aktualnie ma wykonaæ ruch
	 */
	private int aktualnyRuch = 0;
	/**
	 * true - gra sie zaczela
	 * false - gra sie nie zaczela
	 */
	private boolean stanGry = false;
	
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
					this.gracze[i] = gracz;
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
				this.gracze[i] = null;
				//jeœli jest kolej gracza na ruch, to przekazuje ruch kolejnemu graczowi
				if (this.aktualnyRuch == i) {
					this.przekazRuchNastepnemuGraczowi();
				}
				//czy jest minimum dwoch graczy przy stole
				if ((this.rozmiarStolu - this.getWolneMiejscaPrzyStole()) < 2) {
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
	private void przekazRuchNastepnemuGraczowi() {
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
		if ((this.rozmiarStolu - this.getWolneMiejscaPrzyStole()) < 2) {
			System.out.println("Gra nie wystartowala");
			return;
		}
		
		this.talia.tasuj();
		//gra sie rozpoczela \/
		this.stanGry = true;
		System.out.println("Gra wystartowala");
	}
	public boolean getStanGry() {
		return this.stanGry;
	}
}

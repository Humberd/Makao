package rozgrywka;

import exceptions.CardException;
import gracze.Czlowiek;
import gracze.Gracz;
import karty.Talia;

public class Gra {
	/**
	 * Zmienna przechowuj�ca maksymaln� licz� graczy przy stole
	 */
	private int rozmiarStolu = 4;
	/**
	 * Tablica przechowuj�ca aktualnych graczy w grze.
	 */
	private Gracz[] gracze = new Gracz[rozmiarStolu];
	/**
	 * Talia sk�adaj�ca si� z 52 kart, kt�rymi b�dzie grana gra.
	 */
	private Talia talia;
	/**
	 * Arbiter gry, kt�ry zna zasady rozgrywki
	 */
	private Arbiter arbiter = new Arbiter();
	/**
	 * Zmienna przechowuj�ca indeks gracza w tablicy "gracze", kt�ry aktualnie ma wykona� ruch
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
	 * Metoda sprawdza, czy jest wolne miejsce przy stole. Je�li jest - dodaje gracza do sto�u.
	 * @param gracz - obiekt klasy Gracz
	 * @return true - pomy�lnie dodano gracza do gry
	 * @return false - nie dodano gracza do gry
	 */
	public boolean dodajGraczaDoGry(Gracz gracz) {
		for (int i =0; i<this.gracze.length; i++) {
			if (this.gracze[i] == null) {
				this.gracze[i] = gracz;
				return true;
			}
		}
		return false;
	}
	
	/**
	 * @return 0-4,... - zwraca liczb� wolnych miejsc przy stole
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
	 * Metoda sprawdza, czy gracz podany w parametrze znajduje si� przy stole. Je�li tak - usuwa gracza ze sto�u
	 * @param gracz - obiekt klasy Gracz
	 * @return true - pomy�lnie usuni�to gracza z gry
	 * @return false - nie usuni�to gracza z gry
	 */
	public boolean usunGraczaZGry(Gracz gracz) {
		for (int i=0; i< this.gracze.length; i++) {
			if (this.gracze[i] == gracz) {
				this.gracze[i] = null;
				//je�li jest kolej gracza na ruch, to przekazuje ruch kolejnemu graczowi
				if (this.aktualnyRuch == i) {
					this.przekazRuchNastepnemuGraczowi();
				}
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Metoda przekazuj�ca ruch nast�pnemu graczowi. Je�li nie ma graczy przy stole ustala wartos� this.aktualnyRuch na 0
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
	
	public void rozpocznijGre () {
		//je�li jest conajmniej 2 graczy przy stole
		if ((this.rozmiarStolu - this.getWolneMiejscaPrzyStole()) < 2) {
			return;
		}
		
//		this.talia.tasuj();
	}
}

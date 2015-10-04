package rozgrywka;

import exceptions.CardException;
import gracze.Czlowiek;
import gracze.Gracz;
import karty.Talia;

public class Gra {
	private int rozmiarStolu = 4;
	private Gracz[] gracze = new Gracz[rozmiarStolu];
	private Talia talia;
	private Arbiter arbiter = new Arbiter();
	private int aktualnyRuch = 0;
	
	public Gra() throws CardException {
		this.talia = new Talia();
		this.talia.tasuj();
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
				return true;
			}
		}
		return false;
	}
}

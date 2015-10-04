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
	 * Metoda sprawdza, czy jest wolne miejsce przy stole. Jeœli jest - dodaje gracza do sto³u.
	 * @param gracz - obiekt klasy Gracz
	 * @return true - pomyœlnie dodano gracza do gry
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
				return true;
			}
		}
		return false;
	}
}

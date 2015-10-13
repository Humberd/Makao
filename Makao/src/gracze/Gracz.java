package gracze;

import karty.Karta;
import rozgrywka.Gra;

public abstract class Gracz {
	/**
	 * Instancja gry, w kt�rej przebywa gracz
	 */
	private Gra gra;
	
	/**
	 * Karty, kt�re gracz posiada na r�ce
	 */
	private Karta[] reka;
	/**
	 * Karty, kt�re wybiera gracz, aby wys�a� do servera
	 */
	private Karta[] wybraneKarty;
	
	/**
	 * @return
	 */
	public abstract boolean zmienKolor(Class<Karta> kolor); 
	
	public abstract boolean zadaj(int wartosc);
	
	public abstract boolean wykonajRuch(Karta[] karty);
	
	public Gra getGra() {
		return gra;
	}
	
	public void setGra(Gra gra) {
		this.gra = gra;
	}

	public Karta[] getReka() {
		return reka;
	}

	public void setReka(Karta[] reka) {
		this.reka = reka;
	}

	public Karta[] getWybraneKarty() {
		return wybraneKarty;
	}

	public void setWybraneKarty(Karta[] wybraneKarty) {
		this.wybraneKarty = wybraneKarty;
	}
	
}

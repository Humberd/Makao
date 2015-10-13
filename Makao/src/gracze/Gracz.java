package gracze;

import karty.Karta;
import rozgrywka.Gra;

public abstract class Gracz {
	/**
	 * Instancja gry, w której przebywa gracz
	 */
	private Gra gra;
	
	/**
	 * Karty, które gracz posiada na rêce
	 */
	private Karta[] reka;
	/**
	 * Karty, które wybiera gracz, aby wys³aæ do servera
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

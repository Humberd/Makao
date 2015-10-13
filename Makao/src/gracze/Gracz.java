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
	
	public abstract boolean rzucKarty(Karta[] karty);
	
	
}

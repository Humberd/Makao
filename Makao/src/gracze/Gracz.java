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
	
	public abstract boolean rzucKarty(Karta[] karty);
	
	
}

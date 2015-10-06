package gracze;

import karty.Karta;
import rozgrywka.Gra;

public abstract class Gracz {
	/**
	 * Instancja gry, w kt�rej przebywa gracz
	 */
	private Gra gra;
	
	/**
	 * 
	 */
	private Karta[] reka;
	private Karta[] wybraneKarty;
	
	/**
	 * @return
	 */
	public abstract boolean zmienKolor(); 
}

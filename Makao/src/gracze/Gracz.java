package gracze;

import java.util.ArrayList;
import java.util.List;

import exceptions.ArbiterException;
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
	private List<Karta> reka = new ArrayList<Karta>();
	/**
	 * Karty, kt�re wybiera gracz, aby wys�a� do servera
	 */
	private List<Karta> wybraneKarty = new ArrayList<Karta>();
	
	public Gracz() {
		
	}
	
	public abstract boolean zmienKolor(Class<Karta> kolor); 
	
	public abstract boolean zadaj(int wartosc);
	
	public abstract boolean wykonajRuch() throws ArbiterException;
	
	/**
	 * @return true - pomy�lnie pobrano kart� z talii
	 * @return false - nie pobrano karty z talii
	 */
	public abstract boolean pobierzKarte();
	
	public Gra getGra() {
		return gra;
	}
	
	public void setGra(Gra gra) {
		this.gra = gra;
	}

	public List<Karta> getReka() {
		return reka;
	}

	public void setReka(List<Karta> reka) {
		this.reka = reka;
	}

	public List<Karta> getWybraneKarty() {
		return wybraneKarty;
	}

	public void setWybraneKarty(List<Karta> wybraneKarty) {
		this.wybraneKarty = wybraneKarty;
	}
	
	public void dajKarteDoReki(Karta karta) {
		this.reka.add(karta);
	}
	public void pushWybraneKarty(Karta karta) {
		this.wybraneKarty.add(karta);
	}
	
}

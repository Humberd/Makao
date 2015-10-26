package gracze;

import java.util.ArrayList;
import java.util.List;

import exceptions.ArbiterException;
import karty.Karta;
import rozgrywka.Gra;

public abstract class Gracz {
	/**
	 * Instancja gry, w której przebywa gracz
	 */
	private Gra gra;
	
	/**
	 * Zlicza ile instancji graczy sie juz pojawilo
	 */
	private static int licznikGraczy=0;
	
	/**
	 * Numer gracza, zeby moc wpisac go w domyslny nick
	 */
	private int numerGracza;
	
	/**
	 * Nick gracza
	 */
	private String nickname;
	
	/**
	 * Karty, które gracz posiada na rêce
	 */
	private List<Karta> reka = new ArrayList<Karta>();
	/**
	 * Karty, które wybiera gracz, aby wys³aæ do servera
	 */
	private List<Karta> wybraneKarty = new ArrayList<Karta>();
	private Karta wybranaKarta;
	
	public Gracz() {
		numerGracza = licznikGraczy++;
		setNickname("DefaultName#"+numerGracza);
	}
	
	public Gracz(String nickname) {
		numerGracza = licznikGraczy++;
		setNickname(nickname);
	}
	
	public abstract boolean zmienKolor(Class<Karta> kolor); 
	
	public abstract boolean zadaj(int wartosc);
	
	public abstract boolean wykonajRuch() throws ArbiterException;
	
	/**
	 * @return true - pomyœlnie pobrano kartê z talii
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

	public Karta getWybranaKarta() {
		return wybranaKarta;
	}

	public void setWybranaKarta(Karta wybranaKarta) {
		this.wybranaKarta = wybranaKarta;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
}

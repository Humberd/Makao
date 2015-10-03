package glowny;

import exceptions.CardException;

public abstract class Karta {
	private int wartosc;
	private char znak;
	private String kolor;
	
	public Karta(int wartosc, String kolor) throws CardException {
		if (wartosc >=2 && wartosc <=14) {
			
		} else {
			throw new CardException(1);
		}
	}
	
	public String toString() {
		return wartosc +" "+ kolor;
	}
	
	public int getWartosc() {
		return wartosc;
	}

	public void setWartosc(int wartosc) {
		this.wartosc = wartosc;
	}

	public char getZnak() {
		return znak;
	}

	public void setZnak(char znak) {
		this.znak = znak;
	}

	public String getKolor() {
		return kolor;
	}

	public void setKolor(String kolor) {
		this.kolor = kolor;
	}
	
}

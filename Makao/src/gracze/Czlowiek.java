package gracze;

import karty.Karta;

public class Czlowiek extends Gracz{

	@Override
	public boolean zmienKolor(Class<Karta> kolor) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean zadaj(int wartosc) { // ¿¹daj
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean wykonajRuch(Karta[] karty) {
		this.getGra().wykonajRuch(this, karty);
		return false;
	}

}

package gracze;

import karty.Karta;

public class Czlowiek extends Gracz{

	@Override
	public boolean zmienKolor(Class<Karta> kolor) {
		return this.getGra().zmienKolor(this, kolor);
	}

	@Override
	public boolean zadaj(int wartosc) { // ¿¹daj
		return this.getGra().zadaj(this, wartosc);
	}

	@Override
	public boolean wykonajRuch(Karta[] karty) {
		return this.getGra().wykonajRuch(this, karty);
	}

}

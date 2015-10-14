package gracze;

import java.util.List;

import karty.Karta;

public class Czlowiek extends Gracz{
	
	public Czlowiek() {
		super();
	}

	@Override
	public boolean zmienKolor(Class<Karta> kolor) {
		return this.getGra().zmienKolor(this, kolor);
	}

	@Override
	public boolean zadaj(int wartosc) { // ¿¹daj
		return this.getGra().zadaj(this, wartosc);
	}

	@Override
	public boolean wykonajRuch(List<Karta> karty) {
		return this.getGra().wykonajRuch(this, karty);
	}

}

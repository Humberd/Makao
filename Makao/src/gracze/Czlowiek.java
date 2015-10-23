package gracze;

import java.util.List;

import exceptions.ArbiterException;
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
	public boolean wykonajRuch() throws ArbiterException {
		return this.getGra().wykonajRuch(this, this.getWybraneKarty());
	}

	@Override
	public boolean pobierzKarte() {
		Karta karta = this.getGra().pobierzKarte(this);
		if (karta != null){
			this.dajKarteDoReki(karta);
			return true;
		}
		return false;
	}

}

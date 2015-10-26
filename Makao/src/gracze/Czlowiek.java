package gracze;

import java.util.List;

import exceptions.ArbiterException;
import karty.Karta;

public class Czlowiek extends Gracz{
	
	public Czlowiek() {
		super();
	}
	
	public Czlowiek(String nickname) {
		super(nickname);
	}

	@Override
	public boolean zmienKolor(String kolor) {
		return this.getGra().zmienKolor(this, kolor);
	}

	@Override
	public boolean zadaj(int wartosc) { // ¿¹daj
		return this.getGra().zadaj(this, wartosc);
	}
	@Override
	public boolean rzucKarte() throws ArbiterException {
		return this.getGra().rzucKarte(this, this.getReka().get(this.getIndeksWybranejKartyWRece()));
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

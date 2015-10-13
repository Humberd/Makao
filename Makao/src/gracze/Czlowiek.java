package gracze;

import karty.Karta;

public class Czlowiek extends Gracz{

	@Override
	public boolean zmienKolor(Class<Karta> kolor) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean zadaj(int wartosc) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean rzucKarty(Karta[] karty) {
		System.out.println("zaladzi");
		return false;
	}

}

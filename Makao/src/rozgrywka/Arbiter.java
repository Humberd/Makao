package rozgrywka;

import java.util.List;

import exceptions.ArbiterException;
import karty.Karta;

/**
 * @author Sawik
 *	Og�lnie rzecz bior�c, to tutaj Arbiter sprawdza, czy ruchy s� dozwolone.
 */
public class Arbiter {
	/**
	 * Pole przechowuj�ce instancj� gry
	 */
	private Gra gra;
	
	/**
	 * Karta, na kt�r� trzeba po�o�y� inn� kart�
	 */
	private Karta kartaBazowa;

	public Arbiter(Gra gra) {
		this.gra = gra;
	}
	
	public Arbiter() throws ArbiterException{
		throw new ArbiterException(1);
	}
	
	/**
	 * @param karty - lista kart w kolejno�ci: pierwsza pozycja na li�cie oznacza pierwsz� kart�, kt�r� k�adzie si� na pocz�tku, ostatnia - t�, kt�ra b�dzie na samej g�rze stosu
	 * @return true - ruch jest dozwolony
	 * @return false - ruch jest nie dozwolony
	 * @throws ArbiterException 
	 */
	public boolean czyRuchJestDozwolony(Karta karta) {
		//jesli arbiter nie dosta� �adnej karty do sprawdzenia
		if (karta == null) return false;
		
		//pobieram z talii kart z gry ostatni� kart� na kupce u�ytych kart
		kartaBazowa = gra.getTalia().peekUzyteKarty();
		
		
		/////////////////////////////////////////////
		//jesli klade Q bite
		if (karta.getWartosc() == 12) {
			//jesli to damy bite
			if ((karta.getKolor().equals("Pik") || karta.getKolor().equals("Kier"))) {
				//jesli zgadza sie wartosc, albo kolor, czyli dama na dame, albo pik na pik oraz nikt nie zada dam nie bitych
				if ((karta.getWartosc() == kartaBazowa.getWartosc() || karta.getKolor().equals(kartaBazowa.getKolor()))  && gra.getZadanie() != 12){
					return true;
				}
				//jesli ktos cos zada, i nikt nie spelnil jeszcze zadania, to moge przyblokowac
				else if (gra.getZadanie() != 0 && kartaBazowa.getWartosc() == 11) {
					return true;
				}
				//jesli ktos cos zada i spelniono juz choc 1 zadanie, to nie moge rzucic
				else if (gra.getZadanie() !=0 && kartaBazowa.getWartosc() != 11) {
					return false;
				}
				//blokuje siorbanie
				else if (gra.getIleKartDoPobrania() != 0) {
					return true;
				}
				//blokuje stanie
				else if (gra.getIleKolejekTrzebaStac() !=0) {
					return true;
				}
			}
		}
		//jesli rzucam na 4, ktora jest mocna
		if (gra.getIleKolejekTrzebaStac() !=0) {
			if (karta.getWartosc() == 4) {
				return true;
			} else {
				return false;
			}
		}
		//byly jakies 2 lub 3 lub K
		if (gra.getIleKartDoPobrania() != 0) {
			//jesli rzucilem 2, to teraz moge rzucic tylko 2
			if (karta.getWartosc() == kartaBazowa.getWartosc()) {
				return true;
			}
			//jesli kolory kart sie zgadzaja, to moze rzucic tylko 2, 3 albo krol
			else if (karta.getKolor().equals(kartaBazowa.getKolor())) {
				if (karta.getWartosc() == 2 || karta.getWartosc() ==3) {
					return true;
				} else if (karta.getWartosc() == 13 &&(karta.getKolor().equals("Pik") || karta.getKolor().equals("Kier"))) {
					return true;
				} else {
					return false;
				}
			}
		}
		//jesli ktos cos ��da
		if (gra.getZadanie() !=0) {
			//jesli karta ma taka sama wartosc jak ��danie
			if (gra.getZadanie() == karta.getWartosc()) {
				//jesli karta jest dam�, albo kr�lem
				if (karta.getWartosc() == 12 || karta.getWartosc() ==13) {
					//jesli karta jest kr�lem, albo dam� NIE bit�
					if (karta.getKolor().equals("Trefl") || karta.getKolor().equals("Karo")){
						return true;
					} 
					//jesli karta jest kr�lem, albo dam� bit�
					else {
						return false;
					}
				}
				//dla pozostalych kart
				else {
					return true;
				}
			}
			//jesli jestem jopkiem
			else if (karta.getWartosc() == 11) {
				//jesli karta na ktora klade jest jopkiem, to moge rzucic jopka
				if (kartaBazowa.getWartosc() == 11) {
					return true;
				} 
				//jesli ktos juz cos polozyl
				else {
					return false;
				}
			}
			// jesli karta ma inna wartosc niz ��danie
			else {
				return false;
			}
		}
		//jesli ktos chce zmiany koloru
		if (gra.getZmianaKoloru() != null) {
			// to moge tylko rzucic zadany kolor
			if (karta.getKolor().equals(gra.getZmianaKoloru())) {
				return true;
			}
			//
			else {
				return false;
			}
		}
		//5 na wszystko, wszystko na 
		//tu nie mam zadnych wiecej warunkow, bo wczesniejsze ify posprawdzaly inne mozliwosci. tutaj zostaly tylko �cinki
		if (karta.getWartosc() == 5 && gra.getZmianaKoloru() == null && gra.getZadanie() == 0 && gra.getIleKartDoPobrania() == 0 && gra.getIleKolejekTrzebaStac() == 0) {
			return true;
		}
		if (kartaBazowa.getWartosc() == 5) {
			return true;
		}
		
		//dla pozostalych
		if (kartaBazowa.getWartosc() == karta.getWartosc() || kartaBazowa.getKolor().equals(karta.getKolor())) {
			return true;
		}
		return false;
	}
}

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
	public boolean czyRuchJestDozwolony(List<Karta> karty) throws ArbiterException {
		//jesli arbiter nie dosta� �adnej karty do sprawdzenia
		if (karty.isEmpty()) throw new ArbiterException(0);
		
		//pobieram z talii kart z gry ostatni� kart� na kupce u�ytych kart
		kartaBazowa = gra.talia.peekUzyteKarty();
		
		if (karty.get(0).getWartosc() == this.kartaBazowa.getWartosc() || karty.get(0).getClass() == this.kartaBazowa.getClass()) {
			return true;
		} else {
			return false;
//			throw new ArbiterException(2);
		}
	}
}

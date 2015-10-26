package rozgrywka;

import java.util.List;

import exceptions.ArbiterException;
import karty.Karta;

/**
 * @author Sawik
 *	Ogólnie rzecz bior¹c, to tutaj Arbiter sprawdza, czy ruchy s¹ dozwolone.
 */
public class Arbiter {
	/**
	 * Pole przechowuj¹ce instancjê gry
	 */
	private Gra gra;
	
	/**
	 * Karta, na któr¹ trzeba po³o¿yæ inn¹ kartê
	 */
	private Karta kartaBazowa;

	public Arbiter(Gra gra) {
		this.gra = gra;
	}
	
	public Arbiter() throws ArbiterException{
		throw new ArbiterException(1);
	}
	
	/**
	 * @param karty - lista kart w kolejnoœci: pierwsza pozycja na liœcie oznacza pierwsz¹ kartê, któr¹ k³adzie siê na pocz¹tku, ostatnia - t¹, która bêdzie na samej górze stosu
	 * @return true - ruch jest dozwolony
	 * @return false - ruch jest nie dozwolony
	 * @throws ArbiterException 
	 */
	public boolean czyRuchJestDozwolony(Karta karta) {
		//jesli arbiter nie dosta³ ¿adnej karty do sprawdzenia
		if (karta == null) return false;
		
		//pobieram z talii kart z gry ostatni¹ kartê na kupce u¿ytych kart
		kartaBazowa = gra.getTalia().peekUzyteKarty();
		
		return true;
	}
}

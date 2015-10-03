package exceptions;

public class CardException extends Exception {
	private static final long serialVersionUID = 1190467983072098461L;

	public CardException(int id) {
		switch(id) {
			case 0: System.err.println("Wyst�pi� b��d."); break;
			case 1: System.err.println("Warto�� karty jest niepoprawna."); break;
			case 2: System.err.println("Brak wartosci karty."); break;
			case 3: System.err.println("Z�y kolor karty."); break;
			default: System.err.println("Wyst�pi� b��d."); break;
		}
		printStackTrace();
	}
}

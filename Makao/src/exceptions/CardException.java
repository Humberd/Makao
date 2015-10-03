package exceptions;

public class CardException extends Exception {
	private static final long serialVersionUID = 1190467983072098461L;

	public CardException(int id) {
		switch(id) {
			case 0: System.err.println("Wyst¹pi³ b³¹d."); break;
			case 1: System.err.println("Wartoœæ karty jest niepoprawna."); break;
			default: System.err.println("Wyst¹pi³ b³¹d."); break;
		}
	}
}

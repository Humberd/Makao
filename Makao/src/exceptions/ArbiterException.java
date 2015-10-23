package exceptions;

public class ArbiterException extends Exception{
	private static final long serialVersionUID = -4338416543965119570L;

	public ArbiterException(int id) {
		super();
		switch(id) {
		case 0: System.err.println("Rêka jest pusta."); break;
		case 1: System.err.println("Tworzenie Arbirta bez gry."); break;
		case 2: System.err.println("Karta ma z³y kolor, albo wartoœæ"); break;
		case 3: System.err.println(""); break;
		default: System.err.println("Wyst¹pi³ b³¹d - b³êdny kod b³êdu"); break;
	}
		
	}
}

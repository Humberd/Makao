package exceptions;

public class PlayerException extends Exception{
	private static final long serialVersionUID = 3448758430074855285L;
	
	public PlayerException(int id) {
		super();
		switch (id) {
			case 1: System.err.println("bllablblbl TODO");
		}
	}

}

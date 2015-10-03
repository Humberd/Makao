package karty;

import java.util.Stack;

import exceptions.CardException;

public class Talia extends Stack<Karta>{

	private static final long serialVersionUID = 1923695545987559264L;
	
	/**
	 * Tablica 52-elementowa zawieraj¹ca obiekty klasy Karta
	 */
	private Karta[] talia = new Karta[52];
	
	/**
	 * Konstruktor tworz¹cy taliê kart.
	 * @throws CardException
	 */
	public Talia () throws CardException {
		int counter = 0;
		for (int i = 0; i<52; i++) {
			if (counter == 0)  this.talia[i] =new Pik((i%13)+2);
			else if (counter == 1)  this.talia[i] =new Trefl((i%13)+2);
			else if (counter == 2)  this.talia[i] =new Karo((i%13)+2);
			else if (counter == 3)  this.talia[i] =new Kier((i%13)+2);
			if (i%13 == 12) counter++;
		}
	}
	
	public Karta[] getTalia() {
		return talia;
	}
	
	/**
	 * Drukuje na ekran taliê kart
	 */
	public void printTalia() {
		for (int i =0; i<52; i++){
			System.out.print(this.talia[i]+", ");
			if (i%13 == 12 ) System.out.println(" ");
		}
	}
	

}

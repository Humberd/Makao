package karty;

import java.util.*;

import exceptions.CardException;

public class Talia{
	/**
	 * 52 elementowy stos kart
	 */
	private Stack<Karta> talia = new Stack<Karta>();
	
	/**
	 * Konstruktor tworz¹cy taliê kart.
	 * @throws CardException
	 */
	public Talia () throws CardException {
		int counter = 0;
		for (int i = 0; i<52; i++) {
			if (counter == 0)  this.talia.push(new Pik((i%13)+2));
			else if (counter == 1)  this.talia.push(new Trefl((i%13)+2));
			else if (counter == 2)  this.talia.push(new Karo((i%13)+2));
			else if (counter == 3)  this.talia.push(new Kier((i%13)+2));
			if (i%13 == 12) counter++;
		}
	}
	/**
	 * Tasowanie talii.
	 */
	public void tasuj() {
		long seed = System.nanoTime();
		Collections.shuffle(this.talia, new Random(seed));
		Collections.shuffle(this.talia, new Random(seed));
	}
	
	public Stack<Karta> getTalia() {
		return talia;
	}
	
	/**
	 * Drukuje na ekran taliê kart
	 */
	public void printTalia() {
		@SuppressWarnings("unchecked")
		Stack<Karta> talia =  (Stack<Karta>) this.talia.clone();
		for (int i =0; i<52; i++){
			System.out.print(talia.pop()+", ");
			if (i%13 == 12 ) System.out.println();
		}
	}
	

}

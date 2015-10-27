package karty;

import java.util.*;

import exceptions.CardException;

public class Talia{
	/**
	 * 52 elementowy stos kart
	 */
	private Stack<Karta> talia = new Stack<Karta>();
	
	/**
	 * stos zu¿ytych kart
	 */
	private Stack<Karta> uzyteKarty = new Stack<Karta>();
	
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
	/**
	 * Przeklada karty ze stosu uzytych kart do talii, któr¹ bêdzie siê graæ
	 */
	public void przelozUzyteKartyDoTalii() {
		this.talia.addAll(this.uzyteKarty);
		this.uzyteKarty.clear();
	}
	
	public Stack<Karta> getTalia() {
		return this.talia;
	}
	
	public Stack<Karta> getUzyteKarty() {
		return this.uzyteKarty;
	}
	
	/**
	 * Drukuje na ekran taliê kart
	 */
	public String printTalia() {
//		@SuppressWarnings("unchecked")
//		Stack<Karta> talia =  (Stack<Karta>) this.talia.clone();
//		int taliaSize = talia.size();
//		for (int i =0; i<taliaSize; i++){
//			System.out.print(talia.pop()+", ");
//			if (i%13 == 12 || i == taliaSize-1) System.out.println();
//		}
		Stack<Karta> tempStos = (Stack<Karta>) this.talia.clone();
		Collections.reverse(tempStos);
		return tempStos.toString();
	}
	public String printUzyteKarty() {
//		@SuppressWarnings("unchecked")
//		Stack<Karta> talia =  (Stack<Karta>) this.uzyteKarty.clone();
//		int taliaSize = talia.size();
//		for (int i =0; i<taliaSize; i++){
//			System.out.print(talia.pop()+", ");
//			if (i%13 == 12  || i == taliaSize-1) System.out.println();
//		}
		Stack<Karta> tempStos = (Stack<Karta>) this.uzyteKarty.clone();
		Collections.reverse(tempStos);
		return tempStos.toString();
	}
///////////////////////////////////////////////////////////////////////////
	public Karta popTalia() {
		//jesli zabraknie w talii kart
		try {
			return this.talia.pop();
		} catch (Exception e) {
			//jesli sa karty w uzywanych kartach to sproboje przerzucic
			try {
				przelozUzyteKartyDoTalii();
				pushUzyteKarty(this.talia.remove(this.talia.size()-1));
				tasuj();
				return this.talia.pop();
			} 
			//jesli jest tylko jedna karta i to na stosie uzywanych to zwraca nic
			catch (Exception e1) {
				return null;
			}
		}
	}
	
	public void pushTalia(Karta karta) {
		this.talia.push(karta);
	}
	
	public int sizeTalia() {
		return this.talia.size();
	}
	
	public Karta peekTalia() {
		return this.talia.peek();
	}
	
	public boolean emptyTalia() {
		return this.talia.empty();
	}
//////////////////////////////////////////////////////////////////////////////
	public Karta popUzyteKarty() {
		return this.uzyteKarty.pop();
	}
	
	public void pushUzyteKarty(Karta karta) {
		this.uzyteKarty.push(karta);
	}
	
	public int sizeUzyteKarty() {
		return this.uzyteKarty.size();
	}
	
	public Karta peekUzyteKarty() {
		return this.uzyteKarty.peek();
	}
	
	public boolean emptyUzyteKarty() {
		return this.uzyteKarty.empty();
	}
	
	

}

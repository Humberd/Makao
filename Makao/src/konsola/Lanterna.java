package konsola;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.ScreenCharacterStyle;
import com.googlecode.lanterna.screen.ScreenWriter;
import com.googlecode.lanterna.terminal.Terminal;

import rozgrywka.Gra;

public class Lanterna {
	Screen screen;
	
	public Lanterna() {
		test1();
	}
	
	public Lanterna(Gra gra) {
		this.screen = TerminalFacade.createScreen();
		this.screen.startScreen();
		this.ekranPowitalny();
		ScreenWriter sw = new ScreenWriter(this.screen);
		Karty karty = new Karty();
		String[] temp = karty.getKarta("A","Pik");
		for (int i = 0; i < temp.length; i++) {
			sw.drawString(5, 2+i, temp[i]);
		}
		screen.refresh();
	}
	
	private void ekranPowitalny() {
		
	}
	public void test1() {
		Screen screen = TerminalFacade.createScreen();
		screen.startScreen();
		ScreenWriter writer = new ScreenWriter(screen);
		int y = 0;
		
		boolean running = true;
		
		while (running) {
			Key key = screen.readInput();
			
			while (key == null) {
				key = screen.readInput();
			}
			
			if (key.getKind() == Key.Kind.Escape) {
				screen.stopScreen();
				System.exit(0);
			}
			System.out.println("Key pressed: " +key.getKind().toString());
//			screen.stopScreen();
//			System.exit(0);
			
		}
		
		
//		Thread.currentThread();
//		terminal.enterPrivateMode();
//		
//		terminal.exitPrivateMode();
	}
}
 
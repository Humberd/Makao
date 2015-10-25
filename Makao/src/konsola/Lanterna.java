package konsola;

import javax.swing.WindowConstants;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.gui.GUIScreen;
import com.googlecode.lanterna.gui.Window;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.ScreenCharacterStyle;
import com.googlecode.lanterna.screen.ScreenWriter;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.Terminal.ResizeListener;
import com.googlecode.lanterna.terminal.TerminalSize;
import com.googlecode.lanterna.terminal.swing.SwingTerminal;

import rozgrywka.Gra;

public class Lanterna {
	Screen screen;
	Gra gra;
	SwingTerminal swing;
	
	
	public Lanterna() {
		test1();
	}
	
	public Lanterna(Gra gra) {
		this.gra = gra;
		this.swing = TerminalFacade.createSwingTerminal(120, 30);
		this.screen = new Screen(this.swing);
		this.screen.startScreen();
		this.swing.getJFrame().setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.swing.getJFrame().setResizable(false);
		
		ScreenWriter sw = new ScreenWriter(this.screen);
		String[] temp = Karty.getKarta("A","Kier");
//		temp = OknoDialogowe.oknoPowitalne();
		for (int i = 0; i < temp.length; i++) {
			sw.drawString(5, 2+i, temp[i]);
		}
		screen.refresh();
		
		
//		this.gui.getScreen().stopScreen();
		
//		System.out.println(window.getWindowSizeOverride());
//		this.screen = TerminalFacade.createScreen();
//		this.screen.startScreen();
//		this.screen.getTerminal().addResizeListener(new ResizeListener() {
//			
//			public void onResized(TerminalSize newSize) {
//				System.out.println("dupa");
//				
//			}
//		});
//		System.out.println(this.screen.getTerminalSize());
//		
//		ScreenWriter sw = new ScreenWriter(this.screen);
//		String[] temp = Karty.getKarta("A","Kier");
////		temp = OknoDialogowe.oknoPowitalne();
//		for (int i = 0; i < temp.length; i++) {
//			sw.drawString(5, 2+i, temp[i]);
//		}
//		screen.refresh();
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
 
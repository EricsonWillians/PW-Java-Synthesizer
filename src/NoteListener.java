import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class NoteListener implements KeyListener  {

	private int keyCode;
	private float noteFrequency;
	
	NoteListener(int key, float note) {
		keyCode = key;
		noteFrequency = note;
	}
	
	public void keyPressed(KeyEvent arg0) {

		if (arg0.getKeyCode() == keyCode) {
			Main.play(noteFrequency);
			System.out.println(keyCode);
		}
		
	}

	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}

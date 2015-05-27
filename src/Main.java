import java.awt.BorderLayout;
import java.awt.DefaultKeyboardFocusManager;
import java.awt.EventQueue;
import java.awt.KeyEventPostProcessor;
import java.awt.Toolkit;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.jsyn.unitgen.LineOut;
import com.jsyn.unitgen.SineOscillator;
import com.jsyn.unitgen.WhiteNoise;

import java.awt.GridLayout;

import javax.swing.JButton;

import java.awt.Font;
import java.awt.event.AWTEventListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JSlider;

public class Main extends JFrame {

	private static final long serialVersionUID = 1L;
	
	public static final float c0 = (float) 16.35;
	public static final float cSharp0 = (float) 17.32;
	public static final float d0 = (float) 18.35;
	public static final float dSharp0 = (float) 19.45;
	public static final float e0 = (float) 20.60;
	public static final float f0 = (float) 21.83;
	public static final float fSharp0 = (float) 23.12;
	public static final float g0 = (float) 24.50;
	public static final float gSharp0 = (float) 25.96;
	public static final float a0 = (float) 27.50;
	public static final float aSharp0 = (float) 29.14;
	public static final float b0 = (float) 30.87;
	
	public static final float[] notes = {c0, cSharp0, d0, dSharp0, e0, f0, 
											fSharp0, g0, gSharp0, a0, aSharp0, b0};
	
	public static final int[] keys = {KeyEvent.VK_Q, KeyEvent.VK_W, KeyEvent.VK_E, 
									  KeyEvent.VK_R, KeyEvent.VK_T, KeyEvent.VK_Y,
									  KeyEvent.VK_A, KeyEvent.VK_S, KeyEvent.VK_D,
									  KeyEvent.VK_F, KeyEvent.VK_G, KeyEvent.VK_H};
	
	public static SineOscillator osc;
//	public static WhiteNoise noise;
	public static LineOut out1;
	public static LineOut out2;
	
	public int octave;
	
	private JPanel contentPane;

	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
				
	}

	public Main() {
		
		setTitle("PW Java Synthesizer 1.02 (By Ericson Willians)");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.addKeyListener(new NoteListener(KeyEvent.VK_Z, 300));
		setContentPane(contentPane);
		
		JPanel mainPanel = new JPanel();
		contentPane.add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
		
		JPanel notesPanel = new JPanel();
		mainPanel.add(notesPanel);
		notesPanel.setLayout(new GridLayout(0, 12, 5, 5));
		

		octave = 0;		
		
		JButton cNote = new JButton("C0");
		notesPanel.add(cNote);
		cNote.setBackground(Color.WHITE);
		cNote.setFont(new Font("Arial", Font.BOLD, 11));
		cNote.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				play(getOctaveFrequency(c0));
			}
			
		});
		
		JButton cSharpNote = new JButton("C0#");
		cSharpNote.setForeground(Color.WHITE);
		cSharpNote.setBackground(Color.BLACK);
		cSharpNote.setFont(new Font("Arial", Font.BOLD, 11));
		notesPanel.add(cSharpNote);
		cSharpNote.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				play(getOctaveFrequency(cSharp0));
			}
			
		});
		
		JButton dNote = new JButton("D0");
		dNote.setBackground(Color.WHITE);
		dNote.setFont(new Font("Arial", Font.BOLD, 11));
		notesPanel.add(dNote);
		dNote.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				play(getOctaveFrequency(d0));
			}
			
		});
		
		JButton dSharpNote = new JButton("D0#");
		dSharpNote.setForeground(Color.WHITE);
		dSharpNote.setBackground(Color.BLACK);
		dSharpNote.setFont(new Font("Arial", Font.BOLD, 11));
		notesPanel.add(dSharpNote);
		dSharpNote.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				play(getOctaveFrequency(dSharp0));
			}
			
		});
		
		JButton eNote = new JButton("E0");
		eNote.setBackground(Color.WHITE);
		eNote.setFont(new Font("Arial", Font.BOLD, 11));
		notesPanel.add(eNote);
		eNote.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				play(getOctaveFrequency(e0));
			}
			
		});
		
		JButton fNote = new JButton("F0");
		fNote.setBackground(Color.WHITE);
		fNote.setFont(new Font("Arial", Font.BOLD, 11));
		notesPanel.add(fNote);
		fNote.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				play(getOctaveFrequency(f0));
			}
			
		});

		JButton fSharpNote = new JButton("F0#");
		fSharpNote.setForeground(Color.WHITE);
		fSharpNote.setBackground(Color.BLACK);
		fSharpNote.setFont(new Font("Arial", Font.BOLD, 11));
		notesPanel.add(fSharpNote);
		fSharpNote.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				play(getOctaveFrequency(fSharp0));
			}
			
		});
		
		JButton gNote = new JButton("G0");
		gNote.setBackground(Color.WHITE);
		gNote.setFont(new Font("Arial", Font.BOLD, 11));
		notesPanel.add(gNote);
		gNote.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				play(getOctaveFrequency(g0));
			}
			
		});
		
		JButton gSharpNote = new JButton("G0#");
		gSharpNote.setForeground(Color.WHITE);
		gSharpNote.setBackground(Color.BLACK);
		gSharpNote.setFont(new Font("Arial", Font.BOLD, 11));
		notesPanel.add(gSharpNote);
		gSharpNote.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				play(getOctaveFrequency(gSharp0));
			}
			
		});
		
		JButton aNote = new JButton("A0");
		aNote.setBackground(Color.WHITE);
		aNote.setFont(new Font("Arial", Font.BOLD, 11));
		notesPanel.add(aNote);
		aNote.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				play(getOctaveFrequency(a0));
			}
			
		});
		
		JButton aSharpNote = new JButton("A0#");
		aSharpNote.setForeground(Color.WHITE);
		aSharpNote.setBackground(Color.BLACK);
		aSharpNote.setFont(new Font("Arial", Font.BOLD, 11));
		notesPanel.add(aSharpNote);
		aSharpNote.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				play(getOctaveFrequency(aSharp0));
			}
			
		});
		
		JButton bNote = new JButton("B0");
		bNote.setBackground(Color.WHITE);
		bNote.setFont(new Font("Arial", Font.BOLD, 11));
		notesPanel.add(bNote);
		bNote.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				play(getOctaveFrequency(b0));
			}
			
		});
		bNote.addKeyListener(new NoteListener(KeyEvent.VK_G, getOctaveFrequency(b0)));
		
		final ArrayList<JButton> buttons = new ArrayList<JButton>();
		buttons.add(cNote); buttons.add(cSharpNote); buttons.add(dNote);
		buttons.add(dSharpNote); buttons.add(eNote); buttons.add(fNote);
		buttons.add(fSharpNote); buttons.add(gNote); buttons.add(gSharpNote);
		buttons.add(aNote); buttons.add(aSharpNote); buttons.add(bNote);
		
		final JSlider octaveSlider = new JSlider();
		octaveSlider.setValue(0);	
		octaveSlider.setMaximum(8);
		contentPane.add(octaveSlider, BorderLayout.SOUTH);
		octaveSlider.addChangeListener(new ChangeListener() {

			public void stateChanged(ChangeEvent arg0) {
				octave = octaveSlider.getValue();
				for (JButton e : buttons) {
					e.setText(e.getText().replaceAll("(-)?\\d+(\\.\\d*)?", Integer.toString(octave)));
				}
			}
			
		});
		
		KeyEventPostProcessor pp = new KeyEventPostProcessor() {
			
		    public boolean postProcessKeyEvent(KeyEvent e) {
		        for (int i = 0; i < 12; i++) {
		        	if (e.getKeyCode() == keys[i]) {
		        		play(getOctaveFrequency(notes[i]));
		        	}
		        }
		        return true;
		    }
		    
		};

		DefaultKeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventPostProcessor(pp);
		
	}
	
	public float getOctaveFrequency(float note) {
		
		float octaveFrequency = note;
		
		if (octave == 1) {
			octaveFrequency = note * 2;
		} else if (octave == 2) {
			octaveFrequency = note * 2 * 2;
		} else if (octave == 3) {
			octaveFrequency = note * 2 * 2 * 2;
		} else if (octave == 4) {
			octaveFrequency = note * 2 * 2 * 2 * 2;
		} else if (octave == 5) {
			octaveFrequency = note * 2 * 2 * 2 * 2 * 2;
		} else if (octave == 6) {
			octaveFrequency = note * 2 * 2 * 2 * 2 * 2 * 2;
		} else if (octave == 7) {
			octaveFrequency = note * 2 * 2 * 2 * 2 * 2 * 2 * 2;
		} else if (octave == 8) {
			octaveFrequency = note * 2 * 2 * 2 * 2 * 2 * 2 * 2 * 2;
		}
		
		return octaveFrequency;
		
	}
	
	public static void play(float noteFrequency) {
	
		SynthThread synth = new SynthThread(noteFrequency);
		synth.start();
		
	}

}

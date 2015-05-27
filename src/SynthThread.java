import com.jsyn.JSyn;
import com.jsyn.Synthesizer;
import com.jsyn.unitgen.LineOut;
import com.jsyn.unitgen.SineOscillator;


public class SynthThread implements Runnable {

	private Thread t;
	private float noteFrequency;
	
	SynthThread(float freq) {
		noteFrequency = freq;
	}
	
	public void run() {
		
		Synthesizer synth = JSyn.createSynthesizer();
		
		SineOscillator osc = new SineOscillator();
		LineOut out1 = new LineOut();
		LineOut out2 = new LineOut();
		
		synth.start();

		synth.add(out1);
		synth.add(out2);
		synth.add(osc);
		
		osc.output.connect(0, out1.input, 0);
		osc.output.connect(0, out2.input, 1);
		
		out1.start();
		out2.start();
		osc.start();
		
		osc.frequency.set(noteFrequency);
		
		try {
			synth.sleepFor( 0.5 );
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		synth.stop();
		out1.stop();
		out2.stop();
		
	}
	
	public void start() {
		
		if (t == null) {
			
			t = new Thread(this, "Synth Thread");
			t.start();
			
		}
		
	}
	
	public Thread getThread() {
		
		return t;
				
	}

}

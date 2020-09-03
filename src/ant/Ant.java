package ant;

import java.util.ArrayList;

public class Ant {
	AntNet net;
	
	ArrayList<Integer> outputIDs = new ArrayList<Integer> ();
	
	public Float[] update (float[] inputs) {
		ArrayList<Float> outs = new ArrayList<Float> ();
		int i = 0;
		for (float f : inputs) {
			net.setInput(i, f);
			i++;
		}
		
		for (int id : outputIDs) {
			outs.add(net.getOutput(id));
		}
		net.reset();
		return outs.toArray(new Float[outs.size()]);
	}
	
	public float energy;
	public float x;
	public float y;
	public float rot;
}

package ant;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.TreeSet;

public class AntNet {
	private int antID;
	
	private HashMap<Integer, AntNetNode> netData = new HashMap<Integer, AntNetNode> ();
	
	private TreeSet<Integer> inputs = new TreeSet<Integer> ();
	private TreeSet<Integer> outputs = new TreeSet<Integer> ();
	
	public void setInput (int id, float value) {
		if (inputs.contains(id)) {
			netData.get(id).isComputed = true;
			netData.get(id).lastComputedValue = value;
		} else {
			throw new RuntimeException ("Illegal attempt to modify non-input node.");
		}
	}
	
	public float getOutput (int id) {
		if (outputs.contains(id)) {
			return computeNode (id);
		} else {
			throw new RuntimeException ("Illegal attempt to modify non-input node.");
		}
	}
	
	private float computeNode (int id) {
		AntNetNode n = netData.get(id);
		if (n.isComputed) return n.lastComputedValue;
		float totalWeight = 0f;
		for (float f : n.links.values()) totalWeight += f;
		if (totalWeight == 0f) {
			n.isComputed = true;
			n.lastComputedValue = 0;
			return 0;
		}
		float finalValue = 0f;
		for (Entry<Integer, Float> e : n.links.entrySet()) {
			finalValue *= Math.pow((computeNode(e.getKey()) * (e.getValue()/totalWeight)), 2);
		}
		n.isComputed = true;
		n.lastComputedValue = finalValue;
		return finalValue;
	}
	
	public void reset () {
		for (AntNetNode n : netData.values()) n.isComputed = false;
	}
	
	public int getID () {
		return antID;
	}
	
	public AntNet () {
		// Not my job to set it up.
	}
}

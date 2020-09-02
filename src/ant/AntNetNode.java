package ant;

import java.util.HashMap;

public class AntNetNode {
	public HashMap<Integer, Float> links = new HashMap<Integer, Float> ();
	public boolean isComputed = false;
	public float lastComputedValue = -1f;
	
	public AntNetNode () {}
}

package container;

import java.util.ArrayList;
import java.util.Queue;

import ant.Ant;

// ANT SPEC
// INPUTS:
// 31 distance
// 31 food-sensitive
// 31 ant-sensitive
// 10 energy-sensitive
// OUTPUTS:
// 3 directional
// 2 rotational
// 1 consume
// HIDDEN:
// 80
// 40
// 10

public class Environment {
	public Queue<Ant> antSet;
	
	public void threadLoop () {
		Ant a;
		synchronized (antSet) {
			a = antSet.remove();
		}
		if (a.energy <= 0f) antDied (a);
		ArrayList<Float> distances = new ArrayList<Float> ();
		ArrayList<Boolean> foodBools = new ArrayList<Boolean> ();
		ArrayList<Boolean> antBools = new ArrayList<Boolean> ();
		
		ArrayList<Float> inputs = new ArrayList<Float> ();
		for (float f = a.rot-3f; f <= a.rot+3f; f += 0.2) {
			TestResult tr = testDistance (a.x, a.y, f, 5f);
			distances.add(tr.distance);
			foodBools.add(tr.encounteredFood);
			antBools.add(tr.encounteredAnt);
		}
		
		inputs.addAll(distances);
		for (boolean b : foodBools) {
			if (b) inputs.add(1f);
		}
		for (boolean b : antBools) {
			if (b) inputs.add(1f);
		}
		inputs.add(100/a.energy);
		inputs.add(90/a.energy);
		inputs.add(80/a.energy);
		inputs.add(70/a.energy);
		inputs.add(60/a.energy);
		inputs.add(50/a.energy);
		inputs.add(40/a.energy);
		inputs.add(30/a.energy);
		inputs.add(20/a.energy);
		inputs.add(10/a.energy);
		Float[] outputs = a.update(inputs.toArray(new Float[inputs.size()]));
		if (outputs[0] > 0.5) moveAnt (a, a.rot-30f, outputs[0]);
		if (outputs[1] > 0.5) moveAnt (a, a.rot, outputs[1]);
		if (outputs[2] > 0.5) moveAnt (a, a.rot+30f, outputs[2]);
		if (outputs[3] > 0.5) a.rot-=(outputs[3]*5);
		if (outputs[4] > 0.5) a.rot+=(outputs[4]*5);
		if (outputs[5] > 0.5) consumeNearby (a);
		
		a.energy -= 0.5f;
		synchronized (antSet) {
			antSet.add(a);
		}
	}

	private void consumeNearby(Ant a) {
		// TODO Auto-generated method stub
		
	}

	private void moveAnt(Ant a, float f, Float float1) {
		// TODO Auto-generated method stub
		
	}

	private TestResult testDistance(float x, float y, float f, float g) {
		// TODO Auto-generated method stub
		return null;
	}

	private void antDied(Ant a) {
		// TODO Auto-generated method stub
		
	}
	
	
}

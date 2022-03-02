package simulator.factories;

import org.json.JSONObject;

import simulator.model.DequeuingStrategy;
import simulator.model.MoveFirstStrategy;

public class MoveFirstStrategyBuilder extends Builder<DequeuingStrategy>{
	
	MoveFirstStrategyBuilder(){
		super("move_first_dqs");
	}
	@Override
	protected DequeuingStrategy createTheInstance(JSONObject data) {
		// TODO Auto-generated method stub
		MoveFirstStrategy first;
		first = new MoveFirstStrategy();
		return first;
	}

}

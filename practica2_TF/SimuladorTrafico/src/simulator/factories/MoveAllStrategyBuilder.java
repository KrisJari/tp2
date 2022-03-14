package simulator.factories;
import java.util.List;

import org.json.JSONObject;

import simulator.model.DequeuingStrategy;
import simulator.model.MoveAllStrategy;

public class MoveAllStrategyBuilder extends Builder<DequeuingStrategy>{
	
	public MoveAllStrategyBuilder(){
		super("move_all_dqs");
	}
	@Override
	protected DequeuingStrategy createTheInstance(JSONObject data) {
		// TODO Auto-generated method stub
		MoveAllStrategy all;
		all = new MoveAllStrategy();
		return all;
	}

}

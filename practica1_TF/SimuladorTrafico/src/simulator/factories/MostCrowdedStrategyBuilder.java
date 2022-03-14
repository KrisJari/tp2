package simulator.factories;

import java.util.List;

import org.json.JSONObject;

import simulator.model.LightSwitchingStrategy;
import simulator.model.MostCrowdedStrategy;

public class MostCrowdedStrategyBuilder extends Builder<LightSwitchingStrategy>{

	public MostCrowdedStrategyBuilder() {
		super("most_crowded_lss");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected LightSwitchingStrategy createTheInstance(JSONObject data) {
		// TODO Auto-generated method stub
		MostCrowdedStrategy crow;
		if(data.has("timeslot"))
			crow = new MostCrowdedStrategy(data.getInt("timeslot"));
		else
			crow = new MostCrowdedStrategy(1);
		return crow;
	}
	
	
}

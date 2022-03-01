package simulator.factories;

import org.json.JSONObject;

import simulator.model.LightSwitchingStrategy;

public class MostCrowdedStrategyBuilder extends Builder<LightSwitchingStrategy>{

	MostCrowdedStrategyBuilder() {
		super("most_crowded_lss");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected LightSwitchingStrategy createTheInstance(JSONObject data) {
		// TODO Auto-generated method stub
		return null;
	}

}

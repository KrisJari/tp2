package simulator.factories;

import org.json.JSONObject;

import simulator.model.LightSwitchingStrategy;
import simulator.model.RoundRobinStrategy;

public class RoundRobinStrategyBuilder extends Builder<LightSwitchingStrategy>{
	
	public RoundRobinStrategyBuilder(){
		super("round_robin_lss");
	}

	@Override
	protected LightSwitchingStrategy createTheInstance(JSONObject data) {
		// TODO Auto-generated method stub
		RoundRobinStrategy round;
		if (data.has("timeslot"))
			round = new RoundRobinStrategy(data.getInt("timeslot"));
		else
			round = new RoundRobinStrategy(1);
		
		return round;
	}
	
}

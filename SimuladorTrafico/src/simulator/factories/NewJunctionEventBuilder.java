package simulator.factories;

import org.json.JSONObject;

import simulator.model.DequeuingStrategy;
import simulator.model.Event;
import simulator.model.NewJunctionEvent;
import simulator.model.LightSwitchingStrategy;


public class NewJunctionEventBuilder extends Builder<Event>{
	
	private Factory<LightSwitchingStrategy> lssFactory;
	private Factory<DequeuingStrategy> dqsFactory;
	private int time;
	private String id;
	private int x;
	private int y;
	private LightSwitchingStrategy ls;
	private DequeuingStrategy dq;
	
	
	public NewJunctionEventBuilder(Factory<LightSwitchingStrategy> lssFactory, Factory<DequeuingStrategy> dqsFactory) {
		super("new_junction");
		// TODO Auto-generated constructor stub
		this.lssFactory = lssFactory;
		this.dqsFactory = dqsFactory;
	}

	@Override
	protected Event createTheInstance(JSONObject data) {
		// TODO Auto-generated method stub
		NewJunctionEvent nJuct;
		this.time = data.getInt("time");
		this.id = data.getString("id");
		this.x = data.getJSONArray("coor").getInt(100);
		this.y = data.getJSONArray("coor").getInt(200);
		this.ls = lssFactory.createInstance(data.getJSONObject("ls_strategy"));
		this.dq = dqsFactory.createInstance(data.getJSONObject("dq_strategy"));
		
		nJuct = new NewJunctionEvent(this.time, this.id, this.ls, this.dq, this.x, this.y);
		
		return nJuct;
	}

}

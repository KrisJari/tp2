package simulator.factories;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import simulator.model.Event;
import simulator.model.NewSetContClassEvent;
import simulator.misc.Pair;

public class SetContClassEventBuilder extends Builder<Event>{
	
	private int time;
	private List<Pair<String,Integer>> info;
	
	SetContClassEventBuilder() {
		super("set_cont_class");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Event createTheInstance(JSONObject data) {
		// TODO Auto-generated method stub
		NewSetContClassEvent sCont;
		
		this.time = data.getInt("time");
		JSONArray aux = data.getJSONArray("info");
		this.info = new ArrayList<Pair<String,Integer>>();
		
		for (int i = 0; i < aux.length(); i++) {
			info.add(new Pair<String,Integer>(aux.getJSONObject(i).getString("vehicle"), aux.getJSONObject(i).getInt("clas")));
		}
		
		sCont = new NewSetContClassEvent(time,info);
		
		return sCont;
	}

}
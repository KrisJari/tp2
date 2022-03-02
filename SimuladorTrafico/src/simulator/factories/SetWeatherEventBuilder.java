package simulator.factories;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import simulator.model.Event;
import simulator.model.SetWeatherEvent;
import simulator.model.Weather;
import simulator.misc.Pair;

public class SetWeatherEventBuilder extends Builder<Event>{
	
	private int time;
	private List<Pair<String, Weather>> info;
	
	SetWeatherEventBuilder() {
		super("set_weather");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Event createTheInstance(JSONObject data) {
		// TODO Auto-generated method stub
		SetWeatherEvent sWeather;
		this.time = data.getInt("time");
		
		JSONArray aux = data.getJSONArray("info");
		info = new ArrayList<Pair<String, Weather>>();
		for(int i = 0; i < aux.length(); i++) {
			
			info.add(new Pair<String, Weather>(aux.getJSONObject(i).getString("road"), Weather.valueOf(aux.getJSONObject(i).getString("weather"))));
		}
		
		sWeather = new SetWeatherEvent(time,info);
		
		return sWeather;
	}

}
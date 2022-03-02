package simulator.factories;

import org.json.JSONObject;

import simulator.model.Event;
import simulator.model.Junction;
import simulator.model.NewCityRoadEvent;
import simulator.model.Weather;

public class NewCityRoadEventBuilder extends Builder<Event> {
	
	private int time;
	private String id;
	private String src;
	private String dest;
	private int length;
	private int co2Limit;
	private int maxSpeed;
	private Weather weather;
	
	NewCityRoadEventBuilder() {
		super("new_city_road");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Event createTheInstance(JSONObject data) {
		// TODO Auto-generated method stub
		NewCityRoadEvent nCity;
		this.time = data.getInt("time");
		this.id = data.getString("id");
		this.src = data.getString("src");
		this.dest = data.getString("dest");
		this.length = data.getInt("length");
		this.co2Limit = data.getInt("co2limit");
		this.maxSpeed = data.getInt("maxspeed");
		this.weather = Weather.valueOf(data.getString("weather"));
		
		nCity = new NewCityRoadEvent(time,id,src,dest,length,co2Limit,maxSpeed,weather);
		
		return nCity;
	}

}

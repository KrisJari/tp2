package simulator.factories;

import org.json.JSONObject;

import simulator.model.Event;
import simulator.model.NewCityRoadEvent;
import simulator.model.NewInterCityRoadEvent;
import simulator.model.Weather;

public class NewInterCityRoadEventBuilder extends Builder<Event>{
	
	private int time;
	private String id;
	private String src;
	private String dest;
	private int length;
	private int co2Limit;
	private int maxSpeed;
	private Weather weather;
	
	NewInterCityRoadEventBuilder(){
		super("new_inter_city_road");
	}
	
	@Override
	protected Event createTheInstance(JSONObject data) {
		// TODO Auto-generated method stub
		NewInterCityRoadEvent inCity;
		this.time = data.getInt("time");
		this.id = data.getString("id");
		this.src = data.getString("src");
		this.dest = data.getString("dest");
		this.length = data.getInt("length");
		this.co2Limit = data.getInt("co2limit");
		this.maxSpeed = data.getInt("maxspeed");
		this.weather = Weather.valueOf(data.getString("weather"));
		
		inCity = new NewInterCityRoadEvent(time,id,src,dest,length,co2Limit,maxSpeed,weather);
		
		return inCity;
	}

}

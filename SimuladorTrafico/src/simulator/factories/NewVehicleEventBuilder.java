package simulator.factories;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import simulator.model.Event;
import simulator.model.NewVehicleEvent;

public class NewVehicleEventBuilder extends Builder<Event>{
	
	private int time;
	private String id;
	private int maxSpeed;
	private int contClass;
	private List<String> itinerary;
	

	NewVehicleEventBuilder() {
		super("new_vehicle");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Event createTheInstance(JSONObject data) {
		// TODO Auto-generated method stub
		NewVehicleEvent nVehicle;
		this.time = data.getInt("time");
		this.id = data.getString("id");
		this.maxSpeed = data.getInt("maxspeed");
		this.contClass = data.getInt("class");
		this.itinerary = new ArrayList<String>();
		
		JSONArray aux = data.getJSONArray("itinerary");
		for (int i = 0; i < aux.length(); i++) {
			this.itinerary.add(aux.getString(i));
		}
		
		nVehicle = new NewVehicleEvent(time,id,maxSpeed,contClass,itinerary);
		
		return nVehicle;
	}

}

package simulator.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.json.JSONArray;
import org.json.JSONObject;

public class RoadMap {
	
	protected List<Junction> junct;//lista de cruces
	protected List<Road> r; //lista de carreteras 
	protected List<Vehicle> vh;//lista de vehiculos 
	protected Map<String,Junction> cruces;//mapa de cruces 
	protected Map<String ,Road> roads;//mapa de carreteras 
	protected Map<String,Vehicle> vehicles;//mapa de vehiculos 
	
	public RoadMap(){
		this.junct = new ArrayList<Junction>();
		this.r = new ArrayList<Road>();
		this.vh = new ArrayList<Vehicle>();
		this.cruces = new TreeMap<String, Junction>();
		this.roads = new TreeMap<String, Road>();
		this.vehicles = new TreeMap<String, Vehicle>();
	}
	
	void addJunction(Junction j) {
		if (this.cruces.containsKey(j.getId())) 
			throw new IllegalArgumentException("Esta carretera ya existe");
		else {
			this.junct.add(j);

			this.cruces.put(j.getId(), j);
		}
	}
	
	void addRoad(Road r) {
		if (this.roads.containsKey(r.getId())) 
			throw new IllegalArgumentException("Esta carretera ya existe");
		else {
			this.r.add(r);
			this.roads.put(r.getId(), r);
			
		}
	}
	
	void addVehicle(Vehicle v) {
		if (this.vehicles.containsKey(v.getId()))
			throw new IllegalArgumentException("this vehicle already exits");
		for (int i = 0; i< v.getItinerary().size()-1;i++) {
			if(v.getItinerary().get(i).roadTo(v.getItinerary().get(i+1)) == null) {
				throw new IllegalArgumentException("road does not exit");
			}
		}

			this.vh.add(v);
			this.vehicles.put(v.getId(), v);
		
	}
	
	public Junction getJunction(String id) {
		if(this.cruces.containsKey(id))
			return this.cruces.get(id);
		else 
			return null;
	}
	
	public Road getRoad(String id) {
		if(this.roads.containsKey(id))
			return this.roads.get(id);
		else
			return null;
	}
	
	public Vehicle getVehicle(String id) {
		if (this.vehicles.containsKey(id))
			return this.vehicles.get(id);
		else
			return null;
	}
	
	public List<Junction> getJunctions(){
		return Collections.unmodifiableList(junct);	
	}
	
	public List<Road> getRoads(){
		return Collections.unmodifiableList(r);
	}
	
	public List<Vehicle> getVehicles(){
		return Collections.unmodifiableList(vh);
	}
	
	protected void reset() {
		this.junct.clear();
		this.r.clear();
		this.vh.clear();
		this.cruces.clear();
		this.roads.clear();
		this.vehicles.clear();
	}
	
	public JSONObject report() {
		JSONObject obj = new JSONObject();
		JSONArray junc = new JSONArray();
		JSONArray road = new JSONArray();
		JSONArray vehi = new JSONArray();
		
		
		obj.put("junctions", junc);
//		System.out.println(junct.size());
		for (Junction j : junct) {
			
			junc.put(j.report());
		}
		
		obj.put("roads", road);
		for (Road r : this.r) {
			road.put(r.report());
		}
		
		obj.put("vehicles", vehi);
		for(Vehicle v : this.vh) {
			vehi.put(v.report());
		}
		
		
		return obj;
	}

}


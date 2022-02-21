package simulator.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.json.JSONObject;

public class RoadMap {
	
	protected List<Junction> junct;//lista de cruces
	protected List<Road> r; //lista de carreteras 
	protected List<Vehicle> vh;//lista de vehiculos 
	protected Map<String,Junction> cruces;//mapa de cruces 
	protected Map<String ,Road> roads;//mapa de carreteras 
	protected Map<String,Vehicle> vehicles;//mapa de vehiculos 
	
	protected RoadMap(){
		this.junct = new ArrayList<Junction>();
		this.r = new ArrayList<Road>();
		this.vh = new ArrayList<Vehicle>();
		this.cruces = new TreeMap<String, Junction>();
		this.roads = new TreeMap<String, Road>();
		this.vehicles = new TreeMap<String, Vehicle>();
	}
	
	protected void addJunction(Junction j) {
		if (this.cruces.containsKey(j.getId()))
			throw new IllegalArgumentException("Este cruce ya existe");
		else {
			this.junct.add(j);
			this.cruces.put(j.getId(), j);
		}
	}
	
	protected void addRoad(Road r) {
		if (this.roads.containsKey(r.getId())) 
			throw new IllegalArgumentException("Esta carretera ya existe");
		else {
			this.r.add(r);
			this.roads.put(r.getId(), r);
		}
	}
	
	protected void addVehicle(Vehicle v) {
		if (this.vehicles.containsKey(v.getId()))
			throw new IllegalArgumentException("Este vehiculo ya existe");
		else {
			this.vh.add(v);
			this.vehicles.put(v.getId(), v);
		}
	}
	
	public Junction getJunction(String id) {
		return this.cruces.get(id);
	}
	
	public Road getRoad(String id) {
		return this.roads.get(id);
	}
	
	public Vehicle getVehicle(String id) {
		return this.vehicles.get(id);
	}
	
	public List<Junction> getJunction(){
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
		obj.put("junctions:", this.getJunction());
		obj.put("road:", this.getRoads());
		obj.put("vehicles:", this.getVehicles());
		return obj;
	}
}


package simulator.model;

import java.util.*;

import org.json.JSONArray;
import org.json.JSONObject;

public abstract class Road extends SimulatedObject{
	
	int length;//longitud de carretera
	Junction srcJunct;//cruce origen
	Junction destJunct;//cruce destino
	int maxSpeed;//velocidad maxima permitida en esa carretera
	int speedLimit;//limite de velocidad actual
	int alarmContEx;
	Weather condAmb;
	int contTotal;
	List<Vehicle> vehicles;
	Orden orden;

	Road(String id,Junction srcJunct,Junction destJunct,int maxSpeed,int contLimit,int length,Weather weather ) {
		super(id);
		this.vehicles = new ArrayList<>();


		if (maxSpeed <=0)
			throw new IllegalArgumentException( "Max Speed can´t be negative");
		else
			this.maxSpeed = maxSpeed;

		if (contLimit < 0)
			throw new IllegalArgumentException( "The contamination limit can´t be negative");
		else
			this.alarmContEx = contLimit;

		if (length <= 0)
			throw new IllegalArgumentException( "The leght can´t be negative");
		else
			this.length = length;
		if (srcJunct == null)
			throw new IllegalArgumentException( "The source road can´t be null");
		else
			this.srcJunct = srcJunct;
		if(destJunct == null)
			throw new IllegalArgumentException( "The destination road can´t be null");
		else
			this.destJunct = destJunct;

		if (weather == null)
			throw new IllegalArgumentException( "The weather can´t be null");
		else
			this.condAmb = weather;

		this.srcJunct.addOutGoingRoad(this);
		this.destJunct.addIncomingRoad(this);
		this.speedLimit = maxSpeed;
	}
  
  
	public class Orden implements Comparator<Vehicle> {
		
		public Orden(){
			
		}
		public int compare(Vehicle o1, Vehicle o2) {
		if(o1.getLocation()<o2.getLocation())
		    {
                 return 1;
		    }
			else if(o1.getLocation()==o2.getLocation())
			{
				return 0;
			}
			else{
				return -1;
			}
		}
	}
	
	
	void enter(Vehicle v){// vehiculo a la lista
		if (v.getSpeed() != 0)
			throw new IllegalArgumentException("Incorrect speed");
		else if (v.getLocation() != 0) {
			throw new IllegalArgumentException("Incorrect location");
		}
		else {
			this.vehicles.add(v);
		}
	
	}
	void exit(Vehicle v)
	{
		this.vehicles.remove(v);
	}
	
	void setWeather(Weather w) 
	{
		if (w == null)
			throw new IllegalArgumentException("Road Weather can't be null");
		else
			this.condAmb = w;
	}
	
	abstract void reduceTotalContamination();
	
	abstract void updateSpeedLimit();
	
	abstract int calculateVehicleSpeed(Vehicle v);
	
	
	void addContamination(int c) {
		if(c < 0)
			throw new IllegalArgumentException("Road contamination can't be negative");
		else
			this.contTotal += c;
	}
	
	
	void advance(int time) {

			this.reduceTotalContamination();
			
			this.updateSpeedLimit();
			
			for(Vehicle v:this.vehicles)
			{
				if (!v.getStatus().equals(VehicleStatus.ARRIVED)) {
					v.setSpeed(calculateVehicleSpeed(v));
					 v.advance(time);
				}
			}
			//clases anidadas
			orden = new Orden();
			this.vehicles.sort(orden);
	}

	@Override
	public JSONObject report() {
		JSONObject obj = new JSONObject();
		JSONArray vh=new JSONArray();
        obj.put("id",this._id);
		obj.put("speedlimit",this.getSpeedLimit());
		obj.put("weather",this.getWeather().toString());
		obj.put("co2",this.getTotalCO2());
		obj.put("vehicles", vh);
		for(Vehicle v:vehicles)
		{
			vh.put(v.getId());
		}
		
		
		return obj;
	}

	//getters y setter publicos 
	public Junction getDestJunct() {
		return destJunct;
	}
	public void setDestJunct(Junction destJunc) {
		this.destJunct = destJunc;
	}
	
	
	public int getContLimit() {
		return alarmContEx;
	}
	public void setAlarmContEx(int alarmContEx) {
		this.alarmContEx = alarmContEx;
	}
	public Weather getWeather() {
		return condAmb;
	}
	public void setCondAmb(Weather condAmb) {
		this.condAmb = condAmb;
	}
	public int getTotalCO2() {
		return contTotal;
	}
	public void setContTotal(int contTotal) {
		if(contTotal<0)
			this.contTotal = 0;
		else {
		this.contTotal = contTotal;
		}
	}
	public List<Vehicle> getVehicles() {
		return Collections.unmodifiableList(vehicles);
	}
	public void setVehicles(List<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public Junction getSrcJunct() {
		return srcJunct;
	}
	public void setSrcJunct(Junction srcJunct) {
		this.srcJunct = srcJunct;
	}
	public int getMaxSpeed() {
		return maxSpeed;
	}
	public void setMaxSpeed(int maxSpeed) {
		this.maxSpeed = maxSpeed;
	}
	public int getSpeedLimit() {
		return speedLimit;
	}
	public void setSpeedLimit(int speedLimit) {
		this.speedLimit = speedLimit;
	}
	
	
	
}
	

    

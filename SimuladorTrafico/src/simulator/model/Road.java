package simulator.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public abstract class Road extends SimulatedObject{
	
	private int length;//longitud de carretera
	Junction srcJunct;//cruce origen
	Junction destJunct;//cruce destino
	private int maxSpeed;//velocidad maxima permitida en esa carretera
	private int alarmContEx;
	private Weather condAmb;
	private int contTotal;
	private List<Vehicle> vehicles;
	private int longRoad;
	private Orden orden;

	public Road(String id,Junction srcJunct,Junction destJunct,int maxSpeed,int contLimit,int length,Weather weather ) {
		super(id);
		this.vehicles = new ArrayList<>();


		if (maxSpeed < 0)
			throw new IllegalArgumentException( "Max Speed can´t be negative");
		else
			this.maxSpeed = maxSpeed;

		if (contLimit < 0)
			throw new IllegalArgumentException( "The contamination limit can´t be negative");
		else
			this.alarmContEx = contLimit;

		if (length < 0)
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
	public void enter(Vehicle v){//a�adir vehiculo a la lista
		if (v.getSpeed() != 0)
			throw new IllegalArgumentException("Incorrect velocity");
		else if (v.getLocation() != 0) {
			throw new IllegalArgumentException("Incorrect location");
		}
		else {
			vehicles.add(v);
		}
	
	}
	public void exit(Vehicle v)
	{
		vehicles.remove(v);
	}
	public void setWeather(Weather w) 
	{
		if (this.condAmb == null)
			throw new IllegalArgumentException("Road Weather can�t be null");
		else
			this.condAmb = w;
	}
	
	abstract void reduceTotalContamination();
	
	abstract void updateSpeedLimit();
	
	abstract int calculateVehicleSpeed(Vehicle v);
	
	
	public void addContamination(int c) {
		if(c < 0)
			throw new IllegalArgumentException("Road contamination can�t be negative");
		else
			this.contTotal = c;
	}
	
	
	public void advance(int time) {

			reduceTotalContamination();
			updateSpeedLimit();
			for(Vehicle v:this.vehicles)
			{
                 v.setSpeed(calculateVehicleSpeed(v));
				 v.advance(time);
			}
			//clases anidadas
			orden = new Orden();
			this.vehicles.sort(orden);
	}

	@Override
	public JSONObject report() {
		JSONObject obj = new JSONObject();
		JSONArray vh=new JSONArray();
        obj.put("id:",_id);
		obj.put("speedlimit:",this.getMaxSpeed());
		obj.put("weather:",this.getCondAmb());
		obj.put("co2:",this.getCondAmb());
		for(Vehicle v:vehicles)
		{
			vh.put(v.getId());
		}
		obj.put("vehicles:", vh);
		
		return obj;
	}

	//getters y setter publicos 
	public Junction getDestJunc() {
		return destJunct;
	}
	public void setDestJunc(Junction destJunc) {
		this.destJunct = destJunc;
	}
	
	
	public int getAlarmContEx() {
		return alarmContEx;
	}
	public void setAlarmContEx(int alarmContEx) {
		this.alarmContEx = alarmContEx;
	}
	public Weather getCondAmb() {
		return condAmb;
	}
	public void setCondAmb(Weather condAmb) {
		this.condAmb = condAmb;
	}
	public int getTotalCO2() {
		return contTotal;
	}
	public void setContTotal(int contTotal) {
		contTotal = contTotal;
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
	public int getLongRoad()
	{
		return longRoad;
	}
	
	
	
}
	

    

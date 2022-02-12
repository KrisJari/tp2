package simulator.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import org.json.JSONObject;

public abstract class Road extends SimulatedObject
{
	
	private int length;//longitud de carretera
	Junction srcJunct;//cruce origen
	Junction destJunc;//cruce destino
	private int maxSpeed;//velocidad maxima permitida en esa carretera
	private int limitVel;//un vehiculo no puede circular a mas de esta velocidad
	private int alarmContEx;
	private Weather condAmb;
	private int ContTotal;
	private List<Vehicle> vehicles;
	private int longRoad;
	
	//hola

	Road(String id,Junction srcJunct,Junction destJunc,int maxspeed,int contLimit,int length,Weather weather ) {
		super(id);
		this.srcJunct = srcJunct;
		this.destJunc = destJunc;
		this.length = length;
		this.maxSpeed = maxspeed;
		this.limitVel = maxspeed;
		this.alarmContEx = contLimit;
		this.condAmb = weather;
		this.condAmb=weather;
		this.limitVel=maxspeed;
		vehicles = new ArrayList<>();


		if (maxSpeed < 0)
		throw new IllegalArgumentException( "Max Speed can´t be negative");
	if (contLimit < 0)
		throw new IllegalArgumentException( "The contamination limit can´t be negative");
	if (length < 0)
		throw new IllegalArgumentException( "The leght can´t be negative");
	if (srcJunct == null)
		throw new IllegalArgumentException( "The source road can´t be null");
	if(destJunc == null)
		throw new IllegalArgumentException( "The destination road can´t be null");
	if (weather == null)
		throw new IllegalArgumentException( "The weather can´t be null");


	}
  
  
	public class Orden implements Comparator<Vehicle> {
		@Override
		public int compare(Vehicle o1, Vehicle o2) {
		if(o1.getLocation()<o2.getLocation())
		    {
                 return -1;
		    }
			else if(o1.getLocation()==o2.getLocation())
			{
				return 0;
			}
			else{
				return 1;
			}
		}
	}
	public void enter(Vehicle v)
	{//a�adir vehiculo a la lista
		if(v.getLocation()==0&&v.getSpeed()==0)
		{
		vehicles.add(v);
		}
		else{
			throw new IllegalArgumentException("Incorrect location"+v.getLocation()+"Incorrect velocity"+v.getSpeed());
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
			c=ContTotal;
	}
	
	
	public void advance(int time) {

			reduceTotalContamination();
			updateSpeedLimit();

               //int speed=calculateVehicleSpeed(v.);
			for(Vehicle v:vehicles)
			{
                //  v.setSpeed(speed);
			}
			
	}

	@Override
	public JSONObject report() {
		JSONObject obj = new JSONObject();
        obj.put("id:",_id);
		obj.put("speedlimit:",this.getLimitVel());
		obj.put("weather:",this.getCondAmb());
		obj.put("co2:",this.getCondAmb());
		obj.put("vehicles:", this.vehicles);
		
		return obj;
	}

	//getters y setter publicos 
	public Junction getDestJunc() {
		return destJunc;
	}
	public void setDestJunc(Junction destJunc) {
		this.destJunc = destJunc;
	}
	
	public int getLimitVel() {
		return limitVel;
	}
	public void setLimitVel(int limitVel) {
		this.limitVel = limitVel;
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
	public int getContTotal() {
		return ContTotal;
	}
	public void setContTotal(int contTotal) {
		ContTotal = contTotal;
	}
	public List<Vehicle> getVehicles() {
		return vehicles;
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
	

    

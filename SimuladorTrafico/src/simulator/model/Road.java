package simulator.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.json.JSONObject;

public abstract class Road extends SimulatedObject
{
	
	private int longRoad;//longitud de carretera
	Junction srcJunc;//cruce origen
	Junction destJunc;//cruce destino
	private int velMax;
	private int limitVel;
	private int alarmContEx;
	private Weather condAmb;
	private int ContTotal;
	private List<Vehicle> vehicles; 
	
	//hola

	Road(String id,Junction srcJunct,Junction destJunc,int maxspeed,int contLimit,int length,Weather weather ) {
		super(id);
	
		this.condAmb=weather;
		this.limitVel=velMax;
		vehicles = new ArrayList<Vehicle>();
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
void enter(Vehicle v)
{//a�adir vehiculo a la lista
	vehicles.add(v);

}
void exit(Vehicle v)
{
	
}
void setWeather(Weather w)
{
	
}

void reduceTotalContamination()
{
	
}
void updateSpeedLimit()
{
	
}
public void addContamination(int c) {
		if
		(c<0)
	{
		throw new IllegalArgumentException();
	}
	c=ContTotal;
    }

int calculateVehicleSpeed(Vehicle v)
{
	return ContTotal;
	
}
	@Override
	void advance(int time) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public JSONObject report() {
		// TODO Auto-generated method stub
		return null;
	}
//getters y setter publicos 
	
	public int getLongRoad()
	{
		return longRoad;
	}
	
}
	

    

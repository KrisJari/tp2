package simulator.model;

import java.util.List;

import org.json.JSONObject;

public abstract class Road extends SimulatedObject{
	
	private int longRoad;//longitud de carretera
	Junction srcJunc;//cruce origen
	Junction destJunc;//cruce destino
	private int velMax;
	private int limitVel;
	private int alarmContEx;
	private Weather condAmb;
	private int ContTotal;
	private List<Vehicle> vehicles; 

	Road(String id,Junction srcJunct,Junction destJunc,int maxspeed,int contLimit,int length,Weather weather ) {
		super(id);
	
		this.condAmb=weather;
		this.limitVel=velMax;
		
	}
void enter(Vehicle v)
{//a�adir vehicuo a la lista
	
}
void exit(Vehicle v)
{
	
}
void setWeather(Weather w)
{
	
}
void addContamination(int c)
{
	
}
void reduceTotalContamination()
{
	
}
void updateSpeedLimit()
{
	
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

package simulator.model;

import java.util.List;

import org.json.JSONObject;

public abstract class Road extends SimulatedObject{
	
	private int longitud=0;
	Junction srcJunc;
	Junction destJunc;
	private int velMax=0;
	private int limitVel;
	private int alarmContEx;
	private Weather condAmb;
	private int ContTotal;
	private List<Vehicle> vehicles; 

	Road(String id,Junction srcJunct,Junction destJunc,int maxspeed,int contLimit,int length,Weather weather ) {
		super(id);
	
		this.condAmb=weather;
		
	}
void enter(Vehicle v)
{//añadir vehicuo a la lista
	
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
	
	
	
}

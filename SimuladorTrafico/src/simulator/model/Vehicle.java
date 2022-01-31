package simulator.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.JSONObject;

public class Vehicle extends SimulatedObject{


    private VehicleStatus estatus;
	private int maxspeed;
	private int contClass;

	Vehicle(String id,int maxspeed,int contClass,List<Junction> itinerary) throws Exception {
		super(id);
		// TODO Auto-generated constructor stub
		
		
			if(maxspeed<0||contClass>=0||contClass<=10||itinerary.size()<2)
			{
				throw new IllegalArgumentException();
			}
		
itinerary=Collections.unmodifiableList(new ArrayList<>(itinerary));
		
		
	
	}
	//hola

	void setSpeed(int s)
	{
		maxspeed=s;
		if(s<0)
		{
			throw new IllegalArgumentException("El número es negativo");
		}
	}
	
	void setContaminationClass(int c)
	{
		contClass=c;
		if(contClass>=0||contClass<=10)
		{
			throw new IllegalArgumentException("El número no está entre 0 y 10");
		
		}
	}
	
	
	@Override
	void advance(int time) {
		// TODO Auto-generated method stub
	estatus=estatus.TRAVELING;
		if(estatus!= null)
		{
			
		}
	}

	@Override
	public JSONObject report() {
		// TODO Auto-generated method stub
		return null;
	}

}

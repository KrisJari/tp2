package simulator.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.JSONObject;

public class Vehicle extends SimulatedObject{


    private VehicleStatus estado;//estado del vehiculo
	private int maxspeed;//velocidad m�xima
	private int contClass;//grado de contaminacion
	private int contTotal;
    private int locAct;
    private int velAct;//velocidad actual
    private int gradCont;//grado de contaminación o etiqueta
    private int distTotal;
	private Road road;
    private Road longRoad;
    private List<Junction> j;
    private Junction junct;

	Vehicle(String id,int maxspeed,int contClass,List<Junction> itinerary) throws Exception {
		
		
		super(id);
		
			if(maxspeed<0||contClass>=0||contClass<=10||itinerary.size()<2)
			{
				throw new IllegalArgumentException();
			}
		
		  itinerary=Collections.unmodifiableList(new ArrayList<>(itinerary));
		this.velAct=0;
		this.locAct=0;
		if(velAct==0){
		this.estado=VehicleStatus.PENDING;
		}
		this.distTotal=0;
	    this.road=null;
	
	}
	

	void setSpeed(int s)
	{
		velAct=Math.min(s, maxspeed);
		if(s<0)
		{
			throw new IllegalArgumentException("El n�mero es negativo");
		}
	}
	
	void setContaminationClass(int c)
	{
		contClass=c;
		if(contClass>=0||contClass<=10)
		{
			throw new IllegalArgumentException("El n�mero no est� entre 0 y 10");
		
		}
	}
	
	
	@Override
	void advance(int time) {
	 
		if (estado.equals(VehicleStatus.TRAVELING))
		{
			int locNew=Math.min(locAct+velAct,road.getLongRoad());
			int loc=locNew-locAct;
			int contaminacion=gradCont*loc;
			
			contaminacion=contTotal;
			 road.addContamination(contaminacion);

			if(locNew>=longRoad.getLongRoad())
		   {
                 junct.enter(this);
				 estado=VehicleStatus.WAITING;
		    }
		}
		
	}

	@Override
	public JSONObject report() {
		JSONObject obj=new JSONObject();
          obj.put("id:",_id);
		  obj.put("speed:",getSpeed());
		  obj.put("distance:",getTotalDistance());
		  obj.put("co2:",getTotalCO2());
          obj.put("class:",getGradCont());
		  obj.put("status",getStatus());
		  

       if(estado.equals(VehicleStatus.PENDING)||estado.equals(VehicleStatus.ARRIVED))
      {
          obj.put("road:",getCarretera());
		  obj.put("location:",getLocation());
        }

		return null;
	}
    //getters
	
	public int getSpeed()
	{
		return velAct;
	}
	public int getTotalDistance()
	{
		return distTotal;
	}
	public int getTotalCO2()
	{
		return contTotal;
	}
	public int getGradCont()
	{
		return gradCont;
	}
	public VehicleStatus getStatus()
	{
		return estado;
	}
	public Road getCarretera()
	{
		return road;
	}
	public int getLocation()
	{
		return locAct;
	}
}

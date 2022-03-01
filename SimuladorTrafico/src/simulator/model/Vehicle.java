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
    private List<Junction> itinerary;
	private int current_junct;

	Vehicle(String id,int maxspeed,int contClass,List<Junction> itinerary) {
		super(id);
		if(maxspeed < 0||contClass >= 0||contClass <= 10 || itinerary.size()<=2){
			throw new IllegalArgumentException(); //CAMBIAR LA EXCEPTION!!!!!!!!!!!!!
		}
		this.maxspeed = maxspeed;
		this.contClass = contClass;
		this.itinerary = Collections.unmodifiableList(new ArrayList<>(itinerary));
		this.current_junct = 0;
		this.velAct = 0;
		this.locAct = 0;
		if(velAct == 0) 
			this.estado = VehicleStatus.PENDING;
		this.distTotal = 0;
	}
	

	public void setSpeed(int s) 
	{
		if(s < 0)
			throw new IllegalArgumentException("El n�mero es negativo");  //CAMBIAR LA EXCEPTION!!!!!!
		else
			velAct = Math.min(s, maxspeed);
	}
	
	public void setContaminationClass(int c) 
	{
		if(contClass >= 0 || contClass <= 10)
			throw new IllegalArgumentException("El n�mero no est� entre 0 y 10"); //CAMBIAR LA EXCEPTION!!!!!!
		else {
			contClass = c;
		}
	}
	
	
	@Override
	public void advance(int time) {
	 
		if (estado.equals(VehicleStatus.TRAVELING))
		{
			int locNew=Math.min(locAct+velAct,road.getLongRoad());
			int loc=locNew-locAct;
			int c=gradCont*loc;
			
			c=contTotal;
			 road.addContamination(c);

			if(locNew>=longRoad.getLongRoad())
		   {
                 this.road.getDestJunc().enter(this);
				 estado=VehicleStatus.WAITING;
		    }
		}
		
	}
	
	protected void moveToNextRoad() {
		this.locAct = 0;
		this.velAct = 0;
		if (!this.estado.equals(VehicleStatus.PENDING) && !this.estado.equals(VehicleStatus.WAITING))
			throw new IllegalArgumentException("Illegal status");
		
		else if (!this.estado.equals(VehicleStatus.PENDING)) {
			this.road.exit(this);
		}
		
        if(current_junct+1 == itinerary.size()){
            estado = VehicleStatus.ARRIVED;
			 this.road.exit(this);
		 }
        else {
			this.estado = VehicleStatus.TRAVELING;
		    this.road = this.itinerary.get(current_junct).roadTo(this.itinerary.get(current_junct+1));
			current_junct++;//indice le sumas más uno por si no estas en 0
			this.road.enter(this);

		}

	}

	@Override
	public JSONObject report() {
		JSONObject obj=new JSONObject();
          obj.put("id:",_id);
		  obj.put("speed:",getSpeed());
		  obj.put("distance:",getTotalDistance());
		  obj.put("co2:",getTotalCO2());
          obj.put("class:",getContClass());
		  obj.put("status",getStatus());
		  

       if(estado.equals(VehicleStatus.PENDING)||estado.equals(VehicleStatus.ARRIVED))
      {
          obj.put("road:",getRoad());
		  obj.put("location:",getLocation());
        }

		return obj;
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
	public int getContClass()
	{
		return gradCont;
	}
	public VehicleStatus getStatus()
	{
		return estado;
	}
	public int getLocation()
	{
		return locAct;
	}
	public void setContClass(int contClass) {
		this.contClass = contClass;
	}
	public List<Junction> getItinerary() {
		return itinerary;
	}
	public void setItinerary(List<Junction> itinerary) {
		this.itinerary = itinerary;
	}
	public Road getRoad() {
		return road;
	}
	public void setRoad(Road road) {
		this.road = road;
	}
	
}

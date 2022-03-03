package simulator.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.JSONObject;

public class Vehicle extends SimulatedObject{


    private VehicleStatus estado;//estado del vehiculo
	private int maxSpeed;//velocidad m�xima
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

	Vehicle(String id,int maxSpeed,int contClass,List<Junction> itinerary) {
		super(id);
		if (maxSpeed < 0)
			throw new IllegalArgumentException("max speed must be a positive number");
		else
			this.maxSpeed = maxSpeed;
		if (contClass >= 0 || contClass <= 10)
			throw new IllegalArgumentException("class must be a number between 0 and 10");
		else 
			this.contClass = contClass;
		if(itinerary.size() < 2)
			throw new IllegalArgumentException("itinerary must be at least 2");
		else 
			this.itinerary = Collections.unmodifiableList(new ArrayList<>(itinerary));
		if(velAct == 0) 
			this.estado = VehicleStatus.PENDING;
		
		this.current_junct = 0;
		this.velAct = 0;
		this.locAct = 0;
		this.distTotal = 0;
	}
	

	public void setSpeed(int s) 
	{
		if(s < 0)
			throw new IllegalArgumentException("speed must be a positive number");  
		else
			this.velAct = Math.min(s, maxSpeed);
	}
	
	public void setContaminationClass(int c) 
	{
		if(c >= 0 || c <= 10)
			throw new IllegalArgumentException("class must be a number between 0 and 10"); 
		else {
			this.contClass = c;
		}
	}
	
	
	@Override
	public void advance(int time) {
	 
		if (this.estado.equals(VehicleStatus.TRAVELING)){
			int locNew = Math.min(this.locAct + this.velAct, this.road.getLongRoad());
			int loc = locNew - this.locAct;
			int c = this.gradCont * loc;
			
			c = this.contTotal;
			this.road.addContamination(c);

			if(locNew >= this.longRoad.getLongRoad()){
                 this.road.getDestJunc().enter(this);
				 this.estado = VehicleStatus.WAITING;
				 this.velAct = 0;
				 this.current_junct++;
		    }
		}
		
	}
	
	protected void moveToNextRoad() {
		this.locAct = 0;
		this.velAct = 0;
		
		if (!this.estado.equals(VehicleStatus.PENDING) && !this.estado.equals(VehicleStatus.WAITING))
			throw new IllegalArgumentException("Illegal status");
		
		if (!this.estado.equals(VehicleStatus.PENDING)) {
			this.road.exit(this);
		}
		
        if(current_junct+1 == itinerary.size()){
           this.estado = VehicleStatus.ARRIVED;
           this.road = null;
		}
        else {
        	Junction posAhora = this.itinerary.get(current_junct);
    		Junction posQueAvanza = this.itinerary.get(current_junct + 1);
    		Road nextRoad = posAhora.roadTo(posQueAvanza);
    		nextRoad.enter(this);
    		this.road = nextRoad;
			this.estado = VehicleStatus.TRAVELING;
			this.current_junct++;//indice le sumas más uno por si no estas en 0
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

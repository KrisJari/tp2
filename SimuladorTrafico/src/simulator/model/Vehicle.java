package simulator.model;

import java.util.*;

import org.json.JSONObject;

public class Vehicle extends SimulatedObject{


    private VehicleStatus estado;//status
	private int maxSpeed;//velocidad maxima
	private int contClass;//grado de contaminacion
	private int contTotal;
    private int locAct;
    private int velAct;//current speed
    private int distTotal;//total travelled distance
	private Road road;
    private Road longRoad;
    private List<Junction> itinerary;
	private int current_junct;

	Vehicle(String id,int maxSpeed,int contClass,List<Junction> itinerary) {
		super(id);
		if (maxSpeed <= 0) {
			throw new IllegalArgumentException("max speed must be a positive number");
		}
		else
			this.maxSpeed = maxSpeed;
		if (contClass < 0 || contClass > 10)
			throw new IllegalArgumentException("class must be a number between 0 and 10");
		else 
			this.contClass = contClass;
		if(itinerary.size() < 2)
			throw new IllegalArgumentException("itinerary must be at least 2");
		else 
			this.itinerary = Collections.unmodifiableList(new ArrayList<>(itinerary));
		if(velAct == 0) 
			this.estado = VehicleStatus.PENDING;
		
//		System.out.println(this.itinerary.toString());
		this.current_junct = 0;
		this.velAct = 0;
		this.locAct = 0;
		this.contTotal = 0;
		this.distTotal = 0;
	}
	

	void setSpeed(int s) 
	{
		if(s < 0)
			throw new IllegalArgumentException("speed must be a positive number");  
		if (s <= this.maxSpeed)
			this.velAct = s;
		else
			this.velAct = this.maxSpeed;
	}
	
	void setContClass(int c) 
	{
		if(c <= 0 || c >= 10)
			throw new IllegalArgumentException("class must be a number between 0 and 10"); 
		else {
			this.contClass = c;
		}
	}
	
	
	@Override
	void advance(int time) {
	 
		if (this.estado.equals(VehicleStatus.TRAVELING)){
			int locNew = Math.min(this.locAct + this.velAct, this.road.getLength());
			int loc = locNew - this.locAct;
			int c = this.contClass * loc;
			
			this.contTotal += c;
			this.road.addContamination(c);
			this.distTotal += locNew - this.locAct;
			this.locAct = locNew;

			if(locNew >= this.road.getLength()){
				 this.estado = VehicleStatus.WAITING;
				 this.velAct = 0;
                 this.road.getDestJunct().enter(this);
				 
		    }
		}
		
	}
	
	void moveToNextRoad() {
		this.locAct = 0;
		this.velAct = 0;
		if (!this.estado.equals(VehicleStatus.PENDING) && !this.estado.equals(VehicleStatus.WAITING))
			throw new IllegalArgumentException("Illegal status");
		
		if (!this.estado.equals(VehicleStatus.PENDING)) {
			this.road.exit(this);
		}
		if(this.current_junct+1 == this.itinerary.size()){
			this.estado = VehicleStatus.ARRIVED;
	        this.road = null;
		}
		else {
			Junction posAhora = this.itinerary.get(current_junct);
//			System.out.println(this.itinerary.get(current_junct));
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
          obj.put("id",this._id);
		  obj.put("speed",getSpeed());
		  obj.put("distance",getTotalDistance());
		  obj.put("co2",getTotalCO2());
          obj.put("class",getContClass());
		  obj.put("status",getStatus().toString());

       if(!(estado.equals(VehicleStatus.PENDING)||estado.equals(VehicleStatus.ARRIVED)))
      {
          obj.put("road",getRoad());
		  obj.put("location",getLocation());
        }

		return obj;
	}
    //getters
	
	protected int getSpeed()
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
		return contClass;
	}
	public VehicleStatus getStatus()
	{
		return estado;
	}
	public void setStatus(VehicleStatus estado) {
		this.estado = estado;
	}

	public int getMaxSpeed() {
		return maxSpeed;
	}

	public int getLocation()
	{
		return locAct;
	}
	
	public List<Junction> getItinerary() {
		return Collections.unmodifiableList(itinerary);
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

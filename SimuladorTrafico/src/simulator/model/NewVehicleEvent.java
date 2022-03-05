package simulator.model;

import java.util.ArrayList;
import java.util.List;

public class NewVehicleEvent extends Event{
	
	private Vehicle vehicle;
	private String id;
	private int maxSpeed;
	private int contClass;
	private List<String> itinerary;

    public NewVehicleEvent(int time, String id, int maxSpeed, int contClass, List<String> itinerary) {
        super(time);
        //TODO Auto-generated constructor stub
        
        if(time < 0) throw new IllegalArgumentException("Time can't be negative");
		if(maxSpeed < 0) throw new IllegalArgumentException("Max Speed must be positive number");
		if(contClass < 0 || contClass > 10) throw new IllegalArgumentException("ContClass must be a value between 0 and 10");
		
        
        this.id = id;
        this.maxSpeed = maxSpeed;
        this.contClass = contClass;
        this.itinerary = itinerary;
    }

    @Override
	public void execute(RoadMap map) {
        // TODO Auto-generated method stub
    	List<Junction> jItinerary = new ArrayList<Junction>();
    	for (String i : this.itinerary) {
    		Junction j = map.getJunction(i);
    		jItinerary.add(j);
    	}
    	
    	this.vehicle = new Vehicle(id,maxSpeed,contClass,jItinerary);
        map.addVehicle(this.vehicle);
        this.vehicle.moveToNextRoad();
    }
    
}

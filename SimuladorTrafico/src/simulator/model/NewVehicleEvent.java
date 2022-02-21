package simulator.model;

import java.util.List;

public class NewVehicleEvent extends Event{
	
	private Vehicle vehicle;

    NewVehicleEvent(int time, String id, int maxSpeed, int contClass, List<Junction> itinerary) {
        super(time);
        //TODO Auto-generated constructor stub
        this.vehicle = new Vehicle(id, maxSpeed, contClass, itinerary);
    }

    @Override
    void execute(RoadMap map) {
        // TODO Auto-generated method stub
        map.addVehicle(this.vehicle);
    }
    
}

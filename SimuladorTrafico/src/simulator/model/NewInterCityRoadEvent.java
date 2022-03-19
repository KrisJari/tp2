package simulator.model;

public class NewInterCityRoadEvent extends NewRoadEvent {
	

    public NewInterCityRoadEvent(int time, String id, String srcJun, String destJunc, int length, int co2Limit, int maxSpeed, Weather weather) {
        super(time, id, srcJun, destJunc, length, co2Limit, maxSpeed, weather);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void execute(RoadMap map) {
        // TODO Auto-generated method stub
    	Road interCity = new InterCityRoad(id, map.getJunction(srcJunc), map.getJunction(destJunc), maxSpeed, co2Limit,lenght, weather);
        map.addRoad(interCity);
    }
    
}

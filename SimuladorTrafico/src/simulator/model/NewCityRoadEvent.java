package simulator.model;

public class NewCityRoadEvent extends NewRoadEvent {
	
	
    public NewCityRoadEvent(int time, String id, String srcJun, String destJunc, int lenght, int co2Limit, int maxSpeed, Weather weather) {
        super(time, id, srcJun, destJunc, lenght,co2Limit, maxSpeed, weather);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void execute(RoadMap map) {
        // TODO Auto-generated method stub
    	Road cityRoad = new CityRoad(this.id, map.getJunction(srcJunc), map.getJunction(destJunc), maxSpeed, co2Limit,lenght, weather);
        map.addRoad(cityRoad);
    }
    
}

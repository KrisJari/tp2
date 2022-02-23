package simulator.model;

public class NewCityRoadEvent extends NewRoadEvent {
	
	
    public NewCityRoadEvent(int time, String id, String srcJun, String destJunc, int lenght, int co2Limit, int maxSpeed, Weather weather) {
        super(time, id, srcJun, destJunc, lenght, maxSpeed, weather);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void execute(RoadMap map) {
        // TODO Auto-generated method stub
    	Road cityRoad = new CityRoad(id, map.getJunction(srcJunc), map.getJunction(destJunc), lenght, co2Limit,maxSpeed, weather);
        map.addRoad(cityRoad);
    }
    
}

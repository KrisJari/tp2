package simulator.model;

public class NewCityRoadEvent extends Event {
	
	private CityRoad cityRoad; 
	
    public NewCityRoadEvent(int time, String id, Junction srcJun, Junction destJunc, int lenght, int co2Limit, int maxSpeed, Weather weather) {
        super(time);
        //TODO Auto-generated constructor stub
        this.cityRoad = new CityRoad(id, srcJun, destJunc, maxSpeed, co2Limit, lenght, weather);
    }

    @Override
    public void execute(RoadMap map) {
        // TODO Auto-generated method stub
        map.addRoad(this.cityRoad);
    }
    
}

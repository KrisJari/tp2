package simulator.model;

public class NewInterCityRoadEvent extends Event  {
	
	private Road interCity;

    NewInterCityRoadEvent(int time, String id, Junction srcJun, Junction destJunc, int length, int co2Limit, int maxSpeed, Weather weather) {
        super(time);
        //TODO Auto-generated constructor stub
        this.interCity = new InterCityRoad(id, srcJun, destJunc,length, co2Limit,maxSpeed,weather);
    }

    @Override
    public void execute(RoadMap map) {
        // TODO Auto-generated method stub
        map.addRoad(this.interCity);
    }
    
}

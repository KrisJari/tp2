package simulator.model;

public abstract class NewRoadEvent extends Event {
    
	protected Road road;
	String id;
	String srcJunc;
	String destJunc;
	int lenght;
	int co2Limit;
	int maxSpeed;
	Weather weather;
	public NewRoadEvent (int time, String id, String srcJunc, String destJunc, int lenght, int co2Limit,int maxSpeed, Weather weather){
		super(time);
		this.id = id;
		this.srcJunc = srcJunc;
		this.destJunc = destJunc;
		this.lenght = lenght;
		this.maxSpeed = maxSpeed;
		this.co2Limit = co2Limit;
		this.weather = weather;
	}
	
	public abstract void execute(RoadMap map);
}

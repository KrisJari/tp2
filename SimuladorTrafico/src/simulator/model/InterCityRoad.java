package simulator.model;

public class InterCityRoad extends Road {

	public InterCityRoad(String id, Junction srcJunct, Junction destJunc, int maxSpeed, int contLimit, int length,
			Weather weather) {
		super(id, srcJunct, destJunc, maxSpeed, contLimit, length, weather);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void reduceTotalContamination() {
		// TODO Auto-generated method stub
		int x = 0;
		Weather tc = this.getWeather();
		if (tc == Weather.SUNNY)
			x = 2;
		else if (tc == Weather.CLOUDY)
			x = 3;
		else if (tc == Weather.RAINY)
			x = 10;
		else if (tc == Weather.WINDY)
			x = 15;
		else 
			x = 20;
		this.setContTotal(((100 - x)*this.getTotalCO2()/ 100));
	}

	@Override
	protected void updateSpeedLimit() {
		// TODO Auto-generated method stub
		if (getTotalCO2() > getContLimit()) {
			this.speedLimit = getMaxSpeed() /2;
		}
		else
			this.speedLimit = this.maxSpeed;

	}

	@Override
	protected int calculateVehicleSpeed(Vehicle v) {
		// TODO Auto-generated method stub
		if (this.getWeather() == Weather.STORM)
			v.setSpeed((int)(getSpeedLimit() * 0.8));
		else
			v.setSpeed(getSpeedLimit());
		return v.getSpeed();
	}

}

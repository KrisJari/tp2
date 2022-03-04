package simulator.model;

public class CityRoad extends Road{

	public CityRoad(String id, Junction srcJunct, Junction destJunc, int maxSpeed, int contLimit, int length,
			Weather weather) {
		super(id, srcJunct, destJunc, maxSpeed, contLimit, length, weather);
		// TODO Auto-generated constructor stub
	}

	@Override
	void reduceTotalContamination() {
		// TODO Auto-generated method stub
		int x = 0;
		if (this.getWeather() == Weather.WINDY || this.getWeather() == Weather.STORM)
			x = 10;
		else
			x = 2;
		this.addContamination((int)(((100.0 - x) / 100.0) * this.getTotalCO2()));
	}

	@Override
	void updateSpeedLimit() {
		// TODO Auto-generated method stub
		setSpeedLimit(getMaxSpeed());
	}

	@Override
	int calculateVehicleSpeed(Vehicle v) {
		// TODO Auto-generated method stub
		v.setSpeed((int)(((11.0 - v.getContClass()) / 11.0) * this.getSpeedLimit()));
		return v.getSpeed();
	}

}

package simulator.model;

public class InterCityRoad extends Road {

	InterCityRoad(String id, Junction srcJunct, Junction destJunc, int maxspeed, int contLimit, int length,
			Weather weather) {
		super(id, srcJunct, destJunc, maxspeed, contLimit, length, weather);
		// TODO Auto-generated constructor stub
	}

	@Override
	void reduceTotalContamination() {
		// TODO Auto-generated method stub
		
	}

	@Override
	void updateSpeedLimit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	int calculateVehicleSpeed(Vehicle v) {
		// TODO Auto-generated method stub
		return 0;
	}

}

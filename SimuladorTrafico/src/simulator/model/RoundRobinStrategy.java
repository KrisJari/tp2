package simulator.model;

import java.util.List;

public class RoundRobinStrategy implements LightSwitchingStrategy {

	private int timeSlot;//numero de tics consecutivos
	
	public RoundRobinStrategy (int timeSlot) {
		this.timeSlot = timeSlot;
	}
	
	@Override
	public int chooseNextGreen(List<Road> roads, List<List<Vehicle>> qs, int currGreen, int lastSwitchingTime,int currTime) {
		if(roads.isEmpty())//esta vacio o no
		{
			return -1;
		}
		else if(currGreen==-1)
		{
           return 0;
		}
		else if(currGreen-lastSwitchingTime < timeSlot)
		{
           return currGreen;
		}
		return (currGreen+1)%roads.size();
	}

}

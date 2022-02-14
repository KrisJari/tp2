package simulator.model;

import java.util.List;

public class RoundRobinStrategy implements LightSwitchingStrategy {

	private int timeSlot;//numero de tics consecutivos
	
	
	
	@Override
	public int chooseNextGreen(List<Road> roads, List<List<Vehicle>> qs, int currGreen, int lastSwitchingTime,
			int currTime) {
		if(roads.isEmpty())//esta vacio o no
		{
			return -1;
		}
		if(currGreen==-1)
		{
           return 0;
		}
		 if(currGreen-lastSwitchingTime<timeSlot)
		{
           return currGreen;
		}
		return (currGreen+1)%roads.size();
	}

}

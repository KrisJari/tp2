package simulator.model;

import java.util.List;
import java.util.ListIterator;

public class MostCrowdedStrategy implements LightSwitchingStrategy{

	private int timeSlot;
	
	public MostCrowdedStrategy(int timeSlot) {
		this.timeSlot = timeSlot;
	}
	@Override
	public int chooseNextGreen(List<Road> roads, List<List<Vehicle>> qs, int currGreen, int lastSwitchingTime, int currTime) {

		 if(roads.isEmpty())
			 return -1;
		 else if(currGreen == -1){
		
			int queueSize = 0;
			int pos = 0;
			for (int i = 0; i < qs.size(); i++) {
				if (queueSize < qs.get(i).size()) {
					queueSize = qs.get(i).size();
					pos = i;
				}
			}
			return pos;
		 }
		 
		 
		 else if(currTime-lastSwitchingTime < this.timeSlot)
			 return currGreen;
		 
		 else {
			 int sizeQueue = 0;

			 int find = (currGreen + 1) % qs.size();
			 int pos = find;
			 
			 for (int i = 0; i< qs.size();i++) {
				 if (sizeQueue < qs.get((find+i)%qs.size()).size()) {
					 sizeQueue = qs.get((find+i)%qs.size()).size();
					 pos = (find+i)%qs.size();
				 }
			 }
			 
			 return pos;
		 }
	}

}

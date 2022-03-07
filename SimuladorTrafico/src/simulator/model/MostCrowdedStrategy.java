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
		 
		 if(currGreen == -1){
		
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
		 
		 if(currTime-lastSwitchingTime<timeSlot)
			 return currGreen;
		 
		 
//		 for (int i = 0; i< qs.size();i++) {
//			 List<Vehicle> l = qs.get((currGreen+1+i)%qs.size());
//		 }
//		 
		 
		 ListIterator<List<Vehicle>> it=qs.listIterator();
		 List<Vehicle> aux1 =qs.get((currGreen+1)%roads.size());
         it.set(aux1);
		 int u=currGreen+1;
		 while(u!=currGreen){
            List<Vehicle> vh=null;
			if(it.hasNext()){
				vh=it.next();
			}
			else{
				it.set(qs.get(0));
			 }
			if(aux1.size()<it.next().size()){
                aux1=vh;
			 }
		  }
		return qs.indexOf(aux1);
	}

}

package simulator.model;

import java.util.List;
import java.util.ListIterator;

public class MostCrowdedStrategy implements LightSwitchingStrategy{

	private int timeSlot;
	@Override
	public int chooseNextGreen(List<Road> roads, List<List<Vehicle>> qs, int currGreen, int lastSwitchingTime, int currTime) {
		List<Vehicle> aux=qs.get(0);

		 if(roads.isEmpty())
			 return -1;
		 
		 if(currGreen==-1){
            for(int i=0;i<qs.size();i++){
                if(aux.size()<qs.get(i).size())
					aux=qs.get(i);
			}
            
			return qs.indexOf(aux);// te da el valor entero de un indice particular de un objeto 
		 }
		 
		 if(currTime-lastSwitchingTime<timeSlot)
			 return currGreen;
		 /////////////////////////////////////////////////////////////////////////////////////////////
		 
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

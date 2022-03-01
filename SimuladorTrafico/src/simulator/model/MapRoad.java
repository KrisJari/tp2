package simulator.model;

import java.util.Comparator;

public class MapRoad implements Comparator<Junction>{
	

//	public int compare(Junction o1, Junction o2) {
//		// TODO Auto-generated method stub
//		int c = -1;
//		if(o1.getId().equals(o2.getId())) {
//			c = 0;
//		}
//		else if(!o1.getId().equals(o2.getId())) {
//			c = 1;
//		}
//		return c;
//	}
	
	public int compare(Junction o1, Junction o2) {
		int c = 0;
		if (o1.getId().equals(o2.getId()))
			c = 0;
		if (o1.getId() == null)
			c = -1;
		if (o2.getId() == null)
			c = 1;
		return c;
	}

}

package simulator.model;

import java.util.Map;

public class MapRoad implements Comparable<Map<Junction,Road>>{
	
	private Map<Junction,Road> mr;
	
	public MapRoad(Map<Junction,Road> mr){
		this.mr = mr;
	}
	
	@Override
	public int compareTo(Map<Junction, Road> o) {
		// TODO Auto-generated method stub
		if (this.mr == o)
			return 0;
		else if (this.mr == null)
			return -1;
		
		return 1;
	}

}

package simulator.model;

import java.util.ArrayList;
import java.util.List;

public class MoveFirstStrategy implements DequeuingStrategy{

	@Override
	public List<Vehicle> dequeue(List<Vehicle> q) {
	
		List<Vehicle> vh=new ArrayList<Vehicle>();
        if(!q.isEmpty())
		{
			vh.add(q.get(0));
		}

		return vh;
	}

}

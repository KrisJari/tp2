package simulator.model;

import java.util.List;
import java.util.Map;

public class RoadMap {
	
	protected List<Junction> junct;//lesta de cruces
	protected List<Road> r; //lista de carreteras 
	protected List<Vehicle> vh;//lista de vehiculos 
	protected Map<String,Junction> cruces;//mapa de cruces 
	protected Map<String ,Road> roads;//mapa de carreteras 
	protected Map<String,Vehicle> vehicles;//mapa de vehiculos 
	
	protected RoadMap()
	{
		
	}
	
	
	
}


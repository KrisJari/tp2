package simulator.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.json.JSONObject;

public class Junction extends SimulatedObject{
	
  private List<Road> road;
  private  Map<Junction,Road> mapRoad;
  private List<List<Vehicle>> colavehicles;
  private int indSV;
  private int ultCamS;
  private LightSwitchingStrategy round;
  private DequeuingStrategy first ;
  private int x;
  private int y;
  
  
	Junction (String id,LightSwitchingStrategy isStrategy,DequeuingStrategy dqStrategy,int xCoor,int yCoor) {
		super(id);
		this.road=new ArrayList<>();
        this.mapRoad=new HashMap<Junction,Road>() ;
		this.colavehicles= new ArrayList<List<Vehicle>>();
        
	}
    void enter(Vehicle v)
	{

	}
   void addIncomingRoad(Road r)
   {
	   if(!r.destJunc.equals(this))
	   {
		   throw new IllegalArgumentException("no es una carretera entrante");
	   }
	   road.add(r);
	   LinkedList colaR=new LinkedList();
	   colaR.add(r);

	   //me creo una cola de roads y luego me creo un mapa con esas colas y Road 
	   //que añade ese mapa al par de Map<Junction ,Road>

	   //Map<Road,ColaR>?
   }
protected Road roadTo (Junction j)
{
	return null;
}


	@Override
	void advance(int time) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public JSONObject report() {
		// TODO Auto-generated method stub
		return null;
	}

}

package simulator.model;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;

public class Junction extends SimulatedObject{
	
  private List<Road> road;
  private  Map<Junction,Road> m;
  private List<List<Vehicle>> v;
  private int indSV;
  private int ultCamS;
  private LightSwitchingStrategy round;
  private DequeuingStrategy first ;
  private int x;
  private int y;
  
  
	Junction(String id,LightSwitchingStrategy isStrategy,DequeuingStrategy dqStrategy,int xCoor,int yCoor) {
		super(id);
		
	}
    void enter(Vehicle v)
	{

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

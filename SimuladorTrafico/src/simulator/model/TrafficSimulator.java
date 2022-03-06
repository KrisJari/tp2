package simulator.model;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

public class TrafficSimulator {
    private RoadMap roadMap; //mapa de carreteras
    private List<Event> events;//lista de eventos
    private int time; //tiempo aka paso de la simulacion
    
    public TrafficSimulator() {
    	this.roadMap = new RoadMap();//mapa de carreteras
    	this.events = new ArrayList<Event>();//eventos a ejectuar
    	this.time = 0;
    }
    
    public void addEvent(Event e) {
    	this.events.add(e);
    }
    
    public void advance() {
    	
    	this.time++;

    	for(int i = 0; i < events.size(); i++) {
    		
    		if (events.get(i).getTime() == this.time) {
    			events.get(i).execute(roadMap);
    		}
    	}
    	
    	for (int i = 0; i < roadMap.getJunctions().size(); i++) {
    		roadMap.getJunctions().get(i).advance(time);
    	}

    	for (int i = 0; i < roadMap.getRoads().size(); i++) {
    		roadMap.getRoads().get(i).advance(time);
    	}
    }
    
    public void reset() {
    	this.time = 0;
    	this.roadMap.reset();
    	this.events.clear();
    }
    
    public JSONObject report() {
    	JSONObject obj = new JSONObject();
    	obj.put("time", this.time);
    	obj.put("state", this.roadMap.report());
    	return obj;
    }
}

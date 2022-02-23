package simulator.model;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

public class TrafficSimulator {
    private RoadMap roadMap; //mapa de carreteras
    private List<Event> events;//lista de eventos
    private int time; //tiempo aka paso de la simulacion
    
    public TrafficSimulator() {
    	this.roadMap = new RoadMap();
    	this.events = new ArrayList<Event>();
    	this.time = 1;
    }
    
    public void addEvent(Event e) {
    	this.events.add(e);
    }
    
    public void advance() {
    	
    }
    
    public void reset() {
    	this.time = 0;
    	this.roadMap.reset();
    	this.events.clear();
    }
    
    public JSONObject report() {
    	return null;
    }
}

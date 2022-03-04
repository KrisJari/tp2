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
    	for (Event e : events) {
    		if(e.getTime() == this.time) {
    			e.execute(roadMap);
    			this.events.remove(e);
    		}
    	}
    	for (Junction j : roadMap.getJunctions()) {
    		j.advance(time);
    	}
    	for (Road r : roadMap.getRoads()) {
    		r.advance(time);
    	}
    }
    
    public void reset() {
    	this.time = 0;
    	this.roadMap.reset();
    	this.events.clear();
    }
    
    public JSONObject report() {
    	JSONObject obj = new JSONObject();
    	obj.put("time", time - 1);
    	obj.put("state", roadMap.report());
    	return obj;
    }
}

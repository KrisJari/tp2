package simulator.model;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import simulator.misc.SortedArrayList;

public class TrafficSimulator implements Observable<TrafficSimObserver> {
    private RoadMap roadMap; //mapa de carreteras
    private List<Event> events;//lista de eventos
    private int time; //tiempo aka paso de la simulacion
    private List<TrafficSimObserver> ob;
    
    public TrafficSimulator() {
    	this.roadMap = new RoadMap();//mapa de carreteras
    	this.events = new SortedArrayList<Event>();//eventos a ejectuar
    	this.time = 0;
    	this.ob = new ArrayList<TrafficSimObserver>();
    }
    
    public void addEvent(Event e) {
    	this.events.add(e);
    	onEventAdded(this.roadMap,this.events,e,this.time);
    	
    	
    }
    
    private void onEventAdded(RoadMap roadMap2, List<Event> events2, Event e, int time2) {
    	for(TrafficSimObserver o : this.ob) {
    		o.onEventAdded(roadMap, events,e,time);
      	}
    	
		
	}

	public void advance() {
    	
    	this.time++;
    	
    	for(TrafficSimObserver o : this.ob) {
    		o.onAdvanceStart(roadMap, events, time);
      	}
    	
    	while(this.events.size()>0 && this.events.get(0).getTime() == this.time) {
    		events.get(0).execute(roadMap);
    		events.remove(0);
    	}
    	
    	
    	for (int i = 0; i < roadMap.getJunctions().size(); i++) {
    		if(time == 6) {
    		roadMap.getJunctions().get(i).advance(time);}
    		else
    			roadMap.getJunctions().get(i).advance(time);
    	}

    	for (int i = 0; i < roadMap.getRoads().size(); i++) {
    		roadMap.getRoads().get(i).advance(time);
    	}
    	
    	for(TrafficSimObserver o : this.ob) {
    		o.onAdvanceEnd(roadMap, events, time);
      	}
    	
    	
    }
    
    public void reset() {
    	this.time = 0;
    	this.roadMap.reset();
    	this.events.clear();
    	
    	onReset(roadMap,events,time);
    }
    
    private void onReset(RoadMap roadMap2, List<Event> events2, int time2) {
		
    	for(TrafficSimObserver o : this.ob) {
    		o.onReset(roadMap, events, time);
      	}
	}

	public JSONObject report() {
    	JSONObject obj = new JSONObject();
    	obj.put("time", this.time);
    	obj.put("state", this.roadMap.report());
    	return obj;
    }

  

	@Override
	public void addObserver(TrafficSimObserver o) {
		if(!ob.contains(o))
			this.ob.add(o);
		
		o.onRegister(roadMap,events,time);
	}
	
	@Override
	public void removeObserver(TrafficSimObserver o) {
	
		
	}
   
}

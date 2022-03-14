package simulator.model;

import java.util.List;

public interface TrafficSimObserver {

    abstract void onAdvanceStart(RoadMap map, List<Event> events, int time);
    abstract void onAdvanceEnd(RoadMap map, List<Event> events, int time);
    abstract void onEventAdded(RoadMap map, List<Event> events, Event e, int time);
    abstract void onReset(RoadMap map, List<Event> events, int time);
    abstract void onRegister(RoadMap map, List<Event> events, int time);
    abstract void onError(String err);
	
}


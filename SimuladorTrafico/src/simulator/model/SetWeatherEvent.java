package simulator.model;

import java.util.ArrayList;
import java.util.List;

import simulator.misc.Pair;

public class SetWeatherEvent extends Event {
	
	private List<Pair<String,Weather>> ws;

    SetWeatherEvent(int time, List<Pair<String,Weather>> ws) {
        super(time);
        //TODO Auto-generated constructor stub
        if (ws.equals(null))
			throw new IllegalArgumentException("Lista de Weather vacia!"); 
        else {
        	this.ws = new ArrayList<Pair<String,Weather>>();
        	this.ws = ws;
        }

    }

    @Override
    public void execute(RoadMap map) {
        // TODO Auto-generated method stub
        for (Pair<String,Weather> w : this.ws) {
        	
        	if(map.getRoad(w.getFirst()).equals(null))
        		throw new IllegalArgumentException("No se ha encontrado la carretera en el mapa");
        	else
        		map.getRoad(w.getFirst()).setWeather(w.getSecond());
        }
    }
    
}

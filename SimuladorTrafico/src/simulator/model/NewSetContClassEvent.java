package simulator.model;

import java.util.ArrayList;
import java.util.List;

import simulator.misc.Pair;

public class NewSetContClassEvent extends Event{
	
	private List<Pair<String, Integer>> cs;

    NewSetContClassEvent(int time, List<Pair<String,Integer>> cs) {
        super(time);
        //TODO Auto-generated constructor stub
        if(cs.equals(null))
			throw new IllegalArgumentException("Lista de Contaminacion vacia!"); 
        else {
        	this.cs = new ArrayList<Pair<String,Integer>>();
        	this.cs = cs;
        }
    }

    @Override
    public void execute(RoadMap map) {
        // TODO Auto-generated method stub
        for (Pair<String,Integer> con : this.cs) {
        	if (map.getVehicle(con.getFirst()).equals(null))
        		throw new IllegalArgumentException("No se ha encontrado el coche en el mapa");
        else
        	map.getVehicle(con.getFirst()).setContaminationClass(con.getSecond());
        }
    }
    
}

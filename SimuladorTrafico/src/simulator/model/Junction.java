package simulator.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


import org.json.JSONObject;

public class Junction extends SimulatedObject{
	
  private List<Road> road;
  private  Map<Junction,Road> mapRoad;
  private List<List<Vehicle>> colavehicles;//representan los coches que circulan por la carretera guardada en la pos i en lista de carreteras entrantes
  private Map<Road,List<Vehicle>> mapaColas;
  private int indSV;//indice del semáforo
  private int ultCamS;//ultimo cambio de cambio de semáforo
  private LightSwitchingStrategy isStrategy;
  private DequeuingStrategy dqStrategy ;
  private int x;
  private int y;
  
  
  Junction (String id,LightSwitchingStrategy isStrategy,DequeuingStrategy dqStrategy,int xCoor,int yCoor) {
		super(id);
		this.road=new ArrayList<>();
        this.mapRoad=new HashMap<Junction,Road>() ;
		this.colavehicles= new ArrayList<List<Vehicle>>();
        if(isStrategy==null||dqStrategy==null)
		{
            throw new IllegalArgumentException("Las estrategias son nulas");
		}
		this.dqStrategy=dqStrategy;
		this.isStrategy=isStrategy;

		if(xCoor<0||yCoor<0)
		{
			throw new IllegalArgumentException("Las coordenadas son negativas");

		}
		this.x=xCoor;
        this.y=yCoor;

		this.ultCamS=1;
	}
  
    public void enter(Vehicle v){
       mapaColas.get(v.getCarretera()).add(v);
	}
    
	public void addOutgoingRoad(Road r){
     mapRoad.put(r.destJunc,r);
     
	 if(!r.destJunc.equals(this)&&r.srcJunct.equals(this))
		throw new IllegalArgumentException("no es una carretera saliente");
	 
	}
	
   public void addIncomingRoad(Road r){
	   if(!r.destJunc.equals(this)){
		   throw new IllegalArgumentException("no es una carretera entrante");
	   }
	   road.add(r);
	   //me creo una cola de roads y luego me creo un mapa con esas colas y Road 
	   //que añade ese mapa al par de Map<Junction ,Road>

	   //Map<Road,ColaR>?==Mapa<Road,List<Vehicle>>

	   //me creo una lista de colas según la página 12 List<List<Vehicles>> pero como es una la declaramos
	   List<Vehicle> cola= new LinkedList<>();
	   //añadimos la final de la lista de colas
	   colavehicles.add(cola);
	   //el mapa carretera cola es mapaColas pagina 12 se dice que se guarde para hacer la búsqueda
	   mapaColas.put(r, cola);
   }
   
   public Road roadTo (Junction j)
   {
		Iterator it=mapRoad.keySet().iterator();
		while(it.hasNext())
		{
	       
	
		}
		return null;
   }


	@Override
	protected void advance(int time) {
		
		
	}

	@Override
	public JSONObject report() {
		
		return null;
	}
	
	public Road roadTo(Junction j) {
		return null;
	}

}

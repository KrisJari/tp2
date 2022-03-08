package simulator.model;

import java.util.*;


import org.json.JSONArray;
import org.json.JSONObject;

public class Junction extends SimulatedObject{
	
	  private List<Road> road;//mapa de carreteras ENTRANTES
	  private Map<Junction,Road> mapRoad;//mapa de carreteras SALIENTES 
	  private List<List<Vehicle>> colavehicles; 
	  private Map<Road, List<Vehicle>> mapaColas;//para hacer la busqueda 
	  private int indSV;//indice del semáforo
	  private int ultCamS;//ultimo cambio de cambio de semáforo
	  private LightSwitchingStrategy isStrategy;
	  private DequeuingStrategy dqStrategy;
	  private int x;
	  private int y;

  
  
	  Junction (String id,LightSwitchingStrategy isStrategy,DequeuingStrategy dqStrategy,int xCoor,int yCoor) {
		super(id);
		this.road = new ArrayList<Road>();
        this.mapRoad=new HashMap<Junction,Road>();
		this.colavehicles= new ArrayList<List<Vehicle>>();
		this.mapaColas = new HashMap<Road, List<Vehicle>>();
        if(isStrategy==null)
            throw new IllegalArgumentException("light switching strategy can't be null");
        else 
        	this.isStrategy=isStrategy;
        if (dqStrategy==null)
            throw new IllegalArgumentException("dequeuing strategy can't be null");
        else
        	this.dqStrategy=dqStrategy;
        if(xCoor<0)
			throw new IllegalArgumentException("coor x can't be null");
        else
    		this.x=xCoor;
        if(yCoor<0)
			throw new IllegalArgumentException("coor y can't be null");
        else
        	this.y=yCoor;

		this.ultCamS=1;
		
	}
  
  	protected void enter(Vehicle v){
  		List<Vehicle> aux = new ArrayList<Vehicle>();
  		for (Vehicle i : v.getRoad().getVehicles()) {
  			if(i.getLocation() == i.getRoad().getLength())
  				aux.add(i);
  		}
  		this.mapaColas.put(v.getRoad(), aux);
  		this.colavehicles.set(this.getRoad().indexOf(v.getRoad()), aux);
	}
    
	protected void addOutGoingRoad(Road r){
	 if(!r.srcJunct.equals(this))
		throw new IllegalArgumentException("no es una carretera saliente");
	 
	   this.mapRoad.put(r.getDestJunct(),r);
	   
	}
	
	protected void addIncomingRoad(Road r){ 
	   if(!r.destJunct.equals(this)){
		   throw new IllegalArgumentException("no es una carretera entrante");
	   }
	   this.road.add(r);
	   List<Vehicle> queue = new ArrayList<Vehicle>();
	   this.colavehicles.add(queue);
	   this.mapaColas.put(r,queue);
	}
	
	Road roadTo (Junction j){
		return this.mapRoad.get(j);
		
	}


	@Override
	protected void advance(int time) {
		List<Vehicle> auxLV = new ArrayList<Vehicle>();
		for (int i = 0; i < this.road.size(); i++) {
			if (indSV == i && !(colavehicles.get(i).isEmpty())) {
				auxLV = dqStrategy.dequeue(colavehicles.get(i));
				for (int j = 0; j < auxLV.size(); j++) {
					auxLV.get(j).moveToNextRoad();
					if (!colavehicles.get(i).isEmpty()) {
						colavehicles.get(i).remove(j);
					}
				}
			}
		}
		
		int verde = isStrategy.chooseNextGreen(road, colavehicles, indSV, ultCamS, time);
		if (verde != indSV) {
			this.indSV = verde;
			this.ultCamS = time - 1;
		}
	}

	@Override
	public JSONObject report() {
		JSONObject obj = new JSONObject();
		obj.put("id:",_id);
		if ( this.indSV != -1)
			obj.put("green:", getIndSV());
		else
			obj.put("green:", "none");
		obj.put("queues", ListRoad());
		
		return obj;
	}

	private JSONArray ListRoad()
	{
		//******************************
		JSONArray carre= new JSONArray();
		JSONArray vehi= new JSONArray();
		JSONObject elem=new JSONObject();
		//*******************************
		for(Road r:road) {
			
		elem.put("road", getRoad());
		
		       for(Vehicle v:mapaColas.get(r)){
			vehi.put(v.getId()); 
			
			}
		elem.put("vehicles", vehi);
		carre.put(elem);
		}
		return carre;
		
		
	}

	public List<Road> getRoad() {
		return road;
	}

	public void setRoad(List<Road> road) {
		this.road = road;
	}

	public Map<Junction, Road> getMapRoad() {
		return mapRoad;
	}

	public void setMapRoad(Map<Junction, Road> mapRoad) {
		this.mapRoad = mapRoad;
	}

	public List<List<Vehicle>> getColavehicles() {
		return colavehicles;
	}

	public void setColavehicles(List<List<Vehicle>> colavehicles) {
		this.colavehicles = colavehicles;
	}

	public Map<Road, List<Vehicle>> getMapaColas() {
		return mapaColas;
	}

	public void setMapaColas(Map<Road, List<Vehicle>> mapaColas) {
		this.mapaColas = mapaColas;
	}

	public int getIndSV() {
		return indSV;
	}

	public void setIndSV(int indSV) {
		this.indSV = indSV;
	}

	public int getUltCamS() {
		return ultCamS;
	}

	public void setUltCamS(int ultCamS) {
		this.ultCamS = ultCamS;
	}

	public LightSwitchingStrategy getIsStrategy() {
		return isStrategy;
	}

	public void setIsStrategy(LightSwitchingStrategy isStrategy) {
		this.isStrategy = isStrategy;
	}

	public DequeuingStrategy getDqStrategy() {
		return dqStrategy;
	}

	public void setDqStrategy(DequeuingStrategy dqStrategy) {
		this.dqStrategy = dqStrategy;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	


}

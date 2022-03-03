package simulator.model;

import java.sql.SQLOutput;
import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.sql.rowset.serial.SQLOutputImpl;

import org.json.JSONArray;
import org.json.JSONObject;

public class Junction extends SimulatedObject{
	
	  private List<Road> road;//mapa de carreteras ENTRANTES
	  private Map<Junction,Road> mapRoad;//mapa de carreteras SALIENTES 
	  private List<List<Vehicle>> colavehicles; 
	  private Map<Road,List<Vehicle>> mapaColas;//para hacer la busqueda 
	  private int indSV;//indice del semáforo
	  private int ultCamS;//ultimo cambio de cambio de semáforo
	  private LightSwitchingStrategy isStrategy;
	  private DequeuingStrategy dqStrategy;
	  private int x;
	  private int y;
  
  
  Junction (String id,LightSwitchingStrategy isStrategy,DequeuingStrategy dqStrategy,int xCoor,int yCoor) {
		super(id);
		this.road = new ArrayList<Road>();
        this.mapRoad=new TreeMap<Junction,Road>();
		this.colavehicles= new ArrayList<List<Vehicle>>();
		this.mapaColas = new TreeMap<Road, List<Vehicle>>();
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
//       this.mapaColas.get(v.getRoad()).add(v);
  		Road enteringRoad = v.getRoad();
  		List<Vehicle> vehicles = mapaColas.get(enteringRoad);
  		vehicles.add(v);
	}
    
	protected void addOutGoingRoad(Road r){
	 if(!r.destJunct.equals(this)&&r.srcJunct.equals(this))
		throw new IllegalArgumentException("no es una carretera saliente");
	 
	   this.mapRoad.put(this,r);
	}
	
	protected void addIncomingRoad(Road r){
	   if(!r.destJunct.equals(this)){
		   throw new IllegalArgumentException("no es una carretera entrante");
	   }
	   this.road.add(r);
	   //me creo una cola de roads y luego me creo un mapa con esas colas y Road 
	   //que añade ese mapa al par de Map<Junction ,Road>

	   //Map<Road,ColaR>?==Mapa<Road,List<Vehicle>>

	   //me creo una lista de colas según la página 12 List<List<Vehicles>> pero como es una la declaramos
	   List<Vehicle> cola = new ArrayList<Vehicle>();
	   //añadimos la final de la lista de colas
	   this.colavehicles.add(cola);
	   //el mapa carretera cola es mapaColas pagina 12 se dice que se guarde para hacer la búsqueda
	   this.mapaColas.put(r, cola);
	   
	}
	
	protected Road roadTo (Junction j){
		
//		List<Road> road2 = j.getRoad();
		return this.mapRoad.get(j);
		
	}


	@Override
	protected void advance(int time) {
		//necesitamos una lista de vehicle que guarde la estrategia colavehicle.get()nos dice en las colas en que indice
		//esta el semaforo
		List <Vehicle> vh = dqStrategy.dequeue(colavehicles.get(indSV));
        for (Vehicle v : vh) {
        	v.moveToNextRoad();
        	if (v.getStatus().equals(VehicleStatus.TRAVELING) || v.getStatus().equals(VehicleStatus.ARRIVED)) {
        		this.colavehicles.get(indSV).remove(v);
        	}
        }
        
        int verde = this.indSV;
        this.indSV = this.isStrategy.chooseNextGreen(road, colavehicles,indSV , ultCamS, time);
        if (this.indSV != verde)
        	this.ultCamS = time;
	
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

package simulator.model;

public class NewJunctionEvent extends Event{
	
	private String id;
	private LightSwitchingStrategy iStrategy;
	private DequeuingStrategy dStrategy;
	private int xCoor, yCoor;

    public NewJunctionEvent(int time,String id,LightSwitchingStrategy iStrategy,DequeuingStrategy dStrategy,int xCoor,int yCoor) {
        super(time);
        //TODO Auto-generated constructor stub
        this.id = id;
        this.iStrategy = iStrategy;
        this.dStrategy = dStrategy;
        this.xCoor = xCoor;
        this.yCoor = yCoor;
    }

    @Override
    public void execute(RoadMap map) {
        // TODO Auto-generated method stub
        Junction junct = new Junction(id, iStrategy, dStrategy, xCoor, yCoor);

        map.addJunction(junct);
        
    }
    
}

package simulator.model;

public class NewJunctionEvent extends Event{
	
	private Junction junct;

    public NewJunctionEvent(int time,String id,LightSwitchingStrategy iStrategy,DequeuingStrategy dStrategy,int xCoor,
    int yCoor) {
        super(time);
        //TODO Auto-generated constructor stub
        this.junct = new Junction (id,iStrategy,dStrategy,xCoor,yCoor);
    }

    @Override
    public void execute(RoadMap map) {
        // TODO Auto-generated method stub
        map.addJunction(this.junct);
    }
    
}

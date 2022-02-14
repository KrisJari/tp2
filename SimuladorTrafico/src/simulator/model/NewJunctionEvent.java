package simulator.model;

public class NewJunctionEvent extends Event{

    public NewJunctionEvent(int time,String id,LightSwitchingStrategy iStrategy,DequeuingStrategy dStrategy,int xCoor,
    int yCoor) {
        super(time);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void execute(RoadMap map) {
        // TODO Auto-generated method stub
        
    }
    
}

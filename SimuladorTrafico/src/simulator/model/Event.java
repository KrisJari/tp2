package simulator.model;

public abstract class Event implements Comparable<Event> {

	protected int _time;//tiempo en el cual este evento tiene que ser ejecutado 

	Event(int time) {
		if (time < 1)
			throw new IllegalArgumentException("Time must be positive (" + time + ")");
		else
			this._time = time;
	}

	protected int getTime() {
		return this._time;
	}

	@Override
	public int compareTo(Event o) {
		// TODO complete the method to compare events according to their _time
		if (this._time == o.getTime()) 
			return 0;
		else if (this._time < o.getTime())
			return -1;
		else
			return 1;
	}

	public abstract void execute(RoadMap map);//el simulador llama a este evento para ejecutar el evento especifico
}

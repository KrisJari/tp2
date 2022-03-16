package simulator.control;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import simulator.factories.Factory;
import simulator.model.Event;
import simulator.model.TrafficSimulator;
import simulator.view.MapComponent;

public class Controller {
	
	private TrafficSimulator ts;
	private Factory<Event> fe;
	
	public Controller(TrafficSimulator sim,Factory<Event> eventsFactory) {
		
		if (sim == null) 
			throw new IllegalArgumentException("Traffic Simulator can't be null");
		if (eventsFactory == null)
			throw new IllegalArgumentException("Events Factory can't be null");
		
		this.ts = sim;
		this.fe = eventsFactory;
	}
	
	public void loadEvents(InputStream in) {
		JSONObject obj = new JSONObject(new JSONTokener(in));
		JSONArray jsonarray= obj.getJSONArray("events");
		
		if (!obj.has("events"))
			throw new IllegalArgumentException();
		
		for (int i = 0; i < jsonarray.length(); i++) {
			ts.addEvent(fe.createInstance(jsonarray.getJSONObject(i)));
		}
	}
	
	public void run(int n, OutputStream out) {
		PrintStream print = new PrintStream(out);
		print.println("{");
		print.println("  \"states\": [");
		
		for (int i = 1; i <= n; i++) {
			ts.advance();
			print.print(ts.report());
			if (i != n) {
				print.println(",");
			}
		}
		
		print.println("\n]");
		print.println("}");

	}
	
	public void reset() {
		ts.reset();
	}

	public void addObserver(MapComponent mapComponent) {
		// TODO Auto-generated method stub
		
	}
}

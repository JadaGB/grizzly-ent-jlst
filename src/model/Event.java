package model;

public class Event {
	
	private int evID;
	private String eventName;
	
	
	public Event() {
		this.evID = 0000;
		this.eventName = "";
	}

	public Event(int evID, String eventName) {
		
		this.evID = evID;
		this.eventName = eventName;
	}
	
	public Event(Event obj) {
		this.evID = obj.evID;
		this.eventName = obj.eventName;
	}

	public int getEvID() {
		return evID;
	}

	public void setEvID(int evID) {
		this.evID = evID;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	@Override
	public String toString() {
		return "Event ID: " + evID + ", Event Name: " + eventName + "\n";
	}
	
	
}

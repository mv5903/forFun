package forFun;

import java.util.ArrayList;

public class Packet {
	
	public String subject;
	public Object message;
	
	public Packet() {
		subject = "No data provided";
		message = null;
	}
	public Packet(String message) {
		subject = "No subject";
		this.message = message;
	}
	public Packet(String subject, String message) {
		this.subject = subject;
		this.message = message;
	}
	public Packet(String subject, ArrayList<String> data) {
		this.subject = subject;
		message = data;
	}
	public String toString() {
		return String.format("%s: %s\n", subject, message);
	}
}

package introsde.assignment3.soap.endpoint;
import introsde.assignment3.soap.UniversityImpl;

import javax.xml.ws.Endpoint;

public class UniversityPublisher {
	public static String SERVER_URL = "http://localhost";
	public static String PORT = "6902";
	public static String BASE_URL = "/ws/university";
	
	public static String getEndpointURL() {
		return SERVER_URL+":"+PORT+BASE_URL;
	}
 
	public static void main(String[] args) {
		String endpointUrl = getEndpointURL();
		System.out.println("Starting University Service...");
		System.out.println("--> Published at = "+endpointUrl);
		Endpoint.publish(endpointUrl, new UniversityImpl());
    }
}


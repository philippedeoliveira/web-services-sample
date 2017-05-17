package clientWS;

import fr.formation.client.ping.Ping;
import fr.formation.client.ping.User;
import fr.formation.client.ping.Users_Service;

public class EntryPoint {

	public static void main(String... args) {
		System.out.println(new Ping().getPingSoapImplPort().ping());
		
		
		for (User u : new Users_Service().getUserServiceImplPort().getUsers().getUsers()) {
			System.out.println(u.getId() + " " + u.getFirstname() + " " + u.getLastname());
		}
	}

}

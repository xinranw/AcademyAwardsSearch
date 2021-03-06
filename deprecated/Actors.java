package deprecated;
import Nominee;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Actors {
	
	private HashMap<String, HashSet<Nominee>> actors;
	
	public Actors() {
		actors = new HashMap<String, HashSet<Nominee>>();
	}
	
	public Nominee[] getNomineesForActor(String actorName){
		Nominee[] nominees = new Nominee[0];
		if (actors.containsKey(actorName)){
			nominees = actors.get(actorName).toArray(new Nominee[0]);
		}
		return nominees;
	}
	
	public void addActorNominee(Nominee nominee){
		String name = nominee.getName();
		if (!actors.containsKey(name)){
			actors.put(name, new HashSet<Nominee>());
		}
		actors.get(name).add(nominee);
	}

	public Set<String> getAllActorNames(){
		return actors.keySet();
	}
}

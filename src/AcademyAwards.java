import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Acts a database to store information about nominees.
 * 
 * @author Xinran Wang
 *
 */

public class AcademyAwards {
	/**
	 * Stores nominees as actors or films
	 * actorDatabase matches name to nominations
	 * filmDatabse matches year to Best Picture nominations
	 */
	private Map<String, Set<Nominee>> actorDatabase;
	private Map<Integer, Set<Nominee>> filmDatabase;
	
	public AcademyAwards(){
		actorDatabase = new HashMap<String, Set<Nominee>>();
		filmDatabase = new HashMap<Integer, Set<Nominee>>();
	}
	
	public void addNominees(Nominee[] nominees){
		for (Nominee n: nominees){
			if (n.isNomineeForActor()){
				addActorNomineeToDatabase(n);
			} else if (n.isAwardForBestPicture()){
				addFilmNomineeToDatabase(n);
			} else {
			}
		}
	}
	
	public Nominee searchForBestPictureWinnerByYear(int year){
		if (!filmDatabase.containsKey(year)){
			return null;
		}
		Set<Nominee> nominees = filmDatabase.get(year);
		if (nominees.isEmpty()){
			return null;
		}
		Nominee winner = null;
		for (Nominee n : nominees){
			if (n.getResult()){
				winner = n;
				break;
			}
		}
		return winner;
	}
	
	public Nominee[] searchForBestPictureNomineesByYear(int year){
		if (!filmDatabase.containsKey(year)){
			return new Nominee[0];
		}
		return filmDatabase.get(year).toArray(new Nominee[0]);
	}
	/**
	 * Gets all actor names. Check whether each name contains the search string.
	 * Return nominations of matched actors
	 */
	public Nominee[] searchForActorNominationsByName(String actorName){
		Set<String> actorNames = actorDatabase.keySet(); 
		Set<Nominee> results = new HashSet<Nominee>();
		for (String name:actorNames){
			if (name.contains(actorName)){
				results.addAll(actorDatabase.get(name));
			}
		}
		return results.toArray(new Nominee[0]);
	}
	
	private void addActorNomineeToDatabase(Nominee nominee){
		String name = nominee.getName();
		if (!actorDatabase.containsKey(name)){
			actorDatabase.put(name, new HashSet<Nominee>());
		}
		actorDatabase.get(name).add(nominee);
	}
	
	private void addFilmNomineeToDatabase(Nominee nominee){
		int year = nominee.getYear();
		if (!filmDatabase.containsKey(year)){
			filmDatabase.put(year, new HashSet<Nominee>());
		}
		filmDatabase.get(year).add(nominee);
	}
}

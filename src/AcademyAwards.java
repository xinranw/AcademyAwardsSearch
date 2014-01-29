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
	 * Stores nominees as actors or films actorDatabase matches name to
	 * nominations filmDatabse matches year to Best Picture nominations
	 */
	private Map<String, Set<Nominee>> actorDatabase;
	private Map<Integer, Set<Nominee>> filmDatabase;

	public AcademyAwards() {
		actorDatabase = new HashMap<String, Set<Nominee>>();
		filmDatabase = new HashMap<Integer, Set<Nominee>>();
	}

	/**
	 * Adds the input array of nominees into the actorDatabase and filmDatabase
	 * fields;
	 * @param nominees
	 */
	public void addAllNominees(Nominee[] nominees) {
		for (Nominee n : nominees) {
			if (n.isNomineeForActor()) {
				addActorNomineeToDatabase(n);
			} else if (n.isNomineeForBestPicture()) {
				addFilmNomineeToDatabase(n);
			} else {
			}
		}
	}

	/**
	 * Performs a search for the Best Picture winner of the input year.
	 * @param year
	 * @return Nominee
	 */
	public Nominee searchForBestPictureWinnerByYear(int year) {
		if (!filmDatabase.containsKey(year)) {
			return null;
		}
		Set<Nominee> nominees = filmDatabase.get(year);
		if (nominees.isEmpty()) {
			return null;
		}
		Nominee winner = null;
		for (Nominee n : nominees) {
			if (n.getResult()) {
				winner = n;
				break;
			}
		}
		return winner;
	}

	/**
	 * Performs a search for the Best Picture nominees of the input year.
	 * @param year
	 * @return Nominee[]
	 */
	public Nominee[] searchForBestPictureNomineesByYear(int year) {
		if (!filmDatabase.containsKey(year)) {
			return new Nominee[0];
		}
		return filmDatabase.get(year).toArray(new Nominee[0]);
	}

	/**
	 * Gets all actor names. Check whether each name contains the search string.
	 * Return nominations of matched actors
	 * If the input is empty, return an empty array.
	 */
	public Nominee[] searchForActorNominationsByName(String actorName) {
		if (actorName.equals("")){
			return new Nominee[0];
		}
		Set<String> actorNames = actorDatabase.keySet();
		Set<Nominee> results = new HashSet<Nominee>();
		for (String name : actorNames) {
			if (name.contains(actorName)) {
				results.addAll(actorDatabase.get(name));
			}
		}
		return results.toArray(new Nominee[0]);
	}

	/**
	 * Adds a Nominee into actorDatabase, using the nominee's name as key
	 * @param nominee
	 */
	private void addActorNomineeToDatabase(Nominee nominee) {
		String name = nominee.getName();
		if (!actorDatabase.containsKey(name)) {
			actorDatabase.put(name, new HashSet<Nominee>());
		}
		actorDatabase.get(name).add(nominee);
	}

	/**
	 * Adds a Nominee into filmDatabase, using the nominee's year as key
	 * @param nominee
	 */
	private void addFilmNomineeToDatabase(Nominee nominee) {
		int year = nominee.getYear();
		if (!filmDatabase.containsKey(year)) {
			filmDatabase.put(year, new HashSet<Nominee>());
		}
		filmDatabase.get(year).add(nominee);
	}
}

import java.util.HashMap;
import java.util.HashSet;

public class Films {
	private HashMap<Integer, HashSet<Nominee>> films;
	
	public Films() {
		films = new HashMap<Integer, HashSet<Nominee>>();
	}
	
	public Nominee[] getNomineesForYear(int year){
		Nominee[] nominees = new Nominee[0];
		if (films.containsKey(year)){
			nominees = films.get(year).toArray(new Nominee[0]);
		}
		return nominees;
	}
	
	public void addFilmNominee(Nominee nominee){
		int year = nominee.getYear();
		if (!films.containsKey(year)){
			films.put(year, new HashSet<Nominee>());
		}
		films.get(year).add(nominee);
	}
	
	public Nominee[] getBestPictureNomineesByYear(int year){
		return films.get(year).toArray(new Nominee[0]);
	}
}

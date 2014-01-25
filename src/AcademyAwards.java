import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class AcademyAwards {
	Actors actorDatabase;
	Films filmDatabase;
	
	public AcademyAwards(){
		actorDatabase = new Actors();
		filmDatabase = new Films();
	}
	
	public void addNominees(Nominee[] nominees){
		for (Nominee n: nominees){
			if (n.isNomineeForActor()){
				actorDatabase.addActorNominee(n);
			} else if (n.getAward().contains("Best Picture")){
				filmDatabase.addFilmNominee(n);
			} else {
				System.out.println("Unnecessary nominee not added: " + n.toString());
			}
		}
	}
	
	public Nominee searchForBestPictureWinnerByYear(int year){
		Nominee[] nominees = filmDatabase.getBestPictureNomineesByYear(year);
		Nominee winner = null;
		for (Nominee n : nominees){
			if (n.getResult()){
				winner = n;
			}
		}
		return winner;
	}
	
	public Nominee[] searchForBestPictureNomineesByYear(int year){
		return filmDatabase.getBestPictureNomineesByYear(year);
	}
	
	public Nominee[] searchForActorNominationsByName(String actorName){
		Set<String> actorNames = actorDatabase.getAllActorNames();
		ArrayList<Nominee> results = new ArrayList<Nominee>(); 
		List<Nominee> nominations;
		for (String name:actorNames){
			if (name.contains(actorName)){
				nominations = Arrays.asList(actorDatabase.getNomineesForActor(name));
				results.addAll(nominations);
			}
		}
		return results.toArray(new Nominee[0]);
	}
}

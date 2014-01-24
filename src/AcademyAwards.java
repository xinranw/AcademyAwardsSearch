public class AcademyAwards {
	Actors actors;
	
	public AcademyAwards(){
	}
	
	public void addNominees(Nominee[] nominees){
		for (Nominee n: nominees){
			if (n.getAward().contains("actor") || n.getAward().contains("actress")){
				actors.addActorNominee(n);
			} else if (n.getAward().contains("Best Picture")){
				
			} else {
				System.out.println("Unexpected nominee. Not added.");
			}
		}
	}
	
	public void searchForBestPictureWinnerByYear(){
		
	}
	
	public void searchForBestPictureNomineesByYear(){
		
	}
	
	
	public void searchForActorNominationsByName(){
		
	}
}

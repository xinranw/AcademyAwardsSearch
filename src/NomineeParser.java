public class NomineeParser {
	
	public NomineeParser(){
	}

	public Nominee extractDataAndOutputNominee(String dataString){
		String[] nomineeInfo = dataString.split(",");
		int year = extractYear(nomineeInfo[0]);
		String award = extractAward(nomineeInfo[1]);
		String name = extractName(nomineeInfo[2]);
		boolean won = extractResult(nomineeInfo[3]);
		return new Nominee(year, award, name, won);
	}

	public static boolean isValidNominee(String line){
		String[] nomineeInfo = line.split(",");
        if (nomineeInfo.length != 4){
        	return false;
        }
        if (!isAwardForActor(nomineeInfo[1]) && !isAwardForBestPicture(nomineeInfo[1])){
        	return false;
        }
		return true;
	}

	public static boolean isAwardForActor(String awardName){
		if (awardName.contains("Actor") || awardName.contains("Actress")){
			return true;
		}
		return false;
	}  
	
	public static boolean isAwardForBestPicture(String awardName){
		return awardName.contains("Best Picture");
	}
	
	private int extractYear(String input){
		int output = 0;
		if (input.contains("/")){
			output = Integer.parseInt(input.substring(0, 2)+input.substring(5, 7));
		} else {
			output = Integer.parseInt(input.substring(0, 4));
		}
		return output;
	}
	
	private String extractAward(String input){		
		return input;
	}
	
	private String extractName(String input){		
		return input;
	}
	
	private boolean extractResult(String input){
		return input.endsWith("YES");
	}	
}

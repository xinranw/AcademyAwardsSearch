import java.io.BufferedReader;
import java.io.Console;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class AcademyAwardsSearch {
	private static File logFile;
	private static AcademyAwards awardsDatabase;
	private static boolean quit = false; 
	private static final String[] HOME_SCREEN_CHOICES = new String[]{
			 "1: Search for best picture award winner by year",
			 "2: Search for best picture award nominees by year",
			 "3: Search for actor/actress nominations by name",
			 "Q: Quit"};
	
	public static final void main(String[] args) {
		if (args.length <= 0 || args[0].equals("")){
			System.out.println("Error - please enter the names of the data file and the log file.");
			System.out.println("usage: java -jar AcademyAwardsSearch.jar <data file> <log file>");
			return;
		} else if (args.length > 2){
			System.out.println("Error - too many inputs.");
			System.out.println("usage: java -jar AcademyAwardsSearch.jar <data file> <log file>");
			return;
		}
		
		
		try {
			String dataFileName = args[0];
			Nominee[] nominees = createNomineesFromDataFile(dataFileName);
			awardsDatabase = new AcademyAwards();
			awardsDatabase.addNominees(nominees);
			String inputString;
			int userSelection = 0;
			
			System.out.println("Welcome to the Oscars database!\n");
			
			while(!quit){
				System.out.println("Please make your selection:");
				displayHomeScreen();
				inputString = getUserInput("> ");
				if (inputString.equals("q")){
					quit();
				} else {
					userSelection = convertStringToInt(inputString);
					switch (userSelection) {
			            case 1:
			            	searchForBestPictureWinnerByYear();
			                break;
			            case 2:  
			            	searchForBestPictureNomineesByYear();
		                    break;
			            case 3:  
			            	searchForActorNominationsByName();
			            	break;
			            default:
			            	System.out.println("Error: unexpected input: " + userSelection + ".");
			            	break;
					}
				}
			}
		} catch (IOException e) {
			System.out.println("Error - data file could not opened. ");
			System.out.println("Please check the file exists and this program has sufficient permissions.");
			e.printStackTrace();
		}
	}

	private static Nominee[] createNomineesFromDataFile(String dataFileName) throws IOException{
		BufferedReader reader = null;
		File file = new File(dataFileName);
	    reader = new BufferedReader(new FileReader(file));
	    String line;
	    NomineeParser nomineeParser = new NomineeParser();
	    ArrayList<Nominee> nominees = new ArrayList<Nominee>(); 
	    
	    while ((line = reader.readLine()) != null) {
	        if (!NomineeParser.isValidNominee(line)){
	        	continue;
	        } 
	        nominees.add(nomineeParser.extractDataAndOutputNominee(line));
	    }
	    reader.close();
	    return nominees.toArray(new Nominee[0]);
	}
	
	private static void displayHomeScreen(){
		for (int i = 0; i < HOME_SCREEN_CHOICES.length; i++){
			System.out.println(HOME_SCREEN_CHOICES[i]);
		}
	}
	
	private static String parseString(String str){
		String parsedString = str;
		parsedString = parsedString.toLowerCase();
		parsedString = parsedString.trim();
		return parsedString;
	}
	
	private static String getUserInput(String prompt){
		Console console = System.console();
		String input = parseString(console.readLine(prompt));
		return input;
	}
	
	private static int convertStringToInt(String str){
		int number = 0;
		try{
			number = Integer.parseInt(str);
//			if (selection < 1 || selection > numberOfOptions){
//				throw new IllegalArgumentException();
//			}
		} catch (IllegalArgumentException e){
			System.out.println("That is not a valid selection.");
//			selection = 0;
		}
		return number;
	}
	
	private static void searchForBestPictureNomineesByYear(){
		String yearString = getUserInput("Please enter the year: ");
		int year = convertStringToInt(yearString);
    	Nominee[] nominees = awardsDatabase.searchForBestPictureNomineesByYear(year);
    	if (nominees.length == 0){
			System.out.println("No results found for year " + year);
			searchForBestPictureNomineesByYear();
		}
    	for (Nominee n : nominees){
    		System.out.println(n.getName());
    	}
	}
	
	private static void searchForBestPictureWinnerByYear(){
		String yearString = getUserInput("Please enter the year: ");
		int year = convertStringToInt(yearString);
		Nominee winner = awardsDatabase.searchForBestPictureWinnerByYear(year);
		if (winner == null){
			System.out.println("No nominees for year " + year);
			searchForBestPictureWinnerByYear();
		}
		System.out.println(winner.getName());
	}
	
	private static void searchForActorNominationsByName(){
		String name = getUserInput("Please enter all or part of the person's name: ");
    	Nominee[] results = awardsDatabase.searchForActorNominationsByName(name);
    	if (results.length == 0){
			System.out.println("No results found for " + name);
		} else {
			for (Nominee n : results){
				System.out.println(n.getName() + " was nominated for " + n.getAward() +" in " + n.getYear());
			}
		}
	}
	
	private static void quit(){
		quit = true;
	}
}


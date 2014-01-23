import java.io.BufferedReader;
import java.io.Console;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


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
			
			while(!quit){
				System.out.println("Welcome to the Oscars database!\n");
				displayHomeScreen();
				inputString = getUserInput();
				try{
					userSelection = convertUserInputToInt(inputString, 3);
					switch (userSelection) {
						case -1:
							quit();
							break;
			            case 1:  
			                break;
			            case 2:  
		                    break;
			            case 3:  
			            	break;
			            default:
			            	displayInvalidInputError(3);
			            	break;
					}
				} catch (IllegalArgumentException e){
					displayInvalidInputError(3);
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
	
	private static String getUserInput(){
		Console console = System.console();
		String input = parseString(console.readLine("> "));
		return input;
	}
	
	private static int convertUserInputToInt(String input, int numberOfOptions) throws IllegalArgumentException{
		if (input.equals("q")){
			return -1;
		}
		int selection = Integer.parseInt(input);
		if (selection < 1 || selection > numberOfOptions ){
			throw new IllegalArgumentException();
		} 
		return selection;
	}
	
	
	private static void displayInvalidInputError(int numberOfOptions){
		String options = "";
		for (int i = 1; i <= numberOfOptions; i++){
			options = options.concat(i + ", ");
		}
		options = options.concat("or Q");
		System.out.println("Invalid input. Valid inputs are: " + options);
		System.out.println("Please try again.");
	}
	
	private static void quit(){
		quit = true;
	}
}


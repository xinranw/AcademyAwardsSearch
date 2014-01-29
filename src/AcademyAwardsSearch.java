import java.io.BufferedReader;
import java.io.Console;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Creates a database of Academy Award nominees as specified by the data file.
 * Provides functionality for searching for Best Picture Winners by year Best
 * Picture nominees by year Best Actor/Supporting Actor nominations by name
 * 
 * All user interactions are logged to the specified log file.
 * 
 * @author Xinran Wang
 * 
 */
public class AcademyAwardsSearch {
	private static File logFile;
	private static AcademyAwards awardsDatabase;

	public static final void main(String[] args) {
		if (args.length <= 0 || args[0].equals("")) {
			System.out
					.println("Error - please enter the names of the data file and the log file.");
			System.out
					.println("usage: java AcademyAwardsSearch <data file> <log file>");
			return;
		} else if (args.length == 1) {
			System.out.println("Error - not enough inputs.");
			System.out
					.println("usage: java AcademyAwardsSearch <data file> <log file>");
			return;
		} else if (args.length > 2) {
			System.out.println("Error - too many inputs.");
			System.out
					.println("usage: java AcademyAwardsSearch <data file> <log file>");
			return;
		}

		try {
			// Set up nominees database from the data file
			String dataFileName = args[0];
			Nominee[] nominees = createNomineesFromDataFile(dataFileName);
			awardsDatabase = new AcademyAwards();
			awardsDatabase.addAllNominees(nominees);

			// Set up log file
			logFile = new File(args[1]);
			checkLogFileAccessibility();

			// Display prompts, get user input, and search for nominations
			System.out.println("Welcome to the Oscars database!\n");
			while (true) {
				displayHomeScreenOptions();
				String inputString = getUserInput("> ");
				if (inputString.equals("q")) {
					System.exit(0);
				}
				// Get user input and call appropriate function
				Integer userSelection = convertStringToInt(inputString);
				if (userSelection == null) {
					System.out.println("That is not a valid selection.\n");
					continue;
				}
				switch (userSelection.intValue()) {
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
					System.out.println("That is not a valid selection.\n");
					break;
				}
			}
		} catch (IOException e) {
			System.out.println("Error - data file could not opened. ");
			System.out
					.println("Please check the file exists and this program has sufficient permissions.");
			e.printStackTrace();
		}
	}

	/**
	 * Parse through each line of a given data file. Converts string data to
	 * Nominees
	 * 
	 * @param dataFileName
	 * @return Nominee[]
	 * @throws IOException
	 */
	private static Nominee[] createNomineesFromDataFile(String dataFileName)
			throws IOException {
		File file = new File(dataFileName);
		BufferedReader reader = new BufferedReader(new FileReader(file));
		ArrayList<Nominee> nominees = new ArrayList<Nominee>();

		String line = "";
		while ((line = reader.readLine()) != null) {
			if (!NomineeParser.isValidNominee(line)) {
				continue;
			}
			nominees.add(NomineeParser.parseStringIntoNominee(line));
		}
		reader.close();
		return nominees.toArray(new Nominee[0]);
	}

	/**
	 * Writes a string to the log file
	 * 
	 * @param message
	 */
	private static void writeToLogFile(String message) {
		checkLogFileAccessibility();
		try {
			PrintWriter writer = new PrintWriter(new FileWriter(logFile, true));
			writer.println("User typed '" + message + "' at time="
					+ System.currentTimeMillis());
			writer.close();
		} catch (IOException e) {
			System.out
					.println("Unexpected error writing to log file. Program quitting.");
			System.exit(1);
		}
	}

	/**
	 * Check whether log file has been initialized, exists, and is writeable. If
	 * log file is not accessible, exit the program.
	 */
	private static void checkLogFileAccessibility() {
		if (logFile == null) {
			System.out.println("No log file on record. Program exiting");
			System.exit(1);
		}
		if (!logFile.exists()) {
			System.out.println("Log file could not be found. Program exiting");
			System.exit(1);
		}
		if (!logFile.canWrite()) {
			System.out.println("Cannot write to log file. Program exiting");
			System.exit(1);
		}
	}

	/**
	 * Display the main selection options
	 */
	private static void displayHomeScreenOptions() {
		System.out.println("Please make your selection:");
		System.out.println("1: Search for best picture award winner by year\n"
				+ "2: Search for best picture award nominees by year\n"
				+ "3: Search for actor/actress nominations by name\n"
				+ "Q: Quit");
	}

	/**
	 * Displays a message to the user and gets his response from console
	 * 
	 * @param prompt
	 * @return String
	 */
	private static String getUserInput(String prompt) {
		Console console = System.console();
		String input = console.readLine(prompt);
		String parsedString = input.toLowerCase().trim();
		writeToLogFile(parsedString);
		return parsedString;
	}

	/**
	 * Parses a string to an Integer object
	 * 
	 * @param str
	 * @return Integer
	 */
	private static Integer convertStringToInt(String str) {
		Integer number = null;
		// Check if the input is a number
		try {
			number = Integer.parseInt(str);
		} catch (IllegalArgumentException e) {
			return null;
		}
		return number;
	}

	/**
	 * Performs a search in AcademyAwards for Best Picture nominees by year.
	 * Repeatedly prompts the user for a year if the input is not valid.
	 */
	private static void searchForBestPictureNomineesByYear() {
		while (true) {
			String yearString = getUserInput("Please enter the year: ");
			Integer year = convertStringToInt(yearString);
			if (year == null) {
				System.out.println("That is not a valid input.\n");
				continue;
			}
			Nominee[] nominees = awardsDatabase
					.searchForBestPictureNomineesByYear(year);
			if (nominees.length == 0) {
				System.out.println("No results found for year " + year + "\n");
				continue;
			}
			for (Nominee n : nominees) {
				System.out.println(n.getName());
			}
			System.out.println("");
			break;
		}
		return;
	}

	/**
	 * Performs a search in AcademyAwards for the Best Picture winner by year.
	 * Repeatedly prompts the user for a year if the input is not valid.
	 */
	private static void searchForBestPictureWinnerByYear() {
		while (true) {
			String yearString = getUserInput("Please enter the year: ");
			Integer year = convertStringToInt(yearString);
			if (year == null) {
				System.out.println("That is not a valid input.\n");
				continue;
			}
			Nominee winner = awardsDatabase
					.searchForBestPictureWinnerByYear(year);
			if (winner == null) {
				System.out.println("No nominees for year " + year + "\n");
				continue;
			}
			System.out.println(winner.getName());
			System.out.println("");
			break;
		}
		return;
	}

	/**
	 * Performs a search in AcademyAwards for all movies for which an actor has
	 * been nominated.
	 * Asks for another input if the current query returns no results.
	 */
	private static void searchForActorNominationsByName() {
		while (true){
			String name = getUserInput("Please enter all or part of the person's name: ");
			Nominee[] results = awardsDatabase
					.searchForActorNominationsByName(name);
			if (results.length == 0) {
				System.out.println("No results found for " + name + "\n");
				continue;
			}
			for (Nominee n : results) {
				System.out.println(n.getName() + " was nominated for "
						+ n.getAward() + " in " + n.getYear());
			}
			System.out.println("");
			break;
		}
		return;
	}
}

/**
 * NomineeParser is a library containing methods for parsing strings into
 * Nominee objects.
 * 
 * @author Xinran
 * 
 */
public class NomineeParser {

	/**
	 * Converts a line of data into a Nominee object
	 */
	public static Nominee parseStringIntoNominee(String dataString) {
		String[] nomineeInfo = dataString.split(",");
		int year = extractYear(nomineeInfo[0]);
		String award = extractAward(nomineeInfo[1]);
		String name = extractName(nomineeInfo[2]);
		boolean won = extractResult(nomineeInfo[3]);
		return new Nominee(year, award, name, won);
	}

	/**
	 * Checks if the given line contains a valid nominee. The input needs to be
	 * split by commas into exactly 4 substrings.
	 * 
	 * @param line
	 * @return boolean
	 */
	public static boolean isValidNominee(String line) {
		String[] nomineeInfo = line.split(",");
		if (nomineeInfo.length != 4) {
			return false;
		}
		return true;
	}

	private static int extractYear(String input) {
		int output = 0;
		if (input.contains("/")) {
			output = Integer.parseInt(input.substring(0, 2)
					+ input.substring(5, 7));
		} else {
			output = Integer.parseInt(input.substring(0, 4));
		}
		return output;
	}

	private static String extractAward(String input) {
		return input;
	}

	private static String extractName(String input) {
		return input;
	}

	private static boolean extractResult(String input) {
		return input.endsWith("YES");
	}
}

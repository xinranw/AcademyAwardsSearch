import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;


public class AcademyAwardsSearch {

	public static void main(String[] args) {
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
			readDataFile(args[0]);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error - data file could not opened. ");
			System.out.println("Please check the file exists and this program has sufficient permissions.");
			e.printStackTrace();
		}
	}

	private static void readDataFile(String dataFileName) throws IOException{
		BufferedReader reader = null;
		File file = new File(dataFileName);
	    reader = new BufferedReader(new FileReader(file));
	    String line;
	    NomineeParser nomineeParser = new NomineeParser();
	    while ((line = reader.readLine()) != null) {
	        if (!NomineeParser.isValidNominee(line)){
	        	continue;
	        }
	        Nominee nominee = nomineeParser.extractDataAndOutputNominee(line);
	        System.out.println(nominee.toString());
	    }
	    reader.close();
	}
}

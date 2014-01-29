/**
 * @author Xinran
 *
 */

public class Nominee {
	private int year;
	private String award;
	private String name;
	private boolean won;
	
	public Nominee(int year, String award, String name, boolean won){
		this.year = year;
		this.award = award;
		this.name = name;
		this.won = won;
	}	

	public boolean isNomineeForActor(){
		if (award.contains("Actor") || award.contains("Actress")){
			return true;
		}
		return false;
	}  
	
	public boolean isNomineeForBestPicture(){
		return award.contains("Best Picture");
	}
	
	public int getYear(){
		return year;
	}
	
	public String getAward(){
		return award;
	}
	
	public String getName(){
		return name;
	}
	
	public boolean getResult(){
		return won;
	}
	
	public String toString(){
		String output = "";
		output = output.concat("Year: " + year + ", Award: " + award + ", Name: " + name);
		output = won ? output.concat(", Result: " + "YES") : output.concat(", Result: " + "NO");
		return output;
	}
}

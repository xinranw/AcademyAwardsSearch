import static org.junit.Assert.*;

import org.junit.*;


public class AcademyAwardsTest {
	private AcademyAwards empty = new AcademyAwards();
	Nominee actor1 = new Nominee(1999, "Best Actor", "actor1", false);
	Nominee actor2 = new Nominee(1998, "Best Actor", "actor1", true);
	Nominee film1 = new Nominee(1999, "Best Picture", "film1", true);
	Nominee film2 = new Nominee(1999, "Best Picture", "film2", false);

	@Test
	public final void testSearchForBestPictureWinnerByYear() {
		assertNull("Empty AcademyAwards returns a null winner", 
				empty.searchForBestPictureWinnerByYear(1999));
		AcademyAwards multiFilms = new AcademyAwards();
		multiFilms.addAllNominees(new Nominee[]{film1});
		assertEquals("Awards with 1 film returns film1 for 1999 Best Picture",
				"film1",
				multiFilms.searchForBestPictureWinnerByYear(1999).getName());
	}

	@Test
	public final void testSearchForBestPictureNomineesByYear() {
		assertEquals("Empty AcademyAwards returns an empty array", 
				0,
				empty.searchForBestPictureNomineesByYear(1999).length);	
		AcademyAwards multiFilms = new AcademyAwards();
		multiFilms.addAllNominees(new Nominee[]{film1});
		assertEquals("Awards with 1 film returns 1 for 1999 Best Picture nominees",
				1,
				multiFilms.searchForBestPictureNomineesByYear(1999).length);
		multiFilms.addAllNominees(new Nominee[]{film2});
		assertEquals("Awards with two 1999 films returns 2 for 1999 Best Picture nominees",
				2,
				multiFilms.searchForBestPictureNomineesByYear(1999).length);
	}

	@Test
	public final void testSearchForActorNominationsByName() {
		assertEquals("Empty AcademyAwards returns an empty array",
				0,
				empty.searchForActorNominationsByName("blah").length);
		AcademyAwards multiActors = new AcademyAwards();
		multiActors.addAllNominees(new Nominee[]{actor1});
		assertEquals("awards with 1 actor returns 1",
				1,
				multiActors.searchForActorNominationsByName("actor1").length);
		multiActors.addAllNominees(new Nominee[]{actor2});
		assertEquals("awards with 1 actor with two nominations",
				2,
				multiActors.searchForActorNominationsByName("actor1").length);
	}

}

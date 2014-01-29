import static org.junit.Assert.*;

import org.junit.Test;

public class NomineeTest {
	Nominee actor = new Nominee(1999, "Actor", "James Bond", false);
	Nominee film = new Nominee(1999, "Best Picture", "Her", true);

	@Test
	public final void testIsNomineeForActor() {
		assertTrue("Nominee with Actor in award name should return true",
				actor.isNomineeForActor());
		assertFalse("Nominee without Actor in award name should return false",
				film.isNomineeForActor());
	}

	@Test
	public final void testIsAwardForBestPicture() {
		assertFalse("Nominee with Actor in award name should return false",
				actor.isNomineeForBestPicture());
		assertTrue("Nominee with Film in award name should return true",
				film.isNomineeForBestPicture());
	}

	@Test
	public final void testGetYear() {
		assertEquals("Actor's year should be 1999", 1999, actor.getYear());
	}

	@Test
	public final void testGetAward() {
		assertEquals("Film's award should be 'Best Picture'", "Best Picture",
				film.getAward());
	}

	@Test
	public final void testGetName() {
		assertEquals("Actor's name should be 'James Bond'", "James Bond",
				actor.getName());
	}

	@Test
	public final void testGetResult() {
		assertFalse("Actor did not win", actor.getResult());
		assertTrue("Film did win", film.getResult());
	}


}

import static org.junit.Assert.*;
import org.junit.Test;

public class NomineeParserTest {

	@Test
	public final void tooManyComponentsNominee() {
		assertFalse("Nominee has more than 4 components",
				NomineeParser.isValidNominee("1, 2, 3, 4, 5"));
	}

	@Test
	public final void tooFewComponentsNominee() {
		assertFalse("Nominee has fewer than 4 components",
				NomineeParser.isValidNominee("1, 2, 3"));
	}

	@Test
	public final void actorShouldBeAValidNominee() {
		assertTrue("Actors are valid nominees",
				NomineeParser.isValidNominee("1, Actor, 3, 4"));
		assertTrue("Actresses are valid nominees",
				NomineeParser.isValidNominee("1, Actress, 3, 4"));
	}

	@Test
	public final void bestpictureShouldBeAValidNominee() {
		assertTrue("Best picture award is a valid nominee",
				NomineeParser.isValidNominee("1, Best Picture, 3, 4"));
	}
}

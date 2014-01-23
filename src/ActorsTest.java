import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class ActorsTest {

	@Test
	public final void testNewActorsObjectIsEmpty() {
		Actors actors = new Actors();
		assertEquals("Newly initialized Actors should be empty", actors.getAllActorNames().length, 0);
	}
	
	@Test
	public final void getNomineesForActor(){
		Actors actors = new Actors();
		assertEquals("An actor name not in an Actors object returns an empty array", actors.getNomineesForActor("test").length, 0);
	}
	
	@Test
	public final void addActorNomineePair(){
		Actors actors = new Actors();
		Nominee testNominee1 = new Nominee(1950, "Actor", "Actor1", false);
		Nominee testNominee2 = new Nominee(1950, "Actor", "Actor2", false);
		Nominee testNominee3 = new Nominee(1960, "Actor", "Actor1", false);
		actors.addActorNomineePair(testNominee1);
		assertEquals("Add actor-nominee pair once", actors.getAllActorNames().length, 1);
		actors.addActorNomineePair(testNominee1);
		assertEquals("Add same actor-nominee pair twice", actors.getAllActorNames().length, 1);
		actors.addActorNomineePair(testNominee2);
		assertEquals("Add new actor-nominee pair", actors.getAllActorNames().length, 2);
		actors.addActorNomineePair(testNominee3);
		assertEquals("Add new actor-nominee pair with same name", actors.getNomineesForActor(testNominee3.getName()).length, 2);
	}
	
	

}

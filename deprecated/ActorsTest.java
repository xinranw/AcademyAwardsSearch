package deprecated;
import static org.junit.Assert.*;
import Nominee;

import org.junit.Test;


public class ActorsTest {

	@Test
	public final void testNewActorsObjectIsEmpty() {
		Actors actors = new Actors();
		assertEquals("Newly initialized Actors should be empty", actors.getAllActorNames().size(), 0);
	}
	
	@Test
	public final void getNomineesForActor(){
		Actors actors = new Actors();
		assertEquals("An actor name not in an Actors object returns an empty array", actors.getNomineesForActor("test").length, 0);
	}
	
	@Test
	public final void addActorNominee(){
		Actors actors = new Actors();
		Nominee testNominee1 = new Nominee(1950, "Actor", "Actor1", false);
		Nominee testNominee2 = new Nominee(1950, "Actor", "Actor2", false);
		Nominee testNominee3 = new Nominee(1960, "Actor", "Actor1", false);
		actors.addActorNominee(testNominee1);
		assertEquals("Add actor-nominee once", actors.getAllActorNames().size(), 1);
		actors.addActorNominee(testNominee1);
		assertEquals("Add same actor-nominee twice", actors.getAllActorNames().size(), 1);
		actors.addActorNominee(testNominee2);
		assertEquals("Add new actor-nominee", actors.getAllActorNames().size(), 2);
		actors.addActorNominee(testNominee3);
		assertEquals("Add new actor-nominee with same name", actors.getNomineesForActor(testNominee3.getName()).length, 2);
	}
	
	

}

package fi.haagahelia.janttonen.movieapp;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import fi.haagahelia.janttonen.movieapp.domain.Actor;
import fi.haagahelia.janttonen.movieapp.domain.ActorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class ActorRepositoryTests {

	@Autowired
	private ActorRepository arepo;
	
	// find all actors
	@Test
	public void findAllByName() {
		Iterable<Actor> actors = arepo.findAll();
		assertThat(actors).hasSize(10);
		assertThat(actors.iterator().next().getName()).isEqualTo("Timothée Chalamet");
	}
	
	//find all actors from certain movie
	//search from the first movie
	@Test
	public void findActorsByMovieId(){
	List<Actor> actors = arepo.findActorsByMovieId((long) 1);
	assertThat(actors).hasSize(4);
	assertThat(actors.iterator().next().getName()).isEqualTo("Timothée Chalamet");
	}
	
	//save a new actor
	public void createNewActor() {
		Actor testActor = new Actor(null , "Test Actor");
		arepo.save(testActor);
		assertThat(testActor.getId()).isNotNull();
	}
	
	//delete an actor
	@Test
    public void deleteActorById() {
        arepo.deleteById((long) 1);
        assertThat(arepo.count()).isEqualTo(9);
    }
	
}

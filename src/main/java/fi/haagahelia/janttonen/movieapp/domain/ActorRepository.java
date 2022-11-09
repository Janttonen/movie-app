package fi.haagahelia.janttonen.movieapp.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ActorRepository extends CrudRepository<Actor, Long>{
	
	List<Actor> findByName(String title);

	List<Actor> findActorsByMovieId(long movieId);
}

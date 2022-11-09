package fi.haagahelia.janttonen.movieapp.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<Movie, Long>{
	//find all movies
	public Iterable<Movie> findAllByOrderByIdAsc();
	
	List<Movie> findByTitle(String title);
	
}

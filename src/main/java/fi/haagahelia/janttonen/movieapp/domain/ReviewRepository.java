package fi.haagahelia.janttonen.movieapp.domain;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ReviewRepository extends CrudRepository<Review, Long> {


	@Query("SELECT avg(r.points) FROM Review r JOIN Movie m ON m.id = r.movie.id")
	double avgPoints(); 
	
	List<Review> findReviewsByMovieId(Long movieId);
}

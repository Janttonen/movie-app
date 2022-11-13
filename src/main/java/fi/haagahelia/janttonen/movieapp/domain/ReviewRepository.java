package fi.haagahelia.janttonen.movieapp.domain;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends CrudRepository<Review, Long> {


	@Query("SELECT avg(r.points) FROM Review r JOIN Movie m ON m.id = r.movie.id WHERE m.id =:movieId")
	double avgPoints(@Param("movieId") Long movieId); 
	
	List<Review> findReviewsByMovieId(Long movieId);
}

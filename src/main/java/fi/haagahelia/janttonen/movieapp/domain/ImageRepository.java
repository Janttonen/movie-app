package fi.haagahelia.janttonen.movieapp.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ImageRepository extends CrudRepository<Image, Long>{
	List<Image> findByMovieId(Long movieId);

}

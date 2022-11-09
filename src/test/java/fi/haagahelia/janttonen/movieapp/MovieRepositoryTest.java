package fi.haagahelia.janttonen.movieapp;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import fi.haagahelia.janttonen.movieapp.domain.Movie;
import fi.haagahelia.janttonen.movieapp.domain.MovieRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class MovieRepositoryTest {

	@Autowired
	private MovieRepository mrepo;
	
	// find all movies
	@Test
	public void findAllByOrderByIdAsc() {
		Iterable<Movie> movies = mrepo.findAllByOrderByIdAsc();
		assertThat(movies).hasSize(3);
		assertThat(movies.iterator().next().getTitle()).isEqualTo("Dune");
	}

	//find director
	@Test 
    public void findByTitleShouldReturnDirector() {
		List<Movie> movie = mrepo.findByTitle("Star Wars");
        
        assertThat(movie).hasSize(1);
        assertThat(movie.get(0).getDirector()).isEqualTo("George Lucas");
    }
	
	// save a movie
	@Test
	public void createNewMovie() {
		Movie testMovie = new Movie("Parasite", "Bong Joon Ho", "testdemo", 2019, null);
		mrepo.save(testMovie);
		
		assertThat(testMovie.getId()).isNotNull();
	}
	
	//delete movie byId(3)
	//if a list has 3 movie
	@Test
    public void deleteMovieById() {
        mrepo.deleteById((long) 3);
        assertThat(mrepo.count()).isEqualTo(2);
    }
}

package fi.haagahelia.janttonen.movieapp;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import fi.haagahelia.janttonen.movieapp.domain.Genre;
import fi.haagahelia.janttonen.movieapp.domain.GenreRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class GenreRepositoryTests {

	@Autowired
	private GenreRepository grepo;
	
	// find all genres
	@Test
	public void findAllGenres() {
		Iterable<Genre> genres = grepo.findAll();
		assertThat(genres).hasSize(3);
		assertThat(genres.iterator().next().getName()).isEqualTo("Sci Fi");
	}
	
	//save a new genre
	public void createNewGenre() {
		Genre testGenre = new Genre("Test Genre");
		grepo.save(testGenre);
		assertThat(testGenre.getId()).isNotNull();
	}
	
	//delete a genre
	@Test
    public void deleteGenreById() {
        grepo.deleteById((long) 1);
        assertThat(grepo.count()).isEqualTo(2);
    }
	
}

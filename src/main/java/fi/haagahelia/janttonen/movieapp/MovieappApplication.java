package fi.haagahelia.janttonen.movieapp;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.haagahelia.janttonen.movieapp.domain.Actor;
import fi.haagahelia.janttonen.movieapp.domain.ActorRepository;
import fi.haagahelia.janttonen.movieapp.domain.Genre;
import fi.haagahelia.janttonen.movieapp.domain.GenreRepository;
import fi.haagahelia.janttonen.movieapp.domain.Movie;
import fi.haagahelia.janttonen.movieapp.domain.MovieRepository;

@SpringBootApplication
public class MovieappApplication {

	private static final Logger log = LoggerFactory.getLogger(MovieappApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(MovieappApplication.class, args);
	}

	@Bean
	public CommandLineRunner addTestData(MovieRepository mrepo, ActorRepository arepo, GenreRepository grepo){
			return (args) -> {
				
				log.info("Create some genres");
				Genre g1 = new Genre("Sci Fi");
				Genre g2 = new Genre("Space Opera");
				Genre g3 = new Genre("Horror");
				grepo.save(g1);
				grepo.save(g2);
				grepo.save(g3);
				
				log.info("Save couple of movies");
				Movie m1 = new Movie("Dune", "Denis Villeneuve", "Sikke hyvä", 2021, g1);
				Movie m2 = new Movie("Star Wars", "George Lucas", "Hassu", 1977, g2);
				Movie m3 = new Movie("poisto", "George Lucas", "Hassu", 1977, g2);
				mrepo.save(m1);
				mrepo.save(m2);
				mrepo.save(m3);
				
				log.info("Add some actors");
				arepo.save(new Actor(m1, "Timothée Chalamet"));
				arepo.save(new Actor(m2, "Mark Hamill"));
				arepo.save(new Actor(m2, "Harrison Ford"));
				arepo.save(new Actor(m1, "minna"));
				
			}; 
	}
}

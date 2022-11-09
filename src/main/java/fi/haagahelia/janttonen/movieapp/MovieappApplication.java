package fi.haagahelia.janttonen.movieapp;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import fi.haagahelia.janttonen.movieapp.domain.Actor;
import fi.haagahelia.janttonen.movieapp.domain.ActorRepository;
import fi.haagahelia.janttonen.movieapp.domain.Genre;
import fi.haagahelia.janttonen.movieapp.domain.GenreRepository;
import fi.haagahelia.janttonen.movieapp.domain.Movie;
import fi.haagahelia.janttonen.movieapp.domain.MovieRepository;
import fi.haagahelia.janttonen.movieapp.domain.Review;
import fi.haagahelia.janttonen.movieapp.domain.ReviewRepository;
import fi.haagahelia.janttonen.movieapp.domain.User;
import fi.haagahelia.janttonen.movieapp.domain.UserRepository;

@SpringBootApplication
public class MovieappApplication {

	private static final Logger log = LoggerFactory.getLogger(MovieappApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(MovieappApplication.class, args);
	}

	@Bean
	public CommandLineRunner addTestData(MovieRepository mrepo, ActorRepository arepo, GenreRepository grepo, ReviewRepository rrepo, UserRepository urepo){
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
				
				log.info("Save a few reviews");
				rrepo.save(new Review(m1, "käyttäjänä koi tämän olevan liian vaikeaaa hyi", 3.5));
				rrepo.save(new Review(m1, "miten näitäkin ajetaan", 2));
				rrepo.save(new Review(m2, "jassoo", 5));
				
				log.info("Create some users");
				User u1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "user@gmail.com", "USER");
				User u2 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "admin@gmail.com", "ADMIN");
				urepo.save(u1);
				urepo.save(u2);
			};
	
			
	}
}

package fi.haagahelia.janttonen.movieapp;

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
import fi.haagahelia.janttonen.movieapp.domain.Image;
import fi.haagahelia.janttonen.movieapp.domain.ImageRepository;
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
	public CommandLineRunner addTestData(MovieRepository mrepo, ActorRepository arepo, GenreRepository grepo,
			ReviewRepository rrepo, UserRepository urepo, ImageRepository irepo) {
		return (args) -> {

			log.info("Create some genres");
			Genre g1 = new Genre("Sci Fi");
			Genre g2 = new Genre("Space Opera");
			Genre g3 = new Genre("Thriller");
			grepo.save(g1);
			grepo.save(g2);
			grepo.save(g3);
			
			log.info("Save couple of movies");
			Movie m1 = new Movie("Dune", "Denis Villeneuve",
					"A mythic and emotionally charged hero's journey, \"Dune\" tells the story of Paul Atreides, a brilliant and gifted young man born into a great destiny beyond his understanding, who must travel to the most dangerous planet in the universe to ensure the future of his family and his people. As malevolent forces explode into conflict over the planet's exclusive supply of the most precious resource in existence-a commodity capable of unlocking humanity's greatest potential-only those who can conquer their fear will survive",
					2021, g1);
			Movie m2 = new Movie("Star Wars: Episode IV - A New Hope", "George Lucas",
					"The Imperial Forces, under orders from cruel Darth Vader, hold Princess Leia hostage in their efforts to quell the rebellion against the Galactic Empire. Luke Skywalker and Han Solo, captain of the Millennium Falcon, work together with the companionable droid duo R2-D2 and C-3PO to rescue the beautiful princess, help the Rebel Alliance and restore freedom and justice to the Galaxy.",
					1977, g2);
			Movie m3 = new Movie("Snowpiercer", "\n" + "Bong Joon Ho",
					"Set in 2031, the entire world is frozen except for those aboard the Snowpiercer. For seventeen years, the world's survivors are on a train hurtling around the globe creating their own economy and class system. Led by Curtis (Chris Evans), a group of lower-class citizens living in squalor at the back of the train are determined to get to the front of the train and spread the wealth around. Each section of the train holds new surprises for the group who have to battle their way through. A revolution is underway.",
					2013, g3);
			Movie m4 = new Movie("Testi", "moi", "hahaha", 2021, g1);
			mrepo.save(m1);
			mrepo.save(m2);
			mrepo.save(m3);
			mrepo.save(m4);
			

			log.info("Add some actors");
			arepo.save(new Actor(m1, "Timothée Chalamet"));
			arepo.save(new Actor(m1, "Rebecca Ferguron"));
			arepo.save(new Actor(m1, "Zendaya"));
			arepo.save(new Actor(m1, "Oscar Isac"));
			arepo.save(new Actor(m2, "Mark Hamill"));
			arepo.save(new Actor(m2, "Harrison Ford"));
			arepo.save(new Actor(m2, "Carrie Fisher"));
			arepo.save(new Actor(m3, "Chris Evans"));
			arepo.save(new Actor(m3, "Jamie Bell"));
			arepo.save(new Actor(m3, "Tilda Swinton"));

			log.info("Save a few reviews");
			rrepo.save(new Review(m1,
					"m1 Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum lectus arcu, aliquet et eros in, semper accumsan lectus. Maecenas gravida, libero nec facilisis laoreet, ex nunc elementum orci, eget accumsan tellus ex quis sem. Cras facilisis ultricies arcu, at porttitor nibh blandit interdum. Praesent id venenatis sapien. Quisque commodo sed ex id vestibulum. Curabitur nec condimentum metus, vel ultricies erat. Vestibulum ultricies pharetra lorem in tempor. Aliquam pharetra faucibus vehicula. Nunc ac tincidunt augue. Etiam est arcu, auctor nec tincidunt at, aliquet non arcu. Sed nec scelerisque lacus. Praesent pretium urna at fringilla suscipit. Cras pretium, nisl at iaculis pellentesque, nulla mi tincidunt nibh, nec vulputate magna risus eget elit. Praesent ullamcorper turpis finibus, sodales risus eget, elementum elit.",
					3.5));
			rrepo.save(new Review(m1,
					"m1 Aliquam vel pellentesque felis. Sed hendrerit tempus congue. In hac habitasse platea dictumst. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum eleifend convallis neque nec vulputate. Duis gravida id magna id hendrerit. Aenean eget massa aliquet, volutpat ligula eu, placerat erat. Maecenas mattis malesuada lacus, sit amet iaculis enim congue quis. In iaculis cursus tellus non vestibulum.",
					2));
			rrepo.save(new Review(m2,
					"m2Integer eu ornare mauris. Curabitur porta ante eros, in consectetur nisi tincidunt vitae. Vivamus placerat fermentum bibendum. Fusce nec malesuada diam. Pellentesque iaculis nisl et neque vestibulum luctus. Integer luctus faucibus congue. Sed in dolor et ex pharetra vehicula. Quisque ultrices sodales tempus. Nullam porttitor in magna ut tempor. ",
					5.0));
			rrepo.save(new Review(m2,
					"m2 Morbi facilisis, elit quis tempor consequat, neque massa dignissim velit, vel vestibulum ex ex a enim. Curabitur magna elit, consectetur non diam sit amet, feugiat pellentesque sem. ",
					5));
			rrepo.save(new Review(m2,
					"m2 Maecenas eget ante enim. Nullam interdum porttitor enim ac fringilla. Nullam nisi justo, mattis vitae bibendum at, placerat id ex. Cras faucibus vitae erat id varius. Praesent convallis elit ut diam viverra luctus. Nullam cursus, felis eget pharetra faucibus, quam dolor eleifend arcu, et commodo tortor magna in metus.",
					5));
			rrepo.save(new Review(m3,
					"m3 Integer eu ornare mauris. Curabitur porta ante eros, in consectetur nisi tincidunt vitae. Vivamus placerat fermentum bibendum. Fusce nec malesuada diam. Pellentesque iaculis nisl et neque vestibulum luctus. Integer luctus faucibus congue. Sed in dolor et ex pharetra vehicula. Quisque ultrices sodales tempus. Nullam porttitor in magna ut tempor. ",
					2.5));
			rrepo.save(new Review(m3,
					"m3 Integer eu ornare mauris. Curabitur porta ante eros, in consectetur nisi tincidunt vitae. Vivamus placerat fermentum bibendum. Fusce nec malesuada diam. Pellentesque iaculis nisl et neque vestibulum luctus. Integer luctus faucibus congue. Sed in dolor et ex pharetra vehicula. Quisque ultrices sodales tempus. Nullam porttitor in magna ut tempor. ",
					4.5));
			rrepo.save(new Review(m3,
					"m3 Maecenas eget ante enim. Nullam interdum porttitor enim ac fringilla. Nullam nisi justo, mattis vitae bibendum at, placerat id ex. Cras faucibus vitae erat id varius. Praesent convallis elit ut diam viverra luctus. Nullam cursus, felis eget pharetra faucibus, quam dolor eleifend arcu, et commodo tortor magna in metus.",
					3));
			rrepo.save(new Review(m3, "m3 moikku", 2.5));

			log.info("Create some users");
			User u1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "user@gmail.com",
					"USER");
			User u2 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C",
					"admin@gmail.com", "ADMIN");
			urepo.save(u1);
			urepo.save(u2);

			log.info("save some images to movie");
			irepo.save(new Image(m2, "/imgfolder/swpic1.jpg",
					"https://www.pexels.com/fi-fi/kuva/taivas-yo-avaruus-vuori-2670898"));
			irepo.save(new Image(m2, "/imgfolder/swpic2.jpg", "https://unsplash.com/@joelfilip"));
			irepo.save(new Image(m1, "/imgfolder/dynepic1.jpg", "https://unsplash.com/@weirick"));
			irepo.save(new Image(m3, "/imgfolder/sppic1.jpg",
					"https://unsplash.com/s/photos/train?utm_source=unsplash&utm_medium=referral&utm_content=creditCopyText"));
			irepo.save(new Image(m4, "/imgfolder/errorpic.jpg", "haha"));
			
			log.info("fetch all genres");
			for(Genre genre : grepo.findAll()) {
				log.info(genre.toString());
			}
			
			log.info("fetch all movies");
			for(Movie movie: mrepo.findAll()) {
				log.info(movie.toString());
			}
			
			log.info("fetch all actors");
			for(Actor actor: arepo.findAll()) {
				log.info(actor.toString());
			}
			
			log.info("fetch all reviews");
			for(Review review: rrepo.findAll()) {
				log.info(review.toString());
			}
			
			log.info("fetch all images");
			for(Image image: irepo.findAll()) {
				log.info(image.toString());
			}
		};
	}
}

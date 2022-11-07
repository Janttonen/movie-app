package fi.haagahelia.janttonen.movieapp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.server.ResponseStatusException;

import fi.haagahelia.janttonen.movieapp.domain.Actor;
import fi.haagahelia.janttonen.movieapp.domain.GenreRepository;
import fi.haagahelia.janttonen.movieapp.domain.Movie;
import fi.haagahelia.janttonen.movieapp.domain.MovieRepository;

@Controller
public class MovieController {

	@Autowired
	private MovieRepository mrepo;

	@Autowired
	private GenreRepository grepo;

	// Show the movie app by default
	@GetMapping("/")
	public String redirectRoot() {
		return "redirect:/movie-app";
	}

	// Show a list of movies
	@GetMapping("/movie-app")
	public String listMovies(Model model) {
		model.addAttribute("movies", mrepo.findAllByOrderByIdAsc());
		return "movieapp";
	}

	// get certain movie in order to view more details
	// find genres and actors
	@GetMapping("/movie-app/{id}")
	public String findMovies(@PathVariable(name = "id") Long movieId, Model model) {
		Movie movie = mrepo.findById(movieId).orElse(null);
		if (movie == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		model.addAttribute("movie", movie);
		return "viewmovie";
	}

	// Show a list of movies in editpage
	@GetMapping("/edit-page")
	public String listAllMovies(Model model) {
		model.addAttribute("movies", mrepo.findAllByOrderByIdAsc());
		return "editpage";
	}

	// show a from for adding new movie and category
	@GetMapping("/add-movie")
	public String addNewMovie(Model model) {
		model.addAttribute("movie", new Movie());
		model.addAttribute("genres", grepo.findAll());
		return "addmovie";
	}

	// saving the new movie (with genre)
	@PostMapping("/save-movie")
	public String saveMovie(Movie movie) {
		mrepo.save(movie);
		return "redirect:/edit-movie/" + movie.getId();
	}

	// editing movie
	// adding some actors
	@GetMapping("/edit-movie/{id}")
	public String editMovie(@PathVariable("id") Long movieId, Model model) {
		Movie movie = mrepo.findById(movieId).orElse(null);
		if (movie == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		model.addAttribute("movie", movie);
		model.addAttribute("genres", grepo.findAll());
		model.addAttribute("newActor", new Actor(movie));
		return "editmovie";
	}

	// saving changes
	@PostMapping("../save")
	public String saveChanges(Movie movie) {
		mrepo.save(movie);
		return "redirect:/movieapp";
	}
	
	//delete movie (admin)
	@GetMapping("/delete-movie/{id}")
	    public String deleteMovie(@PathVariable("id") Long movieId) {
	    	mrepo.deleteById(movieId);
	        return "redirect:/edit-page";
	    }  

}

package fi.haagahelia.janttonen.movieapp.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import fi.haagahelia.janttonen.movieapp.domain.Actor;
import fi.haagahelia.janttonen.movieapp.domain.Movie;
import fi.haagahelia.janttonen.movieapp.domain.MovieRepository;
import fi.haagahelia.janttonen.movieapp.domain.Review;
import fi.haagahelia.janttonen.movieapp.domain.ReviewRepository;

@Controller
public class ReviewController {

	@Autowired
	private ReviewRepository rrepo;

	@Autowired
	private MovieRepository mrepo;

	// show all reviews from a certain movie
	@GetMapping("/movie-app/all-reviews/{id}")
	public String listAllReviews(@PathVariable("id") Long movieId, Model model) {
		Movie movie = mrepo.findById(movieId).orElse(null);
		if (movie == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

		model.addAttribute("movie", movie);
		model.addAttribute("reviews", rrepo.findAll());
		return "reviewpage";
	}

	// add a new review to a movie
	@GetMapping("/movie-app/add-review/{id}")
	public String addReview(@PathVariable("id") Long movieId, Model model) {
		Movie movie = mrepo.findById(movieId).orElse(null);
		if (movie == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

		
		model.addAttribute("movie", movie);
		model.addAttribute("newReview", new Review(movie));
		return "addreview";
	}

	// save review
	@PostMapping("/movie-app/save-review")
	public String saveReview(@Valid Review review, BindingResult result) {
		if (result.hasErrors()) {
            return "/movie-app/all-reviews/" + review.getMovie().getId();
        }
        else {
        	rrepo.save(review);
            return "redirect:/movie-app/all-reviews/" + review.getMovie().getId();
        }
	}

	// Handle the form for deleting a review
	//Only admin can delete reviews
	@PostMapping("/admin/delete-review")
	public String deleteReview(@RequestParam Long reviewId, @RequestParam Long movieId) {
		rrepo.deleteById(reviewId);
		return "redirect:/all-reviews/" + movieId;
	}
}

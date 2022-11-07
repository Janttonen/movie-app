package fi.haagahelia.janttonen.movieapp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import fi.haagahelia.janttonen.movieapp.domain.Genre;
import fi.haagahelia.janttonen.movieapp.domain.GenreRepository;

public class GenreController {

	@Autowired
	private GenreRepository grepo;

	@GetMapping("/genre-list")
	public String showGenre(Model model) {
		model.addAttribute("genres", grepo.findAll());
		return "genrelist";
		}
	
	//genrelist show only to an admin
	// show a list of existing genres and add more
	@GetMapping("/edit-genres")
	public String addAndShowGenre(Model model) {
		model.addAttribute("genres", grepo.findAll());
		model.addAttribute("newGenre", new Genre());
		return "genrelist";
	}

	// save a genre
	@PostMapping("/save-genres")
	public String saveGenre(Genre genre) {
		grepo.save(genre);
		return "redirect:/edit-genre/";
	}

	// Handle the form for deleting a genre
	@PostMapping("/delete-genres/{id}")
	public String deleteGenre(@PathVariable(name = "id") Long genreId) {
		grepo.deleteById(genreId);
		return "redirect:/edit-genre/";
	}

}

package fi.haagahelia.janttonen.movieapp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fi.haagahelia.janttonen.movieapp.domain.Genre;
import fi.haagahelia.janttonen.movieapp.domain.GenreRepository;

@Controller
public class GenreController {

	@Autowired
	private GenreRepository grepo;

	// genrelist shown only to an admin
	// show a list of existing genres and add more
	@GetMapping("/admin/edit-genres")
	public String addAndShowGenre(Model model) {
		model.addAttribute("genres", grepo.findAll());
		model.addAttribute("newGenre", new Genre());
		return "genrelist";
	}

	// save a genre
	@PostMapping("/admin/save-genres")
	public String saveGenre(Genre genre) {
		grepo.save(genre);
		return "redirect:/admin/edit-genres/";
	}

	// Handle the form for deleting a genre
	@PostMapping("/admin/delete-genres")
	public String deleteGenre(@RequestParam Long genreId) {
		grepo.deleteById(genreId);
		return "redirect:/admin/edit-genres/";
	}

}

package fi.haagahelia.janttonen.movieapp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import fi.haagahelia.janttonen.movieapp.domain.Actor;
import fi.haagahelia.janttonen.movieapp.domain.ActorRepository;

@Controller
public class ActorController {

	@Autowired
	private ActorRepository arepo;

	// Save actor when done changes in edit mode
	@PostMapping("/admin/save-actor")
	public String saveActor(Actor actor) {
		arepo.save(actor);
		return "redirect:/admin/edit-movie/" + actor.getMovie().getId();
	}

	// Handle the form for deleting an actor
	@PostMapping("/admin/delete-actor")
	public String deleteActor(@RequestParam Long actorId, @RequestParam Long movieId) {
		arepo.deleteById(actorId);
		return "redirect:/admin/edit-movie/" + movieId;
	}
}

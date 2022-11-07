package fi.haagahelia.janttonen.movieapp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import fi.haagahelia.janttonen.movieapp.domain.ImageRepository;

@Controller
public class ImageController {

	@Autowired
	private ImageRepository irepo;
	
	//show images any site
	
}

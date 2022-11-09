package fi.haagahelia.janttonen.movieapp;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import fi.haagahelia.janttonen.movieapp.web.MovieController;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class MovieappApplicationTests {

	@Autowired
	private MovieController mc;

	@Test
	public void contextLoads() {
		assertThat(mc).isNotNull();
		assertThat("MovieApp").contains("Movie");
	}
}

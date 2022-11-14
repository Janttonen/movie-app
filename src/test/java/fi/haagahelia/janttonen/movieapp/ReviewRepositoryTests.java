package fi.haagahelia.janttonen.movieapp;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import fi.haagahelia.janttonen.movieapp.domain.Review;
import fi.haagahelia.janttonen.movieapp.domain.ReviewRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class ReviewRepositoryTests {

	@Autowired
	private ReviewRepository rrepo;
	
	// find all reviews
	@Test
	public void findAllReviews() {
		Iterable<Review> reviews = rrepo.findAll();
		assertThat(reviews).hasSize(10);
		assertThat(reviews.iterator().next().getUserReview()).isEqualTo("Very good movie! Loved it!");
	}
	
	//find all review from certain movie
	//search from the second movie
	@Test
	public void findReviewsByMovieId(){
	List<Review> reviews = rrepo.findReviewsByMovieId((long) 1);
	assertThat(reviews).hasSize(3);
	assertThat(reviews.iterator().next().getUserReview()).isEqualTo("Very good movie! Loved it!");
	}
	
	//save a new review
	public void createNewReview() {
		Review testReview = new Review(null , "Test Review", 4.5);
		rrepo.save(testReview);
		assertThat(testReview.getId()).isNotNull();
	}
	
	//delete an review
	@Test
    public void deleteReviewById() {
        rrepo.deleteById((long) 1);
        assertThat(rrepo.count()).isEqualTo(9);
    }
	
}

package fi.haagahelia.janttonen.movieapp.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "reviewId")
	private Long id;

	// many reviews to one movie
	@ManyToOne
	@JsonIgnoreProperties("reviews")
	@JoinColumn(name = "movieId")
	private Movie movie;

	@NotBlank
	private String userReview;
	
	@NotNull
	@Min(value=0, message="must be equal or greater than 0")  
    @Max(value=5, message="must be equal or less than 5")  
	private double points;

	public Review() {
	}

	public Review(Movie movie) {
		super();
		this.movie = movie;
	}

	public Review(Movie movie, String userReview, double points) {
		super();
		this.movie = movie;
		this.userReview = userReview;
		this.points = points;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public String getUserReview() {
		return userReview;
	}

	public void setUserReview(String userReview) {
		this.userReview = userReview;
	}

	public double getPoints() {
		return points;
	}

	public void setPoints(double points) {
		this.points = points;
	}

	@Override
	public String toString() {
		if (this.movie != null) {
			return "Review [id=" + id + ", movie=" + this.getMovie() + ", userReview=" + userReview + ", points="
					+ points + "]";
		} else {
			return "Review [id=" + id + ", userReview=" + userReview + ", points=" + points + "]";
		}
	}

}

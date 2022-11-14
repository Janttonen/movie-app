package fi.haagahelia.janttonen.movieapp.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "movieId")
	private Long id;

	@NotBlank
	private String title;
	@NotBlank
	private String director;
	@NotBlank
	@Column(name = "description", columnDefinition = "LONGTEXT")
	private String description;

	@NotNull
	@Column(name = "publishing_year")
	private int year;

	// multiple movies in one genre
	@ManyToOne
	@JsonIgnoreProperties("movies")
	@JoinColumn(name = "genreId")
	private Genre genre;

	// multiple actors in one movies
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "movie")
	@JsonIgnoreProperties("movie")
	private List<Actor> actors;

	// multiple reviews in one movies
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "movie")
	@JsonIgnoreProperties("movie")
	private List<Review> reviews;

	// multiple images to one movie
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "movie")
	private List<Image> imageUrls;

	public Movie() {
	}

	public Movie(String title, String director, String description, int year, Genre genre) {
		super();
		this.title = title;
		this.director = director;
		this.description = description;
		this.year = year;
		this.genre = genre;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public List<Actor> getActors() {
		return actors;
	}

	public void setActors(List<Actor> actors) {
		this.actors = actors;
	}

	public List<Image> getImageUrls() {
		return imageUrls;
	}

	public void setImageUrls(List<Image> imageUrls) {
		this.imageUrls = imageUrls;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	@Override
	public String toString() {
		if (this.genre != null) {
			return "Movie [id=" + id + ", title=" + title + ", director=" + director + ", description=" + description
					+ ", year=" + year + ", genre=" + this.getGenre() + "]";
		} else {
			return "Movie [id=" + id + ", title=" + title + ", director=" + director + ", description=" + description
					+ ", year=" + year + "]";
		}
	}

}

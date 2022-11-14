package fi.haagahelia.janttonen.movieapp.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "actors")
public class Actor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "actorId")
	private Long id;

	// many actors to one movie
	@ManyToOne
	@JsonIgnoreProperties("actors")
	@JoinColumn(name = "movieId")
	private Movie movie;

	@NotBlank
	private String name;

	public Actor() {
	}

	public Actor(Movie movie) {
	}

	public Actor(Movie movie, String name) {
		super();
		this.movie = movie;
		this.name = name;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		if (this.movie != null) {
			return "Actor [id=" + id + ", movie=" + this.getMovie() + ", name=" + name + "]";
		} else {
			return "Actor [id=" + id + ", name=" + name + "]";
		}
	}
}

package fi.haagahelia.janttonen.movieapp.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Genre {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "genreId")
	private Long id;
	private String name;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "genre")
	private List<Movie> movies;

	public Genre() {
	}
	
	public Genre(String name) {
		super();
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Movie> getMovies() {
		return movies;
	}

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}

	@Override
	public String toString() {
		return "Genre [id=" + id + ", name=" + name + "]";
	}
}

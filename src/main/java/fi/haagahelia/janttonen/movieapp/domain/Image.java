package fi.haagahelia.janttonen.movieapp.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Image {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="imageId")
	private Long id;
	
	//multiple images to one movie
	@ManyToOne
	@JsonIgnoreProperties("images")
	@JoinColumn(name = "movieId")
	private Movie movie;
	
	
	private String image;
	private String imgSource;

	public Image() {
	}
	
	public Image(Movie movie) {
		this.movie = movie;
	}
	
	
	public Image(Movie movie, String image, String imgSource) {
		super();
		this.movie = movie;
		this.image = image;
		this.imgSource = imgSource;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getImgSource() {
		return imgSource;
	}

	public void setImgSource(String imgSource) {
		this.imgSource = imgSource;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}


	@Override
	public String toString() {
		return "Image [id=" + id + ", image=" + image + ", imgSource=" + imgSource + "]";
	}
}

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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "imageId")
	private Long id;

	// multiple images to one movie
	@ManyToOne
	@JsonIgnoreProperties("imageUrls")
	@JoinColumn(name = "movieId")
	private Movie movie;

	private String imageUrl;
	private String imgSource;

	public Image() {
	}

	public Image(Movie movie) {
		this.movie = movie;
	}

	public Image(Movie movie, String imageUrl, String imgSource) {
		super();
		this.movie = movie;
		this.imageUrl = imageUrl;
		this.imgSource = imgSource;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
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
		if (this.movie != null) {
			return "Image [id=" + id + ", imageUrl=" + imageUrl + ", imgSource=" + imgSource + ", movie="
					+ this.getMovie() + "]";
		} else {
			return "Image [id=" + id + ", imageUrl=" + imageUrl + ", imgSource=" + imgSource + "]";
		}
	}
}

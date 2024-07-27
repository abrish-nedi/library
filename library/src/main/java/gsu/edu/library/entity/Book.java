package gsu.edu.library.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="booktable")
public class Book {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="author")
	private String author;
	@Column(name="genre")
	private String genre;
	@Column(name="title")
	private String title;
	@Column(name="year_published")
	private int yearPublished;
	@Column(name="copies")
	private int copies;
	@Column(name="rental_status")
	private String rentalStatus;
	@Column(name="user_id")
	private Integer userId;
	
	public Book() {}

	
	public Book(int id, String author, String genre, String title, int yearPublished, int copies, String rentalStatus,
			Integer userId) {
		super();
		this.id = id;
		this.author = author;
		this.genre = genre;
		this.title = title;
		this.yearPublished = yearPublished;
		this.copies = copies;
		this.rentalStatus = rentalStatus;
		this.userId = userId;
	}


	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getYearPublished() {
		return yearPublished;
	}
	public void setYearPublished(int yearPublished) {
		this.yearPublished = yearPublished;
	}
	public int getCopies() {
		return copies;
	}
	public void setCopies(int copies) {
		this.copies = copies;
	}
	public String getRentalStatus() {
		return rentalStatus;
	}
	public void setRentalStatus(String rentalStatus) {
		this.rentalStatus = rentalStatus;
	}
	
	
}



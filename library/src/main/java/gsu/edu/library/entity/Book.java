package gsu.edu.library.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="books")
public class Book {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="isbn")
	private String isbn;
	@Column(name="author")
	private String author;
	@Column(name="title")
	private String title;
	@Column(name="rental_status")
	private String rentalStatus;
	
	public Book() {}
	public Book(String isbn, String author, String title, String rentalStatus) {
		super();
		this.isbn = isbn;
		this.author = author;
		this.title = title;
		this.rentalStatus = rentalStatus;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getRentalStatus() {
		return rentalStatus;
	}
	public void setRentalStatus(String rentalStatus) {
		this.rentalStatus = rentalStatus;
	}
	
	
}

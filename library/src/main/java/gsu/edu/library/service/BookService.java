package gsu.edu.library.service;

import gsu.edu.library.entity.Book;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public interface BookService {

	@Autowired
	public List<Book> books();
	public Book addBook(Book book);
	public Book editBook(Book book);
	public Book deleteBook(Book book);
	public List<Book> searchBooksByTitle(String title);

}

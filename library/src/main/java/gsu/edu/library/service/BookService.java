package gsu.edu.library.service;

import java.util.List;

import gsu.edu.library.entity.Book;

public interface BookService {

	public List<Book> books();
	public Book addBook(Book book);
	public Book editBook(Book book);
	public Book deleteBook(Book book);
}

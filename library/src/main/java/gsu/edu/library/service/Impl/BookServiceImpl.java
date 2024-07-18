package gsu.edu.library.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import gsu.edu.library.entity.Book;
import gsu.edu.library.repository.BookRepository;
import gsu.edu.library.service.BookService;

public class BookServiceImpl implements BookService{
	
	private BookRepository bookRepository;
	
	@Autowired
	public BookServiceImpl(BookRepository bookRepository) {
		super();
		this.bookRepository = bookRepository;
	}

	@Override
	public List<Book> books() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Book addBook(Book book) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Book editBook(Book book) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Book deleteBook(Book book) {
		// TODO Auto-generated method stub
		return null;
	}

}

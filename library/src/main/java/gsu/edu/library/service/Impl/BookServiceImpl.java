package gsu.edu.library.service.Impl;

import gsu.edu.library.entity.Book;
import gsu.edu.library.repository.BookRepository;
import gsu.edu.library.service.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> books() {
        return bookRepository.findAll();
    }

    @Override
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book editBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book deleteBook(Book book) {
        bookRepository.delete(book);
        return book;
    }

    @Override
    public List<Book> searchBooksByTitle(String title) {
        return bookRepository.findByTitleContaining(title);
    }
}

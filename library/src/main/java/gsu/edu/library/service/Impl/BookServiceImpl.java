package gsu.edu.library.service.Impl;

import gsu.edu.library.entity.Book;
import gsu.edu.library.entity.Rent;
import gsu.edu.library.repository.BookRepository;
import gsu.edu.library.repository.RentRepository;
import gsu.edu.library.service.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
	 @Autowired
    private  BookRepository bookRepository;
	 @Autowired
    private  RentRepository rentRepository;

//    @Autowired
//    public BookServiceImpl(BookRepository bookRepository) {
//        this.bookRepository = bookRepository;
//    }
//    @Autowired
//    public BookServiceImpl(RentRepository rentRepository) {
//        this.rentRepository = rentRepository;
//    }

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

	@Override
	public Book getById(int bookId) {		
		 return bookRepository.findById(bookId).orElseThrow(() -> null);
	}

	@Override
	public Rent rent(int bookId, int userId) {
		Rent rent = new Rent();
		rent.setBookId(bookId);
		rent.setUserId(userId);
		return rentRepository.save(rent);
	}

	@Override
	public List<Rent> findAllRentByUserId(int userId) {
		return rentRepository.findAllByUserId(userId);
	}

	@Override
	public void returnBook(int bookId, int userId) {
		List<Rent> rents = rentRepository.findAllByUserId(userId);
		if(null!=rents) {
			for(Rent r:rents) {
				if(r.getBookId()==bookId) {
					rentRepository.deleteById(r.getId());
				}
			}
		}
	}
}

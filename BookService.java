package gsu.edu.library.service;

import gsu.edu.library.entity.LibraryAllBooks;

public interface BookService {

    @Autowired
    private BookRepo bookRepo;

    public List<Book> searchBooksByTitle(String title) {
        return BookRepo.findByTitleContaining(title);
    }
}
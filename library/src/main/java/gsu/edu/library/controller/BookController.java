package gsu.edu.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gsu.edu.library.entity.Book;
import gsu.edu.library.service.BookService;


//import gsu.edu.library.service.BookService;

@Controller
public class BookController {
	
	@Autowired
	private BookService bookService;

	
	@GetMapping("/search")
    public List<Book> searchBooks(@RequestParam("title") String title) {
        return bookService.searchBooksByTitle(title);
    }

	//////////////////////////////////////////////start display all book////////////////////
	@GetMapping("/books")
	public String getAllBooks(Model model) {
        List<Book> books = bookService.books();
        
        model.addAttribute("books", books);
        
        return "books/all_books";  
    }
	
	@PostMapping("/books/delete/{id}")
    public String deleteBook(@PathVariable("id") int id) {
        // Retrieve the book by its ID
        Book book = bookService.books().stream()
                .filter(b -> b.getId() == id)
                .findFirst()
                .orElse(null);

        if (book != null) {
            bookService.deleteBook(book);
        }

        return "redirect:/books"; 
    }
//////////////////////////////////////////////end display all book////////////////////	
	
//////////////////////////////////////////////start add book////////////////////	
	@GetMapping("/addbook")
	public String getPageForAddBooks(Model model) {
		//Do something
		return "books/add_books";
	}
	@PostMapping("/addbook")
	public String postAddBooks(Model model) {
		//Do something
		return "redirect:/books";
	}
//////////////////////////////////////////////end add book////////////////////	
	
//////////////////////////////////////////////start add book////////////////////	
//@GetMapping("/editbook")
//public String getPageForEditBooks(Model model) {
////Do something
//return "books/add_books";
//}
//@PostMapping("/addbook")
//public String postEditBooks(Model model) {
////Do something
//return "redirect:/books";
//}
//////////////////////////////////////////////end add book////////////////////	
	
}

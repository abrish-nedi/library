package gsu.edu.library.controller;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

//import gsu.edu.library.service.BookService;

@Controller
public class BookController {
	
//	private BookService bookService;
//	@Autowired
//    public BookController(BookService bookService) {
//		this.bookService = bookService;
//	}

	//////////////////////////////////////////////start display all book////////////////////
	@GetMapping("/books")
	public String allBooks(Model model) {
		//Do something
		return "books/all_books";
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

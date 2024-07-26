package gsu.edu.library.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gsu.edu.library.entity.Book;
import gsu.edu.library.entity.DropDownOption;
import gsu.edu.library.entity.LibraryUser;
import gsu.edu.library.entity.Rent;
import gsu.edu.library.service.BookService;
import gsu.edu.library.service.LibraryUserService;


//import gsu.edu.library.service.BookService;

@Controller
public class BookController {
	
	@Autowired
	private BookService bookService;
	@Autowired
    private LibraryUserService userService;
	
	@GetMapping("/search")
    public List<Book> searchBooks(@RequestParam("title") String title) {
        return bookService.searchBooksByTitle(title);
    }

	//////////////////////////////////////////////start display all book////////////////////
	@GetMapping("/books/{userId}")
	public String getAllBooks(@PathVariable("userId") int userId,Model model) {
        List<Book> books = bookService.books();        
        model.addAttribute("books", books);
        
        if(userId!=0) {
        LibraryUser theUser = userService.findById(userId);
        model.addAttribute("theUserId", userId);
        if(null!=theUser && theUser.getUserType().equals("Admin")) {
        	model.addAttribute("isAdmin", true);
        	model.addAttribute("isCustomer", false);
        }else {
        	model.addAttribute("isCustomer", true);
        	model.addAttribute("isAdmin", false);
        }
        }else {
        	model.addAttribute("isAdmin", true);
        	model.addAttribute("isCustomer", false);
        }
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

        return "redirect:/books/0"; 
    }
//////////////////////////////////////////////end display all book////////////////////	
	
//////////////////////////////////////////////start add book////////////////////	
	@GetMapping("/addbook")
	public String getPageForAddBooks(Model model) {
		Book theBook= new Book();
		model.addAttribute("thebook", theBook);	
		
        List<DropDownOption> typeList = new ArrayList<>();
        typeList.add(new DropDownOption("Free", "Free"));
        typeList.add(new DropDownOption("Rented", "Rented"));        
        model.addAttribute("bookstatus", typeList);
        model.addAttribute("selectedOption", new String());
		return "books/add_books";
	}
	@PostMapping("/addbook")
	public String postAddBooks(@RequestParam("selectedOption") String selectedOption, @ModelAttribute("libraryuser") Book theBook,Model model) {
		theBook.setRentalStatus(selectedOption);
		bookService.addBook(theBook);
		return "redirect:/books/0";
	}
//////////////////////////////////////////////end add book////////////////////	
	
//////////////////////////////////////////////start edit book////////////////////	
    @GetMapping("/editbook/{bookId}")
	public String getPageForEditBooks(@PathVariable("bookId") int id, Model model) {
    	Book theBook= bookService.getById(id);
    	if(null==theBook) {
    		theBook= new Book();
    	}
		model.addAttribute("thebook", theBook);	
		
        List<DropDownOption> typeList = new ArrayList<>();
        typeList.add(new DropDownOption("Free", "Free"));
        typeList.add(new DropDownOption("Rented", "Rented"));        
        model.addAttribute("bookstatus", typeList);
        model.addAttribute("selectedOption", theBook.getRentalStatus());
        
	return "books/edit_books";
	}
	@PostMapping("/editbook")
	public String postEditBooks(@RequestParam("selectedOption") String selectedOption, @ModelAttribute("libraryuser") Book theBook,Model model) {
		theBook.setRentalStatus(selectedOption);
		bookService.addBook(theBook);
	return "redirect:/books/0";
	}
//////////////////////////////////////////////end edit book////////////////////	
//////////////////////////////////////////////start rent////////////////////	
    @GetMapping("/rent/{theUserId}/{bookId}")
	public String getPageForRentBooks(@PathVariable("bookId") int bookId,@PathVariable("theUserId") int userId, Model model) {
    	Book theBook= bookService.getById(bookId);
    	theBook.setUserId(userId);
		model.addAttribute("thebook", theBook);		
        
	return "books/detail_book";
	}
	@PostMapping("/rent")
	public String postRentBooks( @ModelAttribute("libraryuser") Book theBook,Model model) {	
		theBook.setCopies(theBook.getCopies()-1);
		bookService.rent(theBook.getId(), theBook.getUserId());
		bookService.addBook(theBook);
	return "redirect:/rentedbooks/"+theBook.getUserId();//will change
	}
//////////////////////////////////////////////end rent book////////////////////	
	
//////////////////////////////////////////////start rent books////////////////////	
	@GetMapping("/rentedbooks/{theUserId}")
	public String getRentedBooks(@PathVariable("theUserId") int userId, Model model) {
		List<Rent> rents = bookService.findAllRentByUserId(userId);
		List<Book> books = new ArrayList<>();
		if(null!=rents && !rents.isEmpty()) {
			for(Rent r: rents) {
				books.add(bookService.getById(r.getBookId()));
			}
		}
		model.addAttribute("books", books);
		model.addAttribute("theUserId", userId);
		return "books/all_rented_books";
	}
	@PostMapping("/rentedbooks/{theUserId}/{bookId}")
	public String postreturnBooks( @PathVariable("bookId") int bookId,@PathVariable("theUserId") int userId,Model model) {	
		Book theBook= bookService.getById(bookId);
		theBook.setCopies(theBook.getCopies()+1);
		bookService.addBook(theBook);
		bookService.returnBook(bookId,userId);
		return "redirect:/books/"+userId;
	}
//////////////////////////////////////////////end rent book////////////////////	
}

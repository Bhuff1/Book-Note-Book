package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import service.*;
import book.ReviewedBook;
import formobjects.ReviewedBookForm;

@Controller
public class IndexController {
	@GetMapping("/index")
    public String formGet(Model model) {
		BookService bookService = new BookService();
		ArrayList<ReviewedBook> reviewedBooks = bookService.getRecordedBooks(1);
		model.addAttribute("reviewedbookform", new ReviewedBookForm());
		model.addAttribute("books", reviewedBooks);
        return "index";
    }
	
	@PostMapping("/index")
	public String formPost(@ModelAttribute ReviewedBookForm rbf, Model model) {
		rbf.setUserId(1);
		BookService bookService = new BookService();
		bookService.addToRecordedBooks(rbf);
		ArrayList<ReviewedBook> reviewedBooks = bookService.getRecordedBooks(1);
		model.addAttribute("books", reviewedBooks);
		model.addAttribute("reviewedbookform", new ReviewedBookForm());
        return "index";
    }  
	
	@GetMapping("/deleteReviewedBook/{isbn}/{userId}")
	public String deleteBook(@PathVariable("isbn") String isbn, @PathVariable("userId") int userId, Model model) {
		BookService bookService = new BookService();
		bookService.deleteFromRecordedBooks(isbn, userId);
		ArrayList<ReviewedBook> reviewedBooks = bookService.getRecordedBooks(1);
		model.addAttribute("books", reviewedBooks);
		model.addAttribute("reviewedbookform", new ReviewedBookForm());
	    return "redirect:/index";
	}
	
	

}

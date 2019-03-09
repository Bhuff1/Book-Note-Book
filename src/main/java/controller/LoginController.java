package controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import book.ReviewedBook;
import formobjects.LoginForm;
import formobjects.ReviewedBookForm;
import formobjects.InvalidLogin;
import service.BookService;

@Controller
public class LoginController {

	@GetMapping("/login")
    public String formGet(Model model) {
		InvalidLogin il = new InvalidLogin();
		il.setIsValid(false);
		model.addAttribute("il", il);
		model.addAttribute("loginform", new LoginForm());
        return "login";
    }
	
	@PostMapping("/login")
    public String formPost(@ModelAttribute LoginForm lf, Model model) {
		
		BookService bookService = new BookService();
		
		if(bookService.isAuthenticated(lf.getLogin(), lf.getPassword())) {
			ArrayList<ReviewedBook> reviewedBooks = bookService.getRecordedBooks(1);
			model.addAttribute("books", reviewedBooks);
			model.addAttribute("reviewedbookform", new ReviewedBookForm());
			return "redirect:/index";
		} else {
			InvalidLogin il = new InvalidLogin();
			il.setIsValid(true);
			model.addAttribute("il", il);
			model.addAttribute("loginform", new LoginForm());
	        return "login";
		}
    }  
}

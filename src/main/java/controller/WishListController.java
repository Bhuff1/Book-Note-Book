package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import formobjects.ReviewedBookForm;
import formobjects.WishListForm;
import book.WishListBook;
import service.BookService;

@Controller
public class WishListController {
	@GetMapping("/wishlist")
    public String formGet(Model model) {
		BookService bookService = new BookService();
		ArrayList<WishListBook> wishListBooks = bookService.getWishListBooks(1);
		model.addAttribute("reviewedbookform", new ReviewedBookForm());
        model.addAttribute("wishListBooks", wishListBooks);
        model.addAttribute("wishlistform", new WishListForm());
        return "wishlist";
    }
	
	@PostMapping("/wishlist")
    public String formPost(@ModelAttribute WishListForm wlf, Model model) {
		BookService bookService = new BookService();
		wlf.setUserId(1);
		bookService.addToWishList(wlf);
		ArrayList<WishListBook> wishListBooks = bookService.getWishListBooks(1);
		model.addAttribute("reviewedbookform", new ReviewedBookForm());
		model.addAttribute("wishListBooks", wishListBooks);
		model.addAttribute("wishlistform", new WishListForm());
        return "wishlist";
    } 
	
	@PostMapping("/wishlistFinishedBook")
    public String formPost(@ModelAttribute ReviewedBookForm rbf, Model model) {
		BookService bookService = new BookService();
		rbf.setUserId(1);
		bookService.addToRecordedBooks(rbf);
		bookService.deleteFromWishList(rbf.getIsbn(), rbf.getUserId());
		ArrayList<WishListBook> wishListBooks = bookService.getWishListBooks(1);
		model.addAttribute("reviewedbookform", new ReviewedBookForm());
		model.addAttribute("wishListBooks", wishListBooks);
		model.addAttribute("wishlistform", new WishListForm());
        return "wishlist";
    }  
	
	@GetMapping("/deleteWishListBook/{isbn}/{userId}")
	public String deleteBook(@PathVariable("isbn") String isbn, @PathVariable("userId") int userId, Model model) {
		BookService bookService = new BookService();
		bookService.deleteFromWishList(isbn, userId);
		ArrayList<WishListBook> wishListBooks = bookService.getWishListBooks(1);
		model.addAttribute("reviewedbookform", new ReviewedBookForm());
		model.addAttribute("books", wishListBooks);
		model.addAttribute("wishlistform", new WishListForm());
	    return "redirect:/wishlist";
	}

}

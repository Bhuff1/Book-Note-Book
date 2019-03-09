package booknotebook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import service.*;

@SpringBootApplication
@ComponentScan(basePackages = { "controller" } )
public class Main {

	public static void main(String[] args) {
		
		SpringApplication.run(Main.class, args);
		
		/*
		 * BookService handles SQL/HSQL (1) managing of books that have 
		 * been read, (2) managing of the wish list, and (3) the tables
		 * that store the books that count will count towards a badge
		 * if the user reads one.
		 */
		BookService bookService = new BookService();
		
		/*
		 * BadgeService handles NoSQL/MongoDB managing of badges
		 * that the user accrues. 
		 */
		BadgeService badgeService = new BadgeService(); 
		badgeService.addNewUser(1);
		
		bookService.createTables();
		bookService.seedDatabase(); 
		
		badgeService.seedDatabase();
	}
}
